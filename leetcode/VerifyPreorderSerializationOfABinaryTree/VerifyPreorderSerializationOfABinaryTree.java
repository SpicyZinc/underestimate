/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value.
If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

idea:
1. stack
2. out-degree and in-degree
3. regular expression and recursion
*/
import java.util.*;

public class VerifyPreorderSerializationOfABinaryTree {
	public static void main(String[] args) {
		VerifyPreorderSerializationOfABinaryTree eg = new VerifyPreorderSerializationOfABinaryTree();
		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		System.out.println(eg.isValidSerialization(preorder));
	}

    public boolean isValidSerialization(String preorder) {
    	String[] nodes = preorder.split("\\,");
        if (nodes.length == 1 && nodes[0].equals("#")) {
    		return true;
    	}

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < nodes.length; i++) {
        	list.add(nodes[i]);
        	while (
        		list.size() >= 3
        		&& list.get(list.size() - 1).equals("#") 
        		&& list.get(list.size() - 2).equals("#") 
        		&& !list.get(list.size() - 3).equals("#")
        	) {

    			list.remove(list.size() - 1);
    			list.remove(list.size() - 1);
    			list.remove(list.size() - 1);
    			list.add("#");
        	}
        }

        return list.size() == 1 && list.get(0) == "#";
    }

    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }


    // method 2
    // pay attention to when to return early
    // http://bookshadow.com/weblog/2016/02/01/leetcode-verify-preorder-serialization-binary-tree/
    public boolean isValidSerialization(String preorder) {
    	int degree = -1; // for compensate, init with -1 since root has no indegree
    	String[] nodes = preorder.split("\\,");
        for (int i = 0; i < nodes.length; i++) {
        	degree++;
        	if (degree > 0) {
        		return false;
        	}
        	if (!nodes[i].equals("#")) {
        		degree -= 2;
        	}
        	
        }
        return degree == 0;
    }

    // method 3
	public boolean isValidSerialization(String preorder) {
		String s = preorder.replaceAll("\\d+,#,#", "#");
	    return s.equals("#") || !s.equals(preorder) && isValidSerialization(s);
	}
}