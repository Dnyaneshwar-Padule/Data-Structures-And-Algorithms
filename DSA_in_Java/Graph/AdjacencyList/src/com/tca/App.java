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
	        
	        
	        System.out.println();
	        
	        result = graph.improvedDijkstra(0);
	        path = result[0];
	        distance = result[1];

	        for (int v = 0; v < graph.V(); v++) {
	            System.out.println(
	                    "Vertex " + v +
	                    " | Distance: " + distance[v] +
	                    " | Previous: " + path[v]
	            );
	        }
	        
	        System.out.println("----------------");
	        
	        result = graph.bellmanFord(0);
	        
	        if(result.length > 0 ) {
	        	path = result[0];
	        	distance = result[1];
	        	
	        	for (int v = 0; v < graph.V(); v++) {
	        		System.out.println(
	        				"Vertex " + v +
	        				" | Distance: " + distance[v] +
	        				" | Previous: " + path[v]
	        				);
	        	}	        	
	        }
	        
	        WeightedDirectedAdjacencyList graph1 =
	                new WeightedDirectedAdjacencyList(8);
	        graph1.add(0, 1, 4);
	        graph1.add(0, 2, 3);
	        graph1.add(1, 2, 1);
	        graph1.add(1, 3, 2);
	        graph1.add(2, 3, 4);
	        graph1.add(2, 4, 2);
	        graph1.add(3, 5, 3);
	        graph1.add(4, 5, 4);  // Cycle starts here
	        graph1.add(5, 6, -8); // Negative edge in cycle
	        graph1.add(6, 4, 2);  // Cycle: 4 -> 5 -> 6 -> 4 sums to -2
	        graph1.add(5, 7, 5);
	        graph1.add(6, 7, 1);
	        graph1.add(7, 3, 1);
	        
	        
	        System.out.println("----------------");
	        
	        result = graph1.bellmanFord(0);
	        
	        if(result.length > 0 ) {
	        	path = result[0];
	        	distance = result[1];
	        	
	        	for (int v = 0; v < graph.V(); v++) {
	        		System.out.println(
	        				"Vertex " + v +
	        				" | Distance: " + distance[v] +
	        				" | Previous: " + path[v]
	        				);
	        	}	        	
	        }
	        else {
	        	System.out.println("Negative cycle exists");
	        }
	        
	        WeightedDirectedAdjacencyList graph2 =
	                new WeightedDirectedAdjacencyList(8);
	        graph2.add(0, 1, 6);
	        graph2.add(0, 2, 5);
	        graph2.add(0, 3, 5);
	        graph2.add(1, 4, -1); // Valid negative edge
	        graph2.add(2, 1, -2); // Valid negative edge
	        graph2.add(2, 4, 1);
	        graph2.add(3, 2, -2); // Valid negative edge
	        graph2.add(3, 5, -1); // Valid negative edge
	        graph2.add(4, 6, 3);
	        graph2.add(5, 6, 3);
	        graph2.add(5, 7, 3);
	        graph2.add(6, 7, 2);
	        graph2.add(4, 7, -2); // Deep negative edge shortcut
	        graph2.add(1, 5, 2);
	
	System.out.println("----------------");
	        
	        result = graph2.bellmanFord(0);
	        
	        if(result.length > 0 ) {
	        	path = result[0];
	        	distance = result[1];
	        	
	        	for (int v = 0; v < graph.V(); v++) {
	        		System.out.println(
	        				"Vertex " + v +
	        				" | Distance: " + distance[v] +
	        				" | Previous: " + path[v]
	        				);
	        	}	        	
	        }
	        
	        WeightedDirectedAdjacencyList graph3 =
	                new WeightedDirectedAdjacencyList(9);
	        // Connected Component (reachable from source 0)
	        graph3.add(0, 1, 10);
	        graph3.add(0, 2, 15);
	        graph3.add(1, 3, 12);
	        graph3.add(2, 3, 4);

	        // Isolated Component (completely unreachable from source 0)
	        graph3.add(4, 5, 100000); // Will overflow if distance[4] isn't checked
	        graph3.add(4, 6, 250000); 
	        graph3.add(5, 6, 50000);
	        graph3.add(5, 7, 500000); // Massive addition hazard
	        graph3.add(6, 7, 120000);
	        graph3.add(6, 8, 300000);
	        graph3.add(7, 8, 40000);
	        graph3.add(8, 4, 15000);

		System.out.println("----------------");
	        
	        result = graph3.bellmanFord(0);
	        
	        if(result.length > 0 ) {
	        	path = result[0];
	        	distance = result[1];
	        	
	        	for (int v = 0; v < graph.V(); v++) {
	        		System.out.println(
	        				"Vertex " + v +
	        				" | Distance: " + distance[v] +
	        				" | Previous: " + path[v]
	        				);
	        	}	        	
	        }
	        
	}

}
