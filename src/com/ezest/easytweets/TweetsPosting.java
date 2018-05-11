package com.ezest.easytweets;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.bson.Document;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class TweetsPosting {
	MongoDB mongoDB;
	Template template;

	public void postTweets(String topic,Document details) throws ParserConfigurationException, SAXException, IOException, ParseException
	{
		mongoDB=new MongoDB();	
		template = new Template(topic);
		mongoDB.eventExistOrCreate(MongoDBConstants.TOPIC_NAME, topic);		
		mongoDB.insertRecord(mongoDB.getTweets(),topic, mongoDB.getObjectId(), details,details.toString().replaceAll("(Document\\{\\{)|(\\}\\})",""));	
		template.insertTemplate(template,mongoDB.getEvents(), details);

	}
}
