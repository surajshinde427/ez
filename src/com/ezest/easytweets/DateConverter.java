package com.ezest.easytweets;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ezest.easytweets.TimestampToDate;


public class DateConverter {
	
	JSONObject payload=null;
public DateConverter(JSONObject tweetDetails) {
	
	JSONObject schema=tweetDetails.getJSONObject("schema");
	 payload=tweetDetails.getJSONObject("payload");
	JSONArray fields =  schema.getJSONArray("fields");
	
	for (int i = 0; i < fields.length(); ++i) {
	    JSONObject jsonRecord = fields.getJSONObject(i);
	    if(jsonRecord.has("name"))
	    {
	     if(jsonRecord.get("name").toString().equals(DateTimeConstants.DATESCHEMA))
	    		 {
	    	 try {
	    		 	 Object fieldName =jsonRecord.get("field");
	    	 		Timestamp timestamp=new Timestamp((long)payload.get(fieldName.toString()));
	    	 
					TimestampToDate timestampToDate=new TimestampToDate(timestamp);
				
	    		 payload.remove((String) fieldName);
	    		 payload.put(fieldName.toString(),timestampToDate.getDateTime());
	    	 }
	    	 catch (Exception e) {
	    	
			}
	    }
	}
	}
}
public JSONObject getPayload() {
	return payload;
}
public void setPayload(JSONObject payload) {
	this.payload = payload;
}
}
