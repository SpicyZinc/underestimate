/*
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
A node is deepest if it has the largest depth possible among any node in the entire tree.
The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in it's subtree.

Example 1:
Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/01/sketch1.png

We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.
 
Note:
The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.

idea:
tree has to use recursion
if left and right height the same,
then root is the node with the largest depth such that it contains all the deepest nodes in it's subtree.
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class SmallestSubtreeWithAllTheDeepestNodes {
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		if (root == null) {
			return null;
		}

		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);

		if (leftHeight == rightHeight) {
			return root;
		}

		return leftHeight < rightHeight ? subtreeWithAllDeepest(root.right) : subtreeWithAllDeepest(root.left);
	}

	public int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}

		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
}