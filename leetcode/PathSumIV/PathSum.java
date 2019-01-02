/*
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8.
The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.

Given a list of ascending three-digits integers representing a binary with the depth smaller than 5.
You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1
The path sum is (3 + 5) + (3 + 1) = 12.

Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1
The path sum is (3 + 1) = 4.

idea:
https://www.cnblogs.com/grandyang/p/7570954.html

[level|pos|value, level|pos|value, level|pos|value, ... , level|pos|value]
如何由上层的level and pos infer 下一层的 level and pos
int left = (level + 1) * 10 + 2 * pos - 1;
int right = left + 1;
*/

class PathSum {
	public int pathSum(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}

		int[] result = new int[1];
		Map<Integer, Integer> hm = new HashMap<>();
		for (int num : nums) {
			int levelPos = num / 10;
			int val = num % 10;

			hm.put(levelPos, val);
		}

		dfs(nums[0] / 10, 0, hm, result);

		return result[0];
	}

	public void dfs(int rootLevelPos, int currSum, Map<Integer, Integer> hm, int[] result) {
		int level = rootLevelPos / 10;
		int pos = rootLevelPos % 10;
		// 由 root 得到 left and right, key 是 level + pos 的两位数
		int left = (level + 1) * 10 + 2 * pos - 1;
		int right = left + 1;
		currSum += hm.get(rootLevelPos);

		if (!hm.containsKey(left) && !hm.containsKey(right)) {
			result[0] += currSum;
			return;
		}

		if (hm.containsKey(left)) {
			dfs(left, currSum, hm, result);
		}
		if (hm.containsKey(right)) {
			dfs(right, currSum, hm, result);
		}
	}
}