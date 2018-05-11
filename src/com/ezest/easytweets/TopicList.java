package com.ezest.easytweets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class TopicList {
	ArrayList<String> topiclist=new ArrayList<String>();
	public TopicList(ArrayList<String> prefix) throws IOException, KeeperException, InterruptedException, ParserConfigurationException, SAXException, ParseException {
	
	
		Configration config=new Configration();
	    ZooKeeper zk = new ZooKeeper(config.getZookeeper(), 10000, null);
        List<String> topics = zk.getChildren(ServerConstants.TOPICS_PATH, false);
        for (String topic : topics) {
        	for(String topicPrefix:prefix)
        	{
        	if(topic.contains(topicPrefix))
        		topiclist.add(topic);
        	}
        }
	}
	public ArrayList<String> getTopiclist() {
		return topiclist;
	}
	public void setTopiclist(ArrayList<String> topiclist) {
		this.topiclist = topiclist;
	}
}
