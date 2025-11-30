package com.tca;

import com.tca.util.MySkipList;

public class App {

	public static void main(String[] args) {
		
		MySkipList<String, Integer> skipList = new MySkipList<>();
		
		skipList.add("Dnyaneshwar", 20);
		skipList.add("Athrva", 20);
		skipList.add("Prasad", 21);
		
		System.out.println("Size : " + skipList.size());
		System.out.println("Dnyaneshwar contains : " + skipList.contains("Dnyaneshwar"));
		System.out.println("Athrva contains : " + skipList.contains("Athrva"));
		System.out.println("Prasad contains : " + skipList.contains("Prasad"));
		System.out.println("Ganesh contains : " + skipList.contains("Ganesh"));
		
		skipList.remove("Prasad");
		skipList.remove("Ganesh");
		
		System.out.println("Removed");
		System.out.println("Size : " + skipList.size());
		System.out.println("Prasad contains : " + skipList.contains("Prasad"));		
		System.out.println("Athrva : " + skipList.get("Athrva"));
		
		skipList.clear();
	}
}
