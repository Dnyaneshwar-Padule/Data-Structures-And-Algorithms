package com.tca;

import com.tca.util.AdjacencyList;

public class App {
	public static void main(String[] args) {
		
		try {

			AdjacencyList adjacencyList = new AdjacencyList(4,5);
			System.out.println(adjacencyList);
			
			System.out.println("Degree of vertices");
			for(int i = 0; i < adjacencyList.V(); ++i) {
				int inDegree = adjacencyList.inDegree(i);
				int outDegree = adjacencyList.outDegree(i);
				int totalDegree = inDegree + outDegree;
				System.out.println("Degree of " + i);
				System.out.println("\tIndegree     : " + inDegree);
				System.out.println("\tOutdegree    : " + outDegree);
				System.out.println("\tTotal Degree : " + totalDegree);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
