package com.tca;

import com.tca.util.AdjacencyList;

public class App {

	public static void main(String[] args) {
			
		AdjacencyList g = new AdjacencyList(7,10);
		
		System.out.println(g);
		
		System.out.println(g.dfs());
		System.out.println(g.bfs());
	}

}
