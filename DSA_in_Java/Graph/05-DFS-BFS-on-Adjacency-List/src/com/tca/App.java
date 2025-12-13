package com.tca;

import com.tca.util.AdjacencyList;

public class App {
	
	public static void main(String[] args) {
		
		try {
			AdjacencyList adjacencyList = new AdjacencyList(6, 6);
			
			System.out.println("Degree of vertices");
			for(int i = 0; i < adjacencyList.V(); ++i) {
				int degree = adjacencyList.degree(i);
				System.out.println("Degree of " + i + " : " + degree);
			}
			
			System.out.println(adjacencyList);
			
			System.out.println("DFS");
			adjacencyList.DFS();
			
			System.out.println("BFS");
			adjacencyList.BFS();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}