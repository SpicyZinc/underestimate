/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: 
"The lowest common ancestor is defined between two nodes v and w as the lowest node in T 
that has both v and w as descendants (where we allow a node to be a descendant of itself)."
For example, 
        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
the lowest common ancestor (LCA) of nodes 5 and 1 is 3
LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

idea:
1. recursion
http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
2. find paths from root to p and q. note find path return boolean
Loop through the two paths, return last one node before two paths are mismatched
http://blog.csdn.net/xudli/article/details/46871283
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class LowestCommonAncestorOfBinaryTree {
    // Tue Mar 26 19:14:22 2024
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 恰巧 p 或 q 就是root, root 就是永远的祖先
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p, q); 
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q); 
        // 在左子树中没有找到, 那一定在右子树中
        if (leftCommonAncestor == null) {
            return rightCommonAncestor;
        }
        // 在右子树中没有找到, 那一定在左子树中
        if (rightCommonAncestor == null) {
            return leftCommonAncestor;
        }
        // 不在左子树, 也不在右子树, 那说明是根节点
        return root;
    }

	// method 1
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // since neither root == p nor root = q
        // find in two sides
        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q);
        // none of them null
        // 都有祖先
        if (leftCommonAncestor != null && rightCommonAncestor != null) {
            return root;
        }
        
        return leftCommonAncestor == null ? rightCommonAncestor : leftCommonAncestor;
    }

    // method 2, note know how to get path from some root to target node using list
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        List<TreeNode> pathToP = new ArrayList<>();
        List<TreeNode> pathToQ = new ArrayList<>();
        path(root, p, pathToP);
        path(root, q, pathToQ);

        int i = 0;
        while (i < pathToP.size() && i < pathToQ.size()) {
            if (pathToP.get(i) != pathToQ.get(i)) {
                break;
            }
            i++;
        }

        return pathToP.get(i - 1);
    }
    
    public boolean path(TreeNode node, TreeNode target, List<TreeNode> path) {
        if (node == null) {
            return false;
        }

        path.add(node);
        if (node == target) {
            return true;
        }
        if (path(node.left, target, path) || path(node.right, target, path)) {
            return true;
        }
        // continually remove last element from list, make sure no wrong element on correct path
        path.remove(path.size() - 1);

        return false;
    }
}