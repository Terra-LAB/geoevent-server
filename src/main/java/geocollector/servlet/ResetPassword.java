package geocollector.servlet;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.EmailSender;
import geocollector.util.HibernateUtil;


@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
       
	private static final transient Logger log = LoggerFactory.getLogger(ResetPassword.class);
	private static final long serialVersionUID = 7531979311103648115L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info(" Entrou ResetPassword() ");
		
		String email = request.getParameter("email");
		UserDao userDao = new UserDaoImpl();
		
		User u = userDao.findByEmail(email);
		
		if(userDao.exists(email)){
			/*
			 * Cria um novo password usando os 8 primeiros caracteres do Sha256 do nome+email
			 */
			String newPassword = new Sha256Hash(u.getName() + u.getEmail()).toHex().substring(0,7);

			/*
			 * Atualiza a senha atual do usuário com o Sha256 do novo password
			 */
			u.setPassword(new Sha256Hash(newPassword).toHex());
			
			/*
			 * Atualiza o usuário no banco 
			 */
			if(userDao.updatePassword(u)){
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
		    	out.print("password-trocado");
		    	
		    	EmailSender sender = new EmailSender();
		    	sender.sendNewPassword(email, u.getName(), newPassword);
		    	
				log.info("Password atualizado");

		    	out.flush();
				out.close();
			}
			else{
				log.info("Password nao atualizado");
				response.sendRedirect("/erro.jsp");
			}
		}
		else {
			log.info("email nao existe");
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
	    	out.print("email-invalido");
	    	out.flush();
			out.close();
		}
		

	}
	
}
