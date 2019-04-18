package com.philomathanuj.dp;

/**
 * Problem Statement: 
 * Given a text and wild card pattern, implement wild-card pattern matching algorithm that
 * finds if wild card pattern is matched with text.The matching should cover the entire text
 * not just partial text.
 * The wild card pattern can include the characters '?' and '*'.
 * '?' - Matches any single character
 * '*' - Matches any sequence of characters (including the empty space)
 * 
 * 	Text = "baaabab",
	Pattern = “*****ba*****ab", output : true
	Pattern = "baaa?ab", output : true
	Pattern = "ba*a?", output : true
	Pattern = "a*ab", output : false
	
	For recursive solution: https://www.techiedelight.com/wildcard-pattern-matching/
	
	 Solution:
	 // If current characters match, result is same as 
// result for lengths minus one. Characters match
// in two cases:
// a) If pattern character is '?' then it matches  
//    with any character of text. 
// b) If current characters in both match
if ( pattern[j – 1] == ‘?’) || 
     (pattern[j – 1] == text[i - 1])
    T[i][j] = T[i-1][j-1]   
 
// If we encounter ‘*’, two choices are possible-
// a) We ignore ‘*’ character and move to next 
//    character in the pattern, i.e., ‘*’ 
//    indicates an empty sequence.
// b) '*' character matches with ith character in
//     input 
else if (pattern[j – 1] == ‘*’)
    T[i][j] = T[i][j-1] || T[i-1][j]  

else // if (pattern[j – 1] != text[i - 1])
    T[i][j]  = false 
 
 * @author Anuj Sharma
 *
 */
public class WildCardPatternMatching {
	public static void main(String[] args) {
		String str = "xyzxt";
		String pattern = "x?z*";
		boolean matchStatus = wildCardMatch(str,pattern,str.length(),pattern.length());
		System.out.println("Pattern Match Status is: "+matchStatus);
	}

	private static boolean wildCardMatch(String str, String pattern, int sl, int pl) {
		if(pl == 0 && sl == 0) {
			return true; // empty pattern will match will empty string
		}
		boolean[][] cell = new boolean[sl+1][pl+1]; // row: string col: pattern
		
		cell[0][0] = true; // empty string will match empty pattern
		// only * can match with empty string
		// if str = 0 and pattern = * then true as [0][0] holds true
		// if pattern = ***** then true all * would be considered true
		// if ***x then only till third start would be considered true.
		for (int j = 1; j <= pl; j++) {
			if(pattern.charAt(j-1) == '*') {
				cell[0][j] = cell[0][j-1];
			}
		}
		
		 // Two cases if we see a '*' 
        // a) We ignore '*'' character and move 
        //    to next  character in the pattern, 
        //     i.e., '*' indicates an empty sequence. 
        // b) '*' character matches with ith 
        //     character in input 
		for (int i = 1; i <= sl; i++) {
			for (int j = 1; j <= pl; j++) {
				if(pattern.charAt(j-1) == '*') {
					cell[i][j] = cell[i][j-1] || cell[i-1][j]; // left cell or top cell
				}
				else if(pattern.charAt(j-1) == '?' || str.charAt(j-1) == pattern.charAt(j-1)) {
					cell[i][j] = cell[i-1][j-1]; // Diagonal cell // left-top
				}
				else {
					cell[i][j] = false;
				}
			}
		}
		
		
		return cell[sl][pl];
	}

}
