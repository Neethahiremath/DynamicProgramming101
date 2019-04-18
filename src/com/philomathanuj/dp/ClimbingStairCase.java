package com.philomathanuj.dp;

/**
 * Problem: You can climb 1 or 2 stairs with one step. In how many different ways can you climb n stairs
 * Solution: 
 * 1. Solve for the base cases.
 * If n = 1, then the number of ways you could climb 1 stair would be 1. You will need to use one stair in one step
 * and you cannot use 2 stairs in this case as it would exceed n.
 * 2. If n = 2, then the number of ways you could climb stairs would be: 1+1,2 i.e. 2 ways. You could climb 2 stairs
 * either 1 stair at a time or you could climb 2 stairs by jumping 2 stairs in one go.
 * 3. Now that base cases have been defined, how can we reach the nth stair:
 *   # Be at (n-1)th stair and then climb 1 step.
 *   # Be at (n-2)th stair and then climb 2 steps.(To reach he would either come from n-1 taking step or he would come from n-2 taking 2 steps)
 * f(3) = f(1)+f(2) => f(n) = f(n-1)+f(n-2)
 * which is a fibonacci sequence. For verfication purposes, here are some:
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811
 * @author Anuj Sharma
 *
 */
public class ClimbingStairCase {
	
	public static void main(String[] args) {
		int numberOfWays = noOfWaysToClimbStairsRecursively(10);
		numberOfWays = noOfWaysToClimbStairsDP(10);
		System.out.println("Number of ways to climb 10 stairs is "+numberOfWays);
	}

	private static int noOfWaysToClimbStairsDP(int n) {
		// S is the results array holding the number of ways to do climb stairs from 1..n
		int[] S = new int[n];
		// base case
		S[0] = 1;
		S[1] = 2;
		
		for (int i = 2; i < S.length; i++) {
			S[i] = S[i-1]+S[i-2];
		}
		return S[S.length-1];
	}

	private static int noOfWaysToClimbStairsRecursively(int n) {
		if(n == 1) {
			return 1;
		}
		else if(n == 2) {
			return 2; // 1+1 or 2
		}
		else {
			return noOfWaysToClimbStairsRecursively(n-1)+noOfWaysToClimbStairsRecursively(n-2);
		}
	}

}
