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
https://www.cnblogs.com/grandyang/p/8453386.html
based on 中间这个 size of k 的 subarray [i, i + k - 1],
where k <= i <= n - 2k

posLeft[i] 起始位置 start index 在 [0, i] 且 size 为 k 得到最大left sum 的 起始位置 start index

https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108239/Java-DP-Generic-Solution-for-M-subarrays

similar to Maximum Sum of Two Non-Overlapping Subarrays
difference is dp[]
save so far max sum value
or so far to get max sum, the start index
*/
class MaximumSumOfThreeNonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int[] posLeft = new int[n];
        int[] posRight = new int[n];
        
        // accumulative sum
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        // DP for left subarray
        int leftMax = sum[k] - sum[0];
        for (int i = k; i < n; i++) {
            if (sum[i + 1] - sum[i + 1 - k] > leftMax) {
                leftMax = sum[i + 1] - sum[i + 1 - k];
                posLeft[i] = i + 1 - k;
            } else {
                posLeft[i] = posLeft[i - 1];
            }
        }

        // DP for right subarray
        // note, the condition is ">= total" for right interval, and "> total" for left interval
        // note, not forget to initialize
        int rightMax = sum[n] - sum[n - k];
        posRight[n - k - 1 + 1] = n - k - 1 + 1;
        for (int i = n - k - 1; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= rightMax) {
                rightMax = sum[i + k] - sum[i];
                posRight[i] = i;
            } else {
                posRight[i] = posRight[i + 1];
            }
        }

        int maxSum = 0;
        int[] answer = new int[3];
        // test all middle intervals i in [k, n - k * 2]
        for (int i = k; i <= n - 2 * k; i++) {
            int left = posLeft[i - 1];
            int right = posRight[i + k];
            int total = (sum[left + k] - sum[left]) + (sum[i + k] - sum[i]) + (sum[right + k] - sum[right]);

            if (total > maxSum) {
                maxSum = total;
                answer[0] = left;
                answer[1] = i;
                answer[2] = right;
            }
        }

        return answer;
    }
}
