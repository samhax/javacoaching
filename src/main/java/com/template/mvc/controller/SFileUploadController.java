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

import com.java.filer.FileManager;
 
/**
 * Handles requests for the application file upload requests
 */
@Controller
public class SFileUploadController {
 
    private static final Logger logger = LoggerFactory
            .getLogger(SFileUploadController.class);
 
    private static FileManager man = new FileManager();

    /**
     * Upload single file using Spring Controller
     * @throws IOException 
     */
    @RequestMapping(value = "/sview/{name}.{ext}", method = RequestMethod.GET)
    public ModelAndView viewFileHandler(@PathVariable("name") String name,
    		@PathVariable("ext") String ext) throws IOException {
        ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Upload Result");
		model.setViewName("samFileReaderViewer");
		
		String filename = man.getFilename(name, ext);
		
		model.addObject("docname", filename);
        
		try {
        	String document = man.readDocument(name, ext);
            model.addObject("document", document);
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

		try{
	        model.addObject("serverItemList", man.getDirectoryItems());
			return model;
		} catch (Exception x){
			model.addObject("error", "Error trying to create the file folder.");
            return model;
		}
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

		try{
		} catch (Exception x){
			model.addObject("error", "Error trying to create the file folder.");
            
            return model;
		}
        
        if (!file.isEmpty()) {
            try {
                File serverFile = man.saveFile(name, file);
 
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
        
        model.addObject("serverItemList", man.getDirectoryItems());

        return model;
    }
    
}