/*
Symmetric Tree
check a binary tree it is a mirror of itself (ie, symmetric around its center)
this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following is not:

    1
   / \
  2   2
   \   \
   3    3

idea:
recursion version
left.left vs right.right
left.right vs right.left

*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SymmetricTree {
    // recursion
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val == right.val) {
            return helper(left.left, right.right) && helper(left.right, right.left);
        }
        return false;
    }

    // iterative
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
    
        Queue<TreeNode> left = new LinkedList<TreeNode>();
        Queue<TreeNode> right = new LinkedList<TreeNode>();
        left.add(root.left);
        right.add(root.right);
    
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode temp1 = left.poll();
            TreeNode temp2 = right.poll();
      
            if (temp1 == null && temp2 != null || temp1 != null && temp2 == null) {
                return false;
            }

            if (temp1 != null && temp2 != null) {
                if (temp1.val != temp2.val) {
                    return false;
                }
                // note adding sequence
                left.add(temp1.left);
                left.add(temp1.right);
                right.add(temp2.right);
                right.add(temp2.left);
            }
        }

        return true;
    }
}

