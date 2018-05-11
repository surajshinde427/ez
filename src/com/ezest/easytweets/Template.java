package com.ezest.easytweets;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.bson.Document;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import com.ezest.easytweets.Configration;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class Template {


	private List<Document> templateList;
	MessageCreator messageCreater= new MessageCreator();
	MongoDB mongoDb=new MongoDB();


	public Template(String topic) throws ParserConfigurationException, SAXException, IOException, ParseException 
	{
		Configration config=new Configration();
		MongoClient mongoClient =  new MongoClient(config.getMongoDbServer(), config.getPort() );
		MongoDatabase database;
		MongoCollection<Document> template;

		try {
			database = mongoClient.getDatabase(config.getMongoDbDatabase());
			template= database.getCollection(config.getEventCollection());
			setTemplateList((List<Document>) template.find(eq(MongoDBConstants.PARENT_NAME,topic)).into(new ArrayList<Document>()));
			mongoClient.close();
		}
		catch (NullPointerException e) {
			e.getMessage();
		}
		finally {
			mongoClient.close();
		}

	}

	public void insertTemplate(Template template,MongoCollection<Document> events,Document details) throws ParserConfigurationException, SAXException, IOException
	{

		List<Document> templateData = template.getTemplateList();

		for (Document singleTemplateData : templateData) {

			String templateEventName = singleTemplateData.getString(MongoDBConstants.TOPIC_NAME);
			String templateEventMessage = singleTemplateData.getString(MongoDBConstants.TEMPLATE_MESSAGE);

			if(getCondition(singleTemplateData, details))
			{
			messageCreater.ConvertTemplateMessageIntoValues(templateEventMessage,details);
			mongoDb.setObjectId(singleTemplateData.getObjectId("_id"));
			MongoCollection<Document> getEvent=mongoDb.getEvents();
			mongoDb.insertRecord(mongoDb.getTweets(), templateEventName,mongoDb.getObjectId() , details, messageCreater.getMessage());
			}
		}




	}
	private boolean getCondition(Document singleTemplateData,Document details) {
		boolean condition=true;
	@SuppressWarnings("unchecked")
	ArrayList<Document> conditonFiels= (ArrayList<Document>) singleTemplateData.get(MongoDBConstants.TOPIC_CONDITION);
	System.out.println("asdasd");	
	if(conditonFiels!=null)
	{
	for(Document conditionJson:conditonFiels)
	{
	Set<String> keys = conditionJson.keySet();
		for(String key:keys)
		{
			String a = details.get(key).toString();
			String b = conditionJson.get(key).toString();
		     if(a.equals(b))
		     {
		    	 condition=true;
		     }
		     else
		     {
		    	 condition=false;
		     }
		}
	}
	}
	System.out.println("nosaad");
		return condition;
	}

	public List<Document> getTemplateList() {
		return templateList;
	}
	public void setTemplateList(List<Document> templateList) {
		this.templateList = templateList;
	}

}



