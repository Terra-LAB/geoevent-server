package geocollector.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Role;
import geocollector.model.User;



@WebServlet("/Login")
public class UserLoginServlet extends HttpServlet {

	private static final transient Logger log = LoggerFactory.getLogger(UserLoginServlet.class);
	private static final long serialVersionUID = -5004960396891039127L;

	Factory<SecurityManager> factory;
	SecurityManager securityManager;
	Subject currentUser;
	UsernamePasswordToken token;
	UserDao userDao;

	public UserLoginServlet() {}

	@Override
	public void init()
	{		
		factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		currentUser = null;
		token = null;
		userDao = new UserDaoImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String userEmail= request.getParameter("email");
		String userPassword = request.getParameter("senha");
		
		
		try
		{
			currentUser = SecurityUtils.getSubject();
			token = new UsernamePasswordToken(userEmail, userPassword);
			currentUser.login(token);
		}
		catch (UnknownAccountException uae)
		{
			System.err.println(uae.getMessage());
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
	    	out.print("nao-existe");
	    	out.flush();
			out.close();
			return;
		}
		catch (IncorrectCredentialsException ice)
		{
			System.err.println(ice.getMessage());
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
	    	out.print("credenciais invalidas");
	    	out.flush();
			out.close();
			return;
		}
		catch (AuthenticationException ae)
		{
			log.info("Conta nao ativada");
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
	    	out.print("desativada");
	    	out.flush();
			out.close();
		}

		if(currentUser.isAuthenticated())
		{
			if (currentUser.hasRole(Role.ROLE_USER.toString())) {
				if(request.getParameterMap().containsKey("lembrar")){
					Cookie cookieEmail = new Cookie("email-geo", userEmail);
					Cookie cookieKey = new Cookie ("key-geo", userPassword);
					cookieEmail.setMaxAge(365 * 24 * 60 * 60);
					cookieKey.setMaxAge(365 * 24 * 60 * 60);					
					response.addCookie(cookieEmail);
					response.addCookie(cookieKey);			
				}
					
				
				HttpSession session = request.getSession();
	
				User user = userDao.findByEmail(userEmail);
				session.setAttribute("user", user);	
				response.setContentType("text/plain");
				response.getWriter().write("geocollector");
				return;
			}
		}
	}
	
}
