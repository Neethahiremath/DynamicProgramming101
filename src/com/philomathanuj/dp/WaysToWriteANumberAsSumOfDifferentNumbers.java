package com.philomathanuj.dp;

/**
 * Problem: Given n, find the number of different ways to write n as the sum of 1,3,4
 * Example: for n = 5,the answer is 6.
 * 5 = 1+1+1+1+1+1
 *   = 1+1+3
 *   = 1+3+1
 *   = 3+1+1
 *   = 1+4
 *   = 4+1
 *   Solution:
 *   ◮ Define subproblems
	– Let Dn be the number of ways to write n as the sum of 1, 3, 4
	◮ Find the recurrence
	– Consider one possible solution n = x1 + x2 + · · · + xm
	– If xm = 1, the rest of the terms must sum to n − 1
	– Thus, the number of sums that end with xm = 1 is equal to Dn−1
	– Take other cases into account (xm = 3, xm = 4)
	
	◮ Recurrence is then
		Dn = Dn−1 + Dn−3 + Dn−4
	◮ Solve the base cases
		– D0 = 1
		– Dn = 0 for all negative n
		– Alternatively, can set: D0 = D1 = D2 = 1, and D3 = 2
	
 * @author Anuj Sharma
 *
 */
public class WaysToWriteANumberAsSumOfDifferentNumbers {
	public static void main(String[] args) {
		int n = 5;
		int ways = findRecursively(5);
		ways = findByDP(5);
		System.out.println("Total number of ways is: "+ways);
	}

	private static int findByDP(int n) {
		int[] S = new int[n+1];
		S[0] = 1; S[1] = 1; S[2] = 1; S[3] = 2;
		for (int i = 4; i <= n; i++) {
			S[i] = S[i-1]+S[i-3]+S[i-4];
		}
		return S[n];
	}

	private static int findRecursively(int n) {
		if(n==0) {
			return 1;
		}
		else if(n==1) { //1
			return 1;
		}
		else if(n==2) { // 1+1
			return 1;
		}
		else if(n==3) {
			return 2; // 1+1+1, 3
		}
		else if(n < 0) { // for negative numbers
			return 0;
		}
		
		return findRecursively(n-1)+findRecursively(n-3)+findRecursively(n-4);
	}

}
