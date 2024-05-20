/*
An original string, consisting of lowercase English letters, can be encoded by the following steps:

Arbitrarily split it into a sequence of some number of non-empty substrings.
Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
Concatenate the sequence as the encoded string.
For example, one way to encode an original string "abcdefghijklmnop" might be:

Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
Concatenate the elements of the sequence to get the encoded string: "ab121p".
Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

Example 1:
Input: s1 = "internationalization", s2 = "i18n"
Output: true
Explanation: It is possible that "internationalization" was the original string.
- "internationalization" 
  -> Split:       ["internationalization"]
  -> Do not replace any element
  -> Concatenate:  "internationalization", which is s1.
- "internationalization"
  -> Split:       ["i", "nternationalizatio", "n"]
  -> Replace:     ["i", "18",                 "n"]
  -> Concatenate:  "i18n", which is s2

Example 2:
Input: s1 = "l123e", s2 = "44"
Output: true
Explanation: It is possible that "leetcode" was the original string.
- "leetcode" 
  -> Split:      ["l", "e", "et", "cod", "e"]
  -> Replace:    ["l", "1", "2",  "3",   "e"]
  -> Concatenate: "l123e", which is s1.
- "leetcode" 
  -> Split:      ["leet", "code"]
  -> Replace:    ["4",    "4"]
  -> Concatenate: "44", which is s2.

Example 3:
Input: s1 = "a5b", s2 = "c5b"
Output: false
Explanation: It is impossible.
- The original string encoded as s1 must start with the letter 'a'.
- The original string encoded as s2 must start with the letter 'c'.

Constraints:
1 <= s1.length, s2.length <= 40
s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
The number of consecutive digits in s1 and s2 does not exceed 3.

idea:
https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/solutions/1660436/java-dp-memoization/

*/

class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {
    public boolean possiblyEquals(String s1, String s2) {
        return dfs(s1.toCharArray(), s2.toCharArray(), 0, 0, 0, new Boolean[s1.length()+1][s2.length()+1][2001]);
    }

    boolean dfs(char[] s1, char[] s2, int i, int j, int diff, Boolean[][][] dp) {
        if (i == s1.length && j == s2.length) {
            return diff == 0;
        }

        if (dp[i][j][diff+1000] != null)
            return dp[i][j][diff+1000];

        // if both i and j are at the same location and chars are same then simply increment both pointers
        if (i < s1.length && j < s2.length && diff == 0 && s1[i] == s2[j]) {
            if(dfs(s1, s2, i+1, j+1, diff, dp)) {
               return dp[i][j][diff+1000] = true;
            }
        }

        // if s1[i] is literal and diff > 0 then increment i and decrement diff by 1
        if (i < s1.length && !Character.isDigit(s1[i]) && diff > 0 && dfs(s1, s2, i+1, j, diff-1, dp)) {
            return dp[i][j][diff+1000] = true;
        }

        // if s2[j] is literal and diff < 0 then increment j and increment diff by 1
        // as we are done with the current jth char
        if (j < s2.length && !Character.isDigit(s2[j]) && diff < 0 && dfs(s1, s2, i, j+1, diff+1, dp)) {
            return dp[i][j][diff+1000] = true;
        }

        // wildcard matching in s1
        // if s1 contains l123
        // then need to check with val as 1 then val as 12 and val as 123
        for (int k = i, val = 0; k < i + 4 && k < s1.length && Character.isDigit(s1[k]); k++) {
            val = val * 10 + s1[k] -'0';
            if (dfs(s1, s2, k+1, j, diff-val, dp)) {
                return dp[i][j][diff+1000] = true;
            }
        }

        // wildcard matching in s2
        // if s2 contains l123
        // then need to check with val as 1 then val as 12 and val as 123
        for (int k = j, val = 0; k < j + 4 && k < s2.length && Character.isDigit(s2[k]); k++) {
            val = val * 10 + s2[k] -'0';
            if (dfs(s1, s2, i, k+1, diff+val, dp)) {
                return dp[i][j][diff+1000] = true;
            }
        }

        return dp[i][j][diff+1000] = false;
    }
}