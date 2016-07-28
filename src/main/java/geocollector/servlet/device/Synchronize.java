package geocollector.servlet.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.dao.LayerDao;
import geocollector.dao.LayerDaoImpl;
import geocollector.dao.PointDao;
import geocollector.dao.PointDaoImpl;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;


@WebServlet("/sync")
@MultipartConfig
public class Synchronize extends HttpServlet {

	private static final transient Logger log = LoggerFactory.getLogger(Synchronize.class);
	private static final long serialVersionUID = -5004960396891039127L;
	private static final String TAG = "Synchronize:";

	Factory<SecurityManager> factory;
	SecurityManager securityManager;
	Subject currentUser;
	UsernamePasswordToken token;
	UserDao userDao;
	PointDao pointDao;
	LayerDao layerDao;
	
	public Synchronize() {}

	@Override
	public void init()
	{
		userDao = new UserDaoImpl();
		pointDao = new PointDaoImpl();
		layerDao = new LayerDaoImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
    	out.print("SYNCH CHANGES");
    	out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println (TAG + "INICIANDO");
		log.info(TAG + "INICIANDO");
		
		Collection<Part> parts = request.getParts();
		
		InputStream in = request.getPart("idUser").getInputStream();
		int userId = Integer.parseInt(new Scanner(in,"UTF-8").useDelimiter("\\A").next());
		
		System.err.println (TAG + "USER ID " + userId);
		log.info(TAG + "USER ID " + userId);
		
		for (Part part : parts){
			String partName = part.getName();
			
			if (partName.equals("Removed_Points")){
				System.err.println (TAG + " ENTROU REMOVED_PTS");
				log.info(TAG + " ENTROU REMOVED_PTS");
				
				in = part.getInputStream();
				String content = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
				
				if (!content.equals("")){
					String[] points = content.split(",");
					List<String>  pointsList = Arrays.asList(points);				
					removePoints(pointsList);
				}
							
			} else if (partName.startsWith("UpdateLayer")){
				
				in = part.getInputStream();
				String content = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
				
				String[] layers = content.split(",");
				String oldName = layers[0];
				String newName = layers[1];
				String color   = layers[2];
				
				updateLayers(userId, color, oldName, newName);
			} else if (partName.startsWith("RemoveLayer")){
				in = part.getInputStream();
				String content = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
				
				System.err.println(content);

				
				String[] layers = content.split(",");
				List<String> layersList = Arrays.asList(layers);

				System.err.println("RMOVE LAYER 33s");

				removeLayers (layersList, userId);
			}
		}
		
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        writer.write("sucesso");
        writer.flush();
        writer.close();
	}
	
	private void removeLayers(List<String> layersList, int userId) {
		Layer layer;
		System.err.println("RMOVE LAYER");
		for (String layerName : layersList) {
			if (layerDao.exists(layerName, userId)){
				layer = layerDao.getLayer(layerName, userId);
				boolean isDeleted = layerDao.delete (layer);
				
				if (isDeleted){
					System.err.println("FOI DELETADO LAYER");
				} else{
					System.err.println("NAO FOI DELETADO LAYER");

				}
			}
			else{
				System.err.println(TAG + " LAYER " + layerName + " NÃO ENCONTRADA");
			}
		}
	}
	
	public void removePoints (List<String> idPoints) { 	
		for (String id : idPoints){
			Point p = pointDao.findById(Integer.parseInt(id));
			if (p != null)
				pointDao.delete(p);
		}
	}
	/*
	public void removePoints (List<String> idPoints) { 
		
		System.err.println (TAG + " REMOVENDO POINTS");
		log.info(TAG + " REMOVENDO POINTS");
		
		for (String id : idPoints){
			if (pointDao.exists(Integer.parseInt(id))){
				Point p = pointDao.getById(Integer.parseInt(id));
				List<Image> listImages = p.getImages();
				for (Image img : listImages)  {
					File f = new File (img.getFullUrl());
					System.err.println(TAG + " DELETANDO ARQUIVO " + f.getName());
					log.info(TAG + " DELETANDO ARQUIVO " + f.getName());
					
					boolean deleted = f.delete();
					if (deleted){
						System.err.println(TAG + " foi deletado " + f.getAbsolutePath());
						log.info(TAG + " foi deletado " + f.getAbsolutePath());
					}
					else {
						System.err.println(TAG + " nao foi deletado  " + f.getAbsolutePath());
						log.info(TAG + "  nao foi deletado " + f.getAbsolutePath());
					}
					
				
				}
				pointDao.delete(p);
				System.err.println(TAG + " PONTO DELETADO");

			} else 
			{
				System.err.println(TAG + " PONTO NAO EXISTENTE NO BANCO");

			}
		}
	} */
	
	public void updateLayers(int userId, String color, String layerName, String newLayerName) {		
		
		User user = userDao.findById(userId);
		
		for (Layer l : user.getLayers()){
			if (l.getName() == layerName) {
				l.setColor(color);
				l.setName(newLayerName);
				layerDao.update(l);
				break;
			}
		}	
	}
	
	
	/*
	public void updateLayers(int userId, String color, String layerName, String newLayerName) {
		System.err.println (TAG + " ATUALIZANDO LAY");
		log.info(TAG + " ATUALIZANDO LAYER");
		
		System.err.println("NEW LANAME:" + newLayerName);
		System.err.println("LAYER NAME:" + layerName);
		System.err.println("USERID:" + userId);
		
		if (layerDao.exists(layerName, userId)) {

			
			Layer layer = layerDao.getLayer(layerName, userId);
			layer.setName(newLayerName);
			layer.setColor(color);
			
			List<Point> points = layer.getPoints();
			for (Point p : points){
				p.setColor(color);
			}
			boolean updated = layerDao.update(layer);
			
			if (updated)
				System.err.println("ATUALIZO");
			else
				System.err.println("NAO ATUALIZO");
		} else
		{
			System.err.println(TAG + " LAYER " + layerName + "NAO ENCONTRADA NO BANCO");
		}
	}*/
	

}
