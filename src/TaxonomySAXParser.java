
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TaxonomySAXParser extends DefaultHandler
{
	public TaxonomySAXParser(String fileName)
	{
		mFileName = fileName;
		mRoot = new Node();
		
		parseDocument();
		
		mRoot.setChildNodeList(mCurrentNodeList);
		
		printTaxonomy();
	}

	private void parseDocument()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser parser = factory.newSAXParser();
			parser.parse(mFileName, this);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException 
	{
		//super.startElement(uri, localname, qname, attributes);
		
		if(qname.equalsIgnoreCase("taxonomy"))
		{
			mCurrentParentNode = mRoot;
		}
		else if(qname.equalsIgnoreCase("item"))
		{
			mCurrentNode = new Node(attributes.getValue("name"));
			mCurrentParentNode.addToNodeList(mCurrentNode);
		}
//		else if(qname.equalsIgnoreCase("category"))
//		{
//			
//		}
	}

	@Override
	public void endElement(String uri, String localname, String qname) throws SAXException 
	{
		// TODO Auto-generated method stub
		//super.endElement(uri, localname, qname);
		
		if(qname.equalsIgnoreCase("item"))
			mCurrentNodeList.add(mCurrentNode);
	}
	
	public void printTaxonomy()
	{
		for (Node n : mRoot.getChildNodeList())
		{
			System.out.println(n.getName());
		}
	}

	private String mFileName = null;
	private Node mCurrentNode = null;
	private Node mCurrentParentNode = null;
	private ArrayList<Node> mCurrentNodeList = new ArrayList<Node>();
	private Node mRoot = null;
}
