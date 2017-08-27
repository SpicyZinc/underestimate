/*
Given a list of words, please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.

idea:
related to the word break problem
a word can only be formed by words shorter than it
so sort the array first, then add to set
*/

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<String>();
        Set<String> shorterWords = new HashSet<String>();

        if (words.length == 0) return result;
        // sort the words by the length
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                return s.length() - t.length();
            }
        });
        
        for (String word : words) {
            if (canFormFromOtherWords(word, shorterWords)) {
                result.add(word);
            }
            // then add to the set
            shorterWords.add(word);
        }
        
        return result;
    }
    
    public boolean canFormFromOtherWords(String word, Set<String> hs) {
        if (hs.size() == 0) return false;
        
        int len = word.length();
        boolean[] wb = new boolean[len + 1];
        wb[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                String substring = word.substring(j, i);
                if (wb[j] && hs.contains(substring)) {
                    wb[i] = true;
                    break;
                }
            }
        }
        
        return wb[len];
    }
}
