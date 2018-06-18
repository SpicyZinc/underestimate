/*
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

idea:
simple, find contiguous subarray of size of k with the biggest sum
sum array is n or n + 1 length
*/

public class MaximumAverageSubarray {
	public static void main(String[] args) {
		int[] nums = {1,12,-5,-6,50,3};
		int k = 4;
		MaximumAverageSubarray eg = new MaximumAverageSubarray();
		System.out.println(eg.findMaxAverage(nums, k));
	}

    public double findMaxAverage(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int maxSum = sum[k - 1];
        for (int i = k; i < nums.length; i++) {
            maxSum = Math.max(maxSum, sum[i] - sum[i - k]);
        }

        return (double) maxSum / k;
    }
    // sliding window
    public double findMaxAverage(int[] nums, int k) {
        double res = Integer.MIN_VALUE;
        double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        res = Math.max(res, sum / k);
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            res = Math.max(res, sum / k);
        }

        return res;
    }
}