package com.philomathanuj.dp;

/**
 * Suppose Alex accidentally searched for fosh. Which word did he mean fosh or fort?
 * Using lcs both have common substring 2. But we know that fosh is closest to fish as three characters match.
 * Here we need longest common subsequence.
 * 
Time Complexity: O(m*n)
Auxiliary Space: O(m*n)
 * 
 * 		A	B	A	B
0	0	0	0	0
B	0	0	1	0	1
A	0	1	0	2	0
B	0	0	2	0	3
A	0	1	0	3	0

 * @author Anuj Sharma
 *
 */
public class LongestCommonSubsequence {
	
	public static void main(String[] args) {
		String s1 = "fish";
		String s2 = "fosh";
		int maxLength = longestCommonSubsequence(s1.toCharArray(),s2.toCharArray(),s1.length(),s2.length());
		System.out.println("The length of the longest common subsequence is : "+maxLength);
	}

	public static int longestCommonSubsequence(char[] a, char[] b, int al, int bl) {
		int[][] cell = new int[al+1][bl+1];
		for (int i = 0; i <= al; i++) {
			for (int j = 0; j <= bl; j++) {
				if(i == 0 || j == 0) {
					cell[i][j]  = 0;
				}
				else if(a[i-1] == b[j-1]) {
					cell[i][j] = cell[i-1][j-1]+1; // when letters match, top-left cell plus 1
				}
				else {
					cell[i][j] = Math.max(cell[i-1][j],cell[i][j-1]); // when letters don't match take max of top or left cell
				}
			}
		}
		return cell[al][bl];
	}

}
