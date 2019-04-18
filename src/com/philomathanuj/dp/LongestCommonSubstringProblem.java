package com.philomathanuj.dp;

/**
 * If someone misspells a word , you want to be able to guess what word they meant. Alex is searching 
 * for fish but he accidentally put in hish. That's not a word in your dictionary but you have a list of
 * words that are similar.
 * Similar to fish: hish, vista (This is a toy example so you limit yourself to 2 words, in reality you may have
 * thousands of words.)
 * Alex typed hish. Which word did alex mean to type: fish or vista
 *    _|_H__|_I__|_S__|
 * __F_|____|____|____|
 *     |    |    |    |
 * __I_|____|____|____|
 *   S |    |    |    |
 *   H |    |    |    |
 * @author Anuj Sharma
 * Time Complexity: O(a*b)
   Auxiliary Space: O(a*b)
 */
public class LongestCommonSubstringProblem {
	
	public static void main(String[] args) {
		String s1 = "fish";
		String s2 = "hish";
		int maxLength = longestCommonSubStr(s1.toCharArray(),s2.toCharArray(),s1.length(),s2.length());
		System.out.println("The length of the longest common substring is : "+maxLength);
	}

	private static int longestCommonSubStr(char[] a, char[] b, int al, int bl) {
		int[][] cell = new int[al+1][bl+1];
		for (int i = 0; i <= al; i++) {
			for (int j = 0; j <= bl; j++) {
				if(i == 0 || j == 0) {
					cell[i][j] = 0;
				}
				else if(a[i-1] == b[j-1]) {
					cell[i][j] = cell[i-1][j-1]+1; // when characters match : top-left neighbor+1
				}
				else {
					cell[i][j] = 0; // when characters don't match
				}
			}
		}
		return cell[al][bl];
	}

}
