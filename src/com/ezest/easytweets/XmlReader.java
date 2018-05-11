package com.ezest.easytweets;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
public class XmlReader {
	
	public Document readXmlFileToJson(String FilePath) throws SAXException, IOException, ParserConfigurationException
	{
	File config = new File(FilePath);
	DocumentBuilderFactory dbFacctory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFacctory.newDocumentBuilder();
	 Document document = dBuilder.parse(config);
	document.getDocumentElement().normalize();
	return document;
	}
}
