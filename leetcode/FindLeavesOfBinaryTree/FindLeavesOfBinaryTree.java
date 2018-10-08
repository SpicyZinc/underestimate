/*
Given a binary tree, collect a tree's nodes as if you were doing this:
Collect and remove all leaves, repeat until the tree is empty.

Example:
Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    
Output: [[4,5,3],[2],[1]]

Explanation:
1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2

2. Now removing the leaf [2] would result in this tree:
          
          1

3. Now removing the leaf [1] would result in the empty tree:
          []

idea:
https://www.cnblogs.com/grandyang/p/5616158.html

typical dfs like peel off onion
note the technique to remove the outer layer
if left and right both null, remove it and return null
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class FindLeavesOfBinaryTree {
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();

		while (root != null) {
			List<Integer> path = new ArrayList<>();
			root = dfs(root, path);
			result.add(path);
		}

		return result;
	}

	// remove one layer and return the same root without the removed layer
	public TreeNode dfs(TreeNode root, List<Integer> path) {
		if (root == null) {
			return root;
		}
		// check if it is leaf, if so, add to the outer layer 'path'
		if (root.left == null && root.right == null) {
			path.add(root.val);
			return null;
		}

		root.left = dfs(root.left, path);
		root.right = dfs(root.right, path);

		return root;
	}
}