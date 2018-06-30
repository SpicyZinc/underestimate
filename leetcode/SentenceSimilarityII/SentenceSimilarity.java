/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive.
For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
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
BFS
similarity is transitive,
a->b->c->d, use queue to find a->d is connected

union find
*/

import java.util.*;

class SentenceSimilarity {
	public static void main(String[] args) {
		String[] words1 = {"great", "acting", "skills"};
		String[] words2 = {"fine", "drama", "talent"};
		String[][] pairs = {
			{"great", "good"},
			{"fine", "good"}, {"acting","drama"}, {"skills","talent"}
		};
		SentenceSimilarity eg = new SentenceSimilarity();
		boolean isSimilar = eg.areSentencesSimilarTwo(words1, words2, pairs);

		System.out.println(isSimilar);
	}

	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) {
			return false;
		}
		// build up two way map 无向连通图
		// every node, get all its connected nodes
		Map<String, Set<String>> hm = new HashMap<>();
		for (String[] pair : pairs) {
			String start = pair[0];
			String end = pair[1];
			if (hm.containsKey(start)) {
				hm.get(start).add(end);
			} else {
				Set<String> hs = new HashSet<String>();
				hs.add(end);
				hm.put(start, hs);
			}

			if (hm.containsKey(end)) {
				hm.get(end).add(start);
			} else {
				Set<String> hs = new HashSet<String>();
				hs.add(start);
				hm.put(end, hs);
			}
        }

		for (int i = 0; i < words1.length; i++) {
			if (words1[i].equals(words2[i])) {
				continue;
			}

			Set<String> visited = new HashSet<String>();
            boolean succ = false;

			Queue<String> q = new LinkedList<String>();
			q.add(words1[i]);

            while (!q.isEmpty()) {
                String word = q.poll();
                if (hm.get(word).contains(words2[i])) {
                	succ = true;
                	break;
                }

                visited.add(word);
                // all other words connected to word
                for (String connectedWord : hm.get(word)) {
                    if (!visited.contains(connectedWord)) {
                    	q.offer(connectedWord);
                    }
                }
            }
            if (!succ) {
            	return false;
            }
        }

        return true;
    }

    // union find
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        Map<String, String> hm = new HashMap<>();
        for (String[] pair : pairs) {
            String start = pair[0];
            String end = pair[1];
            
            String parent1 = findRoot(start, hm);
            String parent2 = findRoot(end, hm);
            if (!parent1.equals(parent2)) {
                // build union
                hm.put(parent1, parent2);
            }
        }
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            
            if (!word1.equals(word2) && !findRoot(word1, hm).equals(findRoot(word2, hm))) {
                return false;
            }
        }
        
        return true;
    }
    
    private String findRoot(String s, Map<String, String> hm) {
        if (!hm.containsKey(s)) {
            hm.put(s, s);
        }
        String sameGroup = hm.get(s);
        if (s.equals(sameGroup)) {
            return s;
        } else {
            return findRoot(sameGroup, hm);
        }
    }
}