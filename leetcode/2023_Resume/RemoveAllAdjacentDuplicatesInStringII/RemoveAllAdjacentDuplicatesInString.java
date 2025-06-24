/*
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique. 

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lowercase English letters.

idea:
use some array or string builder to simulate stack
*/

class Pair {
    char character;
    int count;

    public Pair(char character, int count) {
        this.character = character;
        this.count = count;
    }
}

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == stack.peek().character) {
                stack.peek().count++;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(s.charAt(i), 1));
            }
        }

        for (Pair p : stack) {
            // 记得不足k的要 都 放进去
            while (p.count > 0) {
                sb.append(p.character);
                p.count--;
            }
        }

        return sb.toString();
    }


    public String removeDuplicates(String s, int k) {
        // 这个就是类似那个stack的作用
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            int size = sb.length();
            int last = size - 1;
            // 目前为止 重复char的个数 如果有不同 马上reset为一
            count[last] = (last > 0 && sb.charAt(last) == sb.charAt(last - 1) ? count[last - 1] : 0) + 1;
            if (count[last] >= k) {
                sb.delete(size - k, size);
            }
        }

        return sb.toString();
    }
}
