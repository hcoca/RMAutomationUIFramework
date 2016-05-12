package org.fundacionjala.automation.framework.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Manage the file roommanager.properties in order to provide the configuration
 * parameters
 * @author alejandraneolopan
 *
 */
public class PropertiesReader {

    /**
     * Return the file roommanager.properties
     * 
     * @return Properties handle for .properties files
     */
    private static Properties getReader() {
	
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

    /**
     * @return String browser name by default
     */
    public static String getBrowser() {
	
	return getReader().getProperty("BROWSER");
    }

    /**
     * @return String String e.g. https://172.20.208.146:4040
     */
    public static String getServiceURL() {
	
	Properties prop = getReader();
	return prop.getProperty("SERVICE_URL_METHOD") + "://"
		+ prop.getProperty("SERVICE_URL") + ":"
		+ prop.getProperty("SERVICE_PORT");
    }

    /**
     * @return String admin url e.g. https://172.20.208.146:4040/admin
     */
    public static String getAdminURL() {
	
	return getServiceURL() + "/"
		+ getReader().getProperty("SERVICE_ADMIN_URL");
    }

    /**
     * @return String tablet url e.g. https://172.20.208.146:4040/tablet
     */
    public static String getTabletURL() {
	
	return getServiceURL() + "/"
		+ getReader().getProperty("SERVICE_TABLET_URL");
    }

    /**
     * @return String username of Administrator
     */
    public static String getUserName() {
	
	return getReader().getProperty("USERNAME");
    }

    /**
     * @return String password of Administrator
     */
    public static String getPassword() {
	
	return getReader().getProperty("PASSWORD");
    }

    /**
     * @return String path of the Log File e.g. “./logs”
     */
    public static String getLogFilePath() {
	
	return getReader().getProperty("PATH_LOG_FILE");
    }

    /**
     * @return String path of the Report Folder e.g. “./reports”
     */
    public static String getReportFolderPath() {
	
	return getReader().getProperty("PATH_REPORT_FOLDER");
    }

    /**
     * @return String Report Screenshots Folder e.g. “./reports/screenshots”
     */
    public static String getScreenshotsPath() {
	
	return getReportFolderPath() + "/"
		+ getReader().getProperty("PATH_SCREENSHOTS_FOLDER");
    }

    /**
     * @return String Chrome Driver Folder e.g. “./lib/chromedriver.exe”
     */
    public static String getChromeDriverPath() {
	
	return getReader().getProperty("PATH_CHROME_DRIVER");
    }

    /**
     * @return String Internet Explorer Driver Folder e.g.
     *         “./lib/IEDriverServer.exe”
     */
    public static String getIexploreDriverPath() {
	
	return getReader().getProperty("PATH_IEXPLORE_DRIVER");
    }

    /**
     * @return String domain admin account (username) for organizer role
     */
    public static String getExchangeOrganizerUser() {
	
	return getReader().getProperty("EXCH_ORGANIZER_USER");
    }

    /**
     * @return String domain admin account (password) for organizer role
     */
    public static String getExchangeOrganizerPwd() {
	
	return getReader().getProperty("EXCH_ORGANIZER_PWD");
    }

    /**
     * @return String domain admin account (mail) for organizer role
     */
    public static String getExchangeOrganizerMail() {
	
	return getReader().getProperty("EXCH_ORGANIZER_MAIL");
    }

    /**
     * @return String domain username account for invite role in meetings
     */
    public static String getExchangeInviteUser() {
	
	return getReader().getProperty("EXCH_INVITE_USER");
    }

    /**
     * @return String domain user password account for invite role in meetings
     */
    public static String getExchangeInvitePwd() {
	
	return getReader().getProperty("EXCH_INVITE_PWD");
    }

    /**
     * @return String domain user account mail for invite role in meetings
     */
    public static String getExchangeInviteMail() {
	
	return getReader().getProperty("EXCH_INVITE_MAIL");
    }

    /**
     * @return String domain name. e.g. roommanager.local
     */
    public static String getExchangeDomain() {
	
	return getReader().getProperty("EXCHANGE_DOMAIN");
    }

    /**
     * @return String Conference room name for to work on tablet GUI
     */
    public static String getConferenceRoom() {
	
	return getReader().getProperty("ROOM_NAME");
    }

    /**
     * @return String Hostname of Exchange Mail Server
     */
    public static String getExchangeHostname() {
	
	return getReader().getProperty("EXCHANGE_HOSTNAME");
    }

    /**
     * @return String An username for connect with Exchange Email Server
     */
    public static String getExchangeConnectUserName() {
	
	return getReader().getProperty("EXCHANGE_USERNAME");
    }

    /**
     * @return String Password of username for connect with Exchange Email
     *         Server
     */
    public static String getExchangeConnectPassword() {
	
	return getReader().getProperty("EXCHANGE_PASSWORD");
    }
    
    /**
     * @return String Host Room Manager Server IP Address (e.g. 172.20.208.84)
     */
    public static String getHostIPAddress() {

	return getReader().getProperty("SERVICE_URL");
    }
    
    /**
     * @return int Port of MongoDB connection
     */
    public static int getMongoDBConnectionPort() {
	
	return Integer.parseInt(getReader()
		.getProperty("MONGO_CONNECTION_PORT"));
    }

    /**
     * @return String Name of MongoDB collection which contains out-of-orders
     */
    public static String getMongoDBOutOfOrderTable() {
	
	return getReader().getProperty("OUTOFORDER_TABLE_NAME");
    }

    /**
     * @return String Name of MongoDB collection which contains meetings
     */
    public static String getMongoDBMeetingTable() {
	
	return getReader().getProperty("MEETINGS_TABLE_NAME");
    }

    /**
     * @return String Name of MongoDB Room Manager database
     */
    public static String getDBName() {

	return getReader().getProperty("DB_NAME");
    }

    /**
     * @return String Name of collection which contains service information
     */
    public static String getServicesTableName() {

	return getReader().getProperty("SERVICES_TABLE_NAME");
    }

    /**
     * @return String Name of field in service collection which contains
     *         impersonation state
     */
    public static String getImpersonateFieldName() {

	return getReader().getProperty("IMPERSONATE_FIELD_NAME");
    }

    /**
     * @return String Name of collection which contains rooms
     */
    public static String getRoomsFieldName() {

	return getReader().getProperty("ROOMS_FIELD_NAME");
    }

    /**
     * @return String Credential authentication type for Impersonation enabled
     */
    public static String getCredentialsAuthenticationType() {

	return getReader().getProperty("CREDENTIALS");
    }

    /**
     * @return String RFID authentication type for Impersonation enabled
     */
    public static String getRFIDAuthenticationType() {

	return getReader().getProperty("RFID");
    }

}
