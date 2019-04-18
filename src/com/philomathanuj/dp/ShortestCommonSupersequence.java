package com.philomathanuj.dp;
/**
 * Given two strings str1 and str2, find the shortest string that has both
 * str1 and str2 as subsequences.
 * Input:   str1 = "geek",  str2 = "eke"
Output: "geeke"

Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
Output:  "AGXGTXAYB"
	This problem is closely related to longest common subsequence problem. Below are steps.

1) Find Longest Common Subsequence (lcs) of two given strings. For example, lcs of “geek” and “eke” is “ek”.

2) Insert non-lcs characters (in their original order in strings) to the lcs found above, and return the result. So “ek” becomes “geeke” which is shortest common supersequence.

	Length of the shortest supersequence  = (Sum of lengths of given two strings) -
                                        (Length of LCS of two given strings) 
 * @author Anuj Sharma
 *
 */
public class ShortestCommonSupersequence {
	
	public static void main(String[] args) {
		String str1 = "abbc";
		String str2 = "bcd";
		int lcs = LongestCommonSubsequence.longestCommonSubsequence(str1.toCharArray(), str2.toCharArray(),str1.length(),str2.length());
	   int result = str1.length()+str2.length()-lcs;
	   System.out.println("The length of the shortest common supersequence is : "+result);
		
	}

}
