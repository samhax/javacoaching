package com.template.mvc.controller;

import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
/**
 * Handles requests for the application file upload requests
 */
@Controller
public class SFileUploadController {
 
    private static final Logger logger = LoggerFactory
            .getLogger(SFileUploadController.class);
 


    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/sview/{name}.{ext}", method = RequestMethod.GET)
    public ModelAndView viewFileHandler(@PathVariable("name") String name,
    		@PathVariable("ext") String ext) {
        ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Upload Result");
		model.setViewName("samFileReaderViewer");
		
		String filename = name + (ext != null && ext.length() > 0 ? "." + ext : "");
		
		model.addObject("docname", filename);
		
		// Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
		String fullname = rootPath + File.separator + "tmpFiles" + File.separator + filename;
        
        BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fullname));
	        try {
	            StringBuilder sb = new StringBuilder();
	            String line = br.readLine();

	            while (line != null) {
	                sb.append(line);
	                sb.append(System.lineSeparator());
	                line = br.readLine();
	            }
	            String document = sb.toString();
	            model.addObject("document", document);
	        } catch (Exception e){
				model.addObject("code", "500");
				model.addObject("error", "Error trying to read the file");
	        } 
	        finally {
	            try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		} catch (FileNotFoundException e) {
			model.addObject("code", "404");
			model.addObject("error", "File not found");
		}
		
		return model;
    }
    
	@RequestMapping(value = { "/supload" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("samFileReader");
		

		// Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");

		try{
	        if (!dir.exists()){
	        	dir.mkdirs();
	        }
		} catch (Exception x){
			model.addObject("error", "Error trying to create the file folder.");
            
            return model;
		}
		
        String[] items = dir.list();
        Arrays.sort(items);
        model.addObject("serverItemList", items);

		
		return model;
	}
	
    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/supload", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        
        ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Upload Result");
		model.setViewName("samFileReaderUploadResult");

		// Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");

		try{
	        if (!dir.exists()){
	        	dir.mkdirs();
	        }
		} catch (Exception x){
			model.addObject("error", "Error trying to create the file folder.");
            
            return model;
		}
        
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
 
                model.addObject("success", "You successfully uploaded file " + name);
            } catch (Exception e) {
                model.addObject("error", "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            model.addObject("error", "You failed to upload " + name
                    + " because the file was empty.");
        }

        String[] items = dir.list();
        Arrays.sort(items);
        model.addObject("serverItemList", items);

        return model;
    }
    
}