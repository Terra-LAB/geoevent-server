package geocollector.servlet;

import geocollector.dao.AddressDatabaseDao;
import geocollector.dao.AddressDatabaseDaoImpl;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.AddressDatabase;
import geocollector.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.HibernateUtil;
import geocollector.util.Links;

@WebServlet("/Upload")
@MultipartConfig
public class UploadAddressDatabase extends HttpServlet {

	private static final long serialVersionUID = -8889263614960112108L;
	private static final transient Logger log = LoggerFactory.getLogger(UploadAddressDatabase.class);
	private String path = "";
	private String fileName = "";
	
	private UserDao userDao = new UserDaoImpl();
	private AddressDatabaseDao addressDatabaseDao = new AddressDatabaseDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	User u = (User) session.getAttribute("user");
    	
    	u = userDao.findById(u.getId());
		session.setAttribute("user", u);

		path = "webapps" + File.separator + "addresscollector" + File.separator  + "user_id_" + u.getId() +  File.separator + "addressDatabase" + File.separator;

		File file = new File(path);
		if (file.isDirectory())
			if(file.list().length > 0)
				FileUtils.cleanDirectory(file);
		
		try{
	    	receiveFile (request, response, u.getId());
	    	insertDatabase (request, response, u.getId());
		} catch (Exception e ){
			log.error(e.getMessage());
		}
		
		response.sendRedirect(Links.USER_HOME);
		
	
	}
	
	private void receiveFile(HttpServletRequest request,
		HttpServletResponse response, int i) throws Exception, ServletException {
		Part filePart = request.getPart("file");
		fileName = getFileName(filePart);
	    InputStream in = filePart.getInputStream();	    
	    
		File dir = new File (path);
		if(!dir.exists()){
			dir.mkdirs();
		} 
	    System.err.println(fileName);
	    
	    OutputStream out = new FileOutputStream(new File(path+fileName));
        IOUtils.copy(in, out);
        out.flush();
        out.close();
	}

	private void insertDatabase(HttpServletRequest request, HttpServletResponse response, int idUser) throws Exception {
		log.debug("Inserindo no banco de dados");
		
		User user 	= userDao.findById(idUser);
		AddressDatabase db = user.getAddressDatabase();
		if ( db != null) {
			addressDatabaseDao.remove(db);
		}
		
		AddressDatabase addressDatabase = new AddressDatabase();
		addressDatabase.setName(fileName);
		

		path = path.replace("webapps", "");
		addressDatabase.setUrlDatabase(path);
		
		user.setAddressDatabase(addressDatabase);
		addressDatabase.setUser(user);
		
		boolean updated = userDao.update(user);
		if (!updated){
			throw new Exception();
		}
	}


	private static String getFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

}