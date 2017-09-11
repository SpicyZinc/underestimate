/*
You are given a string, s, and a list of words, words, that are all of the same length.
Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

idea:
note: concatenation of each word, each word in the concatenation shows exactly once 

take each char in s as start of a possible word
if found this possible word is one of the words[] array, continue in n (length of words[])
if not, break early, go to next char in s by i++

note: make a copy of hm in each iteration
*/

import java.util.*;

public class SubstringWithConcatenationOfAllWords {
	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = {"foo", "bar"};
		// String s = "aaaa";
		// String[] words = {"a", "a"};
        SubstringWithConcatenationOfAllWords eg = new SubstringWithConcatenationOfAllWords();
        System.out.println(eg.findSubstring(s, words));
	}
	
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if (s.length() == 0 || s == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> hm = new HashMap<String, Integer>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        
        int size = s.length();
        int n = words.length;
        int wordLen = words[0].length();
        int i = 0;
        
        while (size - i >= wordLen * n) {
            Map<String, Integer> copyMap = new HashMap<String, Integer>(hm);
            // get the same count of possible words as the number of words
            for (int j = 0; j < n; j++) {
                String possibleWord = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                // if possibleWord not even exists in map, break early, directly i++
                // this saves a lot of time
                if (!copyMap.containsKey(possibleWord)) break;
                int frequency = copyMap.get(possibleWord);
                if (frequency > 1) {
                    copyMap.put(possibleWord, frequency - 1);
                } else {
                    copyMap.remove(possibleWord);
                }
            }
            if (copyMap.size() == 0) {
                result.add(i);
            }
            i++;
        }
        
        return result;
    }
}
