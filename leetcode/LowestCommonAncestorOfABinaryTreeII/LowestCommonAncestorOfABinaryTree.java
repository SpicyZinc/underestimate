/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)".
A descendant of a node x is a node y that is on the path from node x to some leaf node.

Example 1:
https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:
https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.

Constraints:
The number of nodes in the tree is in the range [1, 10^                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   4].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q

Follow up: Can you find the LCA traversing the tree, without checking nodes existence?

idea:
note, p or q could not be in the tree
why p or q found, need to return node??

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/solutions/933835/java-difference-from-236-is-you-need-to-search-the-entire-tree/
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class LowestCommonAncestorOfABinaryTree {
    boolean pFound = false;
    boolean qFound = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = lca(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return node;
        }

        TreeNode leftCommonAncestor = lca(node.left, p, q);
        TreeNode rightCommonAncestor = lca(node.right, p, q);

        if (node == p) {
            pFound = true;
            return node;
        }
        if (node == q) {
            qFound = true;
            return node;
        }

        if (leftCommonAncestor == null) {
            return rightCommonAncestor;
        } else if (rightCommonAncestor == null) {
            return leftCommonAncestor;
        } else {
            return node;
        }
    }
}
