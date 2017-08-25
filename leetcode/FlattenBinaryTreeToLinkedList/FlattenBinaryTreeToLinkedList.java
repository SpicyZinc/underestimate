/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

        1
       / \
      2   5
     / \   \
    3   4   6
	
The flattened tree should look like:
    1 
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6			  

Hint:
If you notice carefully in the flattened tree, each node’s right child points to the next node of a pre-order traversal.

idea:
http://n00tc0d3r.blogspot.com/2013/03/flatten-binary-tree-to-linked-list-in.html

in the flattened tree, 
each node's right child points to the next node of a pre-order traversal result (which is a linked list).
->	->	->	->	->	->

attention: after flatten, result is still a tree with only "right" pointer existing looks like a linked list, but not a list

1. recursion
1) if current node is null or leaf, return it.
2) get the left child and right child of current node, 
	2.1) if the left is not null, set the left as the current node's right, 
		 flat the left tree. return the tail of left child tree as current node.
	2.2) if the right is not null, set the right as the current node's right, 
		 flat the right tree. return the tail of left child tree as current node.
		 
	return root

*/
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
	
    TreeNode(int x) { 
		val = x; 
	}
	
	public void preOrder() {
		preOrder(this);
	}
	public void preOrder(TreeNode root) {
		System.out.print(root.val + " ");
		if (root.left != null) {
			preOrder(root.left);
		}
		if (root.right != null) {
			preOrder(root.right);
		}		
	}
}

public class FlattenBinaryTreeToLinkedList {
	public static void main(String[] args) {
		new FlattenBinaryTreeToLinkedList();
	}
	
	// constructor
	public FlattenBinaryTreeToLinkedList() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		
		root.preOrder();
/*		
my thought: How can I implement preorder tree traversal with Generator or Iterator

		Iterator<TreeNode> preTraversal = new Iterator<TreeNode>() {
			
			TreeNode current = root;		
			TreeNode left = current.left;
			TreeNode right = current.right;

			@Override
			public boolean hasNext() {
				if (current == null) {
					return false;
				}
				else {
					return true;
				}				
			}
		 
			@Override
			public Integer next() {
				TreeNode ret = current;
				current = left;
				
				return ret.val;			
			}
		 
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
*/
		flatten(root);
		root.preOrder();
		
	}
	
	// method 1
	// recursive
    public void flatten(TreeNode root) {
        root = flatten_recursive(root);
    }
    
    public TreeNode flatten_recursive(TreeNode root) {
        if ( root == null || (root.left == null && root.right == null) )
			return root;

		TreeNode left = root.left;
		TreeNode right = root.right;

		root.left = null;

		if (left != null) {
			root.right = left;
			root = flatten_recursive(left);
		}

		if (right != null) {
			root.right = right;
			root = flatten_recursive(right);
		}

		return root;
    }
    // also recursion, simpler version, I used this one to pass leetcode
	public TreeNode flatten(TreeNode root) {  
		if (root == null) return root;  
		TreeNode rightTree = root.right;  
		if (root.left != null) {  
			root.right = root.left;  
			root.left = null;  
			root = flatten(root.right);  
		}  
		if (rightTree != null) {  
			root.right = rightTree;  
			root = flatten(root.right);  
		}  
		return root;  // ???????? return tail so it is leaf now?
	}  
/*	
	// method 2
	// iterative
	// without stack
	//
	public void flatten(TreeNode root) {     
        TreeNode current = root;
        TreeNode right = null;
        TreeNode left = null;
        
        while (current != null) {    
			if (current.left != null) {
				left = current.left;
				TreeNode tmp = left;
				
				while (tmp.right != null) {
					tmp = tmp.right;
				}
				
				if (current.right != null) {
					right = current.right;
				}            
				tmp.right = right;
				
				current.right = left;
				current.left = null;
				
				right = null;
			}
			
			current = current.right;
        }
    }
	
	// method 3
	// iterative with stack
	// go down through the left, when right is not null, push right to stack.
	//
	public void flatten(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) 
			return;
			
        Stack stack = new Stack();
        stack.push(root);
        TreeNode lastNode = null;
		
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (lastNode != null) {
                lastNode.left = null;
                lastNode.right = node;
            }
			
            lastNode = node;
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
        }
    }
*/
}