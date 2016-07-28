package geocollector.servlet.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

@WebServlet("*.sign")
public class SignInFb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignInFb() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Facebook facebook = new FacebookFactory().getInstance();

		request.getSession().setAttribute("facebook", facebook);
		StringBuffer callbackURL = request.getRequestURL();
		int index = callbackURL.lastIndexOf("/");
		callbackURL.replace(index, callbackURL.length(), "").append("/login.cb");

		System.err.println(callbackURL.toString());
		response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));

	}

}
