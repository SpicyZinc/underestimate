/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

 
Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:
The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue

idea:
1. LCA
2. Traverse
all DFS
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

class StepByStepDirectionsFromABinaryTreeNodeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // Get LCA of start and destination node
        TreeNode lca = getLCA(root, startValue, destValue);
        
        List<String> lcaToStart = new ArrayList<>();
        List<String> lcaToDest = new ArrayList<>();
        
        // Then find path from LCA to start 
        traverse(lca, startValue, lcaToStart);
        // Find path from LCA to destination
        traverse(lca, destValue, lcaToDest);
        
        // Since we need to move upward from start till LCA,
        // Thus, convert all characters in lcaToStart path to 'U'
        for (int i = 0; i < lcaToStart.size(); i++) {
            lcaToStart.set(i, "U");
        }

        // Concatenate both paths, to get shortest path from Start node -> Destination node
        String result = "";
        for (String s : lcaToStart) {
            result += s;
        }

        for (String s : lcaToDest) {
            result += s;
        }

        return result;
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
