/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation,
a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique.
In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.

Example:
Input:  ["like", "god",  "internal",   "me",    "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e",  "god",  "internal",   "me",    "i6t",      "interval", "inte4n",    "f2e",  "intr4n"]

Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.

idea:
record where to start abbreviation, normally abbreviation position starts at 1
note: lastly add i to set, search in [i + 1, size - 1], if set not empty, not forget to add i
*/

import java.util.*;
public class WordAbbreviation {
    public static void main(String[] args) {
        WordAbbreviation eg = new WordAbbreviation();
        List<String> dict = new ArrayList<String>();
        dict.add("like");
        dict.add("god");
        dict.add("internal");
        dict.add("me");
        dict.add("internet");
        dict.add("interval");
        dict.add("intension");
        dict.add("face");
        dict.add("intrusion");

        for (String abbr : eg.wordsAbbreviation(dict)) {
            System.out.println(abbr);
        }
    }

	public List<String> wordsAbbreviation(List<String> dict) {
        int size = dict.size();
        String[] abbreviations = new String[size];
        // index to start abbreviation for different word, inclusive
        int[] prefix = new int[size];
        for (int i = 0; i < size; i++) {
            prefix[i] = 1;
            abbreviations[i] = makeAbbr(dict.get(i), 1);
        }

        for (int i = 0; i < size; i++) {
            while (true) {
                Set<Integer> set = new HashSet<>();
                for (int j = i + 1; j < size; j++) {
                	// check all strings, find word with the same abbreviation as i
                    if (abbreviations[j].equals(abbreviations[i])) {
                    	set.add(j);
                    }
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int wordPos : set) {
                	prefix[wordPos] += 1; // increase the prefix position, a longer prefix is used 
                    abbreviations[wordPos] = makeAbbr(dict.get(wordPos), prefix[wordPos]); 
                }
            }
        }

        return Arrays.asList(abbreviations);
    }

    private String makeAbbr(String s, int k) {
        if (k >= s.length() - 2) return s;
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, k));
        sb.append(s.length() - 1 - k);
        sb.append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}