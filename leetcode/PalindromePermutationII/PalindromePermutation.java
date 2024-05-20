/*
Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

Example 1:
Input: "aabb"
Output: ["abba", "baab"]

Example 2:
Input: "abc"
Output: []

idea:
https://www.cnblogs.com/grandyang/p/5315227.html

有多于一个(比如两个)奇数个char的 return empty
半个 string %2 加入chars 来 permute
helper method permute() to 得到所有的 permutations + mid + permutation.reverse()
用set to remove duplicates

这个题就是 permute 和 palindrome 的完美结合
*/
import java.util.*;

class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutation eg = new PalindromePermutation();
        // String s = "aabb";
        // String s = "a";
        String s = "abc";
        List<String> result = eg.generatePalindromes(s);
        System.out.println(result);
    }

    // Tue May 14 23:20:30 2019
    Set<String> set = new HashSet<>();

    public List<String> generatePalindromes(String s) {
        int[] map = new int[128];
        // by the way, populate the map
        if (!canPermutePalindrome(s, map)) {
            return new ArrayList<>();
        }
        // 找出一半来 permute
        char[] st = new char[s.length() / 2];
        char oddChar = 0;
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            // 找出出现了一次的char
            if (map[i] % 2 == 1) {
                oddChar = (char) i;
            }

            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }
        permute(st, 0, oddChar);

        return new ArrayList<String>(set);
    }
    // 能否有可能成为palindrome
    public boolean canPermutePalindrome(String s, int[] map) {
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

    public void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
        } else {
            for (int i = l; i < s.length; i++) {
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    
    // Fri May  3 16:25:09 2024
    // TLE
    Set<String> set = new HashSet<>();
    public List<String> generatePalindromes(String s) {
        permute(s.toCharArray(), 0);
        return new ArrayList<String>(set);
    }

    public void permute(char[] s, int idx) {
        if (idx == s.length) {
            if (isPalindrome(s)) {
                set.add(new String(s));
            }
        } else {
            for (int i = idx; i < s.length; i++) {
                swap(s, idx, i);
                permute(s, idx + 1);
                swap(s, idx, i);
            }
        }
    }

    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public boolean isPalindrome(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            if (s[i] != s[s.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }


    public void permute(char[] chars, int i, List<StringBuilder> permutations) {
        // note, both working
        // if (i == chars.length - 1) {
        if (i == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(c);
            }
            permutations.add(sb);
        } else {
            for (int j = i; j < chars.length; j++) {
                swap(chars, i, j);
                permute(chars, i + 1, permutations);
                swap(chars, i, j);
            }
        }
    }
}