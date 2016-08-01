package com.hand.Exam3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
			String line = null;
			StringBuilder sb= new StringBuilder();
			while((line=br.readLine())!=null)
			{
				sb.append(line);
			}
			br.close();
			is.close();
			
			toXML(sb.toString());
			toJSON(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void toXML(String xml)
    {
    	String[] strings = xml.split(",");
    	String[] names = strings[0].split("\"");
    	
    	
    	
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element element = document.createElement("xml");
			
			Element stock = document.createElement("stock");
			
			Element name = document.createElement("name");
  		   name.setTextContent(names[1]);
			Element open = document.createElement("open");
			open.setTextContent(strings[1]);
			Element close = document.createElement("close");
			close.setTextContent(strings[2]);
			Element current = document.createElement("current");
			current.setTextContent(strings[3]);
			Element high = document.createElement("high");
			high.setTextContent(strings[4]);
			Element low = document.createElement("low");
			low.setTextContent(strings[5]);
			
			stock.appendChild(name);
			stock.appendChild(open);
			stock.appendChild(close);
			stock.appendChild(current);
			stock.appendChild(high);
			stock.appendChild(low);
			element.appendChild(stock);
			document.appendChild(element);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			try {
				Transformer transformer = transformerFactory.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document), new StreamResult(new File("newxml.xml")));
				
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static void toJSON(String json)
    {
    	String[] strings = json.split(",");
    	String[] names = strings[0].split("\"");
    	
    	JsonObject j = new JsonObject();
    	j.addProperty("name", names[1]);
    	j.addProperty("open",strings[1]);
    	j.addProperty("close",strings[2]);
    	j.addProperty("current",strings[3]);
    	j.addProperty("high",strings[4]);
    	j.addProperty("low",strings[5]);
    	try {
			FileOutputStream fos = new FileOutputStream("hand.json");
			fos.write(j.toString().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
