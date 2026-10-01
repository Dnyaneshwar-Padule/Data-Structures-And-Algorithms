package com.tca;

import com.tca.util.WeightedDirectedAdjacencyList;

public class App {

	public static void main(String[] args) {
			
//	    DirectedAdjacencyList graph = new DirectedAdjacencyList(8);
//		graph.add(0, 1);
//	    graph.add(0, 2);
//	    graph.add(1, 3);
//	    graph.add(2, 3);
//	    graph.add(2, 4);
//	    graph.add(3, 5);
//	    graph.add(4, 5);
//	    graph.add(5, 6);
//	    graph.add(4, 7);
//	
//	    System.out.println("Vertices: " + graph.V());
//	    System.out.println("Edges: " + graph.E());
//	
//	    System.out.println("DFS: " + graph.dfs());
//	    System.out.println("BFS: " + graph.bfs());
//	
//	    System.out.println("Topological Sort: " + graph.topologicalSort());
		
		 WeightedDirectedAdjacencyList graph =
	                new WeightedDirectedAdjacencyList(7);

	        graph.add(0, 1, 4);
	        graph.add(0, 2, 2);

	        graph.add(1, 2, 1);
	        graph.add(1, 3, 5);

	        graph.add(2, 1, 1);
	        graph.add(2, 3, 8);
	        graph.add(2, 4, 10);

	        graph.add(3, 4, 2);
	        graph.add(3, 5, 6);

	        graph.add(4, 5, 3);

	        System.out.println("Vertices: " + graph.V());
	        System.out.println("Edges: " + graph.E());

	        System.out.println("\nGraph:");
	        System.out.println(graph);

	        System.out.println("Dijkstra from vertex 0:");

	        int[][] result = graph.dijkstra(0);

	        int[] path = result[0];
	        int[] distance = result[1];

	        for (int v = 0; v < graph.V(); v++) {
	            System.out.println(
	                    "Vertex " + v +
	                    " | Distance: " + distance[v] +
	                    " | Previous: " + path[v]
	            );
	        }
	}

}
