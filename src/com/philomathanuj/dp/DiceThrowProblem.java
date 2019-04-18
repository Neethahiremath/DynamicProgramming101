package com.philomathanuj.dp;

/**
 * Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. 
 * X is the summation of values on each face when all the dice are thrown.
 * 
 * Solution:
 * Let the function to find X from n dice is: Sum(m, n, X)
The function can be represented as:
Sum(m, n, X) = Finding Sum (X - 1) from (n - 1) dice plus 1 from nth dice
               + Finding Sum (X - 2) from (n - 1) dice plus 2 from nth dice
               + Finding Sum (X - 3) from (n - 1) dice plus 3 from nth dice
                  ...................................................
                  ...................................................
                  ...................................................
              + Finding Sum (X - m) from (n - 1) dice plus m from nth dice

So we can recursively write Sum(m, n, x) as following
Sum(m, n, X) = Sum(m, n - 1, X - 1) + 
               Sum(m, n - 1, X - 2) +
               .................... + 
               Sum(m, n - 1, X - m)
 * @author Anuj Sharma
 *
 */
public class DiceThrowProblem {
	public static void main(String[] args) {
		long waysToGetSumX = findWaysToGetSumX(2,2,3);
		System.out.println("The number of ways to get the desired sum is : "+waysToGetSumX);
	}

	private static long findWaysToGetSumX(int m, int n, int x) {
		long[][] cell = new long[n+1][x+1];
		//since x is the sum of values on each face when the dice is thrown if there is only one dice it would
		// get one number when thrown and there will be only one way to get that one number
		// sum i.e. j should be less than faces and also should be less than equal to desired sum
		for (int j = 1; j <= m && j <= x; j++) {
			cell[1][j] = 1;
		}
		// i: number of dice, j: sum
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= x; j++) {
				for (int k = 1; k < j && k <= m; k++) { // j-k is like X-m, i-1 is like n-1, cell[i][j] is to accumulate prev. sums
					cell[i][j] = cell[i][j]+cell[i-1][j-k]; // if j is desired sum and k is taken j-k would need to be fulfilled
				}
			}
		}
		return cell[n][x];	
	}

}
