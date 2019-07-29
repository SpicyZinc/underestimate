/*
Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
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
            \
             7
              \
               8
                \
                 9  
Note:
The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.

idea: recurse on (node.left, node) 到底
然后 reset node.left == null
node.right = recursion(node.right, parent)
*/


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class IncreasingOrderSearchTree {
	public TreeNode increasingBST(TreeNode root) {
		return increasingBST(root, null);
	}

	public TreeNode increasingBST(TreeNode node, TreeNode parent) {
		if (node == null) {
			return parent;
		}

		TreeNode leftmost = increasingBST(node.left, node);

		node.left = null;
		node.right = increasingBST(node.right, parent);

		return leftmost;
	}
}
