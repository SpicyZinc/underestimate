/*
Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.

Example 1:
Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.

Example 2:
Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

Example 3:
Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.

Constraints:
1 <= s.length <= 100
0 <= k <= s.length
s contains only lowercase English letters.
*/

const getLengthOfOptimalCompression = function (s, k) {
  const n = s.length
  const dp = new Array(n + 1).fill(n).map((row) => new Array(k + 1).fill(n))
  dp[0][0] = 0

  for (let i = 1; i <= n; i++) {
    for (let j = 0; j <= k; j++) {
      let letterCount = 0
      let deletion = 0
      // keep s[i], compress same letters, remove different letters
      for (let l = i; l >= 1; l--) {
        if (s.charAt(l - 1) === s.charAt(i - 1)) letterCount++
        else deletion++
        // places = length needed to rep compressed letters.
        // 0 places for count = 1,0, 1 place = <10, 10-99 requires 2 places, 100+ requires 3
        let places = 0
        if (letterCount >= 100) places = 3
        else if (letterCount >= 10) places = 2
        else if (letterCount >= 2) places = 1
        if (j - deletion >= 0) {
          dp[i][j] = Math.min(dp[i][j], dp[l - 1][j - deletion] + 1 + places)
        }
      }
      // delete
      if (j > 0) {
        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1])
      }
    }
  }
  return dp[n][k]
}