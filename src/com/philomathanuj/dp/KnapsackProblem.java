package com.philomathanuj.dp;

public class KnapsackProblem {
	public static void main(String[] args) {
		int[] val = {1,2,3,5}; // in usd for stereo,laptop and guitar
		int [] wt = {4};// in lbs for stereo, laptop and guitar
		int W = 4; // knapsack capacity
		int maxMoney = knapsackMaxValue(W,wt,val,wt.length);
		System.out.println("Max possible money that can be stolen in knapsack : "+maxMoney);
	}

	private static int knapsackMaxValue(int W, int wt[], int val[],int n) {
		int[][] cell = new int[n+1][W+1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= W; j++) {
				if(i == 0 || j == 0) {
					cell[i][j] = 0;
				}
				else if(wt[i-1] <= j) {
					cell[i][j] = Math.max(cell[i-1][j],val[i-1]+cell[i-1][j-wt[i-1]]);// value of current item+ value of remaining space 
				}
				else {
					cell[i][j] = cell[i-1][j]; // previous max
				}
			}
		}
		return cell[n][W];
	}

	
}
