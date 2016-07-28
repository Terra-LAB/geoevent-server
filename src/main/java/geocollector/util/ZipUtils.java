package geocollector.util;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class ZipUtils
{
	private static final transient Logger log = LoggerFactory.getLogger(ZipUtils.class);

	
    List<String> fileList;
    private static String OUTPUT_ZIP_FILE;
    private static String SOURCE_FOLDER;
 
    public ZipUtils(){
    	fileList = new ArrayList<String>();
    }
    
    
    public ZipUtils (String sourceFolder, String outputZipFile) {
    	this.SOURCE_FOLDER = sourceFolder;
    	this.OUTPUT_ZIP_FILE = outputZipFile;
    	fileList = new ArrayList<String>();
    }
 
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	System.err.println("Output to Zip : " + zipFile);
 
    	for(String file : this.fileList){
 
    		System.err.println("File Added : " + file);
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);

        	log.info("FILE INPUT:" + SOURCE_FOLDER + File.separator + file);
        	FileInputStream in = 
                       new FileInputStream(SOURCE_FOLDER + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
 
    	System.out.println("Done");
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
 
    /**
     * Traverse a directory and get all files,
     * and add the file into fileList  
     * @param node file or directory
     */
    public void generateFileList(File node){
 
	    	//add file only
		if(node.isFile()){
			System.err.println(node.getAbsoluteFile().toString());
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}
	 
		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				generateFileList(new File(node, filename));
			}
		}
	 
    }
 
    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file){
    	
    	/* no windows tirar estas atribuicoes */
    	String rootDir = "/opt/jetty/";
    	//String rootDir = "";
    	rootDir += SOURCE_FOLDER;
    	
    	log.error(rootDir);
    	log.error(file);
    	
    	log.error(file.substring(rootDir.length() , file.length()));
    	
    	
    	return file.substring(rootDir.length(), file.length());
    	
    	
    	
    //	return file.substring(SOURCE_FOLDER.length(), file.length());
    }
}