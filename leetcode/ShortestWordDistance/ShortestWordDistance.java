/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3

Input: word1 = "makes", word2 = "coding"
Output: 1

Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

idea:
this problem has 3 variations
1. word1 and word2 are different
2. frequently call shortestWordDistance()
hashmap to save all indexes of a word as word as key, indexes as value
find the minimum difference between two sorted lists 
3. word1 and word2 could be the same
either separate case 1 and 3 
or add flag "turn"

note: no need to use Math.abs(), why? think about it
*/

import java.util.*;

public class ShortestWordDistance {
	public static void main(String[] args) {
		ShortestWordDistance eg = new ShortestWordDistance();
		String[] words = {"practice", "makes", "perfect", "coding", "makes"};
		// String word1 = "coding";
		String word1 = "makes";
		// String word2 = "practice";
		String word2 = "coding";
		int shortest = eg.shortestDistance(words, word1, word2);
		System.out.println(shortest);
	}

	public int shortestDistance(String[] words, String word1, String word2) {
		int idx1 = -1;
		int idx2 = -1;
		int minDistance = Integer.MAX_VALUE;

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (word.equals(word1)) {
				idx1 = i;
				if (idx2 != -1) {
					minDistance = Math.min(minDistance, idx1 - idx2);
				}
			}

			if (word.equals(word2)) {
				idx2 = i;
				if (idx1 != -1) {
					minDistance = Math.min(minDistance, idx2 - idx1);
				}
			}
		}

		return minDistance;
	}
}
