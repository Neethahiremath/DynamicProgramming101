package com.philomathanuj.dp;

/**
 * Given a n*n matrix where all numbers are distinct, 
 * find the maximum length path (starting from any cell) such that all cells along the path are 
 * in increasing order with a difference of 1.
	We can move in 4 directions from a given cell (i, j), 
	i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that 
	the adjacent cells have a difference of 1.
	
	The idea is simple, we calculate longest path beginning with every cell. 
	Once we have computed longest for all cells, we return maximum of all longest paths. 
	One important observation in this approach is many overlapping subproblems. 
	Therefore this problem can be optimally solved using Dynamic Programming.
    Below is Dynamic Programming based implementation that uses a lookup table dp[][] to check 
    if a problem is already solved or not.
 * 
 * @author Anuj Sharma
 *
 */
public class LongestPathInMatrixProblem {
	static int n = 3; // this will account for 3X3 matrix
	public static void main(String[] args) {
		int[][] matrix = {{1,2,9},{5,3,8},{4,6,7}};
		int longestPath = longestPathFinder(matrix);
		System.out.println("The longest path in the matrix is :"+longestPath);
	}

	private static int longestPathFinder(int[][] matrix) {
		int result = 1;
		int[][] cell = new int[n][n];
		for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cell[i][j] = Integer.MIN_VALUE;
		}
		}
		// find longest path starting from all the cells
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(cell[i][j] == Integer.MIN_VALUE) {
					cell[i][j] = computeLongestPath(i,j,matrix,cell);
				}
				result = Math.max(result, cell[i][j]);
			}
		}
		return result;
	}

	private static int computeLongestPath(int i, int j, int[][] matrix, int[][] cell) {
		// base case
		if(i < 0 || i >=n || j <0 || j >=n) {
			return 0;
		}
		if(cell[i][j] != Integer.MIN_VALUE) {
			return cell[i][j];
		}
		/**
		 * // Since all numbers are unique and in range from 1 to n*n, 
        // there is at-most one possible direction from any cell
		 */
		// first --> direction
		if(j < n-1 && ((matrix[i][j]+1) == matrix[i][j+1])) {
			cell[i][j] = 1+computeLongestPath(i, j+1, matrix, cell);
			return cell[i][j];
		}
		// then diagonally down
		// second -> \
		// 			  \
		if(j > 0 && ((matrix[i][j]+1) == matrix[i][j-1])) {
			cell[i][j] = 1+computeLongestPath(i, j-1, matrix, cell);
			return cell[i][j];
		}
		// then ^ up direction
		if(i > 0 && ((matrix[i][j]+1) == matrix[i-1][j])) {
			cell[i][j] = 1+computeLongestPath(i-1, j, matrix, cell);
			return cell[i][j];
		}
		// then down direction
		if(i < n-1 && ((matrix[i][j]+1) == matrix[i+1][j])) {
			cell[i][j] = 1+computeLongestPath(i+1, j, matrix, cell);
			return cell[i][j];
		}
		cell[i][j] = 1;
		
		return cell[i][j];
	}

}
