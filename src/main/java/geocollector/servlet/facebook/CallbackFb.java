package geocollector.servlet.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Role;
import geocollector.util.Links;

@WebServlet("*.cb")
public class CallbackFb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDaoImpl();
	Factory<SecurityManager> factory;
	SecurityManager securityManager;
	UsernamePasswordToken token;
	Subject currentUser;
	geocollector.model.User u;

	public CallbackFb() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
		String oauthCode = request.getParameter("code");

		try {
			facebook.getOAuthAccessToken(oauthCode);
			User user = facebook.getMe();

			currentUser = SecurityUtils.getSubject();
			if (userDao.exists(user.getEmail())) {

				token = new UsernamePasswordToken(user.getEmail(), "facebook");
				Session session = currentUser.getSession();

				try {
					currentUser.login(token);
				} catch (UnknownSessionException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();

					currentUser = new Subject.Builder().buildSubject();
					try {
						currentUser.login(token);
					} catch (UnknownSessionException e2) {
						System.err.println(e2.getMessage());
						e2.printStackTrace();
						currentUser = new Subject.Builder().buildSubject();
					}
					currentUser.login(token);
					session = currentUser.getSession(true);

				}
			} else {

				/* Registrando user novo */
				u = new geocollector.model.User();
				u.setActivated(true);
				u.setEmail(user.getEmail());
				u.setName(facebook.getName());
				u.setPassword(new Sha256Hash("facebook").toHex());
				u.setRole(Role.ROLE_USER);
				u.setBirthday(user.getBirthday());
				u.setGender(user.getGender());
				u.setFacebookAccount(true);

				u = userDao.insertUser(u);
				if (u != null) {
					token = new UsernamePasswordToken(u.getEmail(), "facebook");
					currentUser.login(token);
				} else
					throw new AuthenticationException("Nao conseguiu autenticar");
			}

			/* Dps de registrado ou logado */

			if (currentUser.isAuthenticated()) {
				if (currentUser.hasRole(Role.ROLE_USER.toString())) {
					u = userDao.findByEmail(user.getEmail());
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(30 * 60);
					session.setAttribute("user", u);
					response.sendRedirect(Links.USER_HOME);
				}
			} else {
				System.err.println("ESTA CONTA NAO ESTA AUTENTICADA");
				response.sendRedirect(Links.LOGIN_ERRO);
			}
		} catch (FacebookException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.sendRedirect(Links.LOGIN_ERRO);
		} catch (IncorrectCredentialsException ice) {
			System.err.println(ice.getMessage());
			ice.printStackTrace();
			response.sendRedirect(Links.LOGIN_ERRO);

		} catch (AuthenticationException ae) {
			System.err.println(ae.getMessage());
			ae.printStackTrace();
			response.sendRedirect(Links.LOGIN_ERRO);
		}

	}

}