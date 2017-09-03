/*
Given a binary tree, return all root-to-leaf paths.
For example, given the following binary tree:
   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:
["1->2->5", "1->3"]

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

idea:
dfs for sure
note: stop case is when node is not null, but node.left and node.right are null
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        dfs(root, "", result);
        return result;
    }
    
    public void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            path += node.val;
            result.add(new String(path));
        }
        dfs(node.left, path + node.val + "->", result);
        dfs(node.right, path + node.val + "->", result);
    }
}