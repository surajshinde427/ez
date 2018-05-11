package com.ezest.easytweets;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TemplateReader {
	
	ArrayList<ArrayList<HashMap<String, String>>> topicJson = new  ArrayList<ArrayList<HashMap<String, String>>>();
	
	public  TemplateReader(String xmlFile)
	{
		xmlFile=xmlFile+".xml";
		 File inputFile = new File(xmlFile);
		if (inputFile.isFile() && inputFile.canRead()) {
	  try {
	       
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	       
	        
	     
	        
	        ArrayList<HashMap<String, String>> field=new ArrayList<HashMap<String, String>>();
	        System.out.println("ROOT ELEMENT:"+doc.getDocumentElement().getNodeName());
	      
	        
	        
	        NodeList nList = doc.getElementsByTagName("Template");
	        System.out.println("----------------------------"+nList.getLength());
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	           // System.out.println("\nCurrent Element :" + nNode.getNodeName());
	          //  System.out.println("----------------------------"+nNode.getChildNodes().getLength());
	            NodeList nList1 = nNode.getChildNodes();
	          //  System.out.println("----------------------------"+nList1.getLength());
	            HashMap<String, String> fieldMap = new HashMap<String, String>();
	            for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {
	                Node nNode1 = nList1.item(temp1);
	              //  System.out.println("\nInner Element :" + nNode1.getNodeName());
	            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) nNode1;
	               
	             // System.out.println("\n Inneer Element :" + nNode1.getNodeName());
	              
	              fieldMap.put(eElement.getElementsByTagName("name").item(0).getTextContent(),eElement.getElementsByTagName("new").item(0).getTextContent());
	               System.out.println("First Name : " 
	                  + eElement
	                  .getElementsByTagName("name")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Last Name : " 
	                  + eElement
	                  .getElementsByTagName("new")
	                  .item(0)
	                  .getTextContent());
	               
	            }
	            }
	            field.add(fieldMap);
	              
	        }
	        topicJson.add(field);
	       
	  }
catch (Exception e) {
        e.printStackTrace();
     }
		}
	//return topicJson;
}
	public ArrayList<ArrayList<HashMap<String, String>>> getTopicJson() {
		return topicJson;
	}
	public void setTopicJson(ArrayList<ArrayList<HashMap<String, String>>> topicJson) {
		this.topicJson = topicJson;
	}
}


