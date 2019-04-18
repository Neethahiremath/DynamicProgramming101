package com.philomathanuj.dp;
/**
 * Problem Statement:
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 * If an element is 0, then cannot move through that element.
 * 
 * Solution:
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 8 ->9)
 * @author Anuj Sharma
 *
 */
public class MinJumpsToReachArrayEnd {
	public static void main(String[] args) {
		int[] arr = {1,3,5,8,9,2,6,7,6,8,9};
		int minJumpsRequired = findMinJumps(arr);
		minJumpsRequired = findMinJumpsRec(arr,0);
		System.out.println("The minimum number of jumps needed to reach end of array: "+minJumpsRequired);
	}
	//Recursive solution
	private static int findMinJumpsRec(int[] arr,int j) {
		if(j == arr.length) {
			return 0;
		}
		if(arr[j] == 0) {
			return Integer.MAX_VALUE;
		}
		int min = Integer.MAX_VALUE;
		for (int i = j+1; i <= arr.length && i <= j+arr[j]; i++) {
			int jumps = findMinJumpsRec(arr,i);
			min = Math.min(min,jumps+1);
		}
		return min;
	}
	// DP solution: complexity: O(n^2)
	private static int findMinJumps(int[] arr) {
		int[] S = new int[arr.length];
		// If first element is 0 then end cannot be reached.
		if(arr.length == 0 || arr[0] ==0) {
			return Integer.MAX_VALUE;
		}
		S[0] = 0;
		for (int i = 1; i < arr.length; i++) {
			S[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if(i <= j+arr[j]) {
					S[i] = Math.min(S[i],S[j]+1);
					break;
				}
			}
		}
		return S[arr.length-1];
	}
	
}
