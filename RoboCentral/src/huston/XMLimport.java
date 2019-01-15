package huston;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLimport {
	private Document doc;

	public XMLimport(String path){

		try{
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
		}catch ( Exception e){
			e.printStackTrace();
		}

	}

	public int getLength(String filter){
		return doc.getElementsByTagName(filter).getLength();
	}


	public int reader(int id, String filter, String root){

		NodeList nList = doc.getElementsByTagName(root);
		Node nNode = nList.item(id);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;			
			return parstInt(eElement.getAttribute(filter));
		}
		return 0;
	}
	public static int parstInt(String x)
	{
		try{
			return Integer.parseInt(x.replaceAll("[\\D]", ""));
		}
		catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

}
