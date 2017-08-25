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

    public List<Integer> rightSideViewRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
       	if ( root == null ) {
			return res;
		}
		DFS(root, res, 0);
		return res;
    }

    public void DFS(TreeNode root, List<Integer> res, int level) {
    	if ( res.size() <= level ) {
    		res.add(root.val);
    	}	
    	if ( root.right != null ) {
    		DFS(root.right, res, level + 1);
    	}
    	if ( root.left != null ) {
    		DFS(root.left, res, level + 1);
    	}
    	return;
    }

    public List<Integer> rightSideViewIteration(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if ( root == null ) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while ( !queue.isEmpty() ) {
			int size = queue.size();
			for ( int i = 0; i < size; i++ ) {
				TreeNode queueHead = queue.poll();
				if ( i == 0 ) {
					res.add(queueHead.val);
				}
				if ( queueHead.right != null ) {
					queue.offer( queueHead.right );
				}
				if ( queueHead.left != null ) {
					queue.offer( queueHead.left );
				}
			}
		}

		return res;
    }
}

