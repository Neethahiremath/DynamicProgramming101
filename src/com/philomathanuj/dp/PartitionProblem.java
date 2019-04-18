package com.philomathanuj.dp;

/**
 * Partition problem is to determine whether a given set can be partitioned into two subsets 
 * such that the sum of elements in both subsets is same.
 * Let isSubsetSum(arr, n, sum/2) be the function that returns true if 
there is a subset of arr[0..n-1] with sum equal to sum/2

The isSubsetSum problem can be divided into two subproblems
 a) isSubsetSum() without considering last element 
    (reducing n to n-1)
 b) isSubsetSum considering the last element 
    (reducing sum/2 by arr[n-1] and n to n-1)
If any of the above the above subproblems return true, then return true. 
isSubsetSum (arr, n, sum/2) = isSubsetSum (arr, n-1, sum/2) ||
                              isSubsetSum (arr, n-1, sum/2 - arr[n-1])
 * @author Anuj Sharma
 *
 */
public class PartitionProblem {
	public static void main(String[] args) {
		int[] arr = {1,5,11,5};
		boolean isPartitionPossible = isPartitionPossibleRec(arr);
		isPartitionPossible = isPartitionPossibleDP(arr);
		System.out.println("Is partition possible : "+isPartitionPossible);
	}

	private static boolean isPartitionPossibleDP(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		if(sum % 2 != 0) {
			return false;
		}
		sum = sum/2;
		boolean[][] cell = new boolean[sum+1][arr.length+1];
		
		for (int i = 0; i <= arr.length; i++) {
			cell[i][0] = true;
		}
		
		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= arr.length; j++) {
				cell[i][j] = cell[i][j-1];
				if(i >= arr[j-1]) {
					cell[i][j] = cell[i][j] || cell[i-arr[j-1]][j-1];
				}
			}
		}
		return cell[sum][arr.length];
	}

	private static boolean isPartitionPossibleRec(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		if(sum % 2 != 0) {
			return false;
		}
	
		return isSubsetSum(arr,arr.length,sum/2);
	}

	private static boolean isSubsetSum(int[] arr, int n, int sum) {
		if(sum == 0) {
			return true;
		}
		if(n == 0 && sum != 0) {
			return false;
		}
		if(arr[n-1] > sum) {
			return isSubsetSum(arr,n-1,sum);
		}
		return isSubsetSum(arr,n-1,sum) || isSubsetSum(arr,n-1,sum-arr[n-1]);
	}

}
