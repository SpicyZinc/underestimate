/*
There is a new alien language which uses the Latin alphabet. However, the order among letters are unknown to you.
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
hm char <-> Set<Character> == parent char <-> subsequent following chars
inDegree char <-> cnt of points pointing to char
in essence, this is topological sorting of DAG

有多少个letter 就是多少个vertex 在 graph 中
有的点没有 入度 入度为0 那它们就是 起始点 (lexicographically smallest)
它们指向的点再 一步步减少入度
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
	// 02/07/2019
	public String alienOrder(String[] words) {
		String order = "";
		if (words.length == 0 || words == null) {
			return order;
		}

		int n = words.length;
		Map<Character, Integer> indegree = new HashMap<>();
		Map<Character, Set<Character>> graph = new HashMap<>();

		// initialization indegree
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				indegree.put(c, 0);
			}
		}

		// populate graph
		for (int i = 0; i < n - 1; i++) {
			String curr = words[i];
			String next = words[i + 1];

			int minLen = Math.min(curr.length(), next.length());

			for (int j = 0; j < minLen; j++) {
				char a = curr.charAt(j);
				char b = next.charAt(j);

				// find the first two chars not equal, then a->b
				if (a != b) {
					Set<Character> hs = graph.get(a);

					if (hs == null) {
						hs = new HashSet<>();
					}
                    // note, has to check first?
                    if (!hs.contains(b)) {
                        hs.add(b);
                        graph.put(a, hs);

                        indegree.put(b, indegree.getOrDefault(b, 0) + 1);
                    }

					break;
				}
			}
		}

		// find indegree 0 point (char)
        // queue only store node (char) of indegree 0
		Queue<Character> queue = new LinkedList<>();

		for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
			char c = entry.getKey();
			int val = entry.getValue();
			if (val == 0) {
		        queue.add(c);
			}
		}

		while (!queue.isEmpty()) {
			char c = queue.poll();
			order += c;

			Set<Character> subsequentChars = graph.get(c);
			// 最后等级的char 空的 set
			if (subsequentChars != null) {
				for (char neighbor : subsequentChars) {
					indegree.put(neighbor, indegree.get(neighbor) - 1);
					if (indegree.get(neighbor) == 0) {
						queue.add(neighbor);
					}
				}
			}
		}
        
        if (order.length() != indegree.size()) {
            return "";
        }

		return order;
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
					// note, immediately break when first two chars are NOT equal in the two words
					// 没有必要往下了 因为下面的无法区别 谁是in 谁是out 严格是 c1 -> c2
					break;
				}
			}
		}
		// queue只放放入度为0的 char
		Queue<Character> queue = new LinkedList<Character>();
		// add char of 0 in-degree to queue
		for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}

		while (!queue.isEmpty()) {
			char c = queue.poll();
			order += c;
			Set<Character> subsequentChars = hm.get(c);
			if (subsequentChars != null) {
				for (char nextChar : subsequentChars) {
					inDegree.put(nextChar, inDegree.get(nextChar) - 1);
					if (inDegree.get(nextChar) == 0) {
						queue.add(nextChar);
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