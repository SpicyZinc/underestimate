/*
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:                                   											
Input:                                    											
    1                                   											
   / \                                   											
  0   2                                   											
                                   											
  L = 1                                   											
  R = 2                                   											
Output:                                    											
    1                                   											
      \                                   											
       2                                   											
											
Example 2:
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3
Output: 
      3
     / 
   2   
  /
 1										
											
idea: 
dfs, speechless about I cannot think of it
two cases about a node out of range [L, R]
< L, return recursion on node.right
> R, return recursion on node.left

还是根据BST特性
root < 更小的L 那就 recurse on right
root > 更大的R 那就 recurse on left
*/
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class TrimABinarySearchTree {
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null) return root;

		TreeNode left = trimBST(root.left, L, R);
		TreeNode right = trimBST(root.right, L, R);

		if (root.val < L) {
			return right;
		}
		if (root.val > R) {
			return left;
		}

		root.left = left;
		root.right = right;

		return root;
	}
}