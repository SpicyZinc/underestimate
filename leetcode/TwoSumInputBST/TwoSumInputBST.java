/*
Given a Binary Search Tree and a target number,
return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9
Output: True

Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 28
Output: False

idea:
use hashset to see if existing (target - val) before

damn TT asked me binary search in BST,
aftermath, he wanted me to save space

TC: O(nlogn)
SC: O(1)
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class TwoSumInputBST {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        int k = 28;

        TwoSumInputBST eg = new TwoSumInputBST();
        eg.findTarget(root, k);
    }

    // Wed May  1 22:57:10 2024
    // made 不舒服 Tong Zhou 提醒的屁吗
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
           return false;
        }
 
        return dfs(root, root, k);
    }

    public boolean dfs(TreeNode root, TreeNode node, int k) {
        if (node == null) {
            return false;
        }

        int diff = k - node.val;
        // 如果相等 bst 不可能有两个相等的value
        if (diff != node.val) {
            if (findval(root, diff)) {
                return true;
            }
        }
        // 当时做的时候 java.lang.StackOverflowError
        // 因为node.left node.right 被不断的能成 root.left root.right
        return dfs(root, node.left, k) || dfs(root, node.right,  k);
    }

    // O(logn)
    public boolean findval(TreeNode root, int num) {
        if (root == null) {
            return false;
        }
        while (root != null) {
            if (num == root.val) {
                return true;
            } else if (num < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return false;
    }


    // public boolean findTarget(TreeNode root, int k) {
    //     return dfs(root, k, new HashSet<Integer>());
    // }

    // public boolean dfs(TreeNode node, int k, Set<Integer> hs) {
    //     if (node == null) {
    //         return false;
    //     }
    //     if (hs.contains(k - node.val)) {
    //         return true;
    //     }
        
    //     hs.add(node.val);

    //     return dfs(node.left, k, hs) || dfs(node.right, k, hs);
    // }
}


