/*
You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

Example 2:
Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.

Constraints:
1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^5

idea:
left, right, right is kind of i
while loop in for loop
or while loop in while loop

TC: O(n)
SC: O(k), where k is the size of the window, to store in hashset
*/

class MaximumSumOfDistinctSubarraysWithLengthK {
    public long maximumSubarraySum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        long max = 0;
        long sum = 0;

        int left = 0;

        for (int i = 0; i < nums.length; i++) {
            while (set.contains(nums[i]) || set.size() == k) {
                set.remove(nums[left]);
                sum -= nums[left++];
            }
            sum += nums[i];
            set.add(nums[i]);
            if (set.size() == k) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        int size = nums.length;
        int left = 0;
        int right = 0;
        // note, has to be long
        long sum = 0;
        long maxSum = 0;

        Set<Integer> hs = new HashSet<>();

        for (right = 0; right < size; right++) {
            // While loop to ensure we remove the leftmost element that causes the duplicate
            // until all elements in the current window are unique
            while (hs.contains(nums[right])) {
                hs.remove(nums[left]);
                sum -= nums[left];
                left++;
            }

            sum += nums[right];
            hs.add(nums[right]);

            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, sum);
                if (hs.contains(nums[left])) {
                    hs.remove(nums[left]);
                }
                sum -= nums[left++];
                
            }
        }

        return maxSum;
    }
}
