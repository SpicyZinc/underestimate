/*
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1        <---
 /   \
2     3     <---
 \     \
  5     4   <---

You should return [1, 3, 4].

idea:
in essence, it is level print binary tree

1. recursion, DFS, but right sub tree first
right recurs once, level increases by one
when left recurs, result size is already greater total levels

2. iteration, one queue to level traverse the tree
*/
import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        BinaryTreeRightSideView eg = new BinaryTreeRightSideView();
        // List<Integer> result = eg.rightSideViewIteration(root);
        List<Integer> result = eg.rightSideViewRecursion(root);
        for ( Integer i : result ) {
            System.out.print( i + "  " );
        }
        System.out.println();
    }
    // Fri Apr  5 21:58:30 2024
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;            
        }

        dfs(root, 0, result);

        return result;
    }

    public void dfs(TreeNode node, int level, List<Integer> result) {
        if (result.size() <= level) {
            result.add(node.val);
        }

        if (node.right != null) {
            dfs(node.right, level + 1, result);
        }

        if (node.left != null) {
            dfs(node.left, level + 1, result);
        }
    }

    public List<Integer> rightSideViewRecursion(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        dfs(root, 0, result);

        return result;
    }

    public void dfs(TreeNode node, int level, List<Integer> result) {
        if (result.size() <= level) {
            result.add(node.val);
        }
        // first, right
        if (node.right != null) {
            dfs(node.right, level + 1, result);
        }
        // then, left
        if (node.left != null) {
            dfs(node.left, level + 1, result);
        }
    }

    public List<Integer> rightSideViewIteration(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // always save first element to result, because that is the rightmost
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }

        return result;
    }
}
