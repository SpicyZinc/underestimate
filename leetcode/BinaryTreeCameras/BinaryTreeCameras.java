/*
Given a binary tree, we install cameras on the nodes of the tree.
Each camera at a node can monitor its parent, itself, and its immediate children.
Calculate the minimum number of cameras needed to monitor all nodes of the tree.

Example 1:
https://assets.leetcode.com/uploads/2018/12/29/bst_cameras_01.png

Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:
https://assets.leetcode.com/uploads/2018/12/29/bst_cameras_02.png

Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree.
The above image shows one of the valid configurations of camera placement.

Note:
The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.

idea:
https://zxi.mytechroad.com/blog/tree/leetcode-968-binary-tree-cameras/
https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS

not quite understand
三个状态 来 判断 each tree node的状态
如果是NONE  就+1

*/

// Definition for a binary tree node.
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class BinaryTreeCameras {

	enum State {
		NONE, COVERED, CAMERA
	}

	int cnt = 0;

	public int minCameraCover(TreeNode root) {
		// 没有被cover camera cnt++
		if (dfs(root) == State.NONE) {
			cnt++;
		}

		return cnt;
	}

	// dfs() to return each node State
	public State dfs(TreeNode node) {
		if (node == null) {
			return State.COVERED;
		}

		State left = dfs(node.left);
		State right = dfs(node.right);

		if (left == State.NONE || right == State.NONE) {
			cnt++;
			return State.CAMERA;
		}

		if (left == State.CAMERA || right == State.CAMERA) {
			return State.COVERED;
		}

		return State.NONE;
	}
}