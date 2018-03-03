package Classes;

import java.util.ArrayList;

public class Vertex
{
	
	private int index; //may not be useful because of the corresponding index in the ArrayList of the graph
	private ArrayList<Integer> successor; //List of successors of the vertex
	
	public Vertex(int i)
	{
		index = i;
		successor = new ArrayList<>();
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public ArrayList<Integer> getSuccessor()
	{
		return successor;
	}
	
	public void addSuccessor(int v)
	{
		if(!successor.contains(v))
		{
			successor.add(v);
		}
	}
}
