package com.java.filer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	private static final Logger log = LogManager.getLogger("melogger");

    private static String rootPath = System.getProperty("catalina.home");
    private static File dir = new File(rootPath + File.separator + "tmpFiles");
    

	public String getFullName(String name, String ext) {
		String filename = getFilename(name, ext);
		String fullname = rootPath + File.separator + "tmpFiles" + File.separator + filename;
		return fullname;
	}
    
    public String getFilename(String name, String ext) {
		return name + (ext != null && ext.length() > 0 ? "." + ext : "");
	}

	public String readDocument(String name, String ext) throws FileNotFoundException, IOException {
		String fullname = getFullName(name, ext);
		BufferedReader br = new BufferedReader(new FileReader(fullname));
		try{
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
		    sb.append(line);
		    sb.append(System.lineSeparator());
		    line = br.readLine();
		}
		String document = sb.toString();
		return document;
		} finally {
			br.close();
		}
	}

	public String[] getDirectoryItems() {
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		String[] items = dir.list();
		Arrays.sort(items);
		return items;
	}

	public File saveFile(String name, MultipartFile file) throws IOException, FileNotFoundException {
        if (!dir.exists()){
        	dir.mkdirs();
        }
        
		byte[] bytes = file.getBytes();
 
		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath()
		        + File.separator + name);
		BufferedOutputStream stream = new BufferedOutputStream(
		        new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
		return serverFile;
	}
}
