package com.philomathanuj.dp;

/**
 * Given a rode of length n and prices P[i] for i = 1...n, where P[i] is the price of a rod with length i.
 * Find the maximum total revenue you can make by cutting and selling the rod (Assume no cost for cutting the rod)
 * length i | 1	 2  3  4  5  6  7  8  9  10
 * price Pi | 1  5  8  9  10 17 17 20 24 30
 * 
 * n = 9,
 * 1X9 = 24
 * 1 + 8 = 21
 * 4 + 5 = 19
 * ....
 * 
 *  Solution:
 *  Think of the rod this way:
 *    1  2  3                                                    n-1  n
 *  __|__|__|____________________________________________________|____|
 *  1. Cut a piece of length 1 then find the optimal solution for length n-1 -> p[1]+ Revenue_(n-1)
 *  2. Cut a piece of length 2 then find the optimal solution for length n-2 -> p[2]+ Revenue_(n-2)
 *  3. Cut a piece of length 3 then find the optimal solution for length n-3 -> p[3]+ Revenue_(n-3)
 *  ............
 *  4. Cut a piece of length n-1 then find the optimal solution for length -> p[n-1] + Revenue_1
 *  Sell in one piece -> p[n]+ Revenue_0
 *  
 *  The best choice is maximum of : p[1]+Revenue_(n-1),p[2]+Revenue_(n-2),p[2]+Revenue_(n-3)..p[n-1]+Revenue_1,p[n]
 *  Base Case R[0] = 0
 *  R[n] = max{p[1]+Revenue_(n-1),p[2]+Revenue_(n-2),p[2]+Revenue_(n-3)..p[n-1]+Revenue_1,p[n]}
 * @author Anuj Sharma
 * 
 * 
 */
public class RodCuttingProblem {
	public static void main(String[] args) {
		int[] prices = {3,1,2,3};
		int rodLength = 2;
		int r = findMaxRevenueRecursively(rodLength,prices);
		//int r = findMaxRevenueDP(rodLength,prices);
		System.out.println("Max revenue is : "+r);
	}

	private static int findMaxRevenueDP(int rodLength, int[] prices) {
		// store the results for rodLength 1...n
		int S[] = new int[rodLength];
		// base case
		S[0] = 0;
		for (int i = 0; i < rodLength; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				max = Math.max(max,prices[j]-prices[i-1-j]);
			}
			S[i] = max;
		}
		return S[rodLength-1];
	}

	private static int findMaxRevenueRecursively(int rodLength, int[] prices) {
		if(rodLength <= 0) {
			return 0;
		}
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < rodLength; i++) {
			maxValue = Math.max(maxValue,prices[i]+findMaxRevenueRecursively(rodLength-1-i, prices));
		}
		return maxValue;
	}
	
	

}
