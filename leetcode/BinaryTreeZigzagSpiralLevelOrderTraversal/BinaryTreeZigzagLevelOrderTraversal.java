/*
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

Stack: (LIFO)
pop from stack currentLevel and print the node's value. 
Whenever the current level's order is from left->right, 
push the node's left child, then its right child to stack nextLevel

next time when nodes are popped off nextLevel, reverse the order by simply changing boolean value to !flag

巧妙应用add(0, node.val)

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
	// Sun May 19 01:48:30 2019
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

        
        Queue<TreeNode> queue = new LinkedList<>();
        
        boolean fromLeftToRight = true;
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            List<Integer> path = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                if (fromLeftToRight) {
                    path.add(node.val);
                } else {
                    path.add(0, node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            fromLeftToRight = !fromLeftToRight;
            result.add(path);
        }
        
        return result;
    }

	// one queue plus a flag leftToRight to do the zigzag
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            List<Integer> layer = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (leftToRight) {
                    layer.add(node.val);
                } else {
                    layer.add(0, node.val);
                }
                queue.add(node.left);
                queue.add(node.right);
            }
            leftToRight = !leftToRight;
            if (layer.size() > 0) {
                result.add(layer);    
            }
        }

        return result;
    }
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		
		Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		currentLevel.push(root);
		// because right first popped out, left second
		// push left in first, then push right in second
		boolean flag = false;
		while (!currentLevel.isEmpty()) {
			Stack<TreeNode> nextLevel = new Stack<TreeNode>();
			List<Integer> path = new ArrayList<Integer>();
			
			while (!currentLevel.isEmpty()) {
				TreeNode pop = currentLevel.pop();
				if (pop == null) {
				    continue;
				}
				
				path.add(pop.val);

				if (!flag) {
					nextLevel.push(pop.left);
					nextLevel.push(pop.right);
				} else {
					nextLevel.push(pop.right);
					nextLevel.push(pop.left);
				}
			}
			if (path.size() > 0) {
				result.add(path);
			}
			flag = !flag;
			currentLevel = nextLevel;
		}

		return result;
	}
}