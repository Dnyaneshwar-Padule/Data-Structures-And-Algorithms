package com.tca;

public class Solution {

	public static void towersOfHanoi(int n, char fromPeg, char toPeg, char auxPeg) {
		
		//Single disk, move to 'toPeg' from 'fromPeg'
		if(n == 1) {
			System.out.println("Move disk 1 from peg " + fromPeg + " to peg " + toPeg);
			return;
		}
		
		// Move n-1 disks from A to B using C as aux
		towersOfHanoi( n - 1, fromPeg, auxPeg, toPeg);
		
		// Move remaining disks from A to C
		System.out.println("Move disk from peg " + fromPeg + " to peg " + toPeg);
		
		//Move n -1 disks from B to C using A as aux
		towersOfHanoi(n - 1, auxPeg, toPeg, fromPeg);
	}
}
