package com.ezest.easytweets;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;

public class MessageCreator {
	private String message;



	public void ConvertTemplateMessageIntoValues(String message,Document details) {

		Matcher matcher = Pattern.compile("\\#(.*?)\\#").matcher(message);
		while(matcher.find()) {
			String field="";
			String group = matcher.group();			
			String newString = group.replaceAll("\\#","");
			if(details.get(newString)!=null)
			{
				field=details.get(newString).toString();
			}
			message=message.replace(group, field);
		}

		setMessage(message);
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
