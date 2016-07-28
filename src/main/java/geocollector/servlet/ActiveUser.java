package geocollector.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.User;
import geocollector.util.Links;


@WebServlet("/activeUser")
public class ActiveUser extends HttpServlet {

	private static final long serialVersionUID = 7531979311103648115L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String key = request.getParameter("key");
		
		UserDao userDao = new UserDaoImpl();
		User u = userDao.findByEmail(email);

		String userKey = new Sha256Hash(u.getName() + u.getEmail()).toHex();

		
		if (key.equals(userKey)) {
			userDao.active(u);
			response.sendRedirect(Links.LOGIN_ACCOUNT_ACTIVATED);
		} else {
			response.sendRedirect(Links.ERRO);
		}
	}
}
