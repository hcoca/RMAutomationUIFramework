package org.fundacionjala.automation.framework.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {
	private static final Logger logger = LoggerFactory.getLogger(LogManager.class);
	private LogManager(){}
	
	public static void info(String text){
		logger.info(text);
	}
	
	public static void warning(String text){
		logger.warn(text);
	}
	
	public static void error(String text){
		logger.error(text);
	}	
}
