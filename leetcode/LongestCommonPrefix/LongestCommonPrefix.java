/*
Write a function to find the longest common prefix string among an array of strings.
If there is no common prefix, return an empty string "".

Example 1: 
Input: ["flower","flow","flight"]
Output: "fl"

Example 2: 
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.

idea:
1. combine first two strings to find the first prefix,
then combine this prefix with the 3rd string to get 2nd prefix...
every time use current prefix and next string to generate new prefix
brute force, simple and direct
base = strs[0]
take the first string in the array of string as comparing "base"
compare the "base" with the string starting from 2nd position place in the array
each comparison, use temporary "prefix" to hold the common prefix between these two strings, 
and update base at the end of iteration
next iteration will be based on last iteration's "base" result

s1	s2    s3    s4    s5
|____|
|__________|
|________________|
|______________________|

2. pick char in the 1st string, same position's char in other strings
compare each char in the first string with the rest strings' char at the same position
save each iteration's base to return value so far
if k == 1st string length, break;
if k == length of any other string except 1st one, break;
update return value, k after each iteration
update base char before each iteration
"" is nothing, charAt(0) is meaning length == 1;
if length == 0, charAt(0) is nonsense
*/

public class LongestCommonPrefix {
	public static void main(String[] args) {
		LongestCommonPrefix eg = new LongestCommonPrefix();		

		// String[] strs = {"flower", "flow", "flight"};
		String[] strs = {"dog","doa", "racecar"};
		// String result = eg.longestCommonPrefix(strs);
		String result = eg.lcp(strs);
		System.out.println("LongestCommonPrefix == " + result);
	}

	// Sun Jun  9 19:50:26 2019
	 public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        
        String commonPrefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            String word = strs[i];
            
            int len = Math.min(commonPrefix.length(), word.length());
            
            int j = 0;
            for (; j < len; j++) {
                if (commonPrefix.charAt(j) != word.charAt(j)) {
                    break;
                }
            }
            
            commonPrefix = word.substring(0, j);
        }
        
        return commonPrefix;
    }

    public String longestCommonPrefix(String[] strs) {
        String prefix = "";

        if (strs.length == 0 || strs == null) {
            return prefix;
        }

        prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            String tmpPrefix = "";
            int k = 0;
            while (k < str.length() && k < prefix.length() && str.charAt(k) == prefix.charAt(k)) {
                tmpPrefix += str.charAt(k);
                k++;
            }

            prefix = tmpPrefix;
        }
        
        return prefix;
    }

    // this method the best, neat
    // 以 1st str 作为基础 比较其他的strs
    // 一旦有不相等的c 就 return
    public String lcp(String[] strs) {
        int n = strs.length;

        if (n == 0) {
        	return "";
        }

        int i = 0;
        for (; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < n; j++) {
            	String str = strs[j];

                if (i >= str.length() || str.charAt(i) != c) {
                	return strs[0].substring(0, i);
                }
            }
        }

        return strs[0].substring(0, i);
    }
}