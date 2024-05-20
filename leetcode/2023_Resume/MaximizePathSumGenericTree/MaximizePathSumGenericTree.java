/*
Given a generic tree consisting of N nodes, the task is to find the maximum sum of the path from the root to the leaf node.

Examples:
Input:
https://media.geeksforgeeks.org/wp-content/uploads/20210823091802/treemaxpathsum.jpg

Output: 12
Explanation: The path sum to every leaf from the root are:
For node 4: 1 -> 2 -> 4 = 7
For node 5: 1 -> 2 -> 5 = 8
For node 6: 1 -> 3 -> 6 = 10
For node 7: 1 -> 3 -> 7 = 11
For node 8: 1 -> 3 -> 8 = 12 (maximum)

The maximum path sum is 12 i.e., from root 1 to leaf 8.

idea:
dfs
*/

import java.util.*;

class TreeNode {
    public int val;
    public Vector<TreeNode> children;
    
    public TreeNode(int key) {
        val = key;
        children = new Vector<TreeNode>();
    }
}

public class MaximizePathSumGenericTree {
    // Stores the maximum sum of a path
    static int maxSumPath = 0;

    public static void main(String[] args) {
        MaximizePathSumGenericTree eg = new MaximizePathSumGenericTree();
        TreeNode root = newTreeNode(1);
        root.children.add(newTreeNode(2));
        root.children.add(newTreeNode(3));
        root.children.get(0).children.add(newTreeNode(4));
        root.children.get(1).children.add(newTreeNode(6));
        root.children.get(0).children.add(newTreeNode(5));
        root.children.get(1).children.add(newTreeNode(7));
        root.children.get(1).children.add(newTreeNode(8));
        
        // Function Call
        eg.dfs(root, root.val);
        System.out.println(maxSumPath);
    }

    static TreeNode newTreeNode(int key) {
        TreeNode temp = new TreeNode(key);
        return temp;
    }
    
    // Recursive function to calculate the maximum sum in a path using dfs
    void dfs(TreeNode node, int sum) {
        // If current node is a leaf node
        if (node.children.size() == 0) {
            maxSumPath = Math.max(maxSumPath, sum);
            return;
        }
    
        // Traversing all children of the current node
        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);
            dfs(child, sum + child.val);
        }
    }
}
