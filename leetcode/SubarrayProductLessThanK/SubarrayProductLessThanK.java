/*
Your are given an array of positive integers nums.
Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Note:
0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.

idea:
sliding window
right - left + 1
当right 增加1 introduce a new element, actually 增加了 right - left + 1 个 subarray
why?

example:
for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))
      (6)
   (2, 6)
(5, 2, 6)
*/

class SubarrayProductLessThanK {
	public static void main(String[] args) {
		SubarrayProductLessThanK eg = new SubarrayProductLessThanK();
		int[] nums = {10, 5, 2, 6};
		int k = 100;

		int cnt = eg.numSubarrayProductLessThanK(nums, k);
		System.out.println(cnt);
	}

	public int numSubarrayProductLessThanK(int[] nums, int k) {
		int cnt = 0;
		int product = 1;
		int left = 0;

		for (int right = 0; right < nums.length; right++) {
			product *= nums[right];

			while (right >= left && product >= k) {
				product /= nums[left];
				left++;
			}

			cnt += right - left + 1;
		}

		return cnt;
	}
}