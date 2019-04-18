package com.philomathanuj.dp;

public class MinCoinChangeProblem {
	
	public static void main(String[] args) {
		int[] coins = {1,2,3,5};
		int n = 20;
		int minCoins = minCoinsDP(coins,n);
		System.out.println("Minimum number of coins needed is : "+minCoins);
	}

	private static int minCoinsDP(int[] coins, int n) {
		int[][] cell = new int[coins.length][n+1];
		for (int i = 0; i < coins.length; i++) {
			cell[i][0] = 0;
		}
		for (int i = 0 ; i < coins.length; i++) {
			for (int j = 1; j <= n; j++) {
				if(j >= coins[i]) {
					cell[i][j] = Math.min(i > 0 ? cell[i-1][j]:Integer.MAX_VALUE,1+cell[i][j-coins[i]]);
				}
				else {
					cell[i][j] = cell[i-1][j];
				}
			}
		}
		return cell[coins.length-1][n];
	}

}
