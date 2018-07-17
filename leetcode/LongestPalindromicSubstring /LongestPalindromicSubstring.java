/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:
Input: "babad"
Output: "bab"

Note: "aba" is also a valid answer.

Example:
Input: "cbbd"
Output: "bb"

idea:
http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
1. brute force
2. from each char or space in-between chars to scan towards both directions
a string of length n, the number of central points should be 2 * n - 1
字符作为中心有n个, 间隙有n-1个 e.g., abc, 中心可以是a, b, c or ab的间隙, bc的间隙
往两边同时进行扫描, 直到不是回文串为止
这个思路是对的 但是不是为了 中心 而是为了到达最后一个char 在string
3. dynamic programming
外层循环i从后往前扫
内层循环j从i当前字符扫到结尾处
使用的历史信息是从i+1到n之间的任意子串是否是回文 boolean array is used to record, 需要比较一下头尾字符

*/
class LongestPalindromicSubstring {
	public static void main(String[] args) {
		new LongestPalindromicSubstring();		
	}
	// constructor
	public LongestPalindromicSubstring() {
		String s = "abracadabra";
		System.out.println(s + " isPalindromicString == " + isPalindromic(s));
		System.out.println(s + " getLongestPalindromicSubstring == " + longestPalindrome(s));
	}
	// get LongestPalindromicSubstring
	public String longestPalindrome(String s) {
        String palindrome = "";
        int maxSubPalindromeLength = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String temp = s.substring(i, j);
                int len = j - i + 1;
                if ( isPalindromic(temp) ) {
                    if (len > maxSubPalindromeLength) {
                        palindrome = temp;
                        maxSubPalindromeLength = len;
                    }
                }
            }
        }

        return palindrome;
    }
    
    private boolean isPalindromic(String s) {
        int size = s.length();
        for (int i = 0; i < size/2; i++) {
            if (s.charAt(i) != s.charAt(size - 1 - i)) {
                return false;
            }
        }
        return true;
    }
 	// 2nd method
 	public String longestPalindrome(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }
        
        int n = s.length();
        int max = 0;
        String longest = "";
        
        for (int i = 0; i < n * 2 - 1; i++) {
            // note, a good way to handle parity
            int left = i / 2;
            int right = (i + 1) / 2;
            
            String palindrome = getPalindrome(s, left, right);
            
            if (palindrome.length() > max) {
                max = palindrome.length();
                longest = palindrome;
            }
        }
        
        return longest;
    }
    
    private String getPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }

    // 3rd method dynamic programming
    public String longestPalindrome(String s) {
        String res = "";
        if (s == null || s.length() == 0) {
            return res;
        }

        int maxLength = 0;
        int size = s.length();
        boolean[][] palindromic = new boolean[size][size];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if ( s.charAt(i) == s.charAt(j) && (j - i <= 2 || palindromic[i+1][j-1]) ) {
                    palindromic[i][j] = true;
                    if (maxLength < j - i + 1) {
                        maxLength = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }

        return res;
    }
}