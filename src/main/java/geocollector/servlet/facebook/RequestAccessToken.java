package geocollector.servlet.facebook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.AddressDatabase;
import geocollector.model.Role;
import geocollector.util.HibernateUtil;


@WebServlet("/requestAccessToken")

public class RequestAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final transient Logger log = LoggerFactory.getLogger(RequestAccessToken.class);
	
	private static final String TAG = "RequestAccessToken:";

	UserDao userDao;
	geocollector.model.User u;
	int userId;
	
	UsernamePasswordToken token;
    public RequestAccessToken() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		userDao = new UserDaoImpl();
		String accessToken = request.getHeader("oauth");
		Facebook facebook = new FacebookFactory().getInstance();	
		
		try {
			AccessToken token = new AccessToken(accessToken);
			facebook.setOAuthAccessToken(token);

			String callbackUrl = facebook.getOAuthCallbackURL();
					
			User user = facebook.getMe();			
			if(userDao.exists(user.getEmail())) {
				u = userDao.findByEmail(user.getEmail());
				if(u.isFacebookAccount())
					userId = u.getId();
				else{
					u = null;
		    		System.err.println(TAG + "Usuário nao criado. Email ja existente igual ao do facebook.");
				}
			}
			else {
		    	u = new geocollector.model.User();
		    	u.setActivated(true);
		    	u.setEmail(user.getEmail());
		    	u.setName(facebook.getName());
		    	u.setPassword(new Sha256Hash("facebook").toHex());
		    	u.setRole(Role.ROLE_USER);
		    	u.setBirthday (user.getBirthday());
		    	u.setGender(user.getGender());
		    	u.setFacebookAccount(true);
		    	
		    	u = userDao.insertUser(u);
		    	if (u != null){			
					userId = u.getId();
		    	} else {
		    		System.err.println(TAG + "Usuário nao criado. Email ja existente igual ao do facebook.");
		    	}
				
			}
			
			if(u != null) {
				AddressDatabase db = u.getAddressDatabase();
				if (db != null) {	
					String path = db.getUrlDatabase();
					String name = db.getName();
					
					File file = new File("webapps"+path+name);
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "filename=\"tabela.csv\"");
					response.setHeader("userId",  Integer.toString(userId));
					FileUtils.copyFile(file, response.getOutputStream());
				} else {
					response.setContentType("text/html; charset=UTF-8");
					OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			        writer.write("userId = " + userId+ " logado com sucesso");
					writer.flush();
					writer.close();
				}			
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		        writer.write("error: login ja existente");
				writer.flush();
				writer.close();
			}
						
		} catch (FacebookException e) {
			e.printStackTrace();
			System.err.println(TAG + e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			System.err.println(TAG + e.getMessage());
		}
	}
}
