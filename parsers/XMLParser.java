package parsers;


import java.util.ArrayList;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import models.Post;

public class XMLParser {
	
	SAXParserFactory factory;
	String filePath;
	SAXParser saxParser;
	ArrayList<Post> result = new ArrayList<Post>();
	
	public XMLParser() {
		factory = SAXParserFactory.newInstance();
	}
	
	public ArrayList<Post> parse( String filePath ) {
		this.filePath = filePath;
		
		try {
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = getHandler();
			saxParser.parse(filePath, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	
	protected DefaultHandler getHandler()
	{
		DefaultHandler handler = new DefaultHandler(  ) {

			boolean isTournament = false;
			boolean isDate = false;
			boolean isSportsType = false;
			boolean isName = false;
			boolean isCost = false;
			
			String[] stringItems = new String[4];
			String date;

			public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

				if (qName.equals("tournament")) {
					isTournament = true;
				}
				if (qName.equals("date")) {
					isDate = true;
				}
				if (qName.equals("type")) {
					isSportsType = true;
				}
				if (qName.equals("name")) {
					isName = true;
				}
				if (qName.equals("cost")) {
					isCost = true;
				}
			}

			public void endElement(String uri, String localName,
					String qName) throws SAXException {

				if (qName.equalsIgnoreCase("post")) {
					addPost();					
				}
			}

			public void characters(char ch[], int start, int length) throws SAXException {

				if (isTournament) {
					stringItems[0] =  new String(ch, start, length);
					isTournament = false;
				}
				
				if (isDate) {
					date = new String(ch, start, length);
					isDate = false;
				}
				
				if (isSportsType) {
					stringItems[1] = new String(ch, start, length);
					isSportsType = false;
				}
				
				if(isName) {
					stringItems[2] = new String(ch, start, length);
					isName = false;
				}
				
				if(isCost) {
					stringItems[3] = new String(ch, start, length);
					isCost = false;
				}
				
			}

			protected void addPost()
			{
				Post post = new Post(stringItems);
				post.setDate(date);
				result.add( post );
			}
			
		};
		
		return handler;
	}
	

}
