package com.philomathanuj.dp;

/**
 * Problem Statement:
 * For a given number n > 0, find the number of different ways in which n can be written 
 * as a sum of at two or more positive integers.
 * 
 * Input : n = 5
   Output : 6
   Explanation : All possible six ways are :
		4 + 1
		3 + 2
		3 + 1 + 1
		2 + 2 + 1
		2 + 1 + 1 + 1
		1 + 1 + 1 + 1 + 1
		
 Input n = 4
 Output: 
 		 1+1+1+1
         1+1+2
         1+3
         2+2
  Note: It's just a variation of coin change problem
 * @author Anuj Sharma
 *
 */
public class WriteNAsSumOfTwoOrMoreIntegers {
	public static void main(String[] args) {
		int n = 8;
		int ways = findWaysRecursively(n-1,n);
		System.out.println("The number of ways is : "+ways);
	}

	private static int findWaysRecursively(int i,int n) {
		if(n==0) {
			return 1;
		}
		if(n < 0) {
			return 0;
		}
		if(i <=0 && n >= 1) {
			return 0;
		}
		
		int ways = 0;
				ways = findWaysRecursively(i-1,n)+findWaysRecursively(i,n-i);
			
		
		return ways;
	}

}
