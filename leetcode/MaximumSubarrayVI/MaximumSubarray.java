/*
Given an array of integers. find the maximum XOR subarray value in given array.
What's the XOR: https://en.wikipedia.org/wiki/Exclusive_or

Notice
Expected time complexity O(n).

Example
Given nums = [1, 2, 3, 4], return 7
The subarray [3, 4] has maximum XOR value

Given nums = [8, 1, 2, 12, 7, 6], return 15
The subarray [1, 2, 12] has maximum XOR value

Given nums = [4, 6], return 6
The subarray [6] has maximum XOR value

idea:
direct two loops to xor each element
*/

public class MaximumSubarray {
	public static void main(String[] args) {
		MaximumSubarray eg = new MaximumSubarray();
		int[] a1 = {1,2,3,4};
		int[] a2 = {8, 1, 2, 12, 7, 6};
		int[] a3 = {4, 6};
		int xor1 = eg.maxXorSubarray(a1);
		int xor2 = eg.maxXorSubarray(a2);
		int xor3 = eg.maxXorSubarray(a3);

		System.out.println(xor1 + " " + xor2 + " " + xor3);
	}

	public int maxXorSubarray(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int xor = 0;
			for (int j = i; j < nums.length; j++) {
				xor ^= nums[j];
				max = Math.max(max, xor);
			}
		}

		return max;
	}
}