/*
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
     1
    /\
   /  \
   2   3
  /\  /
 /  \/  
 4  56
Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
	 1
    /\
   /  \
   2   3
  /\   \
 /  \   \
 4  5    7
Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note:
The tree will have between 1 and 100 nodes.

idea:
BFS
For a complete binary tree, there should not be any node after we met an empty one.

When level-order traversal in a complete tree, after the last node, all nodes in the queue should be null.
Otherwise, the tree is not complete

example 1, # # # # # # #
example 2, # 7 # # # #
*/

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class CheckCompletenessOfABinaryTree {
	// Sun Jul  7 18:29:18 2019
	public boolean isCompleteTree(TreeNode root) {
		boolean isLastNode = false;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			if (node == null) {
				isLastNode = true;
			} else {
				if (isLastNode) {
					return false;
				}
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}

		return true;
	}

	// used peek(), not comfortable to understand
	public boolean isCompleteTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (queue.peek() != null) {
			TreeNode node = queue.poll();
			queue.offer(node.left);
			queue.offer(node.right);
		}

		while (!queue.isEmpty() && queue.peek() == null) {
			queue.poll();
		}

		return queue.isEmpty();
	}
}
