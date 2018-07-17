/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input: "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

idea:
The palindrome is either odd or even number of letters.
For odd number of letters, only the letter which appears the largest times would give the the longest palindrome, no more actions;
however, for those letters which appear odd times but less than the largest odd times, we need to cut one time from the total odd times
(oddCnt - maxOdd) == appearing times of the letters which are less than the biggest odd times
(lettersAppearOddTimes-1) == the number of letters appearing odd times except the letter which appears the biggest odd times

greedy algorithm
*/

public class LongestPalindrome {
    // Greedy algorithm
    public int longestPalindrome(String s) {
        int[] letters = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters[c]++;
        }
        
        int maxLen = 0;
        for (int cnt : letters) {
            maxLen += cnt / 2 * 2;
            if (maxLen % 2 == 0 && cnt % 2 == 1) {
                maxLen += 1;
            }
        }
        
        return maxLen;
    }
    // direct thought, pick the letter appearing the most odd times
    // other letters appearing odd times, use them but minus - 1 for each
    // so need some variables to record
    // (lettersAppearOddTimes - 1) is the other letters
	public int longestPalindrome(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c - '0']++;
        }

        int maxLength = 0;
        int lettersAppearOddTimes = 0;
        int totalOddTimes = 0;

        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                if (map[i] % 2 == 0) {
                    maxLength += map[i];
                } else {
                    totalOddTimes += map[i];
                    lettersAppearOddTimes++;
                }   
            }
        }
        // 除去 出现最多的odd 要包含 其他只用 奇数次 - 1 
        return maxLength + (totalOddTimes == 0 ? 0 : totalOddTimes - ( lettersAppearOddTimes - 1 ));
    }
}