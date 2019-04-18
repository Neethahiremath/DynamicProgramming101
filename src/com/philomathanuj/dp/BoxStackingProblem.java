package com.philomathanuj.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Problem Statement:
 * ------------------------
 * There are n cuboidal (rectangular 3D) boxes. 
 * The dimensions of these boxes(length, breadth and height) are given. 
 * The objective is to stack the boxes to achieve maximum height. 
 * But you can place a box on top of another box only if its base dimensions are 
 * strictly lower than the other box. 
 * You can rotate the boxes. 
 * Also, you can use multiple instances of the same box.
 * 
 * Solution:
 * ----------
 * This problem is a variation of Longest increasing subsequence. 
 * The dimensions of the boxes are given. 
 * First, generate all the dimensions(by rotating). 
 * Then sort all these dimensions based upon the base area (l*b). 
 * Now, simply apply LIS technique on these dimensions.
 *
 * Reference: https://www.sanfoundry.com/dynamic-programming-solutions-box-stacking-problem/
 * * @author Anuj Sharma
 *
 */
public class BoxStackingProblem {
	
	public static void main(String[] args) {
	List<Box> boxes = new ArrayList<>();
	Box box1 = new Box(1,2,3);
	Box box2 = new Box(2,3,4);
	Box box3 = new Box(4,5,6);
	boxes.add(box1); boxes.add(box2); boxes.add(box3);
	int maxHeightOfBoxStack = findMaxHeightOfBoxStack(boxes);
	System.out.println("The maximum attainable stack height is : "+maxHeightOfBoxStack);
	}
	
	private static int findMaxHeightOfBoxStack(List<Box> boxes) {
		// for each box, three configurations can be achieved by rotating it so that the base of the box rests on one of its sides. 
		// for 3,4,5 example, if we wary height : {3,4,5},{4,5,3}, {3,5,4} these three config are possible so for each box we create 3 entries more
		List<Box> allConfigBoxes = new ArrayList<>(3*boxes.size());
		for (Box box : boxes) {
			// considering length has height
			int l = Math.min(box.b, box.h);
			int b = Math.max(box.b, box.h);
			int h = box.l;
			allConfigBoxes.add(new Box(l,b,h));
			// considering width as height
			 l = Math.min(box.l, box.h);
			 b = Math.max(box.l, box.h);
			 h = box.b;
			 allConfigBoxes.add(new Box(l,b,h));
			// considering height as height
			 l = Math.min(box.l, box.b);
			 b = Math.max(box.l, box.b);
			 h = box.h;
			 allConfigBoxes.add(new Box(l,b,h));
		}
		// sort the boxes based on base area
		Collections.sort(allConfigBoxes);
		// solutions array: S[i] : maximum possible height by placing ith box at the bottom.
		Integer[] S = new Integer[allConfigBoxes.size()];
		
		// initialize all the heights:: if nothing can be places on top of ith box then the height of ith box itself would be max height so
		// this is the minimum possible height we can have.
		for (int i = 0; i < S.length; i++) {
			S[i] = allConfigBoxes.get(i).h;
		}
		
		// Now we can implement the LIS approach
		for (int i = 1; i < S.length; i++) {
			for (int j = 0; j < i; j++) {
				if(allConfigBoxes.get(j).l < allConfigBoxes.get(i).l && allConfigBoxes.get(j).b < allConfigBoxes.get(i).b) {
					S[i] = Math.max(S[i],S[j]+allConfigBoxes.get(i).h);
				}
			}
		}
		
		return Collections.max(Arrays.asList(S));
	}

	static class Box implements Comparable<Box>{
		int l;
		int b; 
		int h;
		
		public Box(int l, int b, int h) {
			this.l = l;
			this.b = b;
			this.h= h;
		}
		
	    @Override
	    public int compareTo(Box box) {
	    	if(this.l*this.b >= box.l*box.b) {
	    		return 1;
	    	}
	    	else if(this.l*this.b < box.l*box.b) {
	    		return -1;
	    	}
	    	return 0;
	    }
	}

}
