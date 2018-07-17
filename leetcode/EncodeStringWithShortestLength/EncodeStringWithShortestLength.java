/*
Given a non-empty string, encode the string such that its encoded length is the shortest.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.

Example 1:
Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.

Example 2:
Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.

Example 3:
Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".

Example 4:
Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".

Example 5:
Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".

idea:
https://www.cnblogs.com/grandyang/p/6194403.html
DP again
note, dp[i][j]表示[i, j] substring compressed format e.g. 2[ab] 
关键是找sub = abcabc 这种可压缩的情况
其中sub = s[i,j]
用sub+sub = abcabcabcabc
找第二个s在s+s里出现的位置
如果不是len(sub)
则说明sub有重复
那么就要压缩这个sub
重复次数是len(sub) / indexOf(sub, 1)
重复的string用的是之前压缩过的dpi
index = indexOf(sub, 1)。
*/

class EncodeStringWithShortestLength {
    public static void main(String[] args) {
        String s = "abbbabbbcabbbabbbc";
        EncodeStringWithShortestLength eg = new EncodeStringWithShortestLength();
        String encoded = eg.encode(s);
        System.out.println(encoded);
    }

    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];
        
        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                String substr = s.substring(i, j + 1);
                int subLen = j + 1 - i;
                // Checking if string length < 5. In that case, encoding will not help.
                if (subLen < 5) {
                    dp[i][j] = substr;
                } else {
                    dp[i][j] = substr;
                    // Loop for trying all results that we get after dividing the strings into 2 and combine the results of 2 substrings
                    for (int k = i; k < j; k++) {
                        if ((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k + 1][j];
                        }
                    }
                    // Loop for checking if string can itself find some pattern in it which could be repeated.
                    for (int k = 0; k < subLen; k++) {
                        String repeatStr = substr.substring(0, k + 1);
                        int repeatStrLen = k + 1;
                        if (repeatStr != null && subLen % (k + 1) == 0 && substr.replaceAll(repeatStr, "").length() == 0) {
                            String duplicateCompressed = subLen / (k + 1) + "[" + dp[i][i + k] + "]";
                            if (duplicateCompressed.length() < dp[i][j].length()) {
                                dp[i][j] = duplicateCompressed;
                            }
                        }
                    }
                }
            }
        }
        
        return dp[0][n - 1];
    }
}