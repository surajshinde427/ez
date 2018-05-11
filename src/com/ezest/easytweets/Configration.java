package com.ezest.easytweets;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public  class Configration {


	private  String mongoDbServer;
	private  String mongoDbDatabase;
	private  String collectionName;
	private  String eventCollection;
	private  String kafkaServer;
	private  String groupIDConfig;
	private  String zookeeper;
	private  String templateCollection;
	private  ArrayList<String> topicPrefix;
	private  int Port;


	public  Configration() throws ParserConfigurationException, SAXException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		String jsonString = parser.parse(new FileReader("configration.json")).toString();

		JSONObject object =new JSONObject(jsonString);

		extractMongoDbConfig(object); 
		extractKafkaConfig(object);
		extractTopicPrefix(object);

	}

	private void extractTopicPrefix(JSONObject object) {
		ArrayList<String> prefixList=new ArrayList<>();
		JSONArray topicPrefixs =object.getJSONArray("topicPrefix");
		
		for(int i=0;i<topicPrefixs.length();i++) {
			prefixList.add(topicPrefixs.getString(i).toString());
			}	
		setTopicPrefix(prefixList);
	}

	private void extractKafkaConfig(JSONObject object) {


		JSONObject mongoDbJsonObject =object.getJSONObject("KafkaConfig");

		setKafkaServer(mongoDbJsonObject.getString(ServerConstants.KAFKA_SERVER));
		setGroupIDConfig(mongoDbJsonObject.getString(ServerConstants.KAFKA_GROUP));
		setZookeeper(mongoDbJsonObject.getString(ServerConstants.ZOOKEEPER));

	}

	private void extractMongoDbConfig(JSONObject object) {

		JSONObject mongoDbJsonObject =object.getJSONObject("MongoConfig");

		setMongoDbServer(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_SERVER));
		setMongoDbDatabase(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_DATABASE));
		setCollectionName(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_TWEETS));
		setEventCollection(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_TOPIC));
		setTemplateCollection(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_TEMPLATE));
		setPort(Integer.parseInt(mongoDbJsonObject.getString(MongoDBConstants.MONGODB_PORT)));

	}


	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public String getMongoDbServer() {
		return mongoDbServer;
	}

	public void setMongoDbServer(String mongoDbServer) {
		this.mongoDbServer = mongoDbServer;
	}

	public String getMongoDbDatabase() {
		return mongoDbDatabase;
	}

	public void setMongoDbDatabase(String mongoDbDatabase) {
		this.mongoDbDatabase = mongoDbDatabase;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getEventCollection() {
		return eventCollection;
	}

	public void setEventCollection(String eventCollection) {
		this.eventCollection = eventCollection;
	}

	public String getKafkaServer() {
		return kafkaServer;
	}

	public void setKafkaServer(String kafkaServer) {
		this.kafkaServer = kafkaServer;
	}

	public String getGroupIDConfig() {
		return groupIDConfig;
	}

	public void setGroupIDConfig(String groupIDConfig) {
		this.groupIDConfig = groupIDConfig;
	}

	public String getZookeeper() {
		return zookeeper;
	}

	public void setZookeeper(String zookeeper) {
		this.zookeeper = zookeeper;
	}

	public String getTemplateCollection() {
		return templateCollection;
	}

	public void setTemplateCollection(String templateCollection) {
		this.templateCollection = templateCollection;
	}

	public ArrayList<String> getTopicPrefix() {
		return topicPrefix;
	}

	public void setTopicPrefix(ArrayList<String> topicPrefix) {
		this.topicPrefix = topicPrefix;
	}


}
