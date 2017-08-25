/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

idea:
recursion
take any node from [1, n] as root,
in subtree left, any node from [start, i-1] as root  
in subtree right, any node from [i+1, end] as root

base is start > end, stop, add to result list

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; left = null; right = null; }
}

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return dfs(1, n);
    }
    
    public List<TreeNode> dfs(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            // use i as root node
            List<TreeNode> leftTrees = dfs(start, i - 1);
            List<TreeNode> rightTrees = dfs(i + 1, end);
            
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    // make sure it is BST
                    root.left = left;
                    root.right = right;
                    
                    result.add(root);
                }
            }
        }
        
        return result;
    }
}