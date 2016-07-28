package geocollector.factory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.MyKml;
import geocollector.util.ZipUtils;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;

public class KmlFactory {
	private static final transient Logger log = LoggerFactory.getLogger(KmlFactory.class);

	private String layerName;
	private long idUser;
	private String path;
	
	private UserDao userDao;
	
	public KmlFactory (String layerName, long idUser) {
		this.layerName = layerName;
		this.idUser = idUser;
	//	this.path =  "C:" + File.separator + "webapps" + File.separator + "addresscollector" + File.separator +"user_id_" + idUser +  File.separator + "layer_"+layerName + File.separator + "kml" + File.separator;
		this.path =  "webapps" + File.separator + "addresscollector" + File.separator +"user_id_" + idUser +  File.separator + "layer_"+layerName + File.separator + "kml" + File.separator;

		this.userDao = new UserDaoImpl();
	}
	

	
	public File createKml () throws IOException {
		final Kml kml = new MyKml();
		Document document =	kml.createAndSetDocument();
		
		User user = userDao.findById((int)getIdUser());		
		Layer layer = user.getLayerByName(layerName);
		List<Point> listPoints = layer.getPoints();
		
		prepareDirectory(listPoints);
		
		for (Point p : listPoints){
			document.createAndAddPlacemark().withName(p.getName()).withDescription(p.toKml()).createAndSetPoint().addToCoordinates(p.getLongitude(), p.getLatitude());
		}
		kml.setFeature(document);
		
		
		kml.marshal(new File(getPath() + layerName + ".kml"));
				
		ZipUtils zipUtils = new ZipUtils(getPath(), getPath() + layerName + ".zip");
        zipUtils.generateFileList(new File(getPath()));
        zipUtils.zipIt(getPath() + layerName + ".zip");
		
        System.err.println("CAMINHO CRIADO: " + getPath() + layerName + ".zip");
        
        return new File (getPath() + layerName + ".zip");
	}
		
	private void prepareDirectory (List<Point> listPoints){
		try {
			/* Limpa o diretorio do KML */
			File dir = new File (getPath());
			
			if (dir.isDirectory())
				FileUtils.deleteDirectory(dir);
			
			/* Cria a estrutura de diretorio padrao */
			dir = new File (getPath() + "images" + File.separator);
			if (!dir.exists())
				dir.mkdirs();
			
					
		} catch (Exception e) { 
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String getPath () {
		return this.path;
	}
	
	private long getIdUser(){
		return this.idUser;
	}
	
	private String getLayerName(){
		return this.layerName;
	}
	
}
