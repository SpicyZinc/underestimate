/*
Given a binary tree, write a function to get the maximum width of the given tree.
The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes
(the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9
Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input:
          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 
          1
         / \
        3   2 
       /        
      5      
Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input:
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer.

idea:
did not expect i have so many difficulties in this problem
need to go back, almost there

damn, could not figure out
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class MaximumWidthOfBinaryTree {
	// method 1, this method not consider the empty #
	public int widthOfBinaryTree(TreeNode root) {
		int height = getHeight(root);
		int maxWidth = 0;
		for (int i = 1; i <= height; i++) {
			maxWidth = Math.max(maxWidth, getWidthByLevel(root, i));
		}

		return maxWidth;
	}

	public int getWidthByLevel(TreeNode node, int level) {
		if (node == null) {
			return 0;
		}
		if (level == 1) {
			return 1;
		}
		return getWidthByLevel(node.left, level - 1) + getWidthByLevel(node.right, level - 1);
	}

	public int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}
	// method 2, not quite get start.size() == level, start and end add(index)
	public int widthOfBinaryTree(TreeNode root) {
		return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
	}

	public int dfs(TreeNode node, int level, int index, List<Integer> start, List<Integer> end) {
		if (node == null) {
			return 0;
		}
		if (start.size() == level) {
			start.add(index);
			end.add(index);
		} else {
			end.set(level, index);
		}

		int curr = end.get(level) - start.get(level) + 1;
		int left = dfs(node.left, level + 1, index * 2, start, end);
		int right = dfs(node.right, level + 1, index * 2 + 1, start, end);

		return Math.max(curr, Math.max(left, right));
	}
}