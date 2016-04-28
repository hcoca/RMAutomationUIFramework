package org.fundacionjala.automation.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public final String URL_BASE;
	public final String URL_ADMIN;
	public final String URL_TABLET;
	public final String BROWSER;
	public final String ADMIN_USERNAME;
	public final String ADMIN_PWD;
	public final String PATH_DRIVE_CHROME;
	public final String PATH_DRIVE_IE;
	public final String PATH_LOG;
	public final String PATH_REPORT;
	public final String PATH_SCREENSHOT;
	public Properties prop;
	
	public PropertiesReader()
	{
		File file = new File("src/main/resources/roommanager.properties");
		System.out.println("ABSOLUTE path:" + file.getAbsolutePath());  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Setting the values from file properties
		URL_BASE = prop.getProperty("server.method") + "://" 
				 + prop.getProperty("server.url") + ":" 
				 + prop.getProperty("server.port");
		URL_ADMIN = URL_BASE + "/" + prop.getProperty("server.admin");
		URL_TABLET = URL_BASE + "/" + prop.getProperty("server.tablet");
		BROWSER = prop.getProperty("server.browser");
		
		ADMIN_USERNAME = prop.getProperty("admin.username");
		ADMIN_PWD = prop.getProperty("admin.password");
		
		PATH_DRIVE_CHROME = prop.getProperty("path.chromedriver");
		PATH_DRIVE_IE = prop.getProperty("path.iedriver");
		PATH_LOG = prop.getProperty("path.logs");
		PATH_REPORT = prop.getProperty("path.reportfolder");
		PATH_SCREENSHOT = prop.getProperty("path.screenshotfolder");
		
	}
	
}
