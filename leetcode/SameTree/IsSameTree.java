/*
Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
idea:

recursion:
my intuition is right
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> one = new LinkedList<TreeNode>();
        Queue<TreeNode> two = new LinkedList<TreeNode>();
        one.add(p);
        two.add(q);
        while (!one.isEmpty() && !two.isEmpty()) {
            TreeNode tmp1 = one.poll();
            TreeNode tmp2 = two.poll();
            if (tmp1 == null) {
                return tmp2 == null;
            }
            // when reaching here, tmp1 != null
            if (tmp2 == null || tmp1.val != tmp2.val) {
                return false;
            }

            one.add(p.left);
            one.add(p.right);
            two.add(q.left);
            two.add(q.right);       
        }
        
        return true;    
    }
}


