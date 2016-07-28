package geocollector.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvWriter;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;

public class CsvFactory {
	private static final transient Logger log = LoggerFactory.getLogger(CsvFactory.class);

	private String layerName;
	private long idUser;
	private String path;
	
	private UserDao userDao;
	
	public CsvFactory (String layerName, long idUser) {
		this.layerName = layerName;
		this.idUser = idUser;
//		this.path =  "C:" + File.separator + "webapps" + File.separator + "addresscollector" + File.separator +  "user_id_" + idUser +  File.separator + "layer_"+layerName + File.separator + "csv" + File.separator;
		this.path =  "webapps" + File.separator + "addresscollector" + File.separator +  "user_id_" + idUser +  File.separator + "layer_"+layerName + File.separator + "csv" + File.separator;

		this.userDao = new UserDaoImpl();
	}
	
	public File createCsv () throws IOException {		
		String filename = getLayerName() + ".csv";
		prepareDirectory();

    	CsvWriter writer = new CsvWriter (new PrintWriter (new OutputStreamWriter (new FileOutputStream(getPath() + filename), Charset.forName("UTF-8"))), ',');
		
    	User user = userDao.findById((int)getIdUser());
		Layer layer = user.getLayerByName(layerName);
		List<Point> listPoints = layer.getPoints();
			
		for (Point p : listPoints){
			p.setLayer(null);
			String[] csv = p.toCsv().split(",");
			writer.writeRecord(csv);
		}
		writer.close();
		return new File(getPath() + filename);
	}
	
	private void prepareDirectory (){
		try {
			File dir = new File (getPath());
			if(!dir.exists()){
				dir.mkdirs();
			} else if (dir.isDirectory())
					if(dir.list().length > 0)
						FileUtils.cleanDirectory(dir);
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
