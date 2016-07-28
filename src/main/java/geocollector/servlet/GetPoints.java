package geocollector.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;
import geocollector.util.Links;




@WebServlet("/GetPoints")
public class GetPoints extends HttpServlet {
	
	private static final long serialVersionUID = 7536620307372383190L;
	private static final transient Logger log = LoggerFactory.getLogger(GetPoints.class);
	
	private UserDao userDao;
	
	@Override
	public void init()
	{	
		userDao = new UserDaoImpl();
		log.info("Servlet: GetPoints");
	}
	
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	doPost(request, response);
	}
    
    @Override
  	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	/*
    	 * Recupera String com as layers. Converte o conjunto da String em uma lista das layers
    	 */
    	try {
	    	String param = request.getParameter("camadas");
	    	String layers[] = param.split("\\&");
	    	
	    	System.err.println("LAYERS SPLIT:" + layers.toString());
	    	
	    	for (int i = 0 ; i < layers.length; i++){
	    		layers[i] = layers[i].replaceAll("%", " ");
	    	}
	    	List<String> layersList = Arrays.asList(layers);

	    	System.err.println("LAYERS array SPLIT:" + layersList.toString());

	    	/*
	    	 * Recupera o usuário logado e atualiza
	    	 */
	    	HttpSession session = request.getSession();
	    	User user = (User)session.getAttribute("user");
	    	
	    	user = userDao.findById(user.getId());
			session.setAttribute("user", user);
	    	
	    	/*
	    	 * Recupera os pontos de acordo com a lista de layers
	    	 */
	    	List<Layer> camadas = user.getLayers();
	    	List<Point> pontos = new ArrayList<>();
	    	
	    	System.err.println("ENTRANDO FOR");

	    	for(Layer l : camadas) { 
	    		log.info("camadas");
		    	System.err.println("ENTRANDO FOR2");

	    		if (layersList.contains(l.getName())){
	    	    	System.err.println("ENTRANDO FOR3");

	    			log.info("Camada " + l.getName() + " pontos " + l.getPoints().size());
	    			pontos.addAll(l.getPoints());
	    		}
	    	}
	    	
	    	
	    	/* 
	    	 * Seta o usuario dono do ponto para NULL. Isto é feito para não ocorrer o problema de CIRCULAR REFERENCE na hora da conversão para GSON
	    	 */
	    	for (Point p : pontos) { 
		    	System.err.println("ENTRANDO FOR4");
	    		p.setLayer(null);
	    	}
	    	
	    	
	       	/*
	    	 * Converte os pontos para um json, e manda para o cliente
	    	 */
	    	
	    	String json = new Gson().toJson(pontos);
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");
	    	log.info("json:" +json);

	    	response.getWriter().write(json);
    	}
    	catch (Exception e){
    		log.error(e.getMessage());
    		response.sendRedirect(Links.LOGOUT);
    	}

    }
}


