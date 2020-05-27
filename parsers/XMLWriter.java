package parsers;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import models.Post;
 
public class XMLWriter {
 
	protected DocumentBuilderFactory factory;
	protected String filePath;
	protected DocumentBuilder builder;
	protected TransformerFactory transformerFactory;
	protected boolean isCompleted = false;
	
	public XMLWriter( String filePath ) {
		factory = DocumentBuilderFactory.newInstance();
		this.filePath = filePath;
		transformerFactory = TransformerFactory.newInstance();
	}
	
	public void create( ArrayList<Post> posts )
	{
		try {
			 builder = factory.newDocumentBuilder();
			 Document doc = builder.newDocument();
			 Element root = doc.createElement("Posts");
			 doc.appendChild(root);
			 
			 for( int i = 0; i < posts.size(); i++ ) {
				 root.appendChild( getPostNode(doc, posts.get(i) ));
			 }
			 
			 Transformer transformer = transformerFactory.newTransformer();
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 
			 DOMSource source = new DOMSource(doc);
			 
			 StreamResult console = new StreamResult(System.out);
			 StreamResult file = new StreamResult(new File(filePath));
	 
			 transformer.transform(source, console);
			 transformer.transform(source, file);
			 
			 System.out.println("Создание XML файла закончено");
			 
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean isCompleted()
	{
		return isCompleted;
	}
	
	protected Node getPostNode(Document doc, Post post)
	{
		Element postNode = doc.createElement("Post");

		postNode.appendChild(getChild(doc, postNode, "tournament", post.getTournamentName()));
		postNode.appendChild(getChild(doc, postNode, "date", post.getPublicDate()));
		postNode.appendChild(getChild(doc, postNode, "type", post.getSportType()));
		postNode.appendChild(getChild(doc, postNode, "name", post.getFullName()));
		postNode.appendChild(getChild(doc, postNode, "cost", post.getStingAllCost()));
		postNode.appendChild(getChild(doc, postNode, "revenue", post.getStringEarn()));
		
		return postNode;
	}
	
	protected Node getChild( Document doc, Element element, String name, String value )
	{
		Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
	}
 
 
}