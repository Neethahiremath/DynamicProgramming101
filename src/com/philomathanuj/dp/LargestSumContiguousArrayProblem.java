package com.philomathanuj.dp;

public class LargestSumContiguousArrayProblem {
	
	public static void main(String[] args) {
		int[] arr = {-2,-3,4,-1,-2,1,5,-3};
		int maxSubArraySum = maxSubArraySumIdeal(arr);
		maxSubArraySum = maxSubArraySumIterative(arr);
		maxSubArraySum = maxSubArraySumDP(arr);
		System.out.println("Max Sub-Array Sum is : "+maxSubArraySum);
	}
	/*
	 * Ideal DP based solution
	 */
	private static int maxSubArraySumIdeal(int[] arr) {
		int currentMax = arr[0];
		int maxSoFar = arr[0];
		for (int i = 1; i < arr.length; i++) {
			currentMax = Math.max(arr[i], arr[i]+currentMax);
			maxSoFar = Math.max(currentMax, maxSoFar);
		}
		return maxSoFar;
		}
	/**
	 * It will either end in a[i] or it won't.
	 * If it ends in a[i] then either a[i] itself would be max as in {-1,-2,-3,4} or a[i]+ (a[k] to a[i] will be 
	 * max which is nothing but S[i-1])
	 * In another case when it doesn't end in a[i], it could end anywhere from 1 to n so max of S[1],S[2]..and so on
	 * will be the solution.
	 * @param arr
	 * @return
	 */
	private static int maxSubArraySumDP(int[] arr) {
		int[] S = new int[arr.length];
		S[0] = arr[0];
		for (int i = 2; i < arr.length; i++) {
			S[i] = Math.max(arr[i]+S[i-1],arr[i]);
		}
		// max element of S will be the answer
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < S.length; i++) {
			if(S[i] > maxValue) {
				maxValue = S[i];
			}
		}
		return maxValue;
		}
	/**
	 * One way to solve is :
	 * a1,a2,a3,.....an ai -> aj
	 * consider all the combinations a1 to a2, a1 to a3, a1 to a4 then a2 to a3, a2 to a4 and so on 
	 * and then sum from a1 to a2 calling it current sum and max of this with max sum so far
	 * @param arr
	 * @return
	 */
	private static int maxSubArraySumIterative(int[] arr) {
		int maxSum = Integer.MIN_VALUE;
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				int currentSum = 0;
				for (int k = i; k < j; k++) {
				  currentSum = currentSum + arr[k];
				}
				maxSum = Math.max(maxSum,currentSum);
			}
		}
		return maxSum;
		}

}
