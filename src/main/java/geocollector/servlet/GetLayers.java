package geocollector.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.User;
import geocollector.util.Links;




@WebServlet("/GetLayers")
public class GetLayers extends HttpServlet {
	
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
	    	HttpSession session = request.getSession();
	    	User user = (User)session.getAttribute("user");
	    	
	    	user = userDao.findById(user.getId());
			session.setAttribute("user", user);

	    	List<Layer> layers = user.getLayers();  	
	
	    	for (Layer  l : layers){
	    		l.setPoints(null);
	    		l.setUser(null);
	    	}
	    	
	    	String json = new Gson().toJson(layers);

    		
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");

	    	response.getWriter().write(json);
    	}
    	catch (Exception e){
    		System.err.println(e.getMessage());
    		response.sendRedirect(Links.LOGOUT);
    	}
    }

}


