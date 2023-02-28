/*
You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
Return the minimum number of cameras needed to monitor all nodes of the tree.

idea:
3 status
-1 -> camera needed (node asks it's parent to cover it)
1 -> camera not needed (node says its covered)
0 -> node has been covered (returns status to parent that it has a camera and is already covered)

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class BinaryTreeCameras {
    int cameraCount = 0;

    public int minCameraCover(TreeNode root) {
        // if root is the single element in the tree it requires a camera to be covered -> increment the camera count
        if (dfs(root) == -1) {
            cameraCount++; 
        }

        return cameraCount;
    }

    private int dfs(TreeNode root){
        // null nodes are already covered
        if (root == null) {
            return 1;
        }

        // gather info from left and right child 
        int leftStatus = dfs(root.left);
        int rightStatus = dfs(root.right);

        // check if either of the left or right is not covered and needs camera -> increment camera count
        if (leftStatus == -1 || rightStatus == -1) {
            cameraCount++;
            return 0;  //return 0 -> this node is now covered
        }
        // check if either the nodes are covered return 1
        if (leftStatus == 0 || rightStatus == 0) {
            return 1;
        }
        // else ask the parent to handle since its not covered
        return -1;
    }
}
