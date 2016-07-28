package geocollector.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.User;

public class MainRealm extends AuthorizingRealm {
	private UserDao userDao;

	public MainRealm() {
		setName("MainRealm");
		HashedCredentialsMatcher hcm = new HashedCredentialsMatcher("SHA-256");
		setCredentialsMatcher(hcm);
		userDao = new UserDaoImpl();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authToken = (UsernamePasswordToken) token;
		authToken.setRememberMe(false);

		String password = new Sha256Hash(new String(authToken.getPassword())).toHex();

		if (userDao.exists(authToken.getUsername())) {
			User user = userDao.login(authToken.getUsername(), password);
			if (user == null) {
				throw new IncorrectCredentialsException(authToken.getUsername() + ":senha inválido");
			} else {
				if (user.isActivated()) {
					return new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(), getName());
				} else {
					throw new AuthenticationException();
				}

			}
		} else {
			throw new UnknownAccountException(authToken.getUsername() + ":o email informado nao existe.");
		}

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String email = principal.toString();
		User user = userDao.findByEmail(email);

		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		sai.addRole(user.getRole().toString());

		return sai;
	}
}