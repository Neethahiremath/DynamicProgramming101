package com.philomathanuj.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Consider a 2-D map with a horizontal river passing through its center. 
 * There are n cities on the southern bank with x-coordinates a(1) … a(n) and 
 * n cities on the northern bank with x-coordinates b(1) … b(n). 
 * You want to connect as many north-south pairs of cities as possible with bridges 
 * such that no two bridges cross. When connecting cities, you can only connect city a(i)
 * on the northern bank to city b(i) on the southern bank. Maximum number of bridges that
 * can be built to connect north-south pairs with the aforementioned constraints.
 * The values in the upper bank can be considered as the northern x-coordinates of 
 * the cities and the values in the bottom bank can be considered as the corresponding 
 * southern x-coordinates of the cities to which the northern x-coordinate city can be 
 * connected.
 * 
 * Input : 6 4 2 1
        2 3 6 5
Output : Maximum number of bridges = 2
Explanation: Let the north-south x-coordinates
be written in increasing order.

1  2  3  4  5  6
  \  \
   \  \        For the north-south pairs
    \  \       (2, 6) and (1, 5)
     \  \      the bridges can be built.
      \  \     We can consider other pairs also,
       \  \    but then only one bridge can be built 
        \  \   because more than one bridge built will
         \  \  then cross each other.
          \  \
1  2  3  4  5  6 

Input : 8 1 4 3 5 2 6 7 
        1 2 3 4 5 6 7 8
Output : Maximum number of bridges = 5

Approach: It is a variation of LIS problem. The following are the steps to solve the problem.

Sort the north-south pairs on the basis of increasing order of south x-coordinates.
If two south x-coordinates are same, then sort on the basis of increasing order of north x-coordinates.
Now find the Longest Increasing Subsequence of the north x-coordinates.
One thing to note that in the increasing subsequence a value can be greater as well as 
can be equal to its previous value.
We can also sort on the basis of north x-coordinates and find the LIS on the south x-coordinates.
 * @author Anuj Sharma
 *
 */
public class BuildingBridgesProblem {
 public static void main(String[] args) {
	int[] a = {8,1,4,3,5,2,6,7}; // north coordinates
	int[] b = {1,2,3,4,5,6,7,8}; // south coordinates
	int maxNumberOfBridges = findMaxNumberOfBridges(a,b);
	System.out.println("Maximum number of bridges is : "+maxNumberOfBridges);
}

private static int findMaxNumberOfBridges(int[] a, int[] b) {
	int[] sortedIndexOfNorthBasedOnSouth = new int[a.length];
	Map<Integer,Integer> map = new HashMap<>();
	for (int i = 0; i < a.length; i++) {
		map.put(a[i],i);
	}
	for (int i = 0; i < b.length; i++) {
		sortedIndexOfNorthBasedOnSouth[i] = map.get(b[i]);
	}
	int result = LongestIncreasingSubsequenceProblem.longestIncreasingSubsequence(sortedIndexOfNorthBasedOnSouth);
	return result;
}
}
