/*
Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller:
for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)

 
Example 1:
https://assets.leetcode.com/uploads/2019/01/30/tree1.png
Input: [0,1,2,3,4,3,4]
Output: "dba"

Example 2:
https://assets.leetcode.com/uploads/2019/01/30/tree2.png
Input: [25,1,3,1,3,0,2]
Output: "adz"

Example 3:
https://assets.leetcode.com/uploads/2019/02/01/tree3.png
Input: [2,2,1,null,1,0,null,0]
Output: "abc"

Note:
The number of nodes in the given tree will be between 1 and 8500.
Each node in the tree will have a value between 0 and 25.

idea:

*/

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}


class SmallestStringStartingFromLeaf {
	private String ans = "~"; // dummy value '~' > 'z'
	public String smallestFromLeaf(TreeNode root) {
		return dfs(root, "");
	}

	private String dfs(TreeNode n, String str) {
		// base case, and in case root is null.
		if (n == null) { 
			return str;
		}

		str = (char) (n.val + 'a') + str; // prepend current char to the path string from root.
		if (n.left == null && n.right == null && ans.compareTo(str) > 0) { 
			ans = str;
		} // update ans if n is a leaf.

		dfs(n.left, str); // recursion to the left branch.
		dfs(n.right, str); // recursion to the right branch.

		return ans;
	}
}