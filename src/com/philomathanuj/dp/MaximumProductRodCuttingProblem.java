package com.philomathanuj.dp;
/**
 * Problem Statement:
 * Given a rope of length n meters, cut the rope in different parts of integer lengths
 * in a way that maximizes product of lengths of all parts. You must make at least one cut.
 * Assume that the length of rope is more than 2 meters.
 * Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)

Input: n = 3
Output: 2 (Maximum obtainable product is 1*2)

Input: n = 4
Output: 4 (Maximum obtainable product is 2*2)

Input: n = 5
Output: 6 (Maximum obtainable product is 2*3)

Input: n = 10
Output: 36 (Maximum obtainable product is 3*3*4)
 * Solution:
 * We partition the rod of length n into two parts: i and n-i, but don't divide the rod
 * of length i any further.Either this cut will product the maximum product i.e. i*n-i
 * or we need to make further cuts i.e. divide n-i further recursively i.e. i*rec(n-i) where the
 * rod of length n-i is further divided.
 * maxProd(n) = max(i*(n-i), maxProdRec(n-i)*i) for all i in {1, 2, 3 .. n}
 * @author Anuj Sharma
 *
 */
public class MaximumProductRodCuttingProblem {
   public static void main(String[] args) {
	int n = 5;
	int maxProduct = maxProductRec(5);
	maxProduct = maxProductDP(5);
	System.out.println("Max product is :"+maxProduct);
}

private static int maxProductDP(int n) {
	int[] S = new int[n+1];
	S[0] = S[1] = 0;
	for (int i = 1; i <=n; i++) {
		S[i] = S[i-1];
		for (int j = 0; j < i; j++) {
			S[i] = Math.max(S[i],Math.max((i-j)*j,S[i-j]*j));
		}
	}
	return S[n];
}

private static int maxProductRec(int n) {
	if(n == 0 || n ==1) {
		return 0;
	}
	int maxValue = Integer.MIN_VALUE;
	for (int i = 1; i < n; i++) {
		maxValue = Math.max(maxValue, Math.max(i*(n-i),maxProductRec(n-i)*i));	
	}
	
	return maxValue;
}

}
