/*
Given a binary search tree and a new tree node, insert the node into the tree.
You should keep the tree still be a valid binary search tree.

You can assume there is no duplicate values in this tree + node.

Example
Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6

idea:
inserted node is always at leaf position
recursion
iteration like loop linked list, use a pointer current
*/

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class InsertNodeInABinarySearchTree {
    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}
		if (node == null) {
			return root;
		}
		dfs(root, node);

		return root;
	}

	public void dfs(TreeNode root, TreeNode node) {
		if (root.val > node.val && root.left == null) {
			root.left = node;
		} else if (root.val < node.val && root.right == null) {
			root.right = node;
		} else if (root.val > node.val) {
			dfs(root.left, node);
		} else {
			dfs(root.right, node);
		}
	}

	// iteration
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}
		if (node == null) {
			return root;
		}

		TreeNode curr = root;
		TreeNode prev = null;
		while (curr != null) {
			prev = curr;
			if (curr.val > node.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

		if (prev != null) {
			if (prev.val > node.val) {
				prev.left = node;
			} else {
				prev.right = node;
			}
		}

		return root;
	}	
}