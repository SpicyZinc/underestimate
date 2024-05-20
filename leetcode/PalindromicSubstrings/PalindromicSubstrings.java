/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:
The input string length won't exceed 1000.

idea:
有n + (n - 1) 个中心 以中心为准 向左右两边散发 这样符合palindrome的定义

1. substrings 中回文的最多个数
n char + (n - 1) spaces positions

2. damn Yushu Qin xia zhi hui Niantic onsite
https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome
*/

class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings eg = new PalindromicSubstrings();
        String s = "aaa";
        int count = eg.getCount(s);
        // int count = eg.countSubstrings(s);
        System.out.println("Count == " + count);
    }

    public int countSubstrings(String s) {
        int n = s.length();
        int cnt = 0;

        for (int i = 0; i < n * 2 - 1; i++) {
            // note, a good way to handle parity
            int left = i / 2;
            int right = (i + 1) / 2;

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                cnt++;
            }
        }

        return cnt;
    }

    // Fri Aug  6 18:25:08 2021
    public int getCount(String s) {
        int count = 0;
        int size = s.length();

        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
 
            int countOdd = getCountHelper(i, i, s); // imagine the sub string is odd length
            int countEven = getCountHelper(i, i + 1, s); // imagine the sub string is even length

            count += countOdd + countEven;
        }
            
        return count;
    }

    public int getCountHelper(int left, int right, String s) {
        int size = s.length();
        int count = 0;

         while (left >= 0 && right < size) {
            if (s.charAt(left) == s.charAt(right)) {
                count++;
            }
            left--;
            right++;
        }
        
        return count;
    }
}
