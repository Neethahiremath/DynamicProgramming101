package com.philomathanuj.dp;

/**
 * You are given n days and for each day (di) you could either perform a high effort 
 * tasks (hi) or a low effort tasks (li) or no task with the constraint 
 * that you can choose a high-effort tasks only if you chose no task on the previous day.
 * Write a program to find the maximum amount of tasks you can perform within 
 * these n days.
 * 
 * Example:
 * No. of days (n) = 5
Day      L.E.   H.E
1        1       3
2        5       6
3        4       8
4        5       7
5        3       6
Maximum amount of tasks 
        = 3 + 5 + 4 + 5 + 3 
        = 20
        
To find the maximum amount of tasks done till i’th day, we need to compare 2 choices:

Go for high effort tasks on that day, then find the maximum amount of tasks done 
till (i – 2) th day.
Go for low effort task on that day and find the maximum amount of tasks done 
till (i – 1) th day.
Let high [1…n] be the input array for high effort task amount on i’th day and 
low [1…n] be the input array for low effort task amount on ith day.
Let max_task (high [], low [], i) be the function that returns maximum amount of task 
done till ith day, so it will return 
max(high[i] + max_task(high, low, (i – 2)), low [i] + max_task (high, low, (i – 1)))
Therefore, the problem has optimal substructure property as the problem can be solved 
using solutions to subproblems.        
 * @author Anuj Sharma
 *
 */
public class HighEffortLowEffortTasks {
	public static void main(String[] args) {
		int[] high = {1,2,3,5};
		int[] low = {4};
		int maxTasks = maxTasksRec(high,low,low.length);
		maxTasks = maxTasksDP(high,low);
		System.out.println("The maximum amount of tasks is : "+maxTasks);
	}

	private static int maxTasksDP(int[] high, int[] low) {
		int[] S = new int[high.length+1];
		S[0] = 0;// n ==0, no solution exists
		S[1] = high[0]; // n ==1, high effort task on that day will be the solution
		
		for (int i = 2; i <= low.length; i++) {
			S[i] = Math.max(high[i-1]+S[i-2],low[i-1]+S[i-1]);
		}
		return S[low.length];
	}

	private static int maxTasksRec(int[] high, int[] low,int i) {
		if(i <= 0) {
			return 0;
		}
		return Math.max(high[i-1]+maxTasksRec(high, low,i-2),low[i-1]+maxTasksRec(high, low, i-1));
	}
}
