/*
Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

Example 1:
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"

Example 2:
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.

Example 3:
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"

Constraints:
1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.

idea:
HashMap and list
所有char's counts should be the same
*/

class DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();

        if (size1 != size2) {
            return false;
        }

        Map<Character, Integer> hm1 = getCount(word1);
        Map<Character, Integer> hm2 = getCount(word2);

        for (int i = 0; i < size1; i++) {
            char c2 = word2.charAt(i);
            char c1 = word1.charAt(i);

            if (!hm1.containsKey(c2)) {
                return false;
            }

            if (!hm2.containsKey(c1)) {
                return false;
            }
        }

        List<Integer> count1 = new ArrayList<>();
        List<Integer> count2 = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : hm1.entrySet()) {
            count1.add(entry.getValue());
        }


        for (Map.Entry<Character, Integer> entry : hm2.entrySet()) {
            count2.add(entry.getValue());
        }

        Collections.sort(count1);
        Collections.sort(count2);

        return count1.equals(count2);
    }

    public Map<Character, Integer> getCount(String word) {
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0 ; i < word.length(); i++) {
            hm.put(word.charAt(i), hm.getOrDefault(word.charAt(i), 0)+1);
        }

        return hm;
    }
}
