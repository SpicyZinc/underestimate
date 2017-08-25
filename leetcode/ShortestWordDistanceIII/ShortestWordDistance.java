/*
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
word1 and word2 may be the same and they represent two individual words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
Note: You may assume word1 and word2 are both in the list.

idea:
https://segmentfault.com/a/1190000003906667

this problem has 3 variations
1. word1 and word2 are different
2. frequently call shortestWordDistance()
hashmap to save all indexes of a word as word as key, indexes as value
find the minimum difference between two sorted lists 
3. word1 and word2 could be the same
either separate case 1 and 3 
or add flag "turn"

*/
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, distance = Integer.MAX_VALUE, turn = 0, inc = (word1.equals(word2) ? 1 : 0);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) && turn % 2 == 0) {
                idx1 = i;
                if (idx2 != -1) distance = Math.min(distance, idx1 - idx2);
                turn += inc;
            } else if (words[i].equals(word2)) {
                idx2 = i;
                if (idx1 != -1) distance = Math.min(distance, idx2 - idx1);
                turn += inc;
            }
        }

        return distance;
    }
}
