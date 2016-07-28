package geocollector.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.User;

@WebServlet("/trocaSenha")
public class TrocaSenha extends HttpServlet {

	private static final long serialVersionUID = 7531979311103648115L;

	public TrocaSenha() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String oldPassword = request.getParameter("antigoPassword");
		String newPassword = request.getParameter("novoPassword");

		User u = null;
		UserDao userDao = new UserDaoImpl();
		if (userDao.exists(email)) {
			u = userDao.login(email, new Sha256Hash(oldPassword).toHex());

			if (u != null) {
				newPassword = new Sha256Hash(newPassword).toHex();
				u.setPassword(newPassword);
				if (userDao.updatePassword(u)) {
					response.setContentType("text/plain");
					PrintWriter out = response.getWriter();
					out.print("password-trocado");
					out.flush();
					out.close();
				} 
			}
			else {
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.print("erro");
				out.flush();
				out.close();
			}
		} else {
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.print("nao-encontrou");
			out.flush();
			out.close();
		}

	}
}
