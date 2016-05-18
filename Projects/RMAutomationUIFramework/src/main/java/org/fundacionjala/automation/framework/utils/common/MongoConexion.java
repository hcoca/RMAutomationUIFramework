package org.fundacionjala.automation.framework.utils.common;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.DBCollection;

public class MongoConexion {

    private Mongo mongo;
    private DB db;
    private DBCollection collection;

    public MongoConexion() {
	    
    	try {
    		
               initDatabase(PropertiesReader.getHostIPAddress(), 
        	   PropertiesReader.getMongoDBConnectionPort());
               switchDatabse(PropertiesReader.getDBName());
               
		} catch (UnknownHostException e) {
			
			 System.out.println("Error: " + e.getMessage());
		}
            
           
   }

    private void initDatabase(String host, int port) throws UnknownHostException {
    		
    		mongo = new Mongo(host, port);
		
    }

    private void switchDatabse(String databaseName) {

			if (mongo != null) {
			    db = mongo.getDB(databaseName);
			}
    }

    public void switchCollection(String tableName) {

		if (db != null) {
		    collection = db.getCollection(tableName);
		}
    }
    
    public DBCollection getCollection() {
    	
    	return collection;
    }
    
}