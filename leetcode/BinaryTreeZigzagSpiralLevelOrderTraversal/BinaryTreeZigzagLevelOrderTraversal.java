/**
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(from left to right, then right to left for the next level and alternate between).

Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]

    root
		\
		 \
left<---right

idea:

use two stacks: one for currentLevel, one for nextLevel
a boolean a variable to keep track of the current level's order (whether it is left->right or right->left). 

Stack:(LIFO)
pop from stack currentLevel and print the node's value. 
Whenever the current level's order is from left->right, 
push the node's left child, then its right child to stack nextLevel

next time when nodes are popped off nextLevel, reverse the order by simply changing boolean value to !flag

*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeZigzagLevelOrderTraversal {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		BinaryTreeZigzagLevelOrderTraversal aTest = new BinaryTreeZigzagLevelOrderTraversal();
		ArrayList<ArrayList<Integer>> result = aTest.zigzagLevelOrder(root);
		
		for (int i=0; i<result.size(); i++) {
			ArrayList<Integer> tmp = result.get(i);
			for (int j=0; j<tmp.size(); j++) {
				System.out.print(tmp.get(j) + " ");
			}
			System.out.print("\n");
		}
	}
	
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) 
			return result;
		
		Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		currentLevel.push(root);
		// because right first popped out, left second
		// push left in first, then push right in second
		// 
		boolean flag = false;
		while (!currentLevel.isEmpty()) {
			Stack<TreeNode> nextLevel = new Stack<TreeNode>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			while (!currentLevel.isEmpty()) {
				TreeNode pop = currentLevel.pop();
				list.add(pop.val);
				if (!flag) {
					if (pop.left != null) 
						nextLevel.push(pop.left);
					if (pop.right != null) 
						nextLevel.push(pop.right);
				}
				else {
					if (pop.right != null) 
						nextLevel.push(pop.right);
					if (pop.left != null) 
						nextLevel.push(pop.left);
				}
			}
			
			flag = !flag;
			result.add(list);
			currentLevel = nextLevel;
		}
		return result;     
    }
}