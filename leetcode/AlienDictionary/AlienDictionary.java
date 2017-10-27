/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

For example,
Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

The correct order is: "wertf".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

idea:
two maps,
hm char <-> Set<Character> parent char -> children chars
inDegree char <-> frequency
*/

import java.util.*;
class AlienDictionary {

	public static void main(String[] args) {
		AlienDictionary eg = new AlienDictionary();
		String[] words = {
			"wrt",
			"wrf",
			"er",
			"ett",
			"rftt"
		};

		String order = eg.alienOrder(words); // "wertf"
		System.out.println(order);
	}

	public String alienOrder(String[] words) {
		String order = "";
		if (words == null || words.length == 0) {
			return order;
		}

		Map<Character, Set<Character>> hm = new HashMap<>();
		Map<Character, Integer> inDegree = new HashMap<>();
		// initialization of all chars with in-degree as 0
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				inDegree.put(c, 0);
			}
		}
		// populate map of char to its subsequent char
		for (int i = 0; i < words.length - 1; i++) {
			String curr = words[i];
			String next = words[i + 1];

			int minLen = Math.min(curr.length(), next.length());
			for (int j = 0; j < minLen; j++) {
				char c1 = curr.charAt(j);
				char c2 = next.charAt(j);

				if (c1 != c2) {
					Set<Character> set = hm.get(c1);
					if (set == null) {
						set = new HashSet<Character>();
					}
					// c1 != c2, c2 must be c1's subsequent char
					if (!set.contains(c2)) {
						set.add(c2);
						hm.put(c1, set);

						inDegree.put(c2, inDegree.get(c2) + 1);
					}
					// note, immediately break when two chars are NOT equal
					break;
				}
			}
		}

		Queue<Character> queue = new LinkedList<Character>();
		// add char of 0 in-degree to queue
		for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}

		while (!queue.isEmpty()) {
			char c = queue.remove();
			order += c;
			Set<Character> subsequentChars = hm.get(c);
			if (subsequentChars != null) {
				for (char c2 : subsequentChars) {
					inDegree.put(c2, inDegree.get(c2) - 1);
					if (inDegree.get(c2) == 0) {
						queue.add(c2);
					}
				}
			}
		}
		// ideally, the length of result same as degree map size
		// if not, meaning there is a cycle
		if (order.length() != inDegree.size()) {
			return "";
		}

		return order;
	}
}