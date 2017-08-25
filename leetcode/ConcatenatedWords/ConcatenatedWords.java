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
related to the work break problem
a word can only be formed by words shorter than it
so sort the array first, then add to set later
*/


public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<String>();
        Set<String> preWords = new HashSet<String>();
        // sort the words by the length
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                return s.length() - t.length();
            }
        });

        for (String word : words) {
            if (canFormFromOtherWords(word, preWords)) {
                result.add(word);
            }
            // then add to the set
            preWords.add(word);
        }

        return result;
    }

    private boolean canFormFromOtherWords(String s, Set<String> dict) {
        if (dict.isEmpty()) {
            return false;
        }

        int l = s.length();
        boolean[] wb = new boolean[l + 1];
        wb[0] = true;        
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j < i; j++) {
                String tem = s.substring(j, i);
                if (wb[j] && dict.contains(tem)) {
                    wb[i] = true;
                    break; // if miss this line, will Time Limit Exceeded
                }
            }
        }
        
        return wb[l];
    }
}
