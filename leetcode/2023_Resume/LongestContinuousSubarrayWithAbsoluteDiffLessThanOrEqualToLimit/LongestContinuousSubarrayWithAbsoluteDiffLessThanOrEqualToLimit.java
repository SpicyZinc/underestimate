/*
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:
Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
0 <= limit <= 109

idea:

similar to
keep so far max window which contains only index
SlidingWindowMaximum
SlidingWindowMinimum
*/

// Tue Feb  7 12:11:56 2023
class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        int maxLength = 1;
        int l = 0;
        // Find the longest subarray for every right pointer by shrinking left pointer
        for (int r = 0; r < nums.length; r++) {

            // Update maxDeque with new right pointer
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[r]) {
                maxDeque.removeLast();
            }
            // Update minDeque with new right pointer
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[r]) {
                minDeque.removeLast();
            }

            maxDeque.add(nums[r]);
            minDeque.add(nums[r]);

            // Shrink left pointer if exceed limit
            if (maxDeque.peek() - minDeque.peek() > limit) {
                // Before shrink, make sure max min queue should remove possible 被去掉的num
                if (maxDeque.peek() == nums[l]) maxDeque.poll();
                if (minDeque.peek() == nums[l]) minDeque.poll();
                l++;  // Shrink it by increasing the left pointer
            }

            // Update maxLength
            maxLength = r - l + 1;
        }

        return maxLength;
    }
}
