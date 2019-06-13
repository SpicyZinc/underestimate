/*
You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf,
but it must go downwards (traveling only from parent nodes to child nodes).
The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

	  10
	 /  \
	5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

idea:
1. recursion
note: dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum)
记住可以 root root.left root.right

2. recursion + hashmap to memoization
need to go back to 2
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class PathSum {
	// method 1
    public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}

		return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	public int dfs(TreeNode node, int remaining) {
		int cnt = 0;

		if (node == null) {
			return cnt;
		}

		if (remaining == node.val) {
			cnt = 1;
		}

		cnt += dfs(node.left, remaining - node.val);       
		cnt += dfs(node.right, remaining - node.val);

		return cnt;
	}

	// method 2
	public int pathSum(TreeNode root, int sum) {
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(0, 1);

		return dfs(root, 0, sum, hm);
	}

	public int dfs(TreeNode node, int preSum, int target, Map<Integer, Integer> hm) {
		if (node == null) {
			return 0;
		}

		preSum += node.val;
		
		int cntPathToCurr = hm.getOrDefault(preSum - target, 0);

		hm.put(preSum, hm.getOrDefault(preSum, 0) + 1);
		int result = cntPathToCurr + dfs(node.left, preSum, target, hm) + dfs(node.right, preSum, target, hm);
		hm.put(preSum, hm.get(preSum) - 1);

		return result;
	}
}
