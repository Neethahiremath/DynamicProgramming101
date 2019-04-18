package com.philomathanuj.dp;
/**
 * 
 * Given n friends, each one can remain single or can be paired up with some other friend.
 * Each friend can be paired up only once. Find out the total number of ways in which
 * friends can remain single or can be paired up.
 * 
 * Ex. n = 3, Ans: 4
 * {1},{2},{3} all single
 * {1}, {2,3} 2,3 is paired but 1 is single.
 * {1,2}, {3}, 1,2 is paired but 3 is single
 * {1,3}, {2}, 1,3 is paried but 2 is single.
 * 
 * Solution:
 * f(n) = ways n people can remain single 
       or pair up.

	For n-th person there are two choices:
	1) n-th person remains single, we recur 
   	for f(n - 1)
	2) n-th person pairs up with any of the 
   		remaining n - 1 persons. We get (n - 1) * f(n - 2)
	Therefore we can recursively write f(n) as:
	f(n) = f(n - 1) + (n - 1) * f(n - 2)
 
 * @author Anuj Sharma
 *
 */
public class FriendsPairingProblem {
   public static void main(String[] args) {
	int n = 3;
	int pairs = findPairs(n);
	pairs = findPairsDP(n);
	System.out.println("Friends pair :: "+pairs);
}

private static int findPairsDP(int n) {
	int[] S = new int[n+1];
	S[0]=0; S[1] = 1; S[2] = 2;
	for (int i = 3; i <= n; i++) {
		S[i] = S[i-1]+(i-1)*S[i-2];
	}
	return S[n];
}

private static int findPairs(int n) {
	if(n==1) {
		return 1;
	}
	if(n==2) {
		return 2;
	}
	return findPairs(n-1)+(n-1)*findPairs(n-2);
}

}
