/*
Given a string, determine if a permutation of the string could form a palindrome.
For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:
Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?

idea:
https://segmentfault.com/a/1190000003790181

count the occurrence of each character, only at most one character appearing odd times
or use hash table
*/
public class PalindromePermutation {
    // Fri May  3 13:42:14 2024
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }

        return count <= 1;
    }

    // direct method
    // 数个数 最多一个letter是奇数 关键是加 += count%2
    public boolean canPermutePalindrome(String s) {
        int[] chars = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            chars[c - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            count += chars[i] % 2;
        }
        return count <= 1;
    }

    public boolean canPermutePalindrome(String s) {
        int[] cnts = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnts[c] += 1;
        }

        boolean hasOneOddChar = false;
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] % 2 == 1) {
                if (!hasOneOddChar) {
                    hasOneOddChar = true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}