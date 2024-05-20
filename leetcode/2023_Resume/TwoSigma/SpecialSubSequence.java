/*
Consider a string, s = "abc". An alphabetically-ordered sequence of substrings of s would be {"a", "ab", "abc", "b", "bc", "c"}. If we reduce this sequence to only those substrings that start with a vowel and end with a consonant, we're left with {"ab", "abc"}. 
The alphabetically first element in this reduced list is "ab", and the alphabetically last element is "abc". 
As a reminder:
Vowels: a, e, i, o, and u.
Consonants: b, c, d, f, g, h, j, k, l, m, n, p, q, r, s, t, v, w, x, y, and z.

Complete the findSubstrings function in your editor. It has 1 parameter: a string, s, consisting of lowercase English letters (a − z). The function must find the substrings of s that start with a vowel and end with a consonant, then print the alphabetically first and alphabetically last of these substrings.

Input Format
The locked stub code in your editor reads a single string, s, from stdin and passes it to your function.

Constraints
3 ≤ length of s ≤ 5 × 10^5

Output Format
Your function must print two lines of output denoting the alphabetically first and last substrings of s that start with a vowel and end with a consonant. Print the alphabetically first qualifying substring on the first line, and the alphabetically last qualifying substring on the second line.

Sample Input 1
aba

Sample Output 1
ab
ab

Explanation 1
"ab" is the only possible substring which starts with a vowel (a) and ends with a consonant (b). Because we only have 1 qualifying substring, "ab" is both the alphabetically first and last qualifying substring and we print it as our first and second lines of output.

Sample Input 2
aab

Sample Output 2
aab
ab

Explanation 2
There are 2 possible substrings which start with a vowel and end with a consonant: "aab" and "ab". When ordered alphabetically, "aab" comes before "ab". This means that we print "aab" (the alphabetically first qualifying substring) as our first line of output, and we print "ab" (the alphabetically last qualifying substring) as our second line of output.

Sample Input 3
rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe

Sample Output 3
aaop
utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh

Explanation 3
There are 4830 substrings of s, but only 676 of them start with a vowel and end with a consonant. When ordered alphabetically, the first substring is "aaop" and the last substring is "utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh".

idea:
https://leetcode.com/discuss/interview-question/algorithms/125520/adobe-oa-2016-special-subsequence
*/

import java.util.*;

class SpecialSubSequence {
    public static void main(String[] args) {
        SpecialSubSequence eg = new SpecialSubSequence();
        // String s = "abc";

        String s = "aba";
        // String s = "aab";
        // String s = "rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe";

        // List<String> result = eg.findSubstrings(s);
        List<String> result = eg.findSubstrings2(s);
        System.out.println(result);
    }

    public boolean isVowel(char c) {
        String vowels = "aeiou";
        String ch = String.valueOf(c);
        return vowels.contains(ch);
    }

    public List<String> findSubstrings(String str) {
        List<String> result = new ArrayList<>();

        if (str == null || str.length() == 0) {
            return result;
        }

        Map<Character, List<Integer>> map = new HashMap<>();

        int n = str.length();
        boolean hasConsonant = false;

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (isVowel(c)) {
                if (map.containsKey(c)) {
                    List<Integer> indexes = map.get(c);
                    int last = indexes.get(indexes.size() - 1);
                    // Skip the last + 1
                    if (i > last + 1) {
                        indexes.add(i);
                    }
                } else {
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    map.put(c, indexes);
                }
                // if (!map.containsKey(c))
                //     map.put(c, new ArrayList<>());

                // if (!map.get(c).isEmpty()) {
                //     int last = map.get(c).get(map.get(c).size() - 1);
                //     if (i > last && i != last + 1) {
                //         map.get(c).add(i);
                //     }
                // } else {
                //     map.get(c).add(i);
                // }
            } else {
                hasConsonant = true;
            }
        }

        /**
         * if there is no consonant
         */
        if (!hasConsonant) {
            return result;
        }

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        int v = 0;
        String[] solution = null;
        for (; v < vowels.length; v++) {
            char vowel = vowels[v];
            if (map.containsKey(vowel)) {
                solution = getSubStrings(str.toCharArray(), map.get(vowel));
                break;
            }
        }

        if (v == map.size() - 1) {
            return Arrays.asList(solution);
        } else {
            result.add(solution[0]);
            int x = vowels.length - 1;
            // 从最后一个vowel实验
            for (; x > v; x--) {
                char vowel = vowels[x];
                if (map.containsKey(vowel)) {
                    solution = getSubStrings(str.toCharArray(), map.get(vowel));
                    break;
                }
            }

            result.add(solution[1]);
        }

        return result;
    }

    private String[] getSubStrings(char[] str, List<Integer> indexes) {
        String first = null;
        String last = null;

        for (int index : indexes) {
            StringBuilder sb = new StringBuilder();
            int x = index;
            while (x < str.length) {
                sb.append(str[x++]);

                if (!isVowel(sb.charAt(sb.length() - 1))) {
                    String temp = sb.toString();
                    if (first != null) {
                        first = first.compareTo(temp) < 0 ? first : temp;
                    } else {
                        first = temp;
                    }

                    if (last != null) {
                        last = last.compareTo(temp) > 0 ? last : temp;
                    } else {
                        last = temp;
                    }
                }
            }
        }

        return new String[] {first, last};
    }
    // Brute force
    public List<String> findSubstrings2(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        final List<String> subStrings = new ArrayList<>();

        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String s = str.substring(i, j);
                char start = str.charAt(i);
                char end = str.charAt(j - 1);

                if (isVowel(start) && !isVowel(end)) {
                    subStrings.add(s);
                }
            }
        }

        Collections.sort(subStrings);

        if (subStrings.size() == 0) {
            return Collections.EMPTY_LIST;
        } else if (subStrings.size() == 1) {
            return Arrays.asList(subStrings.get(0), subStrings.get(0));
        } else {
            return Arrays.asList(subStrings.get(0), subStrings.get(subStrings.size() - 1));
        }
    }
}