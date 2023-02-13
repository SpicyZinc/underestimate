/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.)
According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

idea:
1. compare every two words
2. for every two words, compare each position, use the position in dictionary
*/
class VerifyAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            dict.put(c, i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], dict) > 0) {
                return false;
            }
        }

        return true;
    }

    public int compare(String word1, String word2, Map<Character, Integer> dict) {
        int size1 = word1.length();
        int size2 = word2.length();

        int minSize = Math.min(size1, size2);
        for (int i = 0; i < minSize; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 != c2) {
                return dict.get(c1) - dict.get(c2);
            }
        }

        return size1 == minSize ? -1 : 1;
    }
}
