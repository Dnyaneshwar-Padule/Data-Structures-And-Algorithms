package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.tca.util.AdjacencyList;

public class App {
	
	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			System.out.print("Enter the number of vertices : ");
			int V = Integer.parseInt(br.readLine());
			
			System.out.print("Enter the number of  edges   : ");
			int E = Integer.parseInt(br.readLine());
			
			AdjacencyList adjacencyList = new AdjacencyList(V, E);

			System.out.println("\nEntet edges (u:v), (0:1)");
			for(int i = 0; i < E; ++i) {
				System.out.print("Enter edge: ");
				String edge = br.readLine();
				String[] tokens = edge.split(":");
				adjacencyList.addEdge( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			
			
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
			
			adjacencyList.shortestPath(2);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}