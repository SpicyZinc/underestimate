/*
You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
The null node needs to be represented by empty parenthesis pair "()".
And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"
Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".

Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"
Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

idea:
1. left == "", right no need to consider ()
   right == "", left need to consider()
2. if (node.left != null || node.right != null) 
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode t) {
		if (t == null) return "";
		String left = tree2str(t.left);
		String right = tree2str(t.right);
		if (left == "" && right == "") return t.val + "";
		if (left == "") {
			return t.val + "()(" + right + ")";
		}
		if (right == "") {
			return t.val + "(" + left + ")";
		}
		return t.val + "(" + left + ")" + "(" + right + ")";
    }
	// method 2
    public String tree2str(TreeNode t) {
		if (t == null) return "";
		StringBuilder sb = new StringBuilder();
		tree2str(t, sb);
		return sb.toString();
	}
	public void tree2str(TreeNode node, StringBuilder sb) {
		if (node != null) {
			sb.append(node.val);
			if (node.left != null || node.right != null) { // note: this is key point
				if (node.left != null) {
					sb.append('(');
					tree2str(node.left, sb);
					sb.append(')');
				}
				else {
					sb.append("()");
				}
				if (node.right != null) {
					sb.append('(');
					tree2str(node.right, sb);
					sb.append(')');	
				}
			}
		}
	}
}