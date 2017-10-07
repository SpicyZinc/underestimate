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
		// int[] sum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}

		double maxAvg = 0.00001;
		for (int i = 0; i <= n - k; i++) {
			for (int j = k; i + j <= n; j++) {
				double average = (sum[i + j] - sum[i]) / j;
				if (maxAvg < average) {
					maxAvg = average;
				}
			}
		}

		return maxAvg;
	}
	// seems working as well
	public double findMaxAverage(int[] nums, int k) {
		int n = nums.length;
		int[] sums = new int[n + 1];
		for (int i = 0; i < n; ++i){
			sums[i + 1] = sums[i] + nums[i];
		}
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i + k < sums.length; i++) {
			max = Math.max(max, (sums[i + k] - sums[i]) / (k * 1.0));
		}
		return max;
	}
}