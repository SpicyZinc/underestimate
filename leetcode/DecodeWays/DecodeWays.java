/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
The number of ways decoding "12" is 2.

idea:
1. recursion
Each time we try to encode the first digit in the message to a letter, 
or we can encode the first two digits into a letter if possible, we then recursively do the same thing for the sub-string. 
When there is no way to encode (e.g., encountering a single '0', or encountering '32' and trying to encode the two-digits 
to a letter), we simply return. 
If there is no sub-string to go on, we know we have found a valid solution, so we increase the count

2. DP:
http://blog.csdn.net/linhuanmars/article/details/24570759

ways[i] = ways[i - 1] + ways[i - 2], because there are at most 26, two digits
ways[i] is the number of decoding ways for substring(0, i)
*/

public class DecodeWays {
    // 240 / 259 test cases passed
    public int numDecodings(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        return dfs(s);
    }
    
    public int dfs(String s) {
        // stopping case
        if (s.length() == 0 || s == null) {
            return 1;
        }
        // case '0' as input
        if (s.charAt(0) == '0') {
            return 0; 
        }

        int cnt = dfs(s.substring(1));
        if (s.length() >= 2) {
            char first = s.charAt(0);
            char second = s.charAt(1);
            if (first == '1' || first == '2' && second >= '0' && second <= '6') {
                cnt += dfs(s.substring(2));
            }
        }

        return cnt;
    }
    // DP
    public int numDecodings(String s) {  
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;  
        }

        int[] ways = new int[s.length() + 1];
        ways[0] = 1;
        ways[1] = 1;

        for (int i = 2; i <= s.length(); i++) {
            char first = s.charAt(i - 2);
            char second = s.charAt(i - 1);

            // 12 1(A) and 2(B) or 12 as L 两种
            // 但是一旦有0 就不行了
            if (second != '0') {
                ways[i] += ways[i - 1];  
            }

            // 如果是1 second可以是[0-9] 没有限制
            // 如果是2 second只能是[0-6]
            if (first == '1' || first == '2' && second >= '0' && second <= '6') {
                ways[i] += ways[i - 2];  
            }
        }

        return ways[s.length()];
    }
}
