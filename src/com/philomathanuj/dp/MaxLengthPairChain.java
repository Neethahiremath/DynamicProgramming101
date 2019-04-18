package com.philomathanuj.dp;

import java.util.ArrayList;
import java.util.List;
/**
 * Problem Statement:
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the 
 * second number.
 * Now we define a pair (c,d) can follow another pair (a,b) if and only if b < c. Chain of pairs can be
 * formed in this fashion.
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the 
 * given pairs. You can select pairs in any order.
 * 
 * Input: [1,2], [2,3], [3,4]
 * Output: 2
 * The longest chain is : [1,2] -> [3,4]
 * 
 * Solution: First note that if (a,b) is before (c,d) then a < b < c < d
 * 1. Sort all the pairs.
 * 2. After sorting define R[i] as the longest chain from pair 1 to pair i
 * Two cases:
 * 1. The longest chain ends in pair i.
 * 2. The longest chain does not end in pair i.
 * For 1. then R[i] = max(1+R[a],1+R[b],1+R[c],....) where a,b,c fit the ith pair.
 * ________(1)_________(b)_____(a)_______(i-1)__i
 * when the pair ends in i, then starting 1 we check the pair that satisfies the ith pair condition which means
 * that it follows ith pair i.e. its second element of pair is less than the first element of ith pair.
 * Since we know pair ends at i, we have already considered that pair so we add 1, then from 0th position or
 * first pair from left we check for all the pairs that satisfy the condition and keep track of the longest
 * pair chain.
 * 
 * For 2, then R[i] = R[i-1]
 * 
 * Combining both we get,
 * R[i] = max(R[i], 1+R[a],1+R[b],...)
 * Base case: R[i] = 1
 * 
 * @author Anuj Sharma
 *
 */
public class MaxLengthPairChain {
	
	public static void main(String[] args) {
		List<List<Integer>> pairsSet = new ArrayList<List<Integer>>(); 
		List<Integer> pair1 = new ArrayList<>();
		List<Integer> pair2 = new ArrayList<>();
		List<Integer> pair3 = new ArrayList<>();
		List<Integer> pair4 = new ArrayList<>();
		List<Integer> pair5 = new ArrayList<>();
		List<Integer> pair6 = new ArrayList<>();
		pair1.add(1); pair1.add(2);
		pair2.add(2); pair2.add(3);
		pair3.add(2); pair3.add(6);
		pair4.add(3); pair4.add(4);
		pair5.add(4); pair5.add(5);
		pair6.add(6); pair6.add(7);
		pairsSet.add(pair1); pairsSet.add(pair2);pairsSet.add(pair3);pairsSet.add(pair4);pairsSet.add(pair5);pairsSet.add(pair6);
		int maxPairs = maxLengthPairChainRecursive(pairsSet.size(),pairsSet);
		maxPairs = maxLengthPairChainDP(pairsSet.size(),pairsSet);
		System.out.println("Max pair chain length is :"+maxPairs);
				
	}

	private static int maxLengthPairChainDP(int size, List<List<Integer>> pairsSet) {
		int[] S = new int[size];
		S[0] = 1;
		for ( int i = 1; i < S.length; i++) {
			S[i] = S[i-1];
			for(int j = 0; j <= i ; j++) {
				if(pairsSet.get(j).get(1) < pairsSet.get(i).get(0)) {
				S[i] = Math.max(S[i],1+S[j+1]);	
				}
			}
		}	
		return S[size-1];
	}

	private static int maxLengthPairChainRecursive(int i, List<List<Integer>> pairsSet) {
		if(i == 1) {
			return 1;
		}
		int maxValue = Integer.MIN_VALUE;
		maxValue = Math.max(maxValue,maxLengthPairChainRecursive(i-1, pairsSet));
		for (int j = i; j > 0; j--) {
			if(pairsSet.get(j-1).get(1) < pairsSet.get(i-1).get(0)) {
				maxValue = Math.max(maxValue,1+ maxLengthPairChainRecursive(i-1, pairsSet));
			}
		}
		return maxValue;
	}

}
