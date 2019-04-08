/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

idea:
recursion

This is a classic combinatorial problem 
that manifests itself in many different ways. These problems are essentially identical:

    Generating all possible ways to balance N pairs of parentheses (i.e. this problem)
    Generating all possible ways to apply a binary operator to N+1 factors
    Generating all full binary trees with N+1 leaves

http://stackoverflow.com/questions/3172179/valid-permutation-of-parenthesis

keep track of how many open and close parentheses are "on stock" for us to use as we're building the string recursively.

    If there's nothing on stock, the string is fully built and you can just print it out
    If there's an open parenthesis available on stock, try and add it on.
        Now you have one less open parenthesis, but one more close parenthesis to balance it out
    If there's a close parenthesis available on stock, try and add it on.
        Now you have one less close parenthesis

Note that if you swap the order of the recursion 
such that you try to add a close parenthesis before you try to add an open parenthesis,
you simply get the same list of balanced parenthesis but in reverse order!

Because concatenating one character to an immutable String of length N is an O(N) operation
use a fixed-size char[] and an index variable to replace String
*/

import java.util.*;

public class GenerateParentheses {
	public static void main(String[] args) {
		GenerateParentheses eg = new GenerateParentheses();
		List<String> result = eg.generateParenthesis(3);
		System.out.println(result.size() + " parentheses are generated:");
		for (String parenthesis : result) {
			System.out.println( parenthesis );
		}
	}

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        if (n == 0) {
            return result;
        }
        
        dfs(n, 0, "", result);
        
        return result;
    }

    public void dfs(int left, int right, String parenthesisAcc, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(parenthesisAcc);
            return;
        }
        
        if (left > 0) {
            dfs(left - 1, right + 1, parenthesisAcc + "(", result);
        }
        
        if (right > 0) {
            dfs(left, right - 1, parenthesisAcc + ")", result);
        }
    }
}