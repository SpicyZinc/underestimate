/*
This is a follow up of Shortest Word Distance.
The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters.
How would you optimize it?
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
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

*/

import java.util.*;

public class ShortestWordDistance {
	HashMap<String, ArrayList<Integer>> hm;
	public ShortestWordDistance(String[] words) {
		hm = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			ArrayList<Integer> indexes = hm.get(word);
			if (indexes == null) {
				indexes = new ArrayList<Integer>();
			}
			indexes.add(i);
			hm.put(word, indexes);
		}
		System.out.println(hm);
	}

    public int shortest(String word1, String word2) {
		ArrayList<Integer> indexesOne = hm.get(word1);
		ArrayList<Integer> indexesTwo = hm.get(word2);
		int i = 0;
		int j = 0;
		int minDistance = Integer.MAX_VALUE;
		while (i < indexesOne.size() && j < indexesTwo.size()) {
			int pos1 = indexesOne.get(i);
			int pos2 = indexesTwo.get(j);
			minDistance = Math.min(minDistance, Math.abs(pos1 - pos2));
			if (pos1 < pos2) {
				i++;
			}
			else {
				j++;
			}
		}

		return minDistance;
    }

    public static void main(String[] args) {
    	String[] words = {"practice", "makes", "perfect", "coding", "makes"};
    	ShortestWordDistance eg = new ShortestWordDistance(words);
    	String word1 = "coding";
    	String word2 = "practice";
		int dis = eg.shortest(word1, word2); //  return 3
		System.out.println("dis == " + dis);
		word1 = "makes";
		word2 = "coding";
		dis = eg.shortest(word1, word2); //  return 1
		System.out.println("dis == " + dis);
    }
}
