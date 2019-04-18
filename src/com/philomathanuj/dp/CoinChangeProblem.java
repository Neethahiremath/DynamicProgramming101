package com.philomathanuj.dp;

import java.util.Arrays;

/**
 * Problem Statement:
 * Given a value N, if we want to make change for N cents and we have an infinite supply
 * of each of S = {S1,S2,S3,...Sm} valued coins, how many ways can we make the change?
 * The order of coins doesn't matter.
 * For example:
 * N = 4, S = {1,2,3}, there are 4 solutions, 1+1+1+1,1+1+2,2+2,1+3
 * 
 * Solution:
 * To count the total number of solutions, we can divide all set solutions into two sets:
 * 1. Solutions that do not contain mth coin i.e. Sm. (n needs to be created with remaining m-1 coins)
 * 2. Solutions that contain at least one mth coin i.e. Sm. (Here, mth coin contributes a value of Sm and remaining m-1 coins should contribute for n-Sm.)
 * Let count(S[],m,n) be the function to count the number of solutions, then it can be written as:
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * count(S[],m,n) = count(S[],m-1,n)+count(S[],m,n-S[m])
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * Also note that when mth coin can be used multiple times. Say n = 10, and Sm = 5, then first time we say either we use 5 value coin or we don't.
 * So when we do use 5 value coin, we need to make remaining 10-5 = 5 value using available coins, see part 2 of solution expression.
 * Now again, same expression is computed but this time for n = 5, Sm = 5, so again we either use 5 value coin or we don't. 
 * But if we do use then we need to make n-Sm i.e. 5-5 = 0 value with remaining coins which indicates we got the result.
 * 
 * @author Anuj Sharma
 *
 */
public class CoinChangeProblem {
	public static void main(String[] args) {
		int[] S = {1,2,3,5};
		int n = 4;
		int ways = findWaysRec(S,S.length,n);
		ways = findWaysDP(S,S.length,n);
		System.out.println("The number of ways to tender change : "+ways);
	}

	private static int findWaysDP(int[] s, int m, int n) {
		if(n < 0) {
			return 0;
		}
		int[] result = new int[n+1];
		Arrays.fill(result,0);
		result[0] = 1;
		/**
		 *  Pick all coins one by one and update the result
		 */
		for (int i = 0; i < m; i++) {
			for (int j = s[i]; j <= n; j++) {
				result[j] += result[j-s[i]]; 
			}
		}
		
		return result[n];
	}

	private static int findWaysRec(int[] s,int m, int n) {
		if(n == 0) {
			return 1; // don't include any coin
		}
		if(n < 0) {
			return 0; // no solution exists
		}
		if(m <=0 && n >= 1) { // if there are no coins left and n is greater than 0, it means that no solution exists.
			return 0;
		}
		return findWaysRec(s, m-1, n)+findWaysRec(s, m, n-s[m-1]);
	}

}
