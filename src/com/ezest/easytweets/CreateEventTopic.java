package com.ezest.easytweets;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class CreateEventTopic {

	public CreateEventTopic(ArrayList<String> topicList) throws ParserConfigurationException, SAXException, IOException, ParseException {
	
		MongoDB mongodb=new MongoDB();
		 Configration config= new Configration();
		ArrayList<String> prefixs = config.getTopicPrefix();
		
		for(String topic:topicList)
		{
			for(String prefix:prefixs)
			{
			topic=topic.replace(prefix,"");
			if(!(mongodb.eventExist(mongodb.getEvents(),MongoDBConstants.TOPIC_NAME, topic)))
			{
				mongodb.insertEvent(mongodb.getEvents(), topic);
			}
			if(!(mongodb.eventExist(mongodb.getTemplate(),MongoDBConstants.TOPIC_NAME, topic)))
			{
				mongodb.insertTemplateStructure(mongodb.getTemplate(), topic);
			}
			}
		}
		
	}

}
