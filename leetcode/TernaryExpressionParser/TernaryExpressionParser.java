/*

In order to pick out useful "?" and ":", we can always begin with the last "?" and the first ":" after the chosen "?".
Therefore, directly seek for the last "?" (or you can simply put all "?" into a stack) and update the string depending on T or F until no more "?"s.

e.g.
"(F ? 1 : (T ? 4 : 5))" => "(F ? 1 : 4)" => "4"
"(T ? (T ? F : 5) : 3)" => "(T ? F : 3)" => "F"

idea:
Since the input is promised to be valid, life is much easier
Use a stack, check from the end of the string to the front.

As long as the next char is not '?', push it to the stack.
If we see a '?', check the next char(left of '?' in the string), 
    pop the top 3 elements from the stack.
    if the next char is T
        push the first element of the 3 elements back to stack
    else 
        pusht the 3rd element of the 3 elements back to stack
pop the only item left in the stack
*/

import java.util.*;

public class TernaryExpressionParser {
	public static void main(String[] args) {
		TernaryExpressionParser eg = new TernaryExpressionParser();
		String s = "F ? 1 : T ? 4 : 5";
		String t = "T ? T ? F : 5 : 3";
		String resultS = eg.parseTernary(s);
		String resultT = eg.parseTernary(t);

		System.out.println("s evaluated to be == " + resultS);
		System.out.println("t evaluated to be == " + resultT);
	}

	public String parseTernary(String expression) {
	    if (expression.length() == 0 || expression == null) {
	    	return null;
	    }

	    Stack<Character> stack = new Stack<Character>();
	    int i = expression.length() - 1;
	    expression = expression.trim();
	    while (i >= 0) {
	    	char c = expression.charAt(i);
	    	if (!stack.isEmpty() && stack.peek() == '?') {
	    		// first pop ?
	    		stack.pop();
	    		char first = stack.pop();
	    		// second pop :
	    		char second = stack.pop();
	    		char temp = c == 'T' ? first : second;
	    		stack.push(temp);
	    	}
	    	else {
	    		stack.push(c);
	    	}
	    	i--;
	    }

	    return String.valueOf(stack.peek());
	}
}
