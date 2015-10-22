package com.java.filer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {
	private static final Logger log = LogManager.getLogger("melogger");
	
	static final String[] FOLDER_NAMES = new String[]{ 
			"Unread Files",
			"Processed Files",
			"Error Files"
			};
	
	public void doStuff(){
		createFolders();
		verifyFolders();
		createFiles();
		moveFiles();
	}
	
	private void moveFiles(){
		log.trace("Moving files");
		File[] files = getFolderPath(FOLDER_NAMES[0]).toFile().listFiles();
		int filesLength = files.length;
		for (int i = 0; i < filesLength; i++) {
			files[i].renameTo(getFilePath(FOLDER_NAMES[1], i).toFile());
		}
	}
	
	private boolean verifyFolders(){
		log.debug("Verifying folders are ok.");
		boolean allDirectoriesOk = true;
		for (String name : FOLDER_NAMES) {
			allDirectoriesOk &= Files.exists(getFolderPath(name), LinkOption.NOFOLLOW_LINKS);
		}
		return allDirectoriesOk;
	}
	
	private void createFolders(){
		log.debug("Creating Folders.");
		for (String name : FOLDER_NAMES) {
			getFolderPath(name).toFile().mkdir();
		}
	}
	
	private void createFiles(){
		log.debug("Creating Unread Files");
		boolean failed = false;
		for (int i = 0; i < 10; i++) {
			try {
				getFilePath(FOLDER_NAMES[0], i).toFile().createNewFile();
			} catch (IOException e) {
				log.error("Failed to create file");
				failed = true;
			}
		}
		if(failed){
			log.error("finished creating files. All ok, apparently");			
		} else {
			log.debug("finished creating files. Something went wrong");
		}
	}

	private Path getFilePath(String folderName, int i) {
		return getFolderPath(folderName + "/file"+String.valueOf(i)+".txt");
	}

	private Path getFolderPath(String name){
		return Paths.get(getCurrentPath().toAbsolutePath() + "/" + name);
	}
	
	private Path getCurrentPath() {
		 return Paths.get("");
	}
}
