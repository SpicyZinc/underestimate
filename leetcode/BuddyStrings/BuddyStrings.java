/*
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
Example 1:
Input: A = "ab", B = "ba"
Output: true

Example 2:
Input: A = "ab", B = "ab"
Output: false

Example 3:
Input: A = "aa", B = "aa"
Output: true

Example 4:
Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true

Example 5:
Input: A = "", B = "aa"
Output: false
 
Note:
0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

idea:
this is check if two string are meta string, coding challenge from revenue pod
self come up with this idea
*/

class BuddyStrings {
	// Mon Sep  9 01:20:08 2019
	public boolean buddyStrings(String A, String B) {
		if (A.length() == 0 && B.length() == 0) {
			return false;
		}

		if (A.length() != B.length()) {
			return false;
		}

		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i)) {
				list.add(A.charAt(i));
				list.add(B.charAt(i));
			}
		}

		if (!list.isEmpty()) {
			return list.size() == 4 && list.get(0) == list.get(3) && list.get(1) == list.get(2) || list.isEmpty() && !A.equals(B);    
		} else {
			boolean isDuplicate = false;
			char[] chars = A.toCharArray();
			Arrays.sort(chars);

			for (int i = 1; i < chars.length; i++) {
				if (chars[i - 1] == chars[i]) {
					isDuplicate = true;
				}
			}

			return A.length() == 1 || isDuplicate;
		}
	}
}