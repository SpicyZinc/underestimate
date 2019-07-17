/*
Given a sorted array consisting of only integers where every element appears twice except for one element which appears once.
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10

idea:
1. it's not hard to solve this problem with O(n)
2. solve this with binary search, O(log(n))
note: every elements appears twice except one, find the one
so the length of array should be odd number
after binary separation, the one should be in the odd number length part
need to check parity again in each separated part
http://bookshadow.com/weblog/2017/03/11/leetcode-single-element-in-a-sorted-array/
*/

public class SingleElementInASortedArray {
	public int singleNonDuplicate(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		int unique = 0;

		for (int i = 1; i < nums.length - 1; i++) {
			int prev = nums[i - 1];
			int curr = nums[i];
			int next = nums[i + 1];

			if (i == 1 && prev != curr) {
				unique = prev;
			} else if (i == nums.length - 2 && curr != next) {
				unique = next;
			} else if (prev != curr && curr != next) {
				unique = curr;
			}
		}

		return unique;
	}

	// binary search, the only one should be in the odd number length part
	public int singleNonDuplicate(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		int low = 0;
		int high = nums.length - 1;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
				return nums[mid];
			} else if (nums[mid] == nums[mid - 1]) {
				// check parity
				// 因为除了一个 其他所有都是两个
				// (mid - 2) - 0 + 1 偶数个, 只能在 后半部分
				if ((mid - 1) % 2 == 0) {
					low = mid + 1;
				} else {
					high = mid - 2;
				}
			} else if (nums[mid] == nums[mid + 1]) {
				if (mid % 2 == 0) {
					low = mid + 2;
				} else {
					high = mid - 1;
				}
			}
		}

		return nums[low];
	}
}