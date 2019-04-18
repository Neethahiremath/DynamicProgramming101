package com.philomathanuj.dp;

/**
 * Partition a set into two subsets such that the difference of subset sums is minimum.
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
If there is a set S with n elements, 
then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of 
abs(sum(Subset1) – sum(Subset2)) should be minimum.

DP:
The task is to divide the set into two parts. 
We will consider the following factors for dividing it. 
Let 
  dp[n+1][sum+1] = {1 if some subset from 1st to i'th has a sum 
                      equal to j
                   0 otherwise}
    
    i ranges from {1..n}
    j ranges from {0..(sum of all elements)}  

So      
    dp[n+1][sum+1]  will be 1 if 
    1) The sum j is achieved including i'th item
    2) The sum j is achieved excluding i'th item.

Let sum of all the elements be S.  

To find Minimum sum difference, w have to find j such 
that Min{sum - j*2  : dp[n][j]  == 1 } 
    where j varies from 0 to sum/2

The idea is, sum of S1 is j and it should be closest
to sum/2, i.e., 2*j should be closest to sum.
 * @author Anuj Sharma
 *
 */
public class MinPartitionProblem {
	public static void main(String[] args) {
		int[] arr = {1,6,11,5};
		int minSubsetSum = minSubsetRec(arr);
		minSubsetSum = minSubsetDP(arr);
		System.out.println("Minimum difference of the subset sum is : "+minSubsetSum);
	}

	private static int minSubsetDP(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		boolean[][] cell = new boolean[arr.length+1][sum+1];
		// 0 is possible with all elements
		for (int i = 0; i <= arr.length; i++) {
			cell[i][0] = true;
		}
		// with 0 elements, no other sum except 0 is possible
		for (int i = 1; i <= sum; i++) {
			cell[0][i] = false;
		} 
		
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				// If ith element is excluded
				cell[i][j] = cell[i-1][j];
				// If ith element is included
				if(arr[i-1] <= j) {
				cell[i][j] = cell[i][j] || cell[i-1][j-arr[i-1]];
				}
			}
			
		}
		
		int minDifference = Integer.MAX_VALUE;
		for (int j = sum/2; j >= 0; j--) {
			if(cell[arr.length][j] == true) {
				minDifference = sum - 2*j;
				break;
			}
		}
		return minDifference;
	}

	private static int minSubsetRec(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return minSubsetRecurse(arr,arr.length,0,sum);
	}

	private static int minSubsetRecurse(int[] arr, int i, int sumCalculated, int sumTotal) {
		if(i == 0) {
			return Math.abs((sumTotal-sumCalculated)-sumCalculated);
		}
		return Math.min(minSubsetRecurse(arr, i-1, sumCalculated+arr[i-1], sumTotal),
				minSubsetRecurse(arr, i-1, sumCalculated, sumTotal));
	}

}
