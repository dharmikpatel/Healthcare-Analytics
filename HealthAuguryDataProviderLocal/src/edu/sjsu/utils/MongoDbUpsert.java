package edu.sjsu.utils;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import edu.sjsu.beans.StateAllDisease;

public class MongoDbUpsert {
	public static void addDiseaseState(String stateName,StateAllDisease stateAllDisease){
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(stateAllDisease);
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			/*mongo.dropDatabase("statedocuments");
			System.out.println("dropped");*/
			System.out.println("state name "+stateName+" "+stateAllDisease.getDiseaseName());
			DB db = mongo.getDB("statedocuments");
			DBObject dbObject = (DBObject)JSON.parse(jsonData);
		 	
		 	DBCollection collection = db.getCollection(stateName);
			collection.insert(dbObject);
			collection.save(dbObject);
			
		   /*DBCursor cursorDocJSON = collection.find();
		   while (cursorDocJSON.hasNext()) {
				System.out.println("statewise data "+stateName+" :: "+cursorDocJSON.next());
		   } */
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
