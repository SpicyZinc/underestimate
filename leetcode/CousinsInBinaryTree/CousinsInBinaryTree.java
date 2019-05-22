/*
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

Example 1:
https://assets.leetcode.com/uploads/2019/02/12/q1248-01.png
Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:
https://assets.leetcode.com/uploads/2019/02/12/q1248-02.png
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:
https://assets.leetcode.com/uploads/2019/02/13/q1248-03.png 
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 
Note:
The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.

idea:
用map
建立 node.val 和 depth 与 parent 的 map
*/


// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class CousinsInBinaryTree {
	Map<Integer, Integer> depthMap = new HashMap<>();
	Map<Integer, TreeNode> parentMap = new HashMap<>();

	public boolean isCousins(TreeNode root, int x, int y) {
		getDepthAndParent(root, null);
		getDepthAndParent(root, null);

		int xDepth = depthMap.get(x);
		int yDepth = depthMap.get(y);

		TreeNode xParent = parentMap.get(x);
		TreeNode yParent = parentMap.get(y);

		if (xDepth == yDepth && xParent != yParent) {
			return true;
		}

		return false;
	}

	public void getDepthAndParent(TreeNode node, TreeNode parent) {
		if (node != null) {
			int val = node.val;
			// update depthMap
			depthMap.put(val, parent == null ? 0 : depthMap.get(parent.val) + 1);
			// update parentMap
			parentMap.put(val, parent);

			getDepthAndParent(node.left, node);
			getDepthAndParent(node.right, node);
		}
	}
}