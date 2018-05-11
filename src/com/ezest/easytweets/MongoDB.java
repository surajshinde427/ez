package com.ezest.easytweets;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	private Configration config;
	private MongoClient mongoClient;	
	private MongoDatabase database;
	private MongoCollection<Document> tweets;
	private MongoCollection<Document> events;
	private MongoCollection<Document> template;


	private ObjectId objectId;

	public MongoDB() throws ParserConfigurationException, SAXException, IOException, ParseException {
		Configration config=new Configration();
		MongoClient mongoClient = new MongoClient(config.getMongoDbServer(), config.getPort() );
		setMongoClient(mongoClient);
		setDatabase(mongoClient.getDatabase(config.getMongoDbDatabase()));
		setTweets(database.getCollection(config.getCollectionName()));
		setEvents(database.getCollection(config.getEventCollection()));
		setTemplate(database.getCollection(config.getTemplateCollection()));

	}
	public ObjectId ObjectIdFetcher(String topic)
	{
		return null;
	}
	public Boolean eventExist(MongoCollection<Document> collection,String EventName,String topic)
	{
		Document myDoc = collection.find(eq(EventName,topic)).first();
		if(myDoc==null)
			return false;
		else
			return true;
	}

	public void eventExistOrCreate(String EventName,String topic)
	{

		Document myDoc = events.find(eq(EventName,topic)).first();
		if(myDoc==null)
		{
			Document document = new Document();
			document.append(MongoDBConstants.TOPIC_NAME, topic);

			events.insertOne(document);

			Document myDoc1 = events.find(eq(MongoDBConstants.TOPIC_NAME,topic)).first();
			setObjectId(new ObjectId(myDoc1.get("_id").toString()));

		}
		else
		{

			Document myDoc1 = events.find(eq(MongoDBConstants.TOPIC_NAME,topic)).first();
			setObjectId(new ObjectId(myDoc1.get("_id").toString()));

		}

	}
	public void insertEvent(MongoCollection<Document> collection,String topic)
	{
		Document EventDetails = new Document();
		EventDetails.append(MongoDBConstants.TOPIC_NAME,topic);
		EventDetails.append(MongoDBConstants.RESTRICTED, "R");
		collection.insertOne(EventDetails);
	}
	public void insertTemplateStructure(MongoCollection<Document> collection,String topic)
	{
		List<Document> array = new ArrayList<Document>();
		Document templateDetails = new Document();
		templateDetails.append(MongoDBConstants.TOPIC_NAME,topic);
		templateDetails.append(MongoDBConstants.TEMPLATES,array);
		collection.insertOne(templateDetails);
	}
	public void insertRecord(MongoCollection<Document> collection,String topic,ObjectId id,Document details,String message)
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DateTimeConstants.TIME_ZONE));
		Date currentDate = calendar.getTime();

		Document documentDetail = new Document();
		documentDetail.append(MongoDBConstants.TPICID,id);
		documentDetail.append(MongoDBConstants.TOPIC_NAME,topic);

		Document document = new Document();
		document.append(MongoDBConstants.MESSAGE,message);
		document.append(MongoDBConstants.TIMESTAMP,new Timestamp(currentDate.getTime()));
		document.append(MongoDBConstants.AUTHOR, documentDetail);
		document.append(MongoDBConstants.VERSION, 0);
		document.append(MongoDBConstants.TWEET_DETAILS,details);

		collection.insertOne(document);
		System.out.println("Record inserted");
		
	}
	public void createTemplate(String topic)
	{

	}
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
	public Configration getConfig() {
		return config;
	}
	public void setConfig(Configration config) {
		this.config = config;
	}
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	public MongoDatabase getDatabase() {
		return database;
	}
	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}
	public MongoCollection<Document> getTweets() {
		return tweets;
	}
	public void setTweets(MongoCollection<Document> tweets) {
		this.tweets = tweets;
	}
	public MongoCollection<Document> getEvents() {
		return events;
	}
	public void setEvents(MongoCollection<Document> events) {
		this.events = events;
	}
	public MongoCollection<Document> getTemplate() {
		return template;
	}
	public void setTemplate(MongoCollection<Document> template) {
		this.template = template;
	}
}
