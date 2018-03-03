package Classes;

import java.util.*;

public class Kosaraju
{
	public Kosaraju(){}
    /** DFS function **/
    public void DFS(ArrayList<Vertex> vertexList, int index, boolean[] visited, List<Integer> comp) //The function traverse the graph and adds each "blocked" components to the list given in parameters
    {
        visited[index] = true;
        for (int i = 0; i < vertexList.get(index).getSuccessor().size(); i++)
            if (!visited[vertexList.get(index).getSuccessor().get(i)])
            {
                DFS(vertexList, vertexList.get(index).getSuccessor().get(i), visited, comp);
            }
        comp.add(index);
    }
    /** function fill order that gives us the "green order" seen in the courses **/
    public List<Integer> fillOrder(ArrayList<Vertex> vertexList, boolean[] visited) 
    {
        List<Integer> order = new ArrayList<Integer>();
 
        for (int i = 0; i < vertexList.size(); i++)
            if (!visited[i])
            {
                DFS(vertexList, i, visited, order);
            }
        return order;
    }
    /** function to get transpose of graph **/
    public ArrayList<Vertex> getTranspose(ArrayList<Vertex> vertexList) //we traverse the original graph and for each successor of a node, we add as successor this node to the previous successor in a new graph of the same size of the original
    {//The graphical representation of the transposed graph is the original directed graph with reversed edges
        ArrayList<Vertex> newGraph = new ArrayList<>();
        for (int i = 0; i < vertexList.size(); i++)
        {
        	Vertex v = new Vertex(i);
            newGraph.add(v);
        }
        for (int k = 0; k < vertexList.size(); k++)
        {
            for (int i = 0; i < vertexList.get(k).getSuccessor().size(); i++)
            {
            	newGraph.get(vertexList.get(k).getSuccessor().get(i)).addSuccessor(k);
            }
        }
        return newGraph;
    }
    /** function to get all SCC **/
    public List<List<Integer>> getSCComponents(ArrayList<Vertex> vertexList)
    {
        boolean[] visited = new boolean[vertexList.size()];
        // Gives the "green" order of the graph
        List<Integer> order = fillOrder(vertexList, visited);
        //get transpose of the graph
        ArrayList<Vertex> reverseGraph = getTranspose(vertexList);
        //clear visited array
        visited = new boolean[vertexList.size()];
        // reverse order list
        Collections.reverse(order);
 
        //get all SCC 
        List<List<Integer>> SCComp = new ArrayList<>();
        for (int i = 0; i < order.size(); i++)
        {
            //Does the same traversal with the reversed list of node ("the reversed green order")
            int v = order.get(i);
            if (!visited[v]) 
            {
                List<Integer> comp = new ArrayList<>();
                DFS(reverseGraph, v, visited, comp);
                SCComp.add(comp); //each time there is a "jump", we add the component list in the SCComp list because it means it is a SCComp one.
            }
        }
        return SCComp;
    }
    /** function to print the SCC **/
    public void printSCC(List<List<Integer>> SCComp)
    {
		for(int i=0;i<SCComp.size();i++)
		{
			System.out.print("group of SCC n°" + (i+1) + " : ");
			for(int k=0;k<SCComp.get(i).size();k++)
			{
				System.out.print("" + (SCComp.get(i).get(k)+1) + "  ");
			}
			System.out.println("");
		}
		System.out.println("");
    }
    /** function to generate the matrix N with the different game levels and the number of direct passages **/
    public int[][] levelsMatrix(List<List<Integer>> SCComp, ArrayList<Vertex> vertexList)
    {
    	int[][] matrix =new int[SCComp.size()][SCComp.size()]; //creates the matrix N
    	for (int i=0; i<SCComp.size(); i++)
        {
            for (int j=0; j<SCComp.get(i).size(); j++)
            {
            	ArrayList<Integer> succList = vertexList.get(SCComp.get(i).get(j)).getSuccessor();
            	for(int k=0; k<succList.size(); k++)
                {
                    if(locateComponent(SCComp, succList.get(k))!=i)
                        matrix[i][locateComponent(SCComp, succList.get(k))]++;//if the node sent in parameter hasn't the same SCC as the one we are actually in, it increments the path between the SCC we are in and the SSC where the different node is.
                }
            }
        }
    	return matrix;
    }
    /** Used to locate the SSComponent in which is situated the node send in parameter **/
    private int locateComponent(List<List<Integer>> SCComp, int index)
    {
         for (int i=0; i<SCComp.size(); i++)
         {
             for (int j=0; j<SCComp.get(i).size(); j++)
             {
                 if(SCComp.get(i).get(j)==index)
                     return i;
             }
         }
         return -1; //if an error occurs
    }
    /** Used to print the matrix N **/
    public void printN(int[][] matrix)
    {
        System.out.print("\nMatrix N :");
        for(int i=0; i<matrix.length; i++)
        {
            System.out.print("\n");
            for(int j=0; j<matrix.length; j++)
            {
                System.out.print(" "+matrix[i][j]);
            }
        }
    }
}