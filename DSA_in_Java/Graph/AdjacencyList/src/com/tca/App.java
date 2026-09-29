package com.tca;

import com.tca.util.DirectedAdjacencyList;
import com.tca.util.UndirectedAdjacencyList;

public class App {

	public static void main(String[] args) {
			
	    DirectedAdjacencyList graph = new DirectedAdjacencyList(8);
		graph.add(0, 1);
	    graph.add(0, 2);
	    graph.add(1, 3);
	    graph.add(2, 3);
	    graph.add(2, 4);
	    graph.add(3, 5);
	    graph.add(4, 5);
	    graph.add(5, 6);
	    graph.add(4, 7);
	
	    System.out.println("Vertices: " + graph.V());
	    System.out.println("Edges: " + graph.E());
	
	    System.out.println("DFS: " + graph.dfs());
	    System.out.println("BFS: " + graph.bfs());
	
	    System.out.println("Topological Sort: " + graph.topologicalSort());
	}

}
