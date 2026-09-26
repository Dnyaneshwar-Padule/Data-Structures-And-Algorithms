package com.tca;

import com.tca.util.Graph;

public class App {

	public static void main(String[] args) {
			
		Graph g = new Graph(9,20);

		System.out.println(g);
		System.out.println(g.dfs());
		System.out.println(g.bfs());
	}
	
}
