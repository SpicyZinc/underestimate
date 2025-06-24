/*
You are given an array of strings words. Each element of words consists of two lowercase English letters.
Create the longest possible palindrome by selecting some elements from words and concatenating them in any order.
Each element can be selected at most once.

Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
A palindrome is a string that reads the same forward and backward.

Example 1:
Input: words = ["lc","cl","gg"]
Output: 6
Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
Note that "clgglc" is another longest palindrome that can be created.

Example 2:
Input: words = ["ab","ty","yt","lc","cl","ab"]
Output: 8
Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
Note that "lcyttycl" is another longest palindrome that can be created.

Example 3:
Input: words = ["cc","ll","xx"]
Output: 2
Explanation: One longest palindrome is "cc", of length 2.
Note that "ll" is another longest palindrome that can be created, and so is "xx".

Constraints:

1 <= words.length <= 105
words[i].length == 2
words[i] consists of lowercase English letters.

idea:
hashmap + greedy
"aa" string only appear once in the string
*/

class LongestPalindromeByConcatenatingTwoLetter {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        int res = 0;
        boolean hasMid = false;

        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        for (String word : count.keySet()) {
            String rev = new StringBuilder(word).reverse().toString();

            // 情况 1：互为反转且不相等，如 ab 和 ba
            if (!word.equals(rev) && count.containsKey(rev)) {
                int pairs = Math.min(count.get(word), count.get(rev));
                res += pairs * 4;
                count.put(word, count.get(word) - pairs);
                count.put(rev, count.get(rev) - pairs);
            }
        }

        // 再处理形如 "gg" 这种自身对称的
        for (String word : count.keySet()) {
            if (word.charAt(0) == word.charAt(1)) {
                int pairs = count.get(word) / 2;
                res += pairs * 4;

                // 允许一个单独放在中间（只加 2）
                if (count.get(word) % 2 == 1 && !hasMid) {
                    res += 2;
                    hasMid = true;
                }
            }
        }

        return res;
    }
}
