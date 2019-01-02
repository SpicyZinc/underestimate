/*
Given a complete binary tree, count the number of nodes.
http://www.programcreek.com/2014/06/leetcode-count-complete-tree-nodes-java/

idea:
https://www.cnblogs.com/grandyang/p/4567827.html
1) get the height of left-most part
2) get the height of right-most part
3) when they are equal, the # of nodes = 2^h - 1
4) when they are not equal, recursively get # of nodes from left&right sub-trees
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class CountCompleteTreeNodes  {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int hl = 0;
        int hr = 0;
        TreeNode left = root;
        TreeNode right = root;

        while (left != null) {
            hl++;
            left = left.left;
        }

        while (right != null) {
            hr++;
            right = right.right;
        }
        
        if (hl == hr) {
            return (2 << (hl - 1)) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}