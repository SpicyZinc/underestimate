/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:
Input: [10,5,15,1,8,null,7]
   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?


idea:
https://www.cnblogs.com/grandyang/p/5188938.html
不断check root, root.left, root.right 是否是bst
if so, count the number of nodes
a lot of dfs() in count(), isValidBST(), largestBSTSubtree()
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isValidBST(root,  nteger.MIN_VALUE, Integer.MAX_VALUE)) {
            return count(root);
        }

        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    public boolean isValidBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        boolean isLeftValid = isValidBST(node.left, min, node.val);
        boolean isRightValid = isValidBST(node.right, node.val, max);

        return min < node.val && node.val < max && isLeftValid && isRightValid;
    }

    public int count(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + count(node.left) + count(node.right);
    }
}