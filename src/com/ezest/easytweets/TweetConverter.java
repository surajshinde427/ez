package com.ezest.easytweets;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class TweetConverter {
	Configration config;

	String topic;
	Document details;

	void processAndConvertToTweet(ConsumerRecord<Long, String> record) throws ParserConfigurationException, SAXException, IOException, ParseException {

		setConfig(new Configration());		
		PrefixRemoverFromTopic(record);		
		setDetails(getTransformedTweets(record));
	}
	private Document getTransformedTweets(ConsumerRecord<Long, String> record) {
		JSONObject json = new JSONObject(record.value());
		DateConverter dateConverter=new DateConverter(json); 
		String payload=dateConverter.getPayload().toString();
		Document details=Document.parse(payload);
		return details;
	}
	private String PrefixRemoverFromTopic(ConsumerRecord<Long, String> record) {
		String topicname=record.topic();
		ArrayList<String> topicPrefix=config.getTopicPrefix();
				for(String prefix:topicPrefix)
				{
					topic=topicname.replace(prefix,"");
				}
		return topic;
	}
	public Configration getConfig() {
		return config;
	}
	public void setConfig(Configration config) {
		this.config = config;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Document getDetails() {
		return details;
	}
	public void setDetails(Document details) {
		this.details = details;
	}

}
