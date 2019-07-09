/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Note:
Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.

Examples:
Input: nums = [1,2,3,4,5], m = 2
Output: 9

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5],
where the largest sum among the two subarrays is only 9.

idea:
https://www.cnblogs.com/grandyang/p/5933787.html
http://blog.csdn.net/mebiuw/article/details/52724293

这么考虑
m = nums.length 就是 每个数 单独成组 那么 这个数组的 max 不能再小
m = 1 不分组 所有数字之和 sum 不能再大
在 sum 和 max 之间 binary search

The answer is between maximum value of input array and sum of those numbers.
binary search to approach the correct answer

We have l = max number of array; r = sum of all numbers in the array;
Every time we do mid = (l + r) / 2;
Use greedy to narrow down left and right boundaries in binary search.
1 Cut the array from left.
2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still less than mid.
3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot.

If we can, it means that the mid value we pick is too small because we've already tried our best to make sure each part holds
as many non-negative numbers as we can but we still have numbers left.
So, it is impossible to cut the array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to l = mid + 1;

If we can't, it is either we successfully divide the array into m parts and the sum of each part is less than mid,
or we used up all numbers before we reach m.
Both of them mean that we should lower mid because we need to find the minimum one. This leads to r = mid - 1;
*/

public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
		long sum = 0;
		int max = 0;
		for (int num: nums) {
			max = Math.max(max, num);
			sum += num;
		}

		return (int) binarySearch(nums, m, max, sum);
	}

	// binary search
	private long binarySearch(int[] nums, int m, long low, long high) {
		while (low <= high) {
			long mid = (low + high) / 2;
			// check if such maximum "mid" value is possible
			if (valid(nums, m, mid)) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	private boolean valid(int[] nums, int m, long max) {
		int currSum = 0;
		int count = 1;
		// 是否有多余m个片段or区间, 大于给定值的max的, 如果有了, 那么就不合法了, 因为这样划分就不止m个, that is to day max passed in too small
		for (int num : nums) {
			currSum += num;

			if (currSum > max) {
				// reset currSum
				currSum = num;
				// increase the number of parts 'count' by 1
				count++;

				if (count > m) {
					return false;
				}
			}
		}

		return true;
	}
}
