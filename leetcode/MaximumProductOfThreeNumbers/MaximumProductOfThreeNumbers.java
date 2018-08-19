/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6

Example 2:
Input: [1,2,3,4]
Output: 24

Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

idea:
consider negative number
find first 3 max, and first 2 min
so the max will be between max(3 max multiplication, 2 min * max)

Or, sort the array first, maxFromHead, maxFromTail two places to have possible biggest product
*/

class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
		// edge case
		if (nums.length <= 3) {
			int res = 1;
			for (int num : nums) {
				res *= num;
			}
			return res;
		}

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		for (int num : nums) {
			if (num > 0) {
				if (num > max1) {
					max3 = max2;
					max2 = max1;
					max1 = num;
				} else if (num > max2) {
					max3 = max2;
					max2 = num;
				} else if (num > max3) {
					max3 = num;
				}
			} else if (num < 0) {
				if (num < min1) {
				    min2 = min1;
					min1 = num;
				} else if (num < min2) {
					min2 = num;
				}
			}
		}

		int maxFromPositive = max1 * max2 * max3;
		int maxFromNegative = 0;
		if (min1 < 0 && min2 < 0) {
			maxFromNegative = min1 * min2 * max1;
		}

		return Math.max( maxFromPositive, maxFromNegative );
	}

	public int maximumProduct(int[] nums) {
		Arrays.sort(nums);
		int len = nums.length;
		int maxFromHead = nums[0] * nums[1] * nums[len - 1];
		int maxFromTail = nums[len - 1] * nums[len - 2] * nums[len - 3];

		return Math.max(maxFromHead, maxFromTail);
	}
}