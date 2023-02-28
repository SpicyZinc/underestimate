/*
Given a binary tree with n nodes,
your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3
Output: True
Explanation: 
    5
   / 
  10
Sum: 15

   10
  /  \
 2    3
Sum: 15

Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20
Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.

idea:

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return false;

        Map<Integer, Integer> hm = new HashMap<>();
        int sum = getSum(root, hm);
        return sum % 2 == 0 && hm.containsKey(sum / 2);
    }
    
    public int getSum(TreeNode node, Map<Integer, Integer> hm) {
        int sum = 0;
        if (node == null) return sum;

        sum += node.val + getSum(node.left, hm) + getSum(node.right, hm);
        hm.put(sum, hm.getOrDefault(sum, 0) + 1);

        return sum;
    }
}