/*
Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
The distance between two nodes is the number of edges on the path from one to the other.


Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
Output: 3
Explanation: There are 3 edges between 5 and 0: 5-3-1-0.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
Output: 2
Explanation: There are 2 edges between 5 and 7: 5-2-7.

Example 3:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
Output: 0
Explanation: The distance between a node and itself is 0.

Constraints:
The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
All Node.val are unique.
p and q are values in the tree.

idea:
same as StepByStepDirectionsFromABinaryTreeNodeToAnother
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
class FindDistanceInABinaryTree {
    public int findDistance(TreeNode root, int p, int q) {
        // Get LCA of start and destination node
        TreeNode lca = getLCA(root, p, q);
        
        List<String> lcaToStart = new ArrayList<>();
        List<String> lcaToDest = new ArrayList<>();
        
        // Then find path from LCA to start 
        traverse(lca, p, lcaToStart);
        // Find path from LCA to destination
        traverse(lca, q, lcaToDest);
        
        return lcaToStart.size() + lcaToDest.size();
    }
        
    // Function to get LCA of given two nodes
    TreeNode getLCA(TreeNode root, int start, int dest) {
        if (root == null) return null;

        if (root.val == start || root.val == dest) {
            return root;
        }

        TreeNode left = getLCA(root.left, start, dest);
        TreeNode right = getLCA(root.right, start, dest);

        if (left != null && right != null) {
            return root;
        }
        
        return left != null ? left : right;
    }
    
    public boolean traverse(TreeNode root, int val, List<String> path) {
        if (root == null) return false;
        
        // If node found, we have our path ready
        // So, no need to explore further
        // Simply return true from here
        if (root.val == val) return true;
        
        // Try to find node in left direction
        path.add("L");
        if (traverse(root.left, val, path)) return true; // If found, then return 
        path.remove(path.size() - 1);  // If not found, then backtrack 
        
        // Try to find node in right direction
        path.add("R");
        if (traverse(root.right, val, path)) return true; // If found, then return 
        path.remove(path.size() - 1); // If not found, then backtrack
        
        // If node is not found in any possible path, 
        // Then return false
        return false;
    }
}