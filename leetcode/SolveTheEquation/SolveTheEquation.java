/*
Solve a given equation and return the value of x in the form of string "x=#value".
The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".
If there are infinite solutions for the equation, return "Infinite solutions".
If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"

Example 2:
Input: "x=x"
Output: "Infinite solutions"

Example 3:
Input: "2x=x"
Output: "x=0"

Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"

Example 5:
Input: "x=x+2"
Output: "No solution"

idea:
1. regular expression, https://discuss.leetcode.com/topic/95203/concise-java-solution
note: exp.split("(?=[-+])");
2. parse the string, https://www.cnblogs.com/immjc/p/7158013.html
*/
public class SolveTheEquation {
	public static void main(String[] args) {
	}
	// method 2
    public String solveEquation(String equation) {
    	if (equation.length() == 0 || equation == null) {
    		return equation;
    	}
    	int[] left = parse(equation.split("=")[0]);
    	int[] right = parse(equation.split("=")[1]);
    	int xCnt = left[0] - right[0];
    	int constant = right[1] - left[1];
    	if (xCnt == 0) {
    		return constant == 0 ? "Infinite solutions" : "No solution";
    	}
    	else if (constant == 0) {
    		return "x=0";
    	}
    	else {
    		return "x=" + constant / xCnt;
    	}
    }

    public int[] parse(String s) {
    	int prev = 1; // sign
    	int xCnt = 0;
    	int constant = 0;
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (c == 'x') {
    			xCnt += prev;
    		}
    		else {
    			if (c == '+') {
    				prev = 1;
    			}
    			else if (c == '-') {
    				prev = -1;
    			}
    			else {
    				int val = 0;
    				while (i < s.length() && Character.isDigit(s.charAt(i))) {
    					val = val * 10 + (int) (s.charAt(i) - '0');
    					i++;
    				}
    				if (i < s.length() && s.charAt(i) == 'x') {
    					xCnt += prev * val;
                        i++;
    				}
    				else {
    					constant += prev * val;
    				}
    				i--;
    			}
    		}
    	}

    	return new int[] {xCnt, constant};
    }
    // method 1
    public String solveEquation(String equation) {
	    int[] left = evaluateExpression(equation.split("=")[0]); 
	  	int[] right = evaluateExpression(equation.split("=")[1]);
	    left[0] -= right[0];
	    left[1] = right[1] - left[1];
	    if (left[0] == 0 && left[1] == 0) return "Infinite solutions";
	    if (left[0] == 0) return "No solution";
	    return "x=" + left[1] / left[0];
	}  

	public int[] evaluateExpression(String exp) {
	    String[] tokens = exp.split("(?=[-+])");
	    int[] res = new int[2];
	    for (String token : tokens) {
	        if (token.equals("+x") || token.equals("x")) res[0] += 1;
			else if (token.equals("-x")) res[0] -= 1;
			else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
			else res[1] += Integer.parseInt(token);
	    }

	    return res;
	}
}