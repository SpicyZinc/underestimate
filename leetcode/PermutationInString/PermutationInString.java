/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

idea:
https://www.cnblogs.com/grandyang/p/6815227.html
同向双指针

	How do we know string p is a permutation of string s?
	Easy, each character in p is in s too. So we can abstract all permutation strings of s to a map (Character -> Count). i.e. abba -> {a:2, b:2}.
	Since there are only 26 lower case letters in this problem, we can just use an array to represent the map.
	
	How do we know string s2 contains a permutation of s1? We just need to create a sliding window with length of s1,
	move from beginning to the end of s2.
	When a character moves in from right of the window, we subtract 1 to that character count from the map.
	When a character moves out from left of the window, we add 1 to that character count.
	So once we see all zeros in the map, meaning equal numbers of every characters between s1 and the substring in the sliding window, we know the answer is true.
*/

public class PermutationInString {
	// Sat Jul  6 01:22:47 2019
	public boolean checkInclusion(String s1, String s2) {
		int[] map = new int[256];
		for (int i = 0; i < s1.length(); i++) {
			map[s1.charAt(i)]++;
		}
		
		int size = s1.length();
		int count = 0;
		int left = 0;

		for (int right = 0; right < s2.length(); right++) {
			char c = s2.charAt(right);
			// 有没有在 map 中的 character 都要 更新
			if (map[c]-- >= 1) { 
				count++;
			}

			while (count == size) {
				// 有且只有 size == window size 这样才是 permutation
				if (right - left + 1 == size) {
					return true;
				}
				
				// move window left
				map[s2.charAt(left)]++;
				if (map[s2.charAt(left)] >= 1) {
					count--;
				}
				left++;
			}
		}

		return false;
	}
}