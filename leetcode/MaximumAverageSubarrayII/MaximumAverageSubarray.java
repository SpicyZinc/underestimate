/*
Given an array consisting of n integers,
find the contiguous subarray whose length is greater than or equal to k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.

Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10^-5 will be accepted.

idea:
it is said binary search is good way to solve it, but I did not understand it
use accumulative sum array, find subarray of length greater than or equal to k, compare and get maxAvg

need to come back for binary search
https://leetcode.com/problems/maximum-average-subarray-ii/discuss/105477/C%2B%2B-Clean-binary-search-solution-with-explanation
*/
class MaximumAverageSubarray {
	public static void main(String[] args) {
		MaximumAverageSubarray eg = new MaximumAverageSubarray();
		int[] nums = {1,12,-5,-6,50,3};
		int k = 4;
		double max = eg.findMaxAverage(nums, k);
		System.out.println(max);
	}

	public double findMaxAverage(int[] nums, int k) {
		int n = nums.length;
		double[] sum = new double[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}

		double maxAvg = Double.NEGATIVE_INFINITY;

		for (int i = 0; i <= n - k; i++) {
			for (int len = k; i + len <= n; len++) {
				double average = (sum[i + len] - sum[i]) / len;
				maxAvg = Math.max(maxAvg, average);
			}
		}

		return maxAvg;
	}

	// binary search
	public double findMaxAverage(int[] nums, int k) {
		double start = nums[0];
		double end = nums[0];

		for (int i = 0; i < nums.length; i++) {
			start = Math.min(nums[i], start);
			end = Math.max(nums[i], end);
		}

		while (start + 0.00001 < end) {
			double mid = (start + end) / 2;

			if (canFind(nums, k, mid)) {
				start = mid;
			} else {
				end = mid;
			}
		}

		return start;
	}

	private boolean canFind(int[] nums, int k, double avg) {
		int i = 0;
		double rightSum = 0;
		double leftSum = 0;
		double minLeftSum = 0;

		for (i = 0; i < k; i++) {
			rightSum += nums[i] - avg;
		}

		for (i = k; i <= nums.length; ++i) {
			if (rightSum - minLeftSum >= 0) {
				return true;
			}

			if (i < nums.length) {
				rightSum += nums[i] - avg;
				leftSum += nums[i - k] - avg;
				minLeftSum = Math.min(minLeftSum, leftSum);
			}
		}

		return false;
	}
}