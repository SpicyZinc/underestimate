/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
For example, "great acting skills" and "fine drama talent" are similar,
if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive.
For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.
However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself.
For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
Finally, sentences can only be similar if they have the same number of words.
So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

idea:
Map of String to hashset

*/

class SentenceSimilarity {
	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		int len1 = words1.length;
		int len2 = words2.length;
		if (len1 != len2) {
			return false;
		}

		Map<String, Set<String>> hm = new HashMap<>();
		for (String[] pair : pairs) {
			String left = pair[0];
			String right = pair[1];
            
            Set<String> set1 = hm.getOrDefault(left, new HashSet<>());
            set1.add(right);
			hm.put(left, set1);
            
            Set<String> set2 = hm.getOrDefault(right, new HashSet<>());
            set2.add(left);
			hm.put(right, set2);
		}

		for (int i = 0; i < len1; i++) {
			String word1 = words1[i];
			String word2 = words2[i];
			if (word1.equals(word2)) {
				continue;
			}
			if (hm.containsKey(word1)) {
				Set<String> set = hm.get(word1);
				if (set.contains(word2)) {
					continue;
				}
			}
			return false;
		}

		return true;
	}
}