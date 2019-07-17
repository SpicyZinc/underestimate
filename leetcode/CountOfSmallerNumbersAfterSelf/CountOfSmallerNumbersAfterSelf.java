/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Return the array [2, 1, 1, 0].

idea:
1. use search insert position, which contains binary search
maintain a sorted list
list.add(index, value) will push value at index to the back
从后往前来 sorted list 的插入位置就是 比这个要插入value 小的 values的个数
2. Brute force
3. construct binary search tree

  1(0, 1)
   \
   6(3, 1)
   /
 2(0, 2)
	 \
	  3(0, 1)
*/

import java.util.*;

public class CountOfSmallerNumbersAfterSelf {
	public static void main(String[] args) {
		CountOfSmallerNumbersAfterSelf eg = new CountOfSmallerNumbersAfterSelf();
		int[] nums = new int[] {5, 2, 6, 1};
		eg.countSmaller(nums);
	}

	// Tue Jul  9 11:01:16 2019
	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		Integer[] counts = new Integer[n];
		List<Integer> sorted = new ArrayList<>();
		// from back to front, this way,
		// guarantee that inserted index will the number of smaller values after inserted value
		for (int i = n - 1; i >= 0; i--) {
			int index = searchInsertPos(sorted, nums[i]);
			counts[i] = index;
			// add to sorted array at the last step
			sorted.add(index, nums[i]);
		}

		return Arrays.asList(counts);
	}

	// search insert position in a list
	public int searchInsertPos(List<Integer> nums, int target) {
		int left = 0;
		int right = nums.size() - 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return left;
	}
	// direct method, 15 / 16 test cases passed
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		if (nums.length == 0 || nums == null) {
			return list;
		}
		for (int i = 0; i < nums.length; i++) {
			list.add(countMin(nums, i));
		}

		return list;
	}

	public int countMin(int[] nums, int idx) {
		int cnt = 0;
		for (int i = idx + 1; i < nums.length; i++) {
			if (nums[i] < nums[idx]) {
				cnt++;
			}
		}

		return cnt;
	}

	// method 3, passed OJ 
	class Node {
		int val;
		int leftSmaller;
		int dup;
		Node left;
		Node right;

		public Node(int val, int leftSmaller) {
			this.val = val;
			this.leftSmaller = leftSmaller;
			this.dup = 1;
			this.left = null;
			this.right = null;
		}
	}

	public List<Integer> countSmaller(int[] nums) {
		Integer[] ans = new Integer[nums.length];

		Node root = null;
		for (int i = nums.length - 1; i >= 0; i--) {
			root = insert(root, nums[i], ans, i, 0);
		}

		return Arrays.asList(ans);
	}

	private Node insert(Node node, int num, Integer[] ans, int i, int preSum) {
		if (node == null) {
			node = new Node(num, 0);
			ans[i] = preSum;
		} else if (node.val == num) {
			node.dup++;
			ans[i] = preSum + node.leftSmaller;
		} else if (node.val > num) {
			node.leftSmaller++;
			node.left = insert(node.left, num, ans, i, preSum);
		} else {
			node.right = insert(node.right, num, ans, i, preSum + node.dup + node.leftSmaller);
		}

		return node;
	}
}
