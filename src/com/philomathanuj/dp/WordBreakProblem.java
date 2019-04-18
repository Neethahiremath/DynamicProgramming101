package com.philomathanuj.dp;

import java.util.Arrays;

/**
 * Problem Statement:
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a 
 * space-separated sequence of dictionary words. (Asked in Google)
 * For example: 
 * Input: Ilikeapple
 * Output : yes, i like apple
 * 
 * Solution:
 * The idea is simple, we consider each prefix and search it in dictionary. 
 * If the prefix is present in dictionary, we recur for rest of the string (or suffix). 
 * If the recursive call for suffix returns true, we return true, otherwise we try next prefix. 
 * If we have tried all prefixes and none of them resulted in a solution, we return false.
 * @author Anuj Sharma
 *
 */
public class WordBreakProblem {
   public static void main(String[] args) {
	boolean isWordBreakPossible = wordBreakRec("ilikeapple");
	isWordBreakPossible = wordBreakDP("ilikeapple");
	System.out.println("Is word break possible for the given input string :"+isWordBreakPossible);
}
   private static boolean wordBreakDP(String str) {
	   if(str.length() == 0) {
		return true;   
	   }
	   boolean[] S = new boolean[str.length()+1];// array of solutions for str length 0 to n-1
	   for (int i = 1; i <= str.length(); i++) {
		// if S[i] is false then check if the current prefix can make it true
		   // current prefix is str.substr(0,i)
		   if(S[i] == false && isWordPresentInDictionary(str.substring(0, i))) {
			   S[i] = true;
		   }
		   // if S[i] is true then check for all substrings starting from (i+1)th character and store 
		   // their results.
		   if(S[i] == true) {
			   // IF we reached the last prefix
			   if(i == str.length()) {
				   return true;
			   }
			   for (int j = i+1; j <= str.length(); j++) {
				// if we reached the last character
				   
				   if(i == 6) {
					   System.out.println("came");
				   }
				// update S[j] if it is false and can be updated
				//not parameter passed to wordPresentMethod is substring starting from index i and length 'j-1'
				   if(S[j] == false && isWordPresentInDictionary(str.substring(i,j))) {
					   S[j] = true;
				   }
				   if(j == str.length() && S[j] == true) {
					   	return true;
				   }
			   }
		   }
	   }
	return false;
}
private static boolean wordBreakRec(String str) {
	   if(str.length() == 0) {
		   return true;
	   }
	   // try all prefix lengths from 1 to size
	   for (int i = 1; i <= str.length(); i++) {
		   // The parameter for dictionaryContains is  
	        // str.substr(0, i) which is prefix (of input  
	        // string) of length 'i'. We first check whether  
	        // current prefix is in  dictionary. Then we  
	        // recursively check for remaining string 
	        // str.substr(i, size-i) which is suffix of   
	        // length size-i 
		   if(isWordPresentInDictionary(str.substring(0,i)) && wordBreakRec(str.substring(i,str.length()-i))) {
			   return true;
		   }
	}
	return false;
}
   static boolean isWordPresentInDictionary(String word) {
	   String[] dictionary = {"i","like","apple"};
	   return Arrays.asList(dictionary).contains(word);
   }
}
