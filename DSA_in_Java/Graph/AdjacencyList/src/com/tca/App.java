package com.tca;

import com.tca.util.UndirectedAdjacencyList;

public class App {

	public static void main(String[] args) {
			
		UndirectedAdjacencyList g = new UndirectedAdjacencyList(7,10);
		
		System.out.println(g);
		
		System.out.println(g.dfs());
		System.out.println(g.bfs());
	}

}
