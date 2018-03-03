package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph
{
	
	private ArrayList<Vertex> g;
	
	public ArrayList<Vertex> getGraph()
	{
		return g;
	}
	
	public Graph(String filename)
	{
		g = new ArrayList<>();
		//reading the file where is contained the matrix that represent the graph
		try{
	        Scanner reader = new Scanner(new FileInputStream("ressources/"+filename+".txt"));
	        
	        int numberOfNodes = Integer.parseInt(reader.next());


	        for (int i = 0; i<numberOfNodes; i++)
	        {
	        	reader.nextLine();
		        Vertex newV = new Vertex(i);
		        g.add(newV);
		        for (int j=0; j<numberOfNodes; j++)
		        {
		        	if (Integer.parseInt(reader.next()) != 0)
		        	{
		        		newV.addSuccessor(j);
		        	}
		        }
	        }

            reader.close();
	    }
	    catch(FileNotFoundException e)
	    {
	    	System.out.println("Error: no file found");
	    }
	}
	
	public void printGraph()
	{
		for(Vertex v : this.g)
		{
			System.out.print("Index : "+ (v.getIndex()+1) + " successor : ");
			for(int i=0; i<v.getSuccessor().size();i++)
			{
				System.out.print("" + (v.getSuccessor().get(i)+1) + "  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
