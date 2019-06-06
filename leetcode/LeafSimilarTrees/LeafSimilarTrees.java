/*
Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
Two binary trees are considered leaf-similar if their leaf value sequence is the same.
Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Note:
Both of the given trees will have between 1 and 100 nodes.

idea:
dfs to get leaves of a tree
then compare by equals()
*/

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class LeafSimilarTrees {
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> listOne = new ArrayList<>();
		List<Integer> listTwo = new ArrayList<>();

		getLeaves(root1, listOne);
		getLeaves(root2, listTwo);

		if (listOne.size() != listTwo.size()) {
			return false;
		}

		return listOne.equals(listTwo);
	}

	public void getLeaves(TreeNode root, List<Integer> leaves) {
		if (root.left == null && root.right == null) {
			leaves.add(root.val);
			return;
		}

		if (root.left != null) {
			getLeaves(root.left, leaves);
		}


		if (root.right != null) {
			getLeaves(root.right, leaves);
		}
	}
}