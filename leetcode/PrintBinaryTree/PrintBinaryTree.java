/*
Print a binary tree in an m*n 2D string array following these rules:

1. The row number m should be equal to the height of the given binary tree.
2. The column number n should always be an odd number.
3. The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part.
The left-bottom part and the right-bottom part should have the same size. 
Even if one subtree is none while the other is not, 
you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
However, if two subtrees are none, then you don't need to leave space for both of them.
4. Each unused space should contain an empty string "".
5. Print the subtrees following the same rules.

Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]

Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]

Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:
[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]

Note: The height of binary tree is in the range of [1, 10].

idea:
dfs
getHeight() to get height first
pow(2, h) - 1 is width for each row, odd number
root.val 填在中间
root.left.val  填在 左边的中间
root.right.val 填在 右边的中间
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class PrintBinaryTree {
	public List<List<String>> printTree(TreeNode root) {
		int height = getHeight(root);
		int width = (int) Math.pow(2, height) - 1;

		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			List<String> layer = new ArrayList<String>();
			for (int j = 0; j < width; j++) {
				layer.add("");
			}
			result.add(layer);
		}

		dfs(root, 0, width - 1, 0, height, result);

		return result;
	}

	public void dfs(TreeNode node, int i, int j, int level, int height, List<List<String>> result) {
		if (node == null || level == height) {
			return;
		}

		int mid = (i + j) / 2;
		result.get(level).set(mid, "" + node.val);

		dfs(node.left, i, mid, level + 1, height, result);
		dfs(node.right, mid + 1, j, level + 1, height, result);
	}

	public int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}
}