package org.fundacionjala.automation.framework.utils.common;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class CrudOpearionRooms {

    public static ArrayList<String> getRooms(String criteria) throws UnknownHostException
    {
    	MongoClient mongoClient = new MongoClient(PropertiesReader.getHostIPAddress(), PropertiesReader.getMongoDBConnectionPort());
    	DB db = mongoClient.getDB(PropertiesReader.getDBName());
    	DBCollection collection = db.getCollection("rooms");

    	BasicDBObject query = new BasicDBObject();
    	String regex = "\\A"+criteria+"\\d";
    	query.put("displayName",
    		new BasicDBObject("$regex", regex).append("$options", "i"));

    	ArrayList<String> rooms = new ArrayList<String>();
    	DBCursor cursor = collection.find(query);
    	while (cursor.hasNext()) {
    	    cursor.next();
    	    rooms.add(cursor.curr().get("displayName").toString());
    	}
    	cursor.close();
    	
    	return rooms;	
     }
	}
