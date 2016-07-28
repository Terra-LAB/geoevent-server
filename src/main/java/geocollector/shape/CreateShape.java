package geocollector.shape;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geocollector.util.ZipUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;


@WebServlet("/newshape")
public class CreateShape  extends HttpServlet  {
	private static final long serialVersionUID = -8889263614960112108L;
	private static final transient Logger log = LoggerFactory.getLogger(CreateShape.class);
	
	

   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("ENTRANDO DOPOST");
		try {
			final SimpleFeatureType TYPE = DataUtilities.createType("Location",
			        "the_geom:Point:srid=4326," + // <- the geometry attribute: Point type
			        "name:String," +   // <- a String attribute
			        "number:Integer"   // a number attribute
			);
			
			/*
	         * A list to collect features as we create them.
	         */
	        List<SimpleFeature> features = new ArrayList<SimpleFeature>();
			
	        /*
	         * GeometryFactory will be used to create the geometry attribute of each feature,
	         * using a Point object for the location.
	         */
			System.err.println("CRIOU FABRICA DE GEOMETRIA");

	        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
	        
	        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
			
	        double latitude;
	        double longitude;
	        
			System.err.println("CRIANDO FEATURES");
			//for (int i = 0 ; i < 3; i ++){
				 latitude = -20.372839;
				 longitude = -44.62378;
				String name = "pt1";
				int number = 1;
				
				Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
				featureBuilder.add(point);
				featureBuilder.add(name);
				featureBuilder.add(number);
				
				SimpleFeature feature = featureBuilder.buildFeature(null);
				features.add(feature);
				
				 latitude = -21.3842343595;
				 longitude = -43.43647;
				name = "pt2";
				 number = 2;
				
				point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
				featureBuilder.add(point);
				featureBuilder.add(name);
				featureBuilder.add(number);
				
				feature = featureBuilder.buildFeature(null);
				features.add(feature);
				
				
				 latitude = -20.3458423;
				 longitude = -43.67849;
				name = "pt3";
				number = 3;
				
				point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
				featureBuilder.add(point);
				featureBuilder.add(name);
				featureBuilder.add(number);
				
				feature = featureBuilder.buildFeature(null);
				features.add(feature);
	//	}
			
			
			System.err.println("CRIANDO FILE");
			File newFile = new File("Location.shp");
			
			
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
	    			System.err.println("COMMITANDO");
	    	    	PrintWriter out = response.getWriter();
	    	    	out.print("comitt");
	    	    	out.flush();
	                featureStore.addFeatures(collection);
	                transaction.commit();
	            } catch (Exception problem) {
	    			System.err.println("ERRO1");
	    	    	PrintWriter out = response.getWriter();
	    	    	out.print("ER1");
	    	    	out.flush();
	                problem.printStackTrace();
	                transaction.rollback();
	            } finally {
	                transaction.close();
	            }
	         //   System.exit(0); // success!
	        } else {
	            System.err.println(typeName + " does not support read/write access");
	        }
	        
	        ZipUtils zipUtils = new ZipUtils();
	        
			
			
		} catch (SchemaException e) {
	    	PrintWriter out = response.getWriter();
	    	out.print("ERO2R");
	    	out.flush();
			// TODO Auto-generated catch block
			System.err.println("ERRO2");

			e.printStackTrace();
		}
		
	}
	
	@Override
	public void destroy()
	{

	}

}
