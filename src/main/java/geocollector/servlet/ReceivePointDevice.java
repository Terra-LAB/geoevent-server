package geocollector.servlet;

import geocollector.dao.LayerDao;
import geocollector.dao.LayerDaoImpl;
import geocollector.dao.PointDao;
import geocollector.dao.PointDaoImpl;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Form;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.HibernateUtil;

import com.google.gson.Gson;

@WebServlet("/ReceivePointDevice")
public class ReceivePointDevice extends HttpServlet {

	private static final long serialVersionUID = -8889263614960112108L;
	private static final transient Logger log = LoggerFactory.getLogger(ReceivePointDevice.class);
	
	private UserDao userDao = new UserDaoImpl();
	private LayerDao layerDao = new LayerDaoImpl();
	private PointDao pointDao = new PointDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.info("ReceivePointDevice: doPost()");
    	
		try {
			log.info("getContentLength");
			int length = request.getContentLength();
			
			log.info("byte");
			log.info("byte:"+ length);
			
			byte[] input = new byte[length];
			
			log.info("servlet input stream");
			ServletInputStream sin = request.getInputStream();
			int c, count = 0 ;
			
			log.info("while");
			while ((c = sin.read(input, count, input.length-count)) != -1) {
				count +=c;
			}
			
			log.info("close servlet");
			sin.close();

			log.info("string json");
			String formJSONString = new String(input);
			response.setStatus(HttpServletResponse.SC_OK);	
			
	    	log.info("ReceivePointDevice: doPost()");
			//System.err.print("LENDO DO OUTPUT");
			
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			
	    	log.info("CONVERTENDO PARA FORM O JSON");
			Form form = loadFormFromJSONGson(formJSONString);
			

			log.info("INSERINDO NO BANCO");
			
			int  serverId = insertPoint(form);
			
			if( serverId > 0 )
				writer.write("PointID = "+ form.getIdPoint() + " e ServerID = " + serverId + " recebido com sucesso");
			else {
				writer.write("PointID = "+ form.getIdPoint() + " e ServerID = " + serverId + " falhou");
			}
			writer.flush();
			writer.close();
			
			System.err.println("Sucesso");
			
		} catch (IOException e) {
	    	log.error("ERRO: " + e.getMessage());

			try{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(e.getMessage());
				response.getWriter().close();
			} catch (IOException ioe) {
			}
		} catch (Exception e) {
	    	log.error("ERRO: " + e.getMessage());
			e.printStackTrace();
		}  
		

	}

	
	private int insertPoint(Form form) throws Exception {

		int idUser = form.getIdUser();
		String layerName = form.getLayer();
		String color = form.getColor();
		
		Point point =  createPointByForm(form);
		User user = userDao.findById(idUser);
		if(user == null)
			throw new Exception();
	
		Layer mLayer = user.getLayerByName(layerName);
		
		/* Se não existe layer, insere no banco e atualiza lista de layers do User */
		if (mLayer == null) {
			log.info("Criando layer ");
			mLayer = new Layer();
			mLayer.setName(layerName);
			mLayer.setPoints(null);
			mLayer.setUser(user);
			mLayer.setColor(color);
			
			mLayer = layerDao.insertLayer(mLayer);
			if (mLayer == null)
				throw new Exception();
			
			List<Layer> listLayer = user.getLayers();
			listLayer.add(mLayer);
			
			user.setLayers(listLayer);
		}
		point.setLayer(mLayer);
		
		point = pointDao.insertPoint(point);
		if (point == null )
			throw new Exception();
		return point.getId();
	}

	private Point createPointByForm(Form form) {
		int idPointDevice = form.getIdPoint();
		String country = form.getCountry();
		String state = form.getState();
		String city = form.getCity();
	    String supplement = form.getSupplement();
	    String postalCode = form.getPostalCode();
	    String color = form.getColor();
	    String layerName = form.getLayer();   
		String name = form.getName();
		String street = form.getStreet();
		String number = form.getNumber();
		String neighborhood = form.getNeighborhood();
		String description = form.getDescription();	
		String latitude = form.getLatitude();
		String longitude = form.getLongitude();
		String macAdress = form.getMacAddress();
		String collectDate = form.getCollectDate();
		String type = form.getType();
		
		Point point = new Point();
		point.setCountry(country);
		point.setState(state);
		point.setCity(city);
		point.setSupplement(supplement);
		point.setPostalCode(postalCode);
		point.setColor(color);
		point.setLayerName(layerName);
		point.setName(name);
		point.setStreet(street);
		point.setNumber(number);
		point.setNeighborhood(neighborhood);
		point.setDescription(description);
		point.setLatitude(Double.parseDouble(latitude));
		point.setLongitude(Double.parseDouble(longitude));
		point.setMacAdress(macAdress);
		point.setCollectDate(collectDate);
		point.setIdPointDevice(idPointDevice);		
		point.setType(type);
		
		return point;
	}

	public Form loadFormFromJSONGson(String jsonString) {
    	log.info("LoadFormFromJSON: método chamado");
        Gson gson = new Gson();
        log.info("JSON: " + jsonString);
        Form form = gson.fromJson(jsonString, Form.class);
        return form;
    }
	
}