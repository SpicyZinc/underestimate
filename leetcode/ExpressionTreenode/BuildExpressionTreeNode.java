/*
The structure of Expression Tree is a binary tree to evaluate certain expressions.
All leaves of the Expression Tree have an number string value. All non-leaves of the Expression Tree have an operator string value.

Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.

Example 1: 
Input: ["2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"]
Output: {[-],[*],[/],[2],[6],[+],[+],#,#,#,#,[23],[7],[1],[2]}
Explanation:
The expression tree will be like

	                 [ - ]
	             /          \
	        [ * ]              [ / ]
	      /     \           /         \
	    [ 2 ]  [ 6 ]      [ + ]        [ + ]
	                     /    \       /      \
	                   [ 23 ][ 7 ] [ 1 ]   [ 2 ] .

After building the tree, you just need to return root node `[-]`. 

Example 2: 
Input: ["10","+","(","3","-","5",")","+","7","*","7","-","2"]
Output: {[-],[+],[2],[+],[*],#,#,[10],[-],[7],[7],#,#,[3],[5]} 
Explanation:
The expression tree will be like
 	                       [ - ]
	                   /          \
	               [ + ]          [ 2 ]
	           /          \      
	       [ + ]          [ * ]
              /     \         /     \
	  [ 10 ]  [ - ]    [ 7 ]   [ 7 ]
	          /    \ 
                [3]    [5]
After building the tree, you just need to return root node `[-]`.

idea:

https://www.jiuzhang.com/solutions/expression-tree-build/#tag-highlight-lang-java
https://www.jianshu.com/p/22990628d74e

这个包含 build and evaluate 两个题目



*/

import java.util.*;

class ExpressionTreeNode {
	public String symbol;
	public ExpressionTreeNode left, right;

	public ExpressionTreeNode(String symbol) {
		this.symbol = symbol;
		this.left = this.right = null;
	}
}

public class BuildExpressionTreeNode {
	public static void main(String[] args) {
		BuildExpressionTreeNode eg = new BuildExpressionTreeNode();

		String[] expression = {"2","*","8","-","(","23","+","7",")","/","(","1","+","2",")"};

		ExpressionTreeNode node = eg.build(expression);

		int eval = eg.eval(node);

		System.out.println(eval);
	}

	public int getValue(String s) {
		int num = 0;
		// check if positive or negative
		if (s.charAt(0) != '-') {
			for (int i = 0; i < s.length(); i++)  {
				num = num * 10 + (s.charAt(i) - '0');  
			}
		} else {
			for (int i = 1; i < s.length(); i++) { 
				num = num * 10 + (s.charAt(i) - '0');  
			}
			num = num * -1; 
		}

		return num;
	}

	// This function receives a node of the syntax tree and recursively evaluates it
	public int eval(ExpressionTreeNode root) {
		if (root == null) {
			return 0;
		}

		// leaf node, can only be value
		if (root.left == null && root.right == null) {
			return getValue(root.symbol);
		}

		int leftVal = eval(root.left);
		int rightVal = eval(root.right);  
		
		// Check which operator to apply  
		if (root.symbol.equals("+")) {
			return leftVal + rightVal;  
		}
		
		if (root.symbol.equals("-")) {
			return leftVal - rightVal;  
		}
		
		if (root.symbol.equals("*")) {
			return leftVal * rightVal;  
		}
		
		return leftVal / rightVal;  
	}

	// Sat May 18 21:21:10 2019
	// new version
	public ExpressionTreeNode build(String[] expression) {
        Stack<ExpressionTreeNode> op = new Stack<ExpressionTreeNode>();
        Stack<ExpressionTreeNode> data = new Stack<ExpressionTreeNode>();

        for (int i=0;i<expression.length;i++) {
            String tmp = expression[i];
            char firstc = tmp.charAt(0);

            if (Character.isDigit(firstc)) {
            	ExpressionTreeNode data1 = new ExpressionTreeNode(tmp);
                data.push(data1);
            } else {
                switch (firstc) {
                    case '(':
                        ExpressionTreeNode node = new ExpressionTreeNode(tmp);
                        op.push(node);
                        break;

                    case '+':
                    case '-':
                        while(!op.isEmpty()&&op.peek().symbol.charAt(0)!='(') {
                            ExpressionTreeNode opnode = op.pop();
                            ExpressionTreeNode data1 = data.pop();
                            ExpressionTreeNode data2 = data.pop();
                            opnode.left = data2;
                            opnode.right = data1;
                            data.push(opnode);
                        }
                        ExpressionTreeNode node2 = new ExpressionTreeNode(tmp);
                        op.push(node2);
                        break;

                    case '*':
                    case '/':
                        while (!op.isEmpty()&&(op.peek().symbol.charAt(0)=='*'||op.peek().symbol.charAt(0)=='/')) {
                            ExpressionTreeNode opnode = op.pop();
                            ExpressionTreeNode data1 = data.pop();
                            ExpressionTreeNode data2 = data.pop();
                            opnode.left = data2;
                            opnode.right = data1;
                            data.push(opnode);
                        }
                        ExpressionTreeNode node3 = new ExpressionTreeNode(tmp);
                        op.push(node3);
                        break;

                    case ')':
                        while(op.peek().symbol.charAt(0)!='('){
                            ExpressionTreeNode opnode = op.pop();
                            ExpressionTreeNode data1 = data.pop();
                            ExpressionTreeNode data2 = data.pop();
                            opnode.left = data2;
                            opnode.right = data1;
                            data.push(opnode);
                        }
                        op.pop();
                        break;
                }
            }
        }

        while (!op.isEmpty()) {
            ExpressionTreeNode opnode = op.pop();
            ExpressionTreeNode data1 = data.pop();
            ExpressionTreeNode data2 = data.pop();

            opnode.left = data2;
            opnode.right = data1;

            data.push(opnode);
        }

        if (data.isEmpty()) {
        	return null;
        }

        return data.pop();
	}


    // class TreeNode {
    //     int val;
    //     ExpressionTreeNode eNode;
    //     public TreeNode(int val, String s) {
    //         this.val = val;
    //         eNode = new ExpressionTreeNode(s);
    //     }
    // }
    // /**
    //  * @param expression: A string array
    //  * @return: The root of expression tree
    //  */
    // public ExpressionTreeNode build(String[] expression) {
    //     if (expression == null || expression.length == 0) {
    //         return null;
    //     }

    //     Stack<TreeNode> stack = new Stack<TreeNode>();
    //     int base = 0;
    //     int val = 0;

    //     for (int i = 0; i < expression.length; i++) {
    //         if (expression[i].equals("(")) {
    //             base += 10;
    //             continue;
    //         }

    //         if (expression[i].equals(")")) {
    //             base -= 10;
    //             continue;
    //         }

    //         val = getWeight(base, expression[i]);
    //         TreeNode node = new TreeNode(val, expression[i]);
    //         while (!stack.isEmpty() && node.val <= stack.peek().val) {
    //             node.eNode.left = stack.pop().eNode;
    //         }
    //         if (!stack.isEmpty()) {
    //             stack.peek().eNode.right = node.eNode;
    //         }
    //         stack.push(node);
    //     }
    //     if (stack.isEmpty()) {
    //         return null;
    //     }
    //     TreeNode rst = stack.pop();
    //     while (!stack.isEmpty()) {
    //         rst = stack.pop();
    //     }
    //     return rst.eNode;
    // }

    // // Calculate weight for characters
    // public int getWeight(int base, String s) {
    //     if (s.equals("+") || s.equals("-")) {
    //         return base + 1;
    //     }

    //     if (s.equals("*") || s.equals("/")) {
    //         return base + 2;
    //     }

    //     return Integer.MAX_VALUE;
    // }
}