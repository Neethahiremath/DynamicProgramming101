package com.philomathanuj.dp;

/**
 * Problem Statement: 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount
 * of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
 * have security system connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 * 
 * A[1..5] = {1,2,3,4,5}
 * We have five houses, the first house has 1 $, second house has 2 $ and so on.
 * Thief can rob 2,4
 * Can rob 1,3,5
 * Can rob 1,3 and so on
 * 
 * Solution:
 * Define R[i] as the maximum money you can steal from house 1 to house i.
 * There are two cases:
 * 1. You steal house i.
 * 2. You don't steal house i
 * If you steal house i, then you can't steal house i-1. But can steal from house 1,2,...(i-2). So if you 
 * steal from house i, then R[i] = nums[i] + max(R[i-2],R[i-3],......R[1])
 * If you don't steal from house i, then the max money you can steal is R[i-1]
 *
 * R[i] = max(R[i-1],nums[i]+ R[i-2],nums[i]+R[i-3,....,nums[i]+R[1]])
 * 
 * Base case:
 * R[1] = nums[1]
 * R[2] = max(nums[1],nums[2])
 * 
 * 
 * Input: [1,2,3,1]
   Output: 4
   Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
   Total amount you can rob = 1 + 3 = 4.
   
   Input: [2,7,9,3,1]
   Output: 12
   Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
   Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * @author Anuj Sharma
 *
 */
public class HouseRobberProblem {
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,5};
		int maxStealAmt = robRecursive(nums.length-1,nums);
		//maxStealAmt = robDP(nums.length,nums);
		System.out.println("The maximum amount a thief can steal is: "+maxStealAmt);
	}

	private static int robDP(int length, int[] nums) {
		int[] S = new int[length];
		S[0] = nums[0];
		S[1] = Math.max(nums[0],nums[1]);
		for(int i = 2; i < length; i++) {
			S[i] = S[i-1]; // Store the previous max
			for(int j = i-2; j >= 0; j--) {
				S[i] = Math.max(S[i],nums[i]+S[j]);
			}
		}
		return S[length-1];
	}
	/**
	 * A comment on the index:
	 * Since we are solving this using recursion, we need to take special care of the index.
	 * here i is the actual number of houses not array index so in terms of array index we
	 * should consider one less when refering to a house, for ex. house 1 is num[0],
	 * house 2 is num[1] and so on.
	 * Also look at the condition j > 0 which is different than j >= 0 of DP solution.
	 * Coz when j=1 is passed to robRecursive method nums[0] will be considered. However, in dp case 0th postion
	 * refers to 0th postion so j >=0 makes sense.
	 * Finally, to refer to R[i] we take nums[i-1] as i is one ahead in terms of index. To refer
	 * to house R[1], we get the money by saying R[1-1] -> R[0].
	 * @param i
	 * @param nums
	 * @return
	 */
	private static int robRecursive(int i, int[] nums) {
		if(i == 1) {
			return nums[0];
		}
		if(i == 2) {
			return Math.max(nums[0], nums[1]);
		}
		
		int maxValue = Integer.MIN_VALUE;
		maxValue = Math.max(maxValue, robRecursive(i-1, nums));
		for(int j = i-2; j > 0; j--) {
			maxValue = Math.max(maxValue,nums[i-1]+robRecursive(j, nums));
		}
		return maxValue;
	}

}
