package Classes;

import java.util.List;

public class Main
{
	public static void main(String[] args)
	{
		Graph graph = new Graph("mat7");
		graph.printGraph();
		Kosaraju kosa = new Kosaraju();
		List<List<Integer>> SCComp = kosa.getSCComponents(graph.getGraph());
		kosa.printSCC(SCComp);
		kosa.printN(kosa.levelsMatrix(SCComp, graph.getGraph()));
	}
}
