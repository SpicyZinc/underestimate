/*
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
A valid path is from root node to any of the leaf nodes.

Example
Given a binary tree, and target = 5:

     1
    / \
   2   4
  / \
 2   3
return
[
  [1, 2, 2],
  [1, 4]
]

idea:
typical dfs, but not remaining, use sum
note, path refers to path from root to leaf,
need to check both left and right == null
*/
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BinaryTreePathSum {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        dfs(root, target, root.val, path, result);

        return result;
    }
    
    public void dfs(TreeNode node, int target, int sum, List<Integer> path, List<List<Integer>> result) {
        if (node.left == null && node.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
                return;
            }
        }

        if (node.left != null) {
            path.add(node.left.val);
            dfs(node.left, target, sum + node.left.val, path, result);
            path.remove(path.size() - 1);
        }

        if (node.right != null) {
            path.add(node.right.val);
            dfs(node.right, target, sum + node.right.val, path, result);
            path.remove(path.size() - 1);
        }
    }
}