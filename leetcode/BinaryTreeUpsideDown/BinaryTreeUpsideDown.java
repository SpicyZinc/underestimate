/*
Given a binary tree where all the right nodes
are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. 
Return the new root.

Example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
  4
 / \
5   2
   / \
  3   1

idea:
https://www.cnblogs.com/grandyang/p/5172838.html

image turn the tree clockwise by 90
dfs to the most left
left.left = right;
left.right = root;
root.left = null;
root.right = null;
*/
   
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int value) {
        this.value = value; 
    }
}

public class BinaryTreeUpsideDown {
	TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}

		TreeNode left = root.left;
		TreeNode right = root.right;

		TreeNode result = upsideDownBinaryTree(left);
		left.left = right;
		left.right = root;

		// remove left and right from root by setting to null
		root.left = null;
		root.right = null;

		return result;
	}
}