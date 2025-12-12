package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.tca.util.DirectedAdjacencyMatrix;

public class App {

	public static void main(String[] args) {
		DirectedAdjacencyMatrix adjacencyMatrix = null;
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			System.out.print("Enter the number of vertices : ");
			int V = Integer.parseInt(br.readLine());
			
			System.out.print("Enter the number of  edges   : ");
			int E = Integer.parseInt(br.readLine());

			adjacencyMatrix = new DirectedAdjacencyMatrix(V, E);
			
			System.out.println("\nEntet edges (u:v), (0:1)");
			for(int i = 0; i < E; ++i) {
				System.out.print("Enter edge: ");
				String edge = br.readLine();
				String[] tokens = edge.split(":");
				adjacencyMatrix.addEdge( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			
			System.out.println("\nDegree of vertices:");
			for(int i = 0; i < V; ++i) {
				int inDegree = adjacencyMatrix.inDegreeOf(i);
				int outDegree = adjacencyMatrix.outDegreeOf(i);
				int totalDegree = inDegree + outDegree;
				System.out.println("Degree of " + i);
				System.out.println("\tIndegree     : " + inDegree);
				System.out.println("\tOutdegree    : " + outDegree);
				System.out.println("\tTotal Degree : " + totalDegree);
			}
			
			System.out.println(adjacencyMatrix);
			
			adjacencyMatrix.DFS();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
