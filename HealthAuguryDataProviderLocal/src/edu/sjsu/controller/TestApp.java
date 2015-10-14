package edu.sjsu.controller;

import java.io.InputStream;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import edu.sjsu.utils.ComputeLRInput;


public class TestApp {
	public static void main(String[] args) {
		Properties prop = new Properties();
    	InputStream input = null;
    	String filename = "edu//sjsu//controller//abbreviation.properties";
		input = ComputeLRInput.class.getClassLoader().getResourceAsStream(filename);
		try {
		prop.load(input);
		String dataForMap = "[";
		String dataForMapEnd = "]";
		String jsonArr = "";
		int counter = 0;
		
		System.out.println(dataForMap+jsonArr+dataForMapEnd);
		String data = dataForMap+jsonArr+dataForMapEnd;
			Mongo mongo = new Mongo("localhost", 27017);
			/*mongo.dropDatabase("stateabbr");
			System.out.println("database droped");*/
			DB db = mongo.getDB("stateabbr");
			DBObject dbObject = null;
			for(String key : prop.stringPropertyNames()) {
				  String value = prop.getProperty(key);
				  jsonArr = jsonArr+"{\"state\":\""+ key+"\",\"abbr\":\""+ value+"\"}";
				  dbObject = (DBObject)JSON.parse(jsonArr);
				  DBCollection collection = db.getCollection("state"+counter);
				  collection.insert(dbObject);
					collection.save(dbObject);
				  /*if(counter>50)
				  {jsonArr = jsonArr+"{\"state\":\""+ key+"\",\"abbr\":\""+ value+"\"}";}
				  else
				  {
					  jsonArr = jsonArr+"{\"state\":\""+ key+"\",\"abbr\":\""+ value+"\"},";
				  }*/
				  counter++;
				}
		 	
			
			
			/*DBCursor cursorDocJSON = collection.find();
		   while (cursorDocJSON.hasNext()) {
				System.out.println(cursorDocJSON.next());
		   }*/
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}