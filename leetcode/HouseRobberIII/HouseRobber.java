/*
The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called the "root."
Besides the root, each house has one and only one parent house. 

After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

Example 3:
      4
     /
    1
   /
  2
 /
3
Maximum amount of money the thief can rob = 4 + 3 = 7.

idea:
1. recursion for sure
rob root: root.val + (root.left.left, root.left.right) + (root.right.left, root.right.right)
or
not rob root: root.left + root.right

2.
maintain an array of length 2: 
dp[0] == the max value if rob the root
dp[1] == the max value if not rob the root

*/
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class HouseRobber {
	// direct recursion method
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        // rob with root
        int robWithRoot = root.val; 
        if (root.left != null) {
            robWithRoot += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            robWithRoot += rob(root.right.left) + rob(root.right.right);
        }
        // rob without root
        int robWithoutRoot = rob(root.left) + rob(root.right);
        
        return Math.max(robWithRoot, robWithoutRoot);
    }
    // a little optimization over recursion with memoization
    public int rob(TreeNode root) {
        return rob(root, new HashMap<TreeNode, Integer>());
    }
    // use hashmap to remember rob node, how much maximum can get
    public int rob(TreeNode root, Map<TreeNode, Integer> hm) {
        if (root == null) return 0;
        if (hm.containsKey(root)) {
            return hm.get(root);
        }
        if (root.left == null && root.right == null) {
            hm.put(root, root.val);
            return root.val;
        }
        // rob with root
        int robWithRoot = root.val; 
        if (root.left != null) {
            robWithRoot += rob(root.left.left, hm) + rob(root.left.right, hm);
        }
        if (root.right != null) {
            robWithRoot += rob(root.right.left, hm) + rob(root.right.right, hm);
        }
        // rob without root
        int robWithoutRoot = rob(root.left, hm) + rob(root.right, hm);
        int max = Math.max(robWithRoot, robWithoutRoot);
        hm.put(root, max);

        return max;
    }
    // DP
    // res[0] 表示不包含当前节点的最大值
    // res[1] 表示包含当前节点的最大值
    public int rob(TreeNode root) {
        int[] res = dfs(root);

        return Math.max(res[0], res[1]);
    }
    
    private int[] dfs(TreeNode node) {
        int dp[] = {0, 0};
        if (node == null) {
            return dp;
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = node.val + left[0] + right[0];

        return dp;
    }
}