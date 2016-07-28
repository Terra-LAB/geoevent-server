package geocollector.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import geocollector.util.Links;

@WebServlet("/logout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = -5004960396891039127L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Subject currentUser = SecurityUtils.getSubject();

		if (currentUser.getPrincipal() != null) {
			currentUser.logout();
		}
	
		response.sendRedirect(Links.HOME_INDEX);

	}

}
