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
http://blog.csdn.net/worldwindjp/article/details/19938131
*/

public class DecodeWays {
    // 225 / 259 test cases passed
    public int numDecodings(String s) {
        List<Integer> ret = new ArrayList<Integer>();
        if (s == null || s.length() == 0) {
            return 0;
        }
        helper(s, ret);
        
        return ret.size();
    }
    
    public void helper(String s, List<Integer> ret) {
        if (s.length() == 0) {
            ret.add(1);
        }
        else { // two cases
            // 1-9
            if (s.charAt(0) >= '1' && s.charAt(0) <= '9') {
                helper(s.substring(1), ret);
            }
            if (s.length() >= 2) {
                // 10-19 and 20-26
                if ( (s.charAt(0) == '1' && s.charAt(1) >= '0' && s.charAt(1) <= '9') || (s.charAt(0) == '2' && s.charAt(1) >= '0' && s.charAt(1) <= '6') ) {
                    helper(s.substring(2), ret);
                }
            } 
        }
    }

    public int numDecodings(String s) {  
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;  
        }
        // ways[i] is s[0, i - 1] the number of decoding ways
        // ways[i] = ways[i - 1] + ways[i - 2], because there are at most 26, two digits
        int[] ways = new int[s.length() + 1];
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= s.length(); i++) {  
            // check if current char is '0'
            if (s.charAt(i - 1) != '0') {
                ways[i] += ways[i - 1];  
            }
            // check if current char + previous char in 1 - 26
            if (s.charAt(i - 2) != '0') {
                int currTwoChars = Integer.parseInt(s.substring(i-2, i));  
                if (currTwoChars >= 1 && currTwoChars <= 26) {  
                    ways[i] += ways[i - 2];  
                }  
            }  
        }

        return ways[s.length()];  
    }
}
