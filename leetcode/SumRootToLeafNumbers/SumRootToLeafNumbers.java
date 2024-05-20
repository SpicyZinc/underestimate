/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
An example is the root-to-leaf path 1->2->3 which represents the number 123.
Find the total sum of all root-to-leaf numbers.

For example,
    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

idea:
similar to DepthOfBinaryTree

note:
Java primitive variable cannot be passed in as address
must use array[], simpler method to deal with it

this problem is NOT simply add all values in the tree together
it has to consider the 10

1. key point: path[0] /= 10;
restore num[0] to its original value
num[0] automatically is restored to its original value

2. preorder traversal dfs
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SumRootToLeafNumbers {
    // Wed Apr 10 01:27:02 2024
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode node, int val) {
        if (node == null) {
            return 0;
        }

        int x = val * 10 + node.val;
        if (node.left == null && node.right == null) {
            return x;
        }

        return dfs(node.left, x) + dfs(node.right, x);
    }

    // pass by value
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    public int dfs(TreeNode node, int sum) {
        // note, node == null, return 0
        if (node == null) {
            return 0;
        }
        
        sum = sum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }
        
        return dfs(node.left, sum) + dfs(node.right, sum);
    }

    // method 1
    public int sumNumbers(TreeNode root) {  
        int[] sum = new int[1];
        int[] path = new int[1];
        GenerateSum(root, path, sum);

        return sum[0];  
    } 
    
    public void GenerateSum(TreeNode root, int[] path, int[] sum) {
        if (root == null) {
            return;
        }

        path[0] = path[0] * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum[0] += path[0];
            // restore to a state where not times 10
            path[0] /= 10;

            return;
        }

        GenerateSum(root.left, path, sum);
        GenerateSum(root.right, path, sum);
        // restore to a state where not times 10
        path[0] /= 10;
    }
}