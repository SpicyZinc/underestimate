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
https://www.cnblogs.com/grandyang/p/6006000.html
based on first 2 rows 3rd column
			3rd col
1st row   .. l
2nd row   .. e

le as prefix to get 3rd row candidates, for each candidates, dfs()

note, empty string also map to string to initiate the process

	0	1	 2	  3
	
0	----|----|----|----

1	---------|----|----

2	--------------|----

3	...................

一行行地填
用大一位的col e.g. 1 and 2 row's 3rd col to decide 3rd row, how? as prefix to get potential form map

ball
area
lead
lady

Trie, https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16
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

	// Sun Jun  9 01:03:53 2019
	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> result = new ArrayList<>();
			
		Map<String, Set<String>> hm = new HashMap<>();
		for (String word : words) {
			for (int i = 0; i <= word.length(); i++) {
				String prefix = word.substring(0, i);
				
				hm.computeIfAbsent(prefix, x -> new HashSet<String>()).add(word);
			}
		}
		
		int squareLen = words[0].length();
		dfs(0, squareLen, hm, new ArrayList<String>(), result);
		
		return result;
	}
	
	public void dfs(int row, int n, Map<String, Set<String>> hm, List<String> square, List<List<String>> result) {
		if (row == n) {
			result.add(new ArrayList<String>(square));
			
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			sb.append(square.get(i).charAt(row));
		}

		String prefixForRow = sb.toString();
		Set<String> candidates = hm.get(prefixForRow);
		if (candidates == null) {
			return;
		}
		
		for (String candidate : candidates) {
			square.add(candidate);
			dfs(row + 1, n, hm, square, result);
			square.remove(square.size() - 1);
		}
	}

	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> result = new ArrayList<>();
		if (words.length == 0 || words == null) {
			return result;
		}
		
		Map<String, Set<String>> hm = new HashMap<>();
		// create all prefix map
		for (String word : words) {
			for (int i = 0; i <= word.length(); i++) {
				String prefix = word.substring(0, i);
				hm.computeIfAbsent(prefix, x -> new HashSet<String>()).add(word);
			}
		}
		
		int squareLen = words[0].length();
		dfs(0, squareLen, hm, new ArrayList<String>(), result);
		
		return result;
	}

	public void dfs(int rowIdx, int squareLen, Map <String, Set<String>> hm, List<String> square, List<List<String>> result) {
		// find one word square
		if (rowIdx == squareLen) {
			result.add(new ArrayList<String>(square));

			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rowIdx; i++) {
			// 用填好row的 col 来推测 row + 1
			// different rows, but the same column (pos)
			sb.append(square.get(i).charAt(rowIdx));
		}

		String prefix = sb.toString();
		Set<String> candidates = hm.get(prefix);
		if (candidates == null) {
			return;
		}

		for (String candidate : candidates) {
			square.add(candidate);
			dfs(rowIdx + 1, squareLen, hm, square, result);
			square.remove(square.size() - 1);
		}
	}
}