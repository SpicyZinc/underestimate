/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3
Output:
1

Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7

Note: You may assume the tree (i.e., the given root node) is not NULL.

idea:
1. BFS
note: for each layer, have to poll() and offer at the same for loop of the layer
offer must add current.left and current.right, not root.left and root.right
2. DFS
recursion, h vs depth
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class FindBottomLeftTreeValue {
    // method 1
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int value = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (i == 0) {
                    value = current.val;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
        }
        return value;
    }
    // method 2
    public int findBottomLeftValue(TreeNode root) {
        int[] h = new int[1];
        int[] leftMostValue = new int[1];
        helper(root, 1, h, leftMostValue);
        return leftMostValue[0];
    }
    
    public void helper(TreeNode node, int depth, int[] h, int[] result) {
        if (h[0] < depth) {
            h[0] = depth;
            result[0] = node.val;
        }
        if (node.left != null) {
            helper(node.left, depth+1, h, result);
        }
        if (node.right != null) {
            helper(node.right, depth+1, h, result);
        }
    }
}