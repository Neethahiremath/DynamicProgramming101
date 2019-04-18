package com.philomathanuj.dp;
/**
 * Problem:  There are n number of eggs and building which has k floors. 
 * Write an algorithm to find the minimum number of drops is required to know the floor 
 * from which if egg is dropped, it will break.
 * 
 * Notes:
 * One trial is – dropping an egg once from the particular floor.
	If egg does not break after dropping, will be used again.
	If egg breaks when dropped from some floor then it will break if dropped from any higher floor.
	If egg does not break when dropped from some floor then it will not break if dropped from any lower floor.
	
	Solution:
	N eggs, k floors

Recursion:  try dropping an egg from each floor from 1 to k and calculate the minimum number of dropping needed in worst case.

Base cases –
Eggs – 1, floors – x : play safe and drop from floor 1, if egg does not break then drop from floor 2 and so on. 
So in worst case x times an egg needs to be dropped to find the solution.
Floors = 0: No trials are required.
Floors = 1: 1 trails is required.
For rest of the case, if an egg is dropped from xth floor then there are only 2 outcomes which are possible. 
Either egg will break OR egg will not break.
If Egg breaks – check the floors lower than x. So problem is reduced is n-1 eggs and x-1 floors.
If egg does not break – check the floors higher than x floors with all the n eggs are remaining. 
 So problem is reduced to n eggs and k-x floors.
(If egg doesn't break then you try for all the floors that are higher than x i.e k-x remaining floor)
 * @author Anuj Sharma
 *
 */
public class EggDroppingPuzzle {
	public static void main(String[] args) {
		// first arg is egg and second is floors
		int minDrop = getMinDropsRec(2,10);
		minDrop = getMinDropDP(2,10);
		System.out.println("The minimum number of trials needed is : "+minDrop);
	}
	// O(n^2)
	private static int getMinDropDP(int eggs, int floors) {
		int[][] cell = new int[eggs+1][floors+1];
		// if floor = 0 no drop required. If floor is 1 only 1 drop is required.
		for (int i = 1; i <= eggs; i++) {
			cell[i][0] = 0;
			cell[i][1] = 1;
		}
		// if only one egg is there then drops = floors
		for (int i = 1; i <= floors; i++) {
			cell[1][i] = i;
		}
		
		for (int i = 2; i <= eggs; i++) {
			for (int j = 2; j <= floors; j++) {
				cell[i][j] = Integer.MAX_VALUE;
				int tempResult;
				for (int k = 1; k <= j; k++) {
					tempResult = 1+Math.max(cell[i-1][k-1], cell[i][j-k]);
					cell[i][j] = Math.min(cell[i][j],tempResult);
				}
			}
		}
		// min drops required in the worst case
		return cell[eggs][floors];
	}
	// O(2^k)
	private static int getMinDropsRec(int eggs, int floors) {
		// floor is 0 then no drop needed if floor is 1 only 1 drop would be needed.
		if(floors == 0 || floors ==1) {
			return floors;
		}
		// if only one egg then you need to try every floor
		if(eggs ==1) {
			return floors;
		}
		
		int minDrops = Integer.MAX_VALUE;
		int tempResults;
		for (int i = 1; i < floors; i++) {
			tempResults = Math.max(getMinDropsRec(eggs-1,i-1),getMinDropsRec(eggs, floors-i));
			minDrops = Math.min(minDrops,tempResults);
		}
		
		return minDrops+1;
	}

}
