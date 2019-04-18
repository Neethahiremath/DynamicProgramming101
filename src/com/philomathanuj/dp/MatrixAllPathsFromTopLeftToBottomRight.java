package com.philomathanuj.dp;

/**
 * Problem Statement:
 * Given two dimensional matrix, write an algorithm to count all possible paths from top left corner 
 * to bottom-right corner. You are allowed to move only in two directions, move right OR move down.
 * 
 * Solution:
 * From every cell you will have two options to make a move, either to go right OR down.
 *  Base case will be check if you have reached to either last row OR last column 
 *  then there is only one way to reach the last cell is to travel through that row or column. x
 * @author Anuj Sharma
 *
 */
public class MatrixAllPathsFromTopLeftToBottomRight {
  public static void main(String[] args) {
	int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
	int countAllPaths = countAllPathsRec(matrix,0,0);
	countAllPaths = countAllPathsDP(matrix);
	System.out.println("The number of possible paths is : "+countAllPaths);
}

private static int countAllPathsDP(int[][] matrix) {
	int[][] cell = new int[matrix.length][matrix.length];
	
	for (int i = 0; i < cell.length; i++) {
		for (int j = 0; j < cell.length; j++) {
			if(i ==0 || j == 0) { // there will be only one way - go right or go down
				cell[i][j] = 1;
				continue;
			}
			cell[i][j] = cell[i-1][j]+cell[i][j-1];
		}
	}
	return cell[matrix.length-1][matrix.length-1];
}

private static int countAllPathsRec(int[][] matrix,int i,int j) {
	if(i == matrix.length -1 || j == matrix.length-1) {
		return 1;
	}
	
	return countAllPathsRec(matrix, i,j+1)+countAllPathsRec(matrix,i+1,j);
	}
}
