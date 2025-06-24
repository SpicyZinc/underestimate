/*
You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
Return any permutation of s that satisfies this property.

Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
Input: order = "bcafg", s = "abcd"
Output: "bcad"
Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.
Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order.
The output "bcad" correctly follows this rule. Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

Constraints:
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.

idea:
主要是考察map的实现?
for each char how many times appear in T,
then based on sequence in S
generate it and then append the remaining
*/

class  CustomSortString {
    // 2025
    public String customSortString(String order, String s) {
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);

            int times = counts[c - 'a'];
            for (int j = 0; j < times; j++) {
                sb.append(c);
            }
            // not forget those covered, do not write again
            counts[c - 'a'] = 0;
        }

        // for those chars not in order
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < counts[c - 'a']; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public String customSortString(String order, String s) {
        // letters[char] = the number of occurrences of 'char' in 'S'.
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters[c - 'a']++;
        }

        StringBuilder result = new StringBuilder();
        // based on chars' sequence in 'order'
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            int times = letters[c - 'a'];
            // c appeared such times in 'S'
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