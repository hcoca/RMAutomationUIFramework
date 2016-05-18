package org.fundacionjala.automation.framework.utils.common;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class RoomMongo {
	private int _id;
	private String emailAddress;
	private int serviceId;
	private boolean enabled;
	private String displayName;
	private MongoConexion mongo;
	private DBCollection collection;
    private BasicDBObject searchQuery;
	
	public RoomMongo(String name) {
		
		displayName = name;
	    serviceId = 0;
		mongo = new MongoConexion(); 
		mongo.switchCollection(PropertiesReader.getRoomsFieldName());
		collection = mongo.getCollection();
		searchQuery = new BasicDBObject().append("displayName", displayName);
	}
	
	public String getDisplayName() {
		
		return displayName;
	}
    
	public void setEnable(boolean status) {
		
		 BasicDBObject newDocument = new BasicDBObject();
		 newDocument.append("$set", new BasicDBObject().append("enabled", status)); 
	     collection.update(searchQuery, newDocument);
	     enabled = status;
	}
	
    public boolean getEnable() {
    	
    	if (!enabled) {
    		
    		String _status = "false";
        	DBCursor cursor = collection.find(searchQuery);
        	
        	while(cursor.hasNext()) {
        	    cursor.next();
        	    _status = cursor.curr().get("enabled").toString();
        	}
        	cursor.close();
    		
        	enabled = Boolean.parseBoolean(_status); 
		}
    	
    	return enabled; 
    }
    
    public int getServiceId() {
    	
    	if (serviceId == 0)
    	{
    		String _serviceId = "";
            DBCursor cursor = collection.find(searchQuery);
        	
        	while(cursor.hasNext()) {
        		
        	    cursor.next();
        	    _serviceId = cursor.curr().get("serviceId").toString();
        	}
        	cursor.close();
        	
        	serviceId = Integer.parseInt(_serviceId);
	
    	}
            	
    	return serviceId;
    	
    }

}
