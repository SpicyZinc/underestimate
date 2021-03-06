/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n^2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

idea:
http://blog.csdn.net/zdavb/article/details/51842977
1. naive 2 for loops O(n^2)
2. binary search
first two are all based on accumulative sum
3. https://discuss.leetcode.com/topic/33738/share-my-solution
*/

public class CountOfRangeSum {
	// Sun Jul  7 21:09:38 2019
	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		long[] sums = new long[nums.length];
		sums[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}

		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				long rangeSum = sums[j] - sums[i] + nums[i];

				if (rangeSum >= lower && rangeSum <= upper) {
					count++;
				}
			}
		}

		return count;
	}

	// binary, divide and conquer
	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}
		
		int n = nums.length;
		long[] sums = new long[n];
		sums[0] = nums[0];

		for (int i = 1; i < n; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
		
		return countNum(nums, sums, 0, n - 1, lower, upper);
	}
	
	public int countNum(int[] nums, long[] sums, int left, int right, int lower, int upper) {
		if (left == right) {
			if (nums[left] >= lower && nums[right] <= upper) {
				return 1;
			}

			return 0;
		}
		
		int count = 0;
		// lower only in left part
		// upper only in right part
		int mid = left + (right - left) / 2;
		for (int i = left; i <= mid; i++) {
			for (int j = mid + 1; j <= right; j++) {
				long sum = sums[j] - sums[i] + nums[i];

				if (sum >= lower && sum <= upper) {
					count++;
				}
			}
		}
		// both lower and upper in left + both lower and upper in right
		return count + countNum(nums, sums, left, mid, lower, upper) + countNum(nums, sums, mid + 1, right, lower, upper);
	}
}