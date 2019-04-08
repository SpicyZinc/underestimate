/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

idea:
try different combination, remove any '(' or ')' from s, and build a new string without it,
see if the new string is valid parentheses, hashset to maintain uniqueness

note: if the first s in the queue is valid,
any other actions of removing '(' or ')' would NOT generate valid parentheses,
so it is redundant, so better return first.
*/

import java.util.*;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses eg = new RemoveInvalidParentheses();
        List<String> result = eg.removeInvalidParentheses("()())()");
        for (String p : result) {
            System.out.println(p);
        }
    }
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            result.add(s);
            return result;
        }
        
        Queue<String> queue = new LinkedList<>();
        Set<String> hs = new HashSet<>();
        queue.offer(s);
        hs.add(s);
        
        boolean found = false;
        while (!queue.isEmpty()) {
            String p = queue.poll();

            if (isValidP(p)) {
                found = true;
                result.add(p);
                // if last potential parenthesis in queue is valid, no need to remove any ( or ) from it
                // because all new strings cannot be valid, return to save time
                if (queue.size() == 0) {
                	return result; 
                }
            }

            if (found) {
                continue;
            }
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);

                if (c == ')' || c == '(') {
                    String newStr = p.substring(0, i) + p.substring(i + 1);
                    if (hs.add(newStr)) {
                        queue.offer(newStr);
                    }
                }
            }
        }

        return result;
    }
    // helper to determine if a string is a valid parenthesis
    private boolean isValidP(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cnt++;
            }
            if (c == ')') {
                if (cnt == 0) {
                    return false;
                }
                cnt--;
            }
        }

        return cnt == 0;
    }
}