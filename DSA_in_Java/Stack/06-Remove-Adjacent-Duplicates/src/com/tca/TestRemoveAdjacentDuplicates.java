package com.tca;

public class TestRemoveAdjacentDuplicates {

	public static void main(String[] args) {
		int arr[] = {1,5,6,8,8,8,0,1,1,0,6,5};
		
		for(int num : arr)
			System.out.print(num + " ");
		
		System.out.println();
		int stkptr = removeAdjacentDuplicates(arr);
		
		for(int i = 0; i <= stkptr; ++i) {
			System.out.print(arr[i] + " ");
		}
		
	}

	private static int removeAdjacentDuplicates(int[] arr) throws NullPointerException {
		int stkptr = -1;
		int i = 0;
		
		while(i < arr.length) {
			if(stkptr == -1 || arr[stkptr] != arr[i]) {
				arr[++stkptr] = arr[i++];
			}
			else {
				while(i < arr.length && arr[stkptr] == arr[i] ) {
					--stkptr;
					++i;
				}
			}
		}
		
		return stkptr;
	}
	
}
