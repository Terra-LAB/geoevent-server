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
import geocollector.model.Role;
import geocollector.model.User;
import geocollector.util.EmailSender;

@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 7531979311103648115L;

	public CadastroUsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeCompleto = request.getParameter("nomeCompleto");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		UserDao userDao = new UserDaoImpl();

		response.setContentType("text/html");
		boolean exists = userDao.exists(email);

		if (exists) {
			response.getWriter().write("existe");
		} else {
			User user = new User();
			user.setName(nomeCompleto);
			user.setEmail(email);
			user.setPassword(new Sha256Hash(senha).toHex());
			user.setActivated(false);
			user.setRole(Role.ROLE_USER);

			user = userDao.insertUser(user);
			if (user != null) {
				EmailSender sender = new EmailSender();
				String key = new Sha256Hash(nomeCompleto + email).toHex();

				sender.sendActiveEmail(email, nomeCompleto, key);
				response.getWriter().write("criado");
			} else
				response.getWriter().write("erro");
		}

	}
}
