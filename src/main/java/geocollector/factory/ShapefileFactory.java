package geocollector.factory;

import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.User;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.ZipUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

public class ShapefileFactory {
	private static final transient Logger log = LoggerFactory.getLogger(ShapefileFactory.class);

	private String layerName;
	private long idUser;
	private String path;
	
	private UserDao userDao;
	
	public ShapefileFactory (String layerName, long idUser) {
		this.layerName = layerName.replace(" ", "_");
		this.idUser = idUser;
		//this.path = "C:" + File.separator + "webapps" + File.separator +  "addresscollector" + File.separator + "user_id_" + idUser +  File.separator + "layer_" + layerName + File.separator + "shapefile" + File.separator;
		//this.path = "webapps" + File.separator +  "addresscollector" + File.separator + "user_id_" + idUser +  File.separator + "layer_" + layerName + File.separator + "shapefile";
		this.path = "webapps" + File.separator +  "addresscollector" + File.separator + "user_id_" + idUser +  File.separator + "layer_" + layerName + File.separator + "shapefile" + File.separator;

		this.userDao = new UserDaoImpl();
	}
	
	public File createShapefile () throws IOException, SchemaException {		
		
		prepareDirectory();
		User user = userDao.findById((int)getIdUser());
		Layer layer = user.getLayerByName(layerName);
		//Layer layer = user.getLayerByName(layerName.replace("_", " "));
		List<Point> listPoints = layer.getPoints();
		
		final SimpleFeatureType TYPE = createFeatureType();
		
		/*
         * A list to collect features as we create them.
         */
        List<SimpleFeature> features = new ArrayList<SimpleFeature>();
        
        /*
         * GeometryFactory will be used to create the geometry attribute of each feature,
         * using a Point object for the location.
         */
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
		
        double latitude;
        double longitude;
			
		for (Point p : listPoints){
			latitude = p.getLatitude();
			longitude = p.getLongitude();
			
			com.vividsolutions.jts.geom.Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
			featureBuilder.add(point);
			featureBuilder.add(p.getName());
			featureBuilder.add(p.getStreet());
			featureBuilder.add(p.getNumber());
			featureBuilder.add(p.getNeighborhood());
			featureBuilder.add(p.getType());
			featureBuilder.add(p.getDescription());
			featureBuilder.add(p.getCollectDate());
	

			SimpleFeature feature = featureBuilder.buildFeature(null);
			features.add(feature);
		}
		
		File newFile = new File(getPath() + File.separator +  layerName + ".shp");
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("url", newFile.toURI().toURL());
        params.put("create spatial index", Boolean.FALSE);
        
        System.err.println("CRIANDO ARQUIVO SHP");
        ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);

        /*
         * TYPE is used as a template to describe the file contents
         */
        newDataStore.createSchema(TYPE);
        
        /*
         * Write the features to the shapefile
         */
		Transaction transaction = new DefaultTransaction("create");

		String typeName = newDataStore.getTypeNames()[0];
        SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
        
        System.err.println("SHAPE:"+SHAPE_TYPE);
        
        
        if (featureSource instanceof SimpleFeatureStore) {
            SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
            /*
             * SimpleFeatureStore has a method to add features from a
             * SimpleFeatureCollection object, so we use the ListFeatureCollection
             * class to wrap our list of features.
             */
            SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);
            featureStore.setTransaction(transaction);
            try {
                featureStore.addFeatures(collection);
                transaction.commit();
            } catch (Exception problem) {
    			System.err.println("ERRO1");
                problem.printStackTrace();
                transaction.rollback();
            } finally {
                transaction.close();
            }
         //   System.exit(0); // success!
        } else {
            System.err.println(typeName + " does not support read/write access");
        }
        
        
        ZipUtils zipUtils = new ZipUtils(getPath(), getPath()  + File.separator + layerName + ".zip");
        zipUtils.generateFileList(new File(getPath() + File.separator));
        zipUtils.zipIt(getPath()  + File.separator + layerName + ".zip");
        
        return new File(getPath() + File.separator +  layerName + ".zip");
	
		
	}
	
	/**
     * Here is how you can use a SimpleFeatureType builder to create the schema for your shapefile
     * dynamically.
     * <p>
     * This method is an improvement on the code used in the main method above (where we used
     * DataUtilities.createFeatureType) because we can set a Coordinate Reference System for the
     * FeatureType and a a maximum field length for the 'name' field dddd
     */
    private static SimpleFeatureType createFeatureType() {

        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        builder.setCRS(DefaultGeographicCRS.WGS84);
        
        // add attributes in order
        builder.add("the_geom", com.vividsolutions.jts.geom.Point.class);
        builder.length(40).add("Name", String.class); // <- 15 chars width for name field
        builder.length(40).add("Sreet",String.class);
        builder.length(10).add("Number",String.class);
        builder.length(20).add("Neighborhood",String.class);
        builder.length(20).add("Type",String.class);
        builder.length(60).add("Description",String.class);
        builder.length(15).add("CollectDate",String.class);
        
        final SimpleFeatureType LOCATION = builder.buildFeatureType();

        return LOCATION;
    }
    
	private void prepareDirectory () throws IOException{
		try{
			File dir = new File (getPath());
			if (dir.isDirectory())
				FileUtils.deleteDirectory(dir);
			if (!dir.exists())
				dir.mkdirs();		
		}catch (IOException e){
			e.printStackTrace();
			log.error(e.getMessage());
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
