package geocollector.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.EmailSender;


@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {

       
	private static final transient Logger log = LoggerFactory.getLogger(SendMessage.class);
	private static final long serialVersionUID = 7531979311103648115L;

    public SendMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nome");
		String email = request.getParameter("email");
		String tel = request.getParameter("telefone");
		String site = request.getParameter("site");
		String message = request.getParameter("mensagem");

		EmailSender  sender = new EmailSender();
		sender.sendContactEmail(name, email, tel, site, message);
		
		response.getWriter().write("criado");
	}
}
