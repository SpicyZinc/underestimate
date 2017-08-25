/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 105 and will be ≥ k.
The given num does not contain any leading zero.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

idea:
1. timely break very important in this problem
2. k times, each time remove one digit
3. no leading zero and higher digit takes precedence than number in lower digits, so this is the removing rule
*/

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
    	if (num.length() <= k) {
    		return "0";
    	}
    	String ans = num;
    	for (int i = 0; i < k; i++) {
    		ans = removeOneDigit(ans);
    	}
        return ans;
    }

    public String removeOneDigit(String ans) {
    	int n = ans.length();
    	int indexToRemove = n - 1;
    	for (int i = 0; i < n - 1; i++) {
    		if (ans.charAt(i) > ans.charAt(i + 1)) {
    			indexToRemove = i;
    			// very crucial
    			break;
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < n; i++) {
    		char c = ans.charAt(i);
    		// no leading zero or regular skip
    		if (sb.length() == 0 && c == '0' || i == indexToRemove) {
    			continue;	
    		}
    		sb.append(c);
    	}

    	return sb.length() == 0 ? "0" : sb.toString();
    }
}