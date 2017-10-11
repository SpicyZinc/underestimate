/*
Given a set of words (without duplicates), find all word squares you can build from them.
A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
b a l l
a r e a
l e a d
l a d y

Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
 
Example 1:
Input:
["area","lead","wall","lady","ball"]
Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 
Example 2:
Input:
["abat","baba","atan","atal"]
Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

idea:
dfs
based on first 2 rows 3rd column
l 1st row
e 2nd row
le as prefix to get 3rd row candidates, for each candidates, dfs()

ball
area
lead
lady

good analysis: https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16
*/

import java.util.*;

class WordSquares {
	public static void main(String[] args) {
		WordSquares eg = new WordSquares();
		String[] words = {"area","lead","wall","lady","ball"};
		List<List<String>> result = eg.wordSquares(words);
		for (List<String> square : result) {
			for (String line : square) {
				System.out.println(line);
			}
			System.out.println();
		}
	}

	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (words.length == 0 || words[0].length() == 0) {
			return result;
		}
		Map<String, Set<String>> hm = new HashMap<>();
		int m = words.length;
		int n = words[0].length();
		// create all prefix map
		for (int i = 0; i < m; i++) {
			for (int j = 0; j <= n; j++) {
				String prefix = words[i].substring(0, j);
				if (!hm.containsKey(prefix)) {
					hm.put(prefix, new HashSet<String>());
				}
				hm.get(prefix).add(words[i]);
			}
		}
		// dfs
		dfs(0, n, hm, new ArrayList<String>(), result);
		return result;
	}

	public void dfs(int pos, int n, Map<String, Set<String>> hm, List<String> square, List<List<String>> result) {
		if (pos == n) {
			result.add(new ArrayList<String>(square));
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pos; i++) {
			// different rows, but the same column (pos)
			sb.append(square.get(i).charAt(pos));
		}
		Set<String> candidates = hm.get(sb.toString());
		if (candidates == null) {
			return;
		}
		for (String s : candidates) {
			square.add(s);
			dfs(pos + 1, n, hm, square, result);
			square.remove(square.size() - 1);
		}
	}
}