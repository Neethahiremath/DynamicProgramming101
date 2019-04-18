package com.philomathanuj.dp;

public class SubsetSumProblem {
	public static void main(String[] args) {
		int[] input = {1,2,3,5};
		int sum = 4;
		boolean isSubsetSum = isSubsetSumDP(input,sum);
		System.out.println("Is Subset Sum Possible : "+isSubsetSum);
	}

	private static boolean isSubsetSumDP(int[] input, int sum) {
		boolean[][] cell = new boolean[input.length][sum+1];
		for (int i = 0; i < cell.length; i++) {
			cell[i][0] = true;
		}

		for (int i = 0; i < input.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if(i == 0) {
					cell[i][j] = i ==j ? true : false;
				}
				else if(j >= input[i]) {
					cell[i][j] = cell[i-1][j] || cell[i-1][j-input[i]]; // top and left
				}
				else {
					cell[i][j] = cell[i-1][j];
				}
			}
		}
		return cell[input.length-1][sum];
	}

}
