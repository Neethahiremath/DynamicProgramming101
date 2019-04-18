package com.philomathanuj.dp;

/**
 * Catalan numbers are a sequence of natural numbers that occurs in many interesting counting problems like following.
1) Count the number of expressions containing n pairs of parentheses which are correctly matched. For n = 3, possible expressions are ((())), ()(()), ()()(), (())(), (()()).

2) Count the number of possible Binary Search Trees with n keys (See this)

3) Count the number of full binary trees (A rooted binary tree is full if every vertex has either two children or no children) with n+1 leaves.

	1 1 2 5 14 42 132 429 1430 4862
  
  Solution:
  Catalan numbers satisfy the following recursive formula.
	C0= 1, Cn+1 = Sigma(i=0 to n) => Ci*Cn-i for n >= 0
Following is the implementation of above recursive formula.
 * @author Anuj Sharma
 *
 */
public class CatalanNumberBalancedParenthesis {
	public static void main(String[] args) {
		int i = 4; // for 5th catalan number
		int n = findNthCatalanNumber(4);
		n = findNthCatalanNumberDP(4);
		System.out.println("nth catalan number is : "+n);
	}

	private static int findNthCatalanNumberDP(int n) {
		int[] S = new int[n+1];
		S[0] = 1;
		S[1] = 1;
		for (int i = 2; i <= n; i++) {
			S[i] = 0;
			for (int j = 0; j < i; j++) {
				S[i] += S[j]*S[i-1-j];
			}
		}
		return S[n];
	}

	private static int findNthCatalanNumber(int n) {
		int result = 0;
		if(n <= 1) return 1;
		for (int i = 0; i < n; i++) {
			result += findNthCatalanNumber(i)*findNthCatalanNumber(n-1-i);
		}
		return result;
	}

}
