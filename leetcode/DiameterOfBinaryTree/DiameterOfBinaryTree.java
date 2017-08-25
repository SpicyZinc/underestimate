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
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = new int[] {0};
        getMaxDepth(root, maxDiameter);
        return maxDiameter[0];
    }
    
    public int getMaxDepth(TreeNode node, int[] maxDiameter) {
        if (node == null) return 0;
        int left = getMaxDepth(node.left, maxDiameter);
        int right = getMaxDepth(node.right, maxDiameter);
        maxDiameter[0] = Math.max(maxDiameter[0], left + right);
        return Math.max(left, right) + 1;
    }
}