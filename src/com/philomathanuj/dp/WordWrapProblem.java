package com.philomathanuj.dp;
/**
 * Problem Statement:
 * Given a sequence of words, and a limit on the number of characters that can be put in one line (line width). 
 * Put line breaks in the given sequence such that the lines are printed neatly. 
 * Assume that the length of each word is smaller than the line width.
   The word processors like MS Word do task of placing line breaks. The idea is to have balanced lines. In other words, not have few lines with lots of extra spaces and some lines with small amount of extra spaces.
   Comments:
   he extra spaces includes spaces put at the end of every line except the last one.  
The problem is to minimize the following total cost.
 Cost of a line = (Number of extra spaces in the line)^3
 Total Cost = Sum of costs for all lines

For example, consider the following string and line width M = 15
 "Geeks for Geeks presents word wrap problem" 
     
Following is the optimized arrangement of words in 3 lines
Geeks for Geeks
presents word
wrap problem 

The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
So optimal value of total cost is 0 + 2*2 + 3*3 = 13

  Comments:
  Please note that the total cost function is not sum of extra spaces, but sum of cubes (or square is also used) of extra spaces. The idea behind this cost function is to balance the spaces among lines. For example, consider the following two arrangement of same set of words:

1) There are 3 lines. One line has 3 extra spaces and all other lines have 0 extra spaces. Total extra spaces = 3 + 0 + 0 = 3. Total cost = 3*3*3 + 0*0*0 + 0*0*0 = 27.

2) There are 3 lines. Each of the 3 lines has one extra space. Total extra spaces = 1 + 1 + 1 = 3. Total cost = 1*1*1 + 1*1*1 + 1*1*1 = 3.

Total extra spaces are 3 in both scenarios, but second arrangement should be preferred because extra spaces are balanced in all three lines. The cost function with cubic sum serves the purpose because the value of total cost in second scenario is less.

Reference: https://www.geeksforgeeks.org/word-wrap-problem-dp-19/
This problem is also known as text justification problem.
 * @author Anuj Sharma
 *
 */
public class WordWrapProblem {
public static void main(String[] args) {
	int[] l = {3,2,2,5}; // aaa, bb,cc,ddddd
	int n = l.length; 
	int M = 6 ; // width of line
	wordWrapDP(l,n,M);
}

//l[] represents lengths of different words in input sequence. For example, 
//l[] = {3, 2, 2, 5} is for a sentence like "aaa bb cc ddddd". n is size of 
//l[] and M is line width (maximum no. of characters that can fit in a line)
private static void wordWrapDP(int[] l, int n, int M) {
	// For simple case, one extra space is used in all below arrays
	// extras[i][j] will have number of extra spaces if words from i 
    // to j are put in a single line 
	int extras[][] = new int[n+1][n+1];
    /// lc[i][j] will have cost of a line which has words from 
    // i to j 
	int[][] lc = new int[n+1][n+1];
	
	// c[i] will have total cost of optimal arrangement of words 
    // from 1 to i 
	int[] c = new int[n+1];
	
	// p[] is used to print the solution.
	int[] p = new int[n+1];
	
	// calculate extra spaces in a single line. The value extra[i][j] 
    // indicates extra spaces if words from word number i to j are 
    // placed in a single line 
	for (int i = 1; i <= n; i++) {
		extras[i][i] = M - l[i-1];
		for (int j = i+1; j <= n; j++) {
			extras[i][j] = extras[i][j-1]-l[j-1]-1; // 1 is for space b/w words and from total spaces we subtract space already consumed by words.
		}
	}
	
    // Calculate line cost corresponding to the above calculated extra 
    // spaces. The value lc[i][j] indicates cost of putting words from 
    // word number i to j in a single line 
	for (int i = 1; i <= n; i++) {
		for (int j = i; j <= n; j++) {
			if(extras[i][j] < 0) { // space is not left that means there are more words and can't fit in width
				lc[i][j] = Integer.MAX_VALUE;
			}
			else if(j == n && extras[i][j] >= 0) {// in one line all words fit in and still space is left
				lc[i][j] = 0;
			}
			else {
				lc[i][j] = extras[i][j]*extras[i][j];
			}
		}
	}
	
	// Calculate minimum cost and find minimum cost arrangement. 
    // The value c[j] indicates optimized cost to arrange words 
    // from word number 1 to j. 
	c[0] = 0;
	for (int j = 1; j <= n; j++) {
		c[j] = Integer.MAX_VALUE;
		for (int i = 1; i <= j; i++) {
			if(c[i-1] != Integer.MAX_VALUE && lc[i][j] != Integer.MAX_VALUE && 	(c[i-1]+lc[i][j] < c[j])) {
				c[j] = c[i-1]+lc[i][j];
				p[j] = i;
			}
			
			
		}
	}
	// print solution
    printSolution(p,n);
}

private static int printSolution(int[] p, int n) {
	int k; 
    if (p[n] == 1) { 
    k = 1; 
    }
    else {
    k = printSolution (p, p[n]-1) + 1; 
    }
    System.out.println("Line number" + " " + k + ": " +  
                "From word no." +" "+ p[n] + " " + "to" + " " + n); 
    return k; 
}


}
