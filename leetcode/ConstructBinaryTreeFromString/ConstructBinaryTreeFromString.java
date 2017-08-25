/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
 

Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".

idea:
not much to say, recursion,
note, recursion pass in A()() format, substring position matter
https://discuss.leetcode.com/topic/82572/java-recursive-solution
*/
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }

	@Override
	public String toString() {
		String res = "";
		if (this.left != null) {
			res += this.left.toString();
		}
		res += this.val;
		if (this.right != null) {
			res += this.right.toString();
		}
		return res;
	}
}

public class ConstructBinaryTreeFromString {
	public static void main(String[] args) {
		ConstructBinaryTreeFromString eg = new ConstructBinaryTreeFromString();
		TreeNode result = eg.str2tree("4(2(3)(1))(6(5))");
		System.out.println(result.toString());
	}
	public TreeNode str2tree(String s) {
		if (s == "") return null;
		int open = 0;
		int closing = 0;
		int firstPos = s.indexOf("(");
		int val = firstPos == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, firstPos));
		TreeNode root = new TreeNode(val);
		if (firstPos == -1) {
			return root;
		}
		else {
			open = 1;
		}
		for (int i = firstPos + 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				open++;
			}
			else if (c == ')') {
				closing++;
			}
			if (open == closing) {
				String left = s.substring(firstPos + 1, i);
				String right = "";
				if (i + 2 < s.length() - 1) {
					right = s.substring(i + 2, s.length() - 1);
				}
				TreeNode leftPart = str2tree(left);
				TreeNode rightPart = str2tree(right);

				root.left = leftPart;
				root.right = rightPart;

				break;
			}
		}
		return root;
	}
}