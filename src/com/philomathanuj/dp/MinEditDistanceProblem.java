package com.philomathanuj.dp;

/**
 * Given two strings str1 and str2 and below operations that can performed on str1. 
 * Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
1. Insert
2. Remove
3. Replace
   All of the above operations are of equal cost.
     
    a b c d e f
      ^       ^
    a z c _ e d
    to convert above string to below string: replace b with z, delete d and finally replace f with d
    
    
 * @author Anuj Sharma
 *
 */
public class MinEditDistanceProblem {
	public static void main(String[] args) {
		String str1 = "abcdef";
		String str2 = "azced";
		int minOperationsToCovert = minEditDistance(str1,str2);
		System.out.println("Minimum Edit Distance between the two strings is :"+minOperationsToCovert);
	}

	private static int minEditDistance(String str1, String str2) {
		int[][] cell = new int[str2.length()+1][str1.length()+1];
		cell[0][0] = 0;
		for (int i = 1; i <= str2.length(); i++) {
			cell[i][0] = cell[i-1][0]+1;
		}
		for (int i = 1; i <= str1.length(); i++) {
			cell[0][i] = cell[0][i-1]+1;
		}
		for (int i = 1; i <= str2.length(); i++) {
			for (int j = 1; j <= str1.length(); j++) {
				if(str2.charAt(i-1) == str1.charAt(j-1)) {
					cell[i][j] = cell[i-1][j-1]; // if chars are same then take top left diagonal cell
				}
				else {
					cell[i][j] = 1+Math.min(cell[i-1][j-1],Math.min(cell[i-1][j],cell[i][j-1]));
					// if chars are different then take 1 plus the minimum of left,top-left diagonal and top cell
				}
			}
		}
		return cell[str2.length()][str1.length()];
	}

}
