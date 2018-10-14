/*
Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

idea:
in the process of getting max depth of the tree, get max diameter
very similar to maxDepth of a binary tree and maximum path sum of binary tree
note: return is not result

就root而言 左边的最大深度和右边的最大深度 之和就是 diameter
同时有可能 root.left or root.right diameterOfBinaryTree() has the max diameter
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class DiameterOfBinaryTree {
	// method 1
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int maxDiameter = getMaxDepth(root.left) + getMaxDepth(root.right);
		return Math.max(maxDiameter, Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
	}

    public int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = getMaxDepth(node.left);
        int right = getMaxDepth(node.right);
       
        return Math.max(left, right) + 1;
    }
    // method 2
    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = new int[] {0};
        getMaxDepth(root, maxDiameter);

        return maxDiameter[0];
    }

    public int getMaxDepth(TreeNode node, int[] maxDiameter) {
        if (node == null) {
            return 0;
        }

        int left = getMaxDepth(node.left, maxDiameter);
        int right = getMaxDepth(node.right, maxDiameter);
        maxDiameter[0] = Math.max(maxDiameter[0], left + right);

        return Math.max(left, right) + 1;
    }
}