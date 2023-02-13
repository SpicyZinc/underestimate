/*
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Note:
The string size will be in the range [1, 100].

idea:
this is variation of valid parenthesis string check
if no *,
meet '(' count++
meet ')' count-- if count < 0 return false
return count == 0
now it has *, recursion
*/
class ValidParenthesisString {
    // Thu Sep  9 23:45:54 2021
    public boolean checkValidString(String s) {
        int cmin = 0, cmax = 0; // open parentheses count in range [cmin, cmax]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else if (c == '*') {
                cmax++; // if `*` become `(` then openCount++
                cmin--; // if `*` become `)` then openCount--
                // if `*` become `` then nothing happens
                // So openCount will be in new range [cmin-1, cmax+1]
            }
            cmin = Math.max(cmin, 0);   // It's invalid if open parentheses count < 0 that's why cmin can't be negative
            if (cmax < 0) return false; // Currently, don't have enough open parentheses to match close parentheses-> Invalid
                                        // For example: ())(
        }

        return cmin == 0; // Return true if can found `openCount == 0` in range [cmin, cmax]
    }

    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    public boolean check(String s, int start, int count) {
        if (count < 0) {
            return false;
        }

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            } else if (c == '*') {
            	// empty string, '(' or ')'
                return check(s, i + 1, count) || check(s, i + 1, count + 1) || check(s, i + 1, count - 1);
            }
        }

        return count == 0;
    }
}
