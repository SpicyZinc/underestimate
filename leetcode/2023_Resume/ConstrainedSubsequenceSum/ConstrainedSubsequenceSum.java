/*
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

Example 1:
Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].

Constraints:
1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

idea:
SlidingWindowMaximum
*/

class ConstrainedSubsequenceSum {
    public int constrainedSubsetSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        // sum[i] means max sum till i
        int[] sum = new int[size];
        int maxSum = nums[0];

        Deque<Integer> dequeue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sum[i] = nums[i];

            if (!dequeue.isEmpty()) {
                sum[i] += sum[dequeue.peek()];
            }

            maxSum = Math.max(maxSum, sum[i]);
           
            if (!dequeue.isEmpty() && i - dequeue.peek() >= k) {
                dequeue.removeFirst();
            }
            
            while (!dequeue.isEmpty() && sum[dequeue.peekLast()] <= sum[i]) {
                dequeue.removeLast();
            }
            
            if (sum[i] > 0) {
               dequeue.offer(i);
            }
        }

        return maxSum;
    }
}
