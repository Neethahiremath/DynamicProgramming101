package com.philomathanuj.dp;
/**
 * A bitonic sequence is a sequence that first monotonically increases then monotonically decreases.
 * 
 * 1. A bitonic sequence
 *  /\
   /  \
       \/
    2. Not a bitonic sequence   
	 /\    
	/  \  / (higher than start)
	    \/           
Obviously if the direction changes more than two times we cannot have a bitonic sequence.
If the direction changes less than two times, we must have a bitonic sequence.

Problem: Find the longest bitonic sequence in the given array.
Solution: Find the LIS from left to right, then find the LIS from right to left. Then sum the values at each
index subtracting one as you have already counted the min value 1 which gets double counted.
  lis from left to right: 
   /
  /
 /
lis from right to left
 \
  \
   \
   merging these, we'd get the longest bitonic sequence.
   /\
  /  \
 /    \ 
 * @author Anuj Sharma
 *
 */
public class LongestBitonicSubSequence {
	public static void main(String[] args) {
		int arr[]  = {2,-1,4,3,5,-1,3,2};
		int lbs = longestBitonicSubsequence(arr);
		System.out.println("The length of the longest bitonic subsequence is :"+lbs);
	}

	private static int longestBitonicSubsequence(int[] arr) {
		int[] LIS_LR = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			LIS_LR[i] = 1;
		}
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					LIS_LR[i] = Math.max(LIS_LR[i],1+LIS_LR[j]);
				}
			}
		}
		
		int[] LIS_RL = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			LIS_RL[i] = 1;
		}
		for (int i = arr.length-2; i >= 0; i--) {
			for (int j = arr.length-1; j > i; j--) {
				if(arr[j] < arr[i]) {
					LIS_RL[i] = Math.max(LIS_RL[i],1+LIS_RL[j]);
				}
			}
		}
		
		int maxValue = LIS_LR[0]+LIS_RL[0] -1;
		for (int i = 0; i < arr.length; i++) {
			if(LIS_LR[i]+LIS_RL[i]-1 > maxValue) {
				maxValue = LIS_LR[i]+LIS_RL[i]-1;
			}
		}
		return maxValue;
	}

}
