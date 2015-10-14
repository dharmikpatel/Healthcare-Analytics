package edu.sjsu.utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class MongoDBInsert {
	public static void insertDataToMongo(String json,String diseaseName){
		try {
			//System.out.println(json);
			Mongo mongo = new Mongo("localhost", 27017);
			/*mongo.dropDatabase("diseasedocuments");
			System.out.println("database droped");*/
			DB db = mongo.getDB("diseasedocuments");
			DBObject dbObject = (DBObject)JSON.parse(json);
		 	
		 	DBCollection collection = db.getCollection(diseaseName);
			collection.insert(dbObject);
			collection.save(dbObject);
			
			DBCursor cursorDocJSON = collection.find();
		   while (cursorDocJSON.hasNext()) {
				System.out.println(cursorDocJSON.next());
		   }
		}
		   catch (UnknownHostException e) {
				e.printStackTrace();
			    } catch (MongoException e) {
				e.printStackTrace();
			    } 
	    }
}	

