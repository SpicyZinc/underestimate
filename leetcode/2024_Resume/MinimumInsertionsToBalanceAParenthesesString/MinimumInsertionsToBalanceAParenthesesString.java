/*
Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.


Example 1:
Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.

Example 2:
Input: s = "())"
Output: 0
Explanation: The string is already balanced.

Example 3:
Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.

Constraints:

1 <= s.length <= 10^5
s consists of '(' and ')' only.

idea:
注意 ()) 这是一对
1- Take a counter, initially it is zero.
2- if '(' this came then insert in stack.
3- if ')' came then check if next of current is closing if this is the case then skip it otherwise increase count.
4- check if stack isEmpty, Yes then increase count otherwise pop brackets.
5- return 2 * stack.size()
*/
class MinimumInsertionsToBalanceAParenthesesString {
    public int minInsertions(String s) {
        int openCount = 0;
        int closeCount = 0;

        int i = 0;
        int size = s.length();

        while (i < size) {
            char c = s.charAt(i);
            // keep increasing the open count if the open bracket is encountered
            if (c == '(') {
                openCount++;
            } else if (c == ')') { // if close is encountered 
                // if the next bracket encountered is close then we found the pair 
                if (i + 1 < size && s.charAt(i + 1) == ')') {
                    // if open is greater we might have to reduce it as it is valid open 
                    if (openCount > 0) {
                        openCount--;
                    } else { // instead of open bracket, close will be incremented here, since in the final result close is not doubled
                        closeCount++;
                    }
                    // since we compared the next character already we can increment it
                    i++;
                } else { // if next is not close bracket we might have to add ) to make it valid and remove open since we found the pair
                    if (openCount > 0) {
                        openCount--;
                        closeCount++;
                    } else { // else we might have to add both open and close hence adding 2 here
                      closeCount = closeCount + 2;  
                    }
                }
            }
            i++;
        }
        // for every open bracket we require 2 times of close brackets + closeCount
        return openCount * 2 + closeCount;
    }
}
