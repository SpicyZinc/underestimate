/*
In an alien language, surprisingly they also use English lowercase letters, but possibly in a different order.
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet,
return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.)
According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

Note:
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

idea:
giving characters in order numeric value
*/

class VerifyingAnAlienDictionary {
	// Sat Jul  6 01:10:01 2019
	public boolean isAlienSorted(String[] words, String order) {
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < order.length(); i++) {
			char c = order.charAt(i);
			map.put(c, i);
		}

		for (int i = 0; i < words.length - 1; i++) {
			String curr = words[i];
			String next = words[i + 1];
			// 如果 curr > next, which is not sorted, 立即 return
			if (compare(curr, next, map)) {
				return false;
			}
		}

		return true;
	}

	public boolean compare(String s, String t, Map<Character, Integer> map) {
		int m = s.length();
		int n = t.length();

		int i = 0;
		int j = 0;

		int cmp = 0;

		while (i < m && j < n) {
			char a = s.charAt(i++);
			char b = t.charAt(j++);

			int aOrder = map.get(a);
			int bOrder = map.get(b);

			cmp = aOrder - bOrder;
			if (cmp != 0) {
				break;
			}
		}

		return cmp == 0 ? m - n > 0 : cmp > 0;
	}

	// different compare
	// > 0 s > t
	// < 0 s < t
	public int compare(String s, String t, Map<Character, Integer> map) {
		int m = s.length();
		int n = t.length();

		int cmp = 0;

		for (int i = 0, j = 0; i < m && j < n && cmp == 0; i++, j++) {
			int alienOrderS = map.get(s.charAt(i));
			int alienOrderT = map.get(t.charAt(j));

			cmp = alienOrderS - alienOrderT;
		}

		return cmp == 0 ? m - n : cmp;
	}
}