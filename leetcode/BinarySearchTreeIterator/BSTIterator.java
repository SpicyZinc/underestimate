/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

idea:
http://blog.csdn.net/whuwangyi/article/details/42304407

using stack, inorder traversal of BST iteratively
iterator is basically make a copy of original BST
*/

import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class BSTIterator {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);

		TreeNode x = new TreeNode(4);
		TreeNode y = new TreeNode(7);

		root.left.right.left = x;    	
		root.left.right.right = y;

		root.right.right = new TreeNode(14);
		root.right.right.left = new TreeNode(13);

		BSTIterator i = new BSTIterator(root);
		while (i.hasNext()) {
			System.out.print( i.next() + "  " );
		}

		System.out.println();
	}

	// iteration
	Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
		return !stack.isEmpty();   
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
		int result = node.val;
		if (node.right != null) {
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}

		return result;
    }

    // recursion
    int index = 0;
    List<TreeNode> list;
   	public BSTIterator(TreeNode root) {
        index = 0;
        list = new ArrayList<TreeNode>();
        dfs(root, list);
    }
    
    public void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public int next() {
        return list.get(index++).val;
    }

    public TreeNode next() {
        return list.get(index++);
    }
}