/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path
such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

idea:
1. recursive
2. iterative
level traversal on the tree, two queues are used
one queue holds nodes level by level
one queue holds accumulative sum (note: accumulative sum)
*/

import java.util.*;

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		this.value = value;
		left = null;
		right = null;
	}
}

public class PathSum {
	public static void main(String[] args) {
		TreeNode aTreeRoot = new TreeNode(10);
		aTreeRoot.left = new TreeNode(8);
		aTreeRoot.right = new TreeNode(2);
		aTreeRoot.left.left = new TreeNode(3);
		aTreeRoot.left.right = new TreeNode(5);
		aTreeRoot.right.left = new TreeNode(2);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input a sum to see if there is root-to-leaf path");
		int sum = scanner.nextInt();
		
		PathSum eg = new PathSum();
		boolean hasOrNot = eg.hasPathSum(aTreeRoot, sum);
		System.out.printf("Existence of root-to-leaf sum path of %d: %B\n", sum, hasOrNot);
	}
	// recursion
	public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // iterative
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<Integer> values = new LinkedList<Integer>();
        nodes.offer(root);
        values.offer(root.val);
 
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();
 
            if (curr.left == null && curr.right == null && sumValue == sum) return true;
 
            if (curr.left != null) {
                nodes.offer(curr.left);
                values.offer(sumValue + curr.left.val);
            }
            if (curr.right != null) {
                nodes.offer(curr.right);
                values.offer(sumValue + curr.right.val);
            }
        }
 
        return false;
    }
}