/*
Given an integer array sorted in ascending order,
write a function to search target in nums.
If target exists, then return its index, otherwise return -1.
However, the array size is unknown to you.
You may only access the array using an ArrayReader interface,
where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000,
and if you access the array out of bounds, ArrayReader.get will return 2147483647.

Example 1:
Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:
Input: array = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

Note:
You may assume that all elements in the array are unique.
The value of each element in the array will be in the range [-9999, 9999].

idea:
binary search quintessential
因为不知道size 所以要确定right 边界
不断 * 2 来确定
其他就是 binary search
or
https://www.cnblogs.com/grandyang/p/9937770.html
*/

class SearchInASortedArrayOfUnknownSize {
	public int search(ArrayReader reader, int target) {
		int right = 1;
		while (reader.get(right) != Integer.MAX_VALUE && reader.get(right) < target) {
			right *= 2;
		}

		int left = right / 2;
		// find the first element which equals the target
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int value = reader.get(mid);
			if (value == target) {
				return mid;
			}
			if (value < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}
}
