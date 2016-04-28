package org.fundacionjala.automation.framework.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	/* Return the file roommanager.properties
	 * @return Properties handle for .properties files
	 */
	private static Properties getReader()
	{
		File file = new File("src/main/resources/roommanager.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	/*
	 * @return String browser name by default
	 */
	public static String getBrowser() {
        return getReader().getProperty("BROWSER");
	}
	/*
	 * @return String String e.g. https://172.20.208.146:4040
	 */
	public static String getServiceURL()
	{
		Properties prop = getReader();
		return prop.getProperty("server.method") + "://" 
		 	 + prop.getProperty("server.url") + ":" 
		 	 + prop.getProperty("server.port");
	}
	/*
	 * @return String admin url e.g. https://172.20.208.146:4040/admin
	 */
	public static String getAdminURL()
	{
		return getServiceURL() + "/"
			+	getReader().getProperty("SERVICE_ADMIN_URL");
	}
	/*
	 * @return String tablet url e.g. https://172.20.208.146:4040/tablet
	 */
	public static String getTabletURL() {
		return getServiceURL() + "/"
			+  getReader().getProperty("SERVICE_TABLET_URL");
	}
	/*
	 * @return String username of Administrator
	 */
	public static String getUserName() {
		return getReader().getProperty("USERNAME");
	}
	/*
	 * @return String password of Administrator
	 */
	public static String getPassword() {
		return getReader().getProperty("PASSWORD");
	}
	/*
	 * @return String path of the Log File e.g. “./logs”
	 */
	public static String getLogFilePath() {
		return getReader().getProperty("PATH_LOG_FILE");
	}
	/*
	 * @return String path of the Report Folder e.g. “./reports”
	 */
	public static String getReportFolderPath() {
		return getReader().getProperty("PATH_REPORT_FOLDER");
	}
	/*
	 * @return String Report Screenshots Folder e.g. “./reports/screenshots”
	 */
	public static String getScreenshotsPath() {
		return getReportFolderPath() + "/" + getReader().getProperty("PATH_SCREENSHOTS_FOLDER");
	}
	/*
	 * @return String Chrome Driver Folder e.g. “./lib/chromedriver.exe”
	 */
	public static String getChromeDriverPath() {
		return getReader().getProperty("PATH_CHROME_DRIVER");
	}
	/*
	 * @return String Internet Explorer Driver Folder e.g. “./lib/IEDriverServer.exe”
	 */
	public static String getIexploreDriverPath() {
		return getReader().getProperty("PATH_IEXPLORE_DRIVER");
	}
	/*
	 * @return String domain admin account (username) for organizer role 
	 */
	public static String getExchangeOrganizerUser() {
		return getReader().getProperty("EXCH_ORGANIZER_USER");
	}
	/*
	 * @return String domain admin account (password) for organizer role 
	 */
	public static String getExchangeOrganizerPwd() {
		return getReader().getProperty("EXCH_ORGANIZER_PWD");
	}
	/*
	 * @return String domain admin account (mail) for organizer role 
	 */
	public static String getExchangeOrganizerMail() {
		return getReader().getProperty("EXCH_ORGANIZER_MAIL");
	}
	/*
	 * @return String domain username account for invite role in meetings
	 */
	public static String getExchangeInviteUser() {
		return getReader().getProperty("EXCH_INVITE_USER");
	}
	/*
	 * @return String domain user password account for invite role in meetings
	 */
	public static String getExchangeInvitePwd() {
		return getReader().getProperty("EXCH_INVITE_PWD");
	}
	/*
	 * @return String domain user account mail for invite role in meetings
	 */
	public static String getExchangeInviteMail() {
		return getReader().getProperty("EXCH_INVITE_MAIL");
	}
	/*
	 * @return String domain name. e.g. roommanager.local
	 */
	public static String getExchangeDomain() {
		return getReader().getProperty("EXCHANGE_DOMAIN");
	}
	/*
	 * @return String e.g. https://172.20.208.146:4040
	 */
	public static String getConferenceRoom() {
		return getReader().getProperty("ROOM_NAME");
	}
	
}
