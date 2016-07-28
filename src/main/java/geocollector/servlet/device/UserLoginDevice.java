package geocollector.servlet.device;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.AddressDatabase;
import geocollector.model.Role;
import geocollector.model.User;


@WebServlet("/LoginDevice")
@MultipartConfig
public class UserLoginDevice extends HttpServlet {

	private static final transient Logger log = LoggerFactory.getLogger(UserLoginDevice.class);
	private static final long serialVersionUID = -5004960396891039127L;

	Factory<SecurityManager> factory;
	SecurityManager securityManager;
	Subject currentUser;
	UsernamePasswordToken token;
	UserDao userDao;

	public UserLoginDevice() {}

	@Override
	public void init()
	{
		log.info("Inicio: UserLoginServlet");
		
		factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		currentUser = null;
		token = null;
		userDao = new UserDaoImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
    	out.print("Login");
    	out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream in = request.getPart("userEmail").getInputStream();
		String userEmail = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
	
		InputStream in2 = request.getPart("userPassword").getInputStream();
		String userPassword = new Scanner(in2,"UTF-8").useDelimiter("\\A").next();
		

		try
		{
			currentUser = SecurityUtils.getSubject();
			token = new UsernamePasswordToken(userEmail, userPassword);
			currentUser.login(token);
		}
		catch (IncorrectCredentialsException ice)
		{
			log.error("Email senha invalido");
			response.setContentType("text/plain");
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
	        writer.write("credenciais-invalidas");
	        writer.flush();
	        writer.close();
			return;
		}
		catch (AuthenticationException ae)
		{
			log.info("Conta nao ativada");
			response.setContentType("text/plain");
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
	        writer.write("conta-nao-ativada");
	        writer.flush();
	        writer.close();
		}
		
		if(currentUser.isAuthenticated())
		{
			if (currentUser.hasRole(Role.ROLE_USER.toString())) {
				User u = userDao.findByEmail(userEmail);
				AddressDatabase db = u.getAddressDatabase();				
				if ( db != null ){
					String path = db.getUrlDatabase();
					String name = db.getName();
					
					File file = new File("webapps"+path+name);
 
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "filename=\"tabela.xls\"");
					response.setHeader("userId",  Integer.toString(u.getId()));
					
					FileUtils.copyFile(file, response.getOutputStream());
				    log.info("######## LOGOU ENVIANDO ARQUIVO ####");
			    	//System.err.println("######## LOGOU ENVIANDO ARQUIVO ####");
				} else {
					response.setContentType("text/html; charset=UTF-8");
					OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			        writer.write("userId = " + u.getId() + " logado com sucesso");
					writer.flush();
					writer.close();
				}
			}
			
		}
	}	
}
