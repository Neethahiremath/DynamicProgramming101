package com.philomathanuj.dp;

public class LongestIncreasingSubsequenceProblem {
	public static void main(String[] args) {
		int[] arr = {3,4,-1,0,6,2,3};
		int lis = longestIncreasingSubsequence(arr);
		System.out.println("Longest Increasing Subsequence is : "+lis);
	}

	public static int longestIncreasingSubsequence(int[] arr) {
		int[] S = new int[arr.length];
		for (int i = 0; i < S.length; i++) {
			S[i] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					S[i] = Math.max(S[i],S[j]+1);
				}
			}
		}
		
		int maxValue = 1;
		for (int i = 0; i < S.length; i++) {
			if(S[i] > maxValue) {
				maxValue = S[i];
			}
		}
		return maxValue;
	}

}
