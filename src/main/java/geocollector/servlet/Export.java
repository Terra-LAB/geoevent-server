package geocollector.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.factory.CsvFactory;
import geocollector.factory.KmlFactory;
import geocollector.factory.ShapefileFactory;
import geocollector.model.User;




@WebServlet("/export")
public class Export extends HttpServlet {
	
	private static final long serialVersionUID = 7536620307372383190L;
	
	private UserDao userDao;
	
	@Override
	public void init()
	{	
		userDao = new UserDaoImpl();
	}
	
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	doPost(request, response);
	}
    
    @Override
  	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    	try {	    		    	
	    	/*
	    	 * Recupera o usuário logado
	    	 */
	    	HttpSession session = request.getSession();
	    	User user = (User)session.getAttribute("user");
	    	
	    	/* Recupera o usuário novamente, para atualizar caso tenha recebido mais pontos*/
	    	user = userDao.findById(user.getId());
			session.setAttribute("user", user);

			String layerName = request.getParameter("layerName");
			String type = request.getParameter("type");
			

			File file = null;
			if (type.equals("csv")){
				CsvFactory csvFactory =  new CsvFactory(layerName, user.getId());
				file = csvFactory.createCsv();
			} else if (type.equals("kml")){
				System.err.println("KML entro");
				KmlFactory kmlFactory = new KmlFactory(layerName, user.getId());
				System.err.println("KML creating");

				file = kmlFactory.createKml();
				System.err.println("KML crio file");
			} else if (type.equals("shapefile")){
				ShapefileFactory shpFactory = new ShapefileFactory(layerName, user.getId());
				file = shpFactory.createShapefile();
			}
			
			response.setContentType("application/octet-stream");
			if(type.equals("csv"))
				response.setHeader("Content-Disposition", "filename=\"" + layerName + ".csv\"");
			if(type.equals("shapefile") || type.equals("kml"))
				response.setHeader("Content-Disposition", "filename=\"" + layerName + ".zip\"");
			FileUtils.copyFile(file, response.getOutputStream());

    	}
    	catch (Exception e){
    		System.err.println(e.getMessage());
    		e.printStackTrace();
    	}
	    	
    }
}


