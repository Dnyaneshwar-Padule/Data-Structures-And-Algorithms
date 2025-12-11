package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) {
		UndirectedAdjacencyMatrix adjacencyMatrix = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			System.out.print("Enter the number of vertices : ");
			int V = Integer.parseInt( br.readLine());
			
			System.out.print("Enter the  number  of  edges : ");
			int E = Integer.parseInt(br.readLine());
		
			adjacencyMatrix  = new UndirectedAdjacencyMatrix(V, E);
			
			System.out.println("\nEnter edges (u:v), (0:1)");
			for(int i = 0; i < E; ++i) {
				System.out.print("Enter edge: ");
				String edges = br.readLine();
				String tokens[] = edges.split(":");
				adjacencyMatrix.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			
			System.out.println("Degree of vertices");
			for(int i = 0; i < V; ++i) {
				System.out.println("Degree of " + i + ":" + adjacencyMatrix.degreeOf(i));
			}
			
			System.out.println(adjacencyMatrix);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
