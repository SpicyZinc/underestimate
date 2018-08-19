/*
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
S was sorted in some custom order previously.
We want to permute the characters of T so that they match the order that S was sorted.
More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 
Note:
S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.

idea:
for each char how many times appear in T,
then based on sequence in S
generate it and then append the remaining
*/

class  CustomSortString {
    public String customSortString(String S, String T) {
        // letters[char] = the number of occurrences of 'char' in T.
        int[] letters = new int[26];
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            letters[c - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        // based on chars' sequence in S
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int times = letters[c - 'a'];
            // c appeared such times in T
            // note, need to consider times of appearance
            for (int j = 0; j < times; j++) {
                result.append(c);
            }
            // Setting letters[char] to zero to denote that we do
            // not need to write 'char' into our answer anymore.
            letters[c - 'a'] = 0;
        }
        // the remaining
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < letters[c - 'a']; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }
}