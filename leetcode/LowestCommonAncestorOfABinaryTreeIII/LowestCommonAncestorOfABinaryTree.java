/*
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

Example 1:
https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: rot = [1,2], p = 1, q = 2
Output: 1
 
Constraints:
The number of nodes in the tree is in the range [2, 105].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q exist in the tree.

idea:
1. use hash set or linked list to record the node to root path
if the other node to root path has in the above path, it must be the common
natural idea
2. switch p and q

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/discuss/950242/Multiple-solution-approaches-in-Java-(with-comments-and-explanation)
*/

class LowestCommonAncestorOfABinaryTree {
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> path = findPath(p);
        while (q != null) {
            for (Node node : path) {
                if (node == q) {
                    return q;
                }
            }
            q = q.parent;
        }

        return q;
    }

    public List<Node> findPath(Node node) {
        List<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> hs = new HashSet<>();
        while (p != null) {
            hs.add(p);
            p = p.parent;
        }

        while (q != null) {
            if (hs.contains(q)) {
                return q;
            }
            q = q.parent;
        }

        return null;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p;
        Node b = q;
        
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        
        return a;
    }
}