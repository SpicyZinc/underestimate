/*
You have a list of words and a pattern, and you want to know which words in words matches the pattern.
A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x),
we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters:
every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern.
You may return the answer in any order.

Example 1:
Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.

Note:
1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20

idea:
same as WordPattern I, loop through to see each word in words is same pattern as 'pattern'
*/

class Solution {
	public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (isSamePattern(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private boolean isSamePattern(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }

        Map<Character, Character> hm = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            char p = pattern.charAt(i);

            if (hm.containsKey(c)) {
                if (hm.get(c) != p) {
                    return false;
                }                
            } else {
                if (hm.containsValue(p)) {
                    return false;
                }
                hm.put(c, p);
            }
        }
        
        return true;
    }
}