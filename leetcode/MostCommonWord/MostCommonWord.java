/*
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.
Words in the paragraph are not case sensitive.
The answer is in lowercase.

Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.

Note:
1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

idea:
how to check if an array contains an element or not in Java, convert to List or Set
note, toLowerCase()
*/

import java.util.*;

class MostCommonWord {
	public static void main(String[] args) {
		String s = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};

		MostCommonWord eg = new MostCommonWord();
		String result = eg.mostCommonWord(s, banned);

		System.out.println(result);
	}
	// Sun May 19 20:03:29 2019
	public String mostCommonWord(String paragraph, String[] banned) {
        int max = 0;
        String mostCommonWord = "";
        
        Set<String> hs = new HashSet<>(Arrays.asList(banned));
            
        Map<String, Integer> hm = new HashMap<>();
        
        String[] matches = paragraph.replaceAll("\\pP", " ").toLowerCase().split("\\s+");
        for (String match : matches) {
            // for banned match, skip it
            if (hs.contains(match)) {
                continue;
            }
            
            hm.put(match, hm.getOrDefault(match, 0) + 1);
            
            if (hm.get(match) > max) {
                max = hm.get(match);
                mostCommonWord = match;
            }
        }
        
        return mostCommonWord;
    }
}