import java.util.ArrayList;

public class Node
{
	public Node() {}
	
	public Node (String name)
	{
		mName = name;
	}
	
	public ArrayList<Node> getChildNodeList()
	{
		return mChildNodeList;
	}
	
	public void setChildNodeList(ArrayList<Node> childNodeList) 
	{
		mChildNodeList = childNodeList;
	}
	
	public void addToNodeList(Node node)
	{
		mChildNodeList.add(node);
	}
	
	public String getName() 
	{
		return mName;
	}
	
	public void setName(String name) 
	{
		mName = name;
	}
	
	private ArrayList<Node> mChildNodeList = new ArrayList<Node>();
	private String mName;
}
