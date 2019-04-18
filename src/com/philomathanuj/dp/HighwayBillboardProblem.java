package com.philomathanuj.dp;
/**
 * Objective:  Suppose you’re managing construction of billboards on the Rocky 
 * & Bullwinkle Memorial Highway, a heavily traveled stretch of road that runs west-east 
 * for M miles. The possible sites for billboards are given by numbers 
 * x1 < x2 < · · · < xn, each in the interval [0, M], 
 * specifying their position in miles measured from the western end of the road. 
 * If you place a billboard at position xi , you receive a revenue of ri > 0.
	Regulations imposed by the Highway Department require that no two billboards be within five miles or less of each other. You’d like to place billboards at a subset of the sites so as to maximize your total revenue, subject to this restriction.
 * 
 * https://algorithms.tutorialhorizon.com/dynamic-programming-highway-billboard-problem/
 * 
 * Example:
 * int[] x = {6, 7, 12, 13, 14};
	int[] revenue = {5, 6, 5, 3, 1};
	int distance = 20;
	int milesRestriction = 5;

	Output: Maximum revenue can be generated :10 ( x1 and x3 billboard will be placed)
 * @author Anuj Sharma
 *
 */
public class HighwayBillboardProblem {
	public static void main(String[] args) {
	 int[] billboard = {6,7,12,13,14};
	 int[] revenue = {5,6,5,3,1};
	 int distance = 20;
	 int restrictionForMiles = 5;
	 int maxRevenue = maxRevenueDP(billboard,revenue,distance,restrictionForMiles);
	 System.out.println("Maximum Revenue is:"+maxRevenue);
	}

	private static int maxRevenueDP(int[] billboard, int[] revenue, int distance, int restrictionForMiles) {
		int[] MR = new int[distance+1];
		// Next billboard that can be placed, will start from index 0 in billboard[]
		int nextBillBoard = 0;
		//example if milesRes = 5 miles then any 2 bill boards has to be more than
        //5 miles away so actually we can put at 6th mile so we can add one mile milesRes
        restrictionForMiles = restrictionForMiles + 1; // actual minimum distance can be between 2 billboards
		MR[0]=0;
		for (int i = 1; i <= distance; i++) {
			// check if all the billboards are not already placed
			if(nextBillBoard < billboard.length) {
				// check if we have billboard for that particular mile
				// if not then copy the optimal solution from i-1th mile
				if(billboard[nextBillBoard] != i) {
					MR[i] = MR[i-1];
				}
				else {
					// we do have billboard for this particular mile
					// now we have two options, either place billboard or ignore it
					if(i >= restrictionForMiles) { // 5 miles restriction you subtract 6
						MR[i] =	Math.max(MR[i-restrictionForMiles]+revenue[nextBillBoard],MR[i-1]);
					}
					else {
						//there are no billboard placed prior to ith mile
                        //we will just place the billboard
						MR[i] = revenue[nextBillBoard];
					}
					 
				}
			}
			else {
				//All the billboards are already placed
                //for rest of the distance copy the previous optimal solution
				MR[i] = MR[i-1];
			}
		}
		return MR[distance];
	}
}

