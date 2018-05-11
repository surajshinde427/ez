package com.ezest.easytweets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.zookeeper.KeeperException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class ConsumerApplication {

	private Configration config;
	private Properties consumerProperties; 
	private ArrayList<String> topicList;


	private  Consumer<Long, String> createConsumer() throws ParserConfigurationException, SAXException, IOException, KeeperException, InterruptedException, ParseException {
		Configration config=new Configration();
		TopicList topicObject=new TopicList(config.getTopicPrefix());
		setTopicList(topicObject.getTopiclist());			  			
		consumerProperties(config);
		System.out.println(getTopicList());
		Consumer<Long, String> consumer = new KafkaConsumer<Long, String>(getConsumerProperties());
		consumer.subscribe(getTopicList());
		new CreateEventTopic(getTopicList());
		return consumer;
	}


	void runConsumer() throws InterruptedException, ParserConfigurationException, SAXException, IOException, KeeperException, ParseException {
		final Consumer<Long, String> consumer =  createConsumer();
		SingleRecordProcessing record=new SingleRecordProcessing();
		try {
			while (true) {
				record.processRecord(consumer);
			}

		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}


	private void consumerProperties(Configration config) {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,config.getKafkaServer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG,config.getGroupIDConfig());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "50000");
		props.put("auto.offset.reset","latest");
		setConsumerProperties(props);

	}
	public ArrayList<String> getTopicList() {
		return topicList;
	}

	public void setTopicList(ArrayList<String> topicList) {
		this.topicList = topicList;
	}

	public Properties getConsumerProperties() {
		return consumerProperties;
	}

	public void setConsumerProperties(Properties consumerProperties) {
		this.consumerProperties = consumerProperties;
	}
	public Configration getConfig() {
		return config;
	}

	public void setConfig(Configration config) {
		this.config = config;
	}




}
