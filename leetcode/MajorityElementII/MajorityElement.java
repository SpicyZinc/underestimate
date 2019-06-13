/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.

Hint:
How many majority elements could it possibly have?

idea:
Note: there is only at most 2 majority elements which appear over n/3 times

loop the array, num is current value
n1, n2 are potential majority elements
c1, c2 are the number of occurrence of n1 and n2
if num is equal to n1 or n2, increase c1 or c2 by 1 correspondingly
if c1 or c2 is equal to zero, which means just start looping through array
if none of the cases, keep subtracting 1 from c1 and c2,
but n1 and n2 are still holding the values which appear the most times
once c1 or c2 are zero, it is time to reset n1 or n2 to be current num

loop through the array again,
based on n1 and n2 find the exact times of their occurrence
if occurrence greater than n/3, add the num to result
*/

import java.util.*;

public class MajorityElement {
	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,2,3};
		MajorityElement eg = new MajorityElement();
		List<Integer> xx = eg.majorityElement(nums);
		System.out.println( xx.toString() );
	}

	public List<Integer> majorityElement(int[] nums) {
		List<Integer> elements = new ArrayList<Integer>();
		int m = 0, n = 0, cm = 0, cn = 0;
		for (int num : nums) {
			if (num == m) {
				cm++;             
			} else if (num == n) {
				cn++;
			} else if (cm == 0) {
				m = num;
				cm = 1;
			} else if (cn == 0) {
				n = num;
				cn = 1;
			} else {
				cm--;
				cn--;
			}
		}

		cm = cn = 0;
		for (int num : nums) {
			if (num == m) {
				cm++;
			} else if (num == n) {
				cn++;
			}
		}
		if (cm > nums.length / 3) {
			elements.add(m);
		}
		if (cn > nums.length / 3) {
			elements.add(n);
		}

		return elements;
	}
}