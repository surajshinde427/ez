package com.ezest.easytweets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;



public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		 
	       
	        	JSONParser parser = new JSONParser();
	    		String jsonString = parser.parse(new FileReader("configration.json")).toString();
	    		JSONObject object =new JSONObject(jsonString);
	            String g=null;
	            
	    			JSONArray mongoDbJsonObject =object.getJSONArray("topicPrefix");
	    			
					for(Object m:mongoDbJsonObject)
					m.toString();
	    		
					System.out.println(g);
					String topic = "my name is suraj";
							topic=topic.replace("ashui","");
							
							System.out.println("***"+topic);
	        /*=    //Reading the array
	           // JSONArray countries = (JSONArray)jsonObject.get("topicPrefix");
	            JSONArray con = (JSONArray)jsonObject.get("MongoConfig");
	            Object jsonObject1 = con;
	            JSONObject jsonObject11 = (JSONObject)jsonObject1;
	            
	            JSONArray con1 = (JSONArray)jsonObject.get("MongoConfig");
	          int i=0;
	            System.out.println("Countries:");
	            for(Object country1 : con1)
	            {
	            	
	                System.out.println("\t"+country1.toString()+"@@@@"+i+1);
	            }*/
	      
	}

}
