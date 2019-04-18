package com.philomathanuj.dp;
/**
 * Asked in Google:
 * ---------------------
 * Pots of gold game: Two players A & B. 
 * There are pots of gold arranged in a line, each containing some gold coins 
 * (the players can see how many coins are there in each gold pot - perfect information). 
 * They get alternating turns in which the player can pick a pot from one of the ends of the line. 
 * The winner is the player which has a higher number of coins at the end. 
 * The objective is to "maximize" the number of coins collected by A, assuming B also plays optimally. 
 * A starts the game. 
   The idea is to find an optimal strategy that makes A win knowing that B is playing optimally as well. 
   How would you do that?
   
    Variation of the problem:
    -----------------------
    Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. 
    We play a game against an opponent by alternating turns. 
    In each turn, a player selects either the first or last coin from the row, 
    removes it from the row permanently, and receives the value of the coin. 
    Determine the maximum possible amount of money we can definitely win if we move first.
    Note: The opponent is as clever as the user.
    
    Useful Comment:
    ---------------
    Say we start with an even number of pots 

	1 2 3 4 5 6 ... 2*n 

	Player A can choose the pots in such a way that he has X = 1st + 3rd + + (2n-1)th pots' gold 
	and the player B has Y = 2nd + 4th + ... + (2n)th or vice versa. 
	Since player A can choose whether to end up with X or Y, player A always wins. 

	In case the number of pots is odd, player B can follow the same strategy right after 
	player A makes first move, so the result depends on the exact amount of gold in the pots, 
	so no 100% winning strategy in this case.
	
	Addendum:
	----------
	When n is even, it is true who ever moves first can choose the odd-pot series or the even-pot series, 
	so whoever moves first wins.
    When n is odd, whoever moves first is guaranteed to lose unless the first pot can be big enough 
    to offset the difference between odd-pot series and even-pot series!
    
    Solution:
    ---------
    F(a,b) = max(min(F(a+2, b), F(a+1, b-1)), min(F(a, b-2), F(a+1,b-1)))
    the thing is, you calculate the max profit of the opponent. 
    suppose you take it from the front, your opponent can choose from F(a+2, b), F(a+1, b-1) , 
    and since the opponent plays optimally, he/she would choose max(F(a+2, b), F(a+1, b-1)), 
    which left you min(F(a+2, b), F(a+1, b-1)); 
    same logic, if you choose from the end, your opponent can choose from F(a, b-2), F(a+1,b-1), 
    and he/she would choose max(F(a, b-2), F(a+1,b-1)), which left you min(F(a, b-2), F(a+1,b-1))
    so you actually need the max left-over so you max whatever your opponent left to you, 
    which is the first formula above.
    
    Geeks Suggestion:
    ----------------
    F(i, j)  represents the maximum value the user can collect from 
         i'th coin to j'th coin.

    F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), 
                   Vj + min(F(i+1, j-1), F(i, j-2) )) 
    Base Cases
    F(i, j)  = Vi           If j == i
    F(i, j)  = max(Vi, Vj)  If j == i+1
    In the above relation, F(i+1, j-1) is calculated twice.
    
 * @author Anuj Sharma
 *
 */
public class OptimalStrategyForAGameProblem {
 public static void main(String[] args) {
	 int[] arr = {8,15,3,7};
	int maxWinnableAmount = optimalGameStrategyDP(arr);
	System.out.println("The maximum amount that player A can win is : "+maxWinnableAmount);
}

private static int optimalGameStrategyDP(int[] arr) {
	int x,y,z,i,j,groupSize;
	/* Vi_Vi+1_Vi+2_______________________Vj-2_Vj-1_Vj
	 * 1.First player selects Vi and second selects Vi+1 then first player will select either Vi+2 or Vj
	 * 2. First player selects Vi and second selects Vj then first player will select either Vi+1 or Vj-1
	 * 3. First player selects Vj and second selects Vi then first player will select either Vi+1 or Vj-1
	 * 4. First player selects Vj and second player selects Vj-1 then first will select either Vi or Vj-2
	 * x : F(i+2, j)
	 * y : F(i+1, j-1)
	 * z : F(i, j-2)
	 * // calculated diagonally one at a time, two at a time etc..
	 * when group is 0 
	 * 1	2	3	4
	 * 5	6	7	8
	 * 9	10	11	12
	 * 13	14	15	16
	 * 
	 * When groupSize is 1 then path followed is: 1,6,11,16
	 * when groupSize is 2 then path followed is : 2,7,12
	 * when groupSize is 3 the path followed is : 3,8
	 * when groupSize is 4 the path followed is : 4	
	 */
    int[][] cell = new int[arr.length][arr.length];
    for (groupSize = 0; groupSize < arr.length; groupSize++) {
    	
		for (i = 0,j = groupSize; j < arr.length; i++,j++) {
			x = i+2 <= j ? cell[i+2][j]:0;
			y = i+1 <= j-1 ? cell[i+1][j-1]:0;
			z = i <= j-2 ? cell[i][j-2]:0;
			cell[i][j] = Math.max(arr[i]+Math.min(x,y), arr[j]+Math.min(y, z));
		}
	}
	return cell[0][arr.length-1];
}
}
