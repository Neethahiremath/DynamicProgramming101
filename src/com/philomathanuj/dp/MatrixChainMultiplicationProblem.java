package com.philomathanuj.dp;

/**
 * Problem Statement:
 * Given a sequence of matrices, find the most efficient way to multiply these matrices
 * together. The problem is not actually to perform the multiplications, but merely to 
 * decide in which order to perform multiplications.
 * We have many options to multiply a chain of matrices because matrix mulitiplication
 * is associative. In other words, no matter how we parenthesize the product, the result
 * will be the same. For example, if we had 4 matrices, A,B,C and D: we could have
 * (ABC)D = (AB)(CD) = A(BCD)
 * However, the order in which we parenthesize the product affects the number of simple
 * arithmetic operations needed to compute the product, or the efficiency.
 * Suppose matrix A is : 10X20
 * matrix B is : 20X30
 * matrix C is : 30X40
 * then: 
 * (AB)C = 10x20X30+10x30x40 = 18000 
 * A(BC) = 10x20x40+20x30x40 = 32000, 
 * therefore, it would be better to adopt approch 1.
 * 
 * Given an array p[] which represents the chain of matrices such that the ith matrix Ai
 * is of dimension p[i-1]Xp[i]. We need to write a function matrixChainOrder that should
 * return the minimum number of multiplications needed to multiply the chain.
 * 
 * Solution with helpful comments:
 * 1. For matrix multiplication, the number of columns in the first matrix should be equal
 * to the number of rows in the second matrix.
 * 2. when we multiply mxn matrix with nxk matrix then the dimension of the resultant matrix
 * would be mxk
 * 3. n multiplications take place for one cell, so how many multiplications will take place
 * for mxk cells : mxkxn = mxnxk which is actually the cost of matrix multiplication.
 * 4. l = 0, individual matrix; A0,A1,A2,A3..
 *    l = 1, group of two matrices; A0A1, A1A2, A2A3
 *    l= 2 group of three matrices, A0A1A2,A1A2A3...
 *    We solve it diagonally using DP grid.
 *    
 *   M[i,j] = 0 if i = j; when only one matrix no multiplication needed;
 *   		= min
 *   			 i <= k < j {	M[i,k]+ M[k+1,j]+ P(i)xP(k+1)xP(j+1)	}
 *   A0 = {3,4}, A1 = {4,5}, A2= {5,6}, A3 = {6,2}, A4 = {2,5}
 *   
 *   k is the partion point.
 *   A0 is i matrix so last element Pi for dimnesion i-1 being first element
 *   A1 is kth matrix as its the parition point so last element being k+1 
 *   A2 is the jth matrix as the last matrix and last element of this being j+1
 *   which explains the last product.
 *   A0(A1A2), A0 being [3,4] and A1A2 being [4,5],[5,6] the result dimension of A1A2 is 4x6
 *   . Now A0 and result dimension is sorted using last product of expression. 3x4x6.
 *   
 *   Input: p[] = {10, 20, 30}  
  Output: 6000  
  There are only two matrices of dimensions 10x20 and 20x30. So there 
  is only one way to multiply the matrices, cost of which is 10*20*30
 * 
 * @author Anuj Sharma
 *
 */
public class MatrixChainMultiplicationProblem {
	public static void main(String[] args) {
		int[] p = {10,20,30};
		int result = matrixChainOrder(p,1,p.length-1);
		result = matrixChainOrderDP(p);
		System.out.println("The minimum number of operations : "+result);
	}
	private static int matrixChainOrderDP(int[] p) {
		int[][] cell = new int[p.length][p.length];
		int i,j,k,l,S;
		// for one matrix no operations
		for (i = 1;  i < cell.length; i++) {
			cell[i][i] = 0;
		}
		// l is group size
		for (l = 2; l < cell.length; l++) {
			for (i = 1; i < cell.length-l+1; i++) {
				j = i+l-1;
				if(j == cell.length) {
					continue;
				}
				cell[i][j] = Integer.MAX_VALUE;
				
				for ( k = i; k <= j-1; k++) {
					cell[i][j] = Math.min(cell[i][j],cell[i][k]+cell[k+1][j]+p[i-1]*p[k]*p[j]);
				}
				
			}
		}
		return cell[1][cell.length-1];
	}
	// Matrix Ai has dimension p[i-1]xp[i] for i = 1..n
	
	private static int matrixChainOrder(int[] p, int i, int j) {
		if(i == j) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		/*
		 * Place parenthesis at different places between first and last matrix,
		 * recursively calculate the count of multiplications for each parenthesis 
		 * placement and return the minimum count
		 */
		for (int k = i; k < j; k++) {
			min = Math.min(min, matrixChainOrder(p, i, k)+ matrixChainOrder(p,k+1, j)+p[i-1]*p[k]*p[j]);
		}
		return min;
	}
}
