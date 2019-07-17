/*
Given an unsorted integer array, find the first missing positive integer.

[1,2,0] return 3
[3,4,-1,1] return 2

Your algorithm should run in O(n) time and uses constant space. 

idea:
1. 
constant space requires to use the array
change array to be like 1 2 3 in position 0 1 2

once find A[i] != i + 1, return i + 1; 
that is the first missing positive

The result is 1  2  3  4  2  -1  0, all sorted elements are placed at fore part of the array

2. direct method,
sort first
*/
import java.util.*;

public class FirstMissingPositive {
	public static void main(String[] args) {
		FirstMissingPositive eg = new FirstMissingPositive();
		int[] A = {3, 2, 0, 2, 4, -1, 1};
		
		System.out.println("\nA_1stMissingPositive == " + eg.firstMissingPositive(A));
	}
	// note, use while
	// 01/22/2019
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;

		for (int i = 0; i < n; i++) {
			// only care about positive number and <= n number
			while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
				swap(nums, i, nums[i] - 1);
			}
		}

		for (int i = 0; i < n; i++) {
			if (i + 1 != nums[i]) {
				return i + 1;
			}
		}

		return n + 1;
	}
	// put i to A[i - 1], so the array looks like: 1, 2, 3, ...
	public int firstMissingPositive(int[] A) { 
		for (int i = 0; i < A.length; i++) {
			while (A[i] != i + 1) {
				// if ever meeting one of the three circumstances, break while loop and keep walking to next element in the array
				// 1. negative
				// 2. A[i] > A.length
				// 3. already value as index being there
				if (A[i] <= 0 || A[i] > A.length || A[i] == A[A[i] - 1]) {
					break;
				}
				swap(A, A[i] - 1, i);
			}
		}
 
		for (int i = 0; i < A.length; i++) {
			// the 1st missing positive number at the place where A[i] != i + 1
			if (A[i] != i + 1) {
				return i + 1;
			}
		}
		// if this array is want 1, 2, 3 in index 0, 1, 2, then return (A.length + 1)
		return A.length + 1;         
	}
	
	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// similar to the above, except && === !||
	public int firstMissingPositive(int[] A) {
		int i = 0;
		while (i < A.length) {
			if (A[i] != i + 1 && A[i] >= 1 && A[i] <= A.length && A[A[i] - 1] != A[i]) {
				swap(A, A[i] - 1, i);
			} else {
				i++;
			}
		}
		for (i = 0; i < A.length; i++) {
			if (A[i] != i + 1) {
				return i + 1;
			}
		}

		return A.length + 1;
	}

	// direct method
	public int firstMissingPositive(int[] A) {
		if (A.length == 0 || A == null) {
			return 1;
		}

		Arrays.sort(A);
		int i = 0;
		while (i < A.length && A[i] <= 0) {
			i++;                        
		}
		// missing positive, starting from 1
		int result = 0;
		for (; i < A.length; i++) {
			// skip repetitive elements
			if (i > 0 && A[i] == A[i - 1]) {
				continue;
			}
			// first positive number should be 1
			if (A[i] != result + 1) {
				return result + 1;
			} else {
				result = A[i];
			}
		}

		return result + 1;
	}
}