package com.philomathanuj.dp;

/**
 * Problem Statement:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e. buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Examples:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * max difference = 6-1 = 5 (not 7-1 as the selling needs to be larger than buying price)
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * In this case no transaction is done, i.e. max_profit = 0
 * 
 * Solution: Define R[i] as the maximum amount of money you can earn from day 1 to day i. There are two cases:
 * 1. Sell at day i.
 * 2. Not Sell at day i
 * If you sell at day i, then you can find the minimum value in days 1 to i so that you make maximum profit.
 * So R[i] = Price[i] - min(Price[i],Price[i-1],..........,Price[1])
 * If you don't sell at day i, then the maximum amount of money you can make is R[i-1].
 * So,
 * R[i] = max(R[i-1], Price[i]-min(Price[i],price[i-1],....price[1]))
 * Base case,
 * R[1] = 0
 * @author Anuj Sharma
 *
 */
public class StockBuySellProblem {
	
	public static void main(String[] args) {
		int[] stockPrices = {1,2,3,5};
		//int maxProfit = maxProfitRecursive(stockPrices.length,stockPrices);
		//maxProfit = maxProfitDP(stockPrices.length,stockPrices);
		int maxProfit = maxProfitDPOptimized(stockPrices.length, stockPrices);
		System.out.println("Max profit will be :"+maxProfit);
	}
	
	// O(n) Complexity
	/**
	 * Here we improve our previous solution by storing the minValue and reusing it.
	 * @param length
	 * @param stockPrices
	 * @return
	 */
		private static int maxProfitDPOptimized(int length, int[] stockPrices) {
			// store the solutions
			int[] S = new int[length];
			// base case
			S[0] = 0;
			int minValue = stockPrices[0];
			for(int i=1; i < length; i++) {
				minValue = Math.min(minValue,stockPrices[i]);
				S[i] = Math.min(4, Math.max(S[i-1], stockPrices[i]-minValue));
			}
		
			return S[length-1];
		}
	
	// O(n^2) Complexity
		/*
		 * This solution is deficient in that we calculate the difference each time from right to left in a loop.
		 */
	private static int maxProfitDP(int length, int[] stockPrices) {
		// store the solutions
		int[] S = new int[length];
		// base case
		S[0] = 0;
		for(int i=1; i < length; i++) {
			S[i] = S[i-1];
			for(int j = 1; j <= i; j++) {
				S[i] = Math.max(S[i], stockPrices[i]- stockPrices[j-1]);
			}
		}
		
		return S[length-1];
	}
	//Exponential complexity
	private static int maxProfitRecursive(int i, int[] stockPrices) {
		if(i == 1) {
			return 0;
		}
		int maxValue = Integer.MIN_VALUE;
		maxValue = Math.max(maxValue,maxProfitRecursive(i-1, stockPrices));
		for(int j = 1; j <= i; j++) {
			maxValue = Math.max(maxValue,stockPrices[i-1]- stockPrices[j-1]);
		}
		return maxValue;
	}

}
