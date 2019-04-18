package com.philomathanuj.dp;

/**
 * Problem Statement: Given a boolean expression with following symbols:
 * 'T': true, 'F': false
 *  and following operators filled between symbols
 *  Operators:
 *  & : boolean And
 *  | : boolean Or
 *  ^ : boolean XOR
 *  Count the number of ways we can parenthesize the expression so that the value of 
 *  expression evalutates to true.
 *  Let the input be in form of two arrays: one contains symbols T and F "in order", and
 *  other contains operators (&,|,^)
 *  
 *  Input: symbol[] = {T,F,T}
 *  operator[] = {^,&};
 *  Output: 2
 *  The given expression is : "T^F&T"
 *  which evaluates to true in two ways:
 *  ((T^F)&T) and (T^(F&T))
 *  
 *  Solution: 
 *  Let, T(i,j) = # of ways to parenthesize symbols (such as T,F not operators) i..j, such 
 *  that this sub-expression evaluates to true.
 *  F(i,j) = # of ways to make subexpression i...j false.
 *  Ex. T or F 	  &   T 	or 	 F
 *      i...(i+1)...(i+2)........j
 *      T(2,4) = 0 => (F & T or F) = F => can never be true
 *  _____________________________________________
 *  |	|	|	|	|	|	|	|	|	|	|	|
 *  |			( i				j )
 *  |___|___|___|___|___|___|___|___|___|___|___|
 *  
 *  Base case: T(i,i) and F(i,i)
 *  Next : T(i,i+1), T(i,i+2)....
 *  and F(i,i+1), F(i,i+2)....
 *  *  |	|	|	|	|	|	|	|	|	|	|	
 *  |			( i		k)&(k+1		j )
 *  |___|___|___|___|___|___|___|___|___|___|___|
 *  
 *  
 *  As shown above number of ways of getting true with & operator is:
 *  
 *  T(i,j) = Sigma of 
 *  	= T(i,k)*T(k+1,j) ; i <= k <= j-1
 *  Similary the # ways for '|' or OR operator:
 *  = Total(i,k)Total(k+1,j)- F(i,k)F(k+1,j)
 *  This is because the only way to get a false in or is when all the operators are false.
 *  That's why if we subtract from total, all the false expressions, we'll get the number
 *  of ways to get true using or.
 *  Total(i,k) = T(i,k)+F(i,k)
 *  # of ways to get true using xor
 *  = T(i,k)F(k+1,j)+F(i,k)T(k+1,j)
 *  
 *  Note:
 *  T(i, i) = 1 if symbol[i] = 'T' 
	T(i, i) = 0 if symbol[i] = 'F' 

	F(i, i) = 1 if symbol[i] = 'F' 
	F(i, i) = 0 if symbol[i] = 'T'
 *  Note: Usually number of ways to find parenthesization expressions is using catalan numbers.
 *  
 * @author Anuj Sharma
 *
 */
public class BooleanParenthesizationProblem {
  public static void main(String[] args) {
	char[] symbol = {'T','T','F','T'};
	char[] operators = {'|','&','^'};
	int numberOfWays = countWaysToParenthesize(symbol,operators);
	System.out.println("The number of ways to parenthesize to get true: "+numberOfWays);
}

private static int countWaysToParenthesize(char[] symbol, char[] operators) {
	int[][] F = new int[symbol.length][symbol.length];
	int[][] T = new int[symbol.length][symbol.length];
	// fill all diagonal entries first
	for (int i = 0; i < symbol.length; i++) {
		F[i][i] = symbol[i] == 'F' ? 1:0;
		T[i][i] = symbol[i] == 'T' ? 1:0;
	}
	// Now fill T[i][i+1],T[i][i+2]....in order
	// F[i][i+1], F[i][i+2]....in order
	for (int gap = 1; gap < symbol.length; gap++) {
		for (int i = 0, j = gap; j < symbol.length; i++,j++) {
			T[i][j] = F[i][j] = 0;
			for (int g = 0; g < gap; g++) {
				// find the place of parenthesization using current value of gap
				int k = i+g;
				// Store Total[i,k] and Total[k+1][j]
				int tik = T[i][k]+F[i][k];
				int tkj = T[k+1][j]+F[k+1][j];
				//Follow the recursion formula as per current operators
				switch(operators[k]) {
				case '&':
					T[i][j] += T[i][k]*T[k+1][j];
					F[i][j] += tik*tkj-T[i][k]*T[k+1][j];
					break;
				case '|':
					F[i][j] += F[i][k]*F[k+1][j];
					T[i][j] += (tik*tkj)-F[i][k]*F[k+1][j];
					break;
				case '^':
					T[i][j] += T[i][k]*F[k+1][j]+F[i][k]*T[k+1][j];
					F[i][j] += T[i][k]*T[k+1][j]+F[i][k]*F[k+1][j];
					break;
				}
			}
		}
	}
	return T[0][symbol.length-1];
}
}
