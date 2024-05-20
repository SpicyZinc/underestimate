/*
You are given a string s containing one or more words. Every consecutive pair of words is separated by a single space ' '.
A string t is an anagram of string s if the ith word of t is a permutation of the ith word of s.

For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc bef" are not.
Return the number of distinct anagrams of s. Since the answer may be very large, return it modulo 10^9 + 7.


Example 1:
Input: s = "too hot"
Output: 18
Explanation: Some of the anagrams of the given string are "too hot", "oot hot", "oto toh", "too toh", and "too oht".

Example 2:
Input: s = "aa"
Output: 1
Explanation: There is only one anagram possible for the given string.

Constraints:
1 <= s.length <= 105
s consists of lowercase English letters and spaces ' '.
There is single space between consecutive words.

idea:
We count permutations of each word, and multiply those permutations.
The number of permutations with repetitions is a factorial of word length, divided by factorial of count of each character.
*/

class CountAnagrams {
    long mod = (long) 1e9+7;
    public int countAnagrams(String s) {
        int n = s.length();
        long[] fact = new long[n + 1];
        fact[0] = 1L;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % mod;
        }

        long answer = 1;
        for (String word : s.split(" ")) {
            int[] ch = new int[26];
            for (char c : word.toCharArray()) {
                ch[c-'a']++;
            }

            long cur = 1;
            for (int a:ch) {
                cur = cur * fact[a] % mod;
            }
            cur = fact[word.length()] * binaryPow(cur, mod-2) % mod; // cur ^ mod / cur ^ 2
            answer = answer * cur % mod;
        }

        return (int) answer;
    }

    long binaryPow(long a, long b) {
        if (b == 0) return 1;

        long res = binaryPow(a, b / 2);
        res = res * res % mod;
        return b % 2 == 0 ? res : res * a % mod;
    }
}
