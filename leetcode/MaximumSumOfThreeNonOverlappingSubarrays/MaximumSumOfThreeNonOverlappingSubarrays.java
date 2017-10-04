/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
Return the result as a list of indices representing the starting position of each interval (0-indexed).
If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).

idea:
https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/
*/
class MaximumSumOfThreeNonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int maxSum = 0;
        int[] sum = new int[n + 1];
        int[] posLeft = new int[n];
        int[] posRight = new int[n];
        int[] answer = new int[3];
        // accumulative sum
        for (int i = 0; i < n; i++) {
        	sum[i + 1] = sum[i] + nums[i];
        }
        // DP for left subarray
        for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
        	if (sum[i + 1] - sum[i + 1 - k] > tot) {
        		posLeft[i] = i + 1 - k;
        		tot = sum[i + 1] - sum[i + 1 - k];
        	} else {
        		posLeft[i] = posLeft[i - 1];
        	}
        }
        // DP for right subarray
        // note, the condition is ">= tot" for right interval, and "> tot" for left interval
        // note, not forget initialize
        posRight[n - k] = n - k;
        for (int i = n - k - 1, tot = sum[n] - sum[n - k]; i >= 0; i--) {
        	if (sum[i + k] - sum[i] >= tot) {
        		posRight[i] = i;
        		tot = sum[i + k] - sum[i];
        	} else {
        		posRight[i] = posRight[i + 1];
        	}
        }
        // test all in term of middle interval
        for (int i = k; i <= n - 2 * k; i++) {
        	int left = posLeft[i - 1];
        	int right = posRight[i + k];
        	int tot = (sum[left + k] - sum[left]) + (sum[i + k] - sum[i]) + (sum[right + k] - sum[right]);
        	if (tot > maxSum) {
        		maxSum = tot;
        		answer[0] = left;
        		answer[1] = i;
        		answer[2] = right;
        	}
        }

        return answer;
    }
}