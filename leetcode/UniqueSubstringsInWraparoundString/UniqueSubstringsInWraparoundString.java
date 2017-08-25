/*
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s.
In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1
Explanation: Only the substring "a" of string "a" is in the string s.

Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.

Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.

idea:
https://www.cnblogs.com/grandyang/p/6143071.html

take abc as an example
a == a                   length = 1  	ending with a
ab == ab, b              length = 2  	ending with b
abc == abc, bc, c        length = 3  	ending with c

The max number of unique substring ends with a letter equals to the length of max contiguous substring ends with that letter.
Example "abcd", the max number of unique substring ends with 'd' is 4, apparently they are "abcd", "bcd", "cd" and "d".
If there are overlapping, we only need to consider the longest one because it covers all the possible substrings.
Example: "abcdbcd", the max number of unique substring ends with 'd' is 4 and all substrings formed by the 2nd "bcd" part are covered in the 4 substrings already.
No matter how long is a contiguous substring in p, it is in s since s has infinite length.
Now we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z' and those substrings are all in s.
Summary is the answer, according to the question.
*/

public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        int[] cnt = new int[26];
        int len = 0;
        for (int i = 0; i < p.length(); i++) {
        	char c = p.charAt(i);
            if (i > 0 && (c == p.charAt(i - 1) + 1 || p.charAt(i - 1) - c == 25)) {
                len++;
            } else {
                len = 1;
            }
            cnt[c - 'a'] = Math.max(cnt[c - 'a'], len);
        }
        int sum = 0;
        for (int i : cnt) {
        	sum += i;
        }

        return sum;
    }
}