/*
Strings A and B are K-similar (for some non-negative integer K)
if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:
Input: A = "ab", B = "ba"
Output: 1

Example 2:
Input: A = "abc", B = "bca"
Output: 2

Example 3:
Input: A = "abac", B = "baca"
Output: 2

Example 4:
Input: A = "aabc", B = "abca"
Output: 2

Note:
1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

idea:
read code, slowly digest will get it

*/
class KSimilarStrings {
    public int kSimilarity(String A, String B) {
        int K = 0;
        if (A.equals(B)) {
            return K;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(A);
        visited.add(A);

        while (!queue.isEmpty()) {
            K++;
            for (int sz = queue.size(); sz > 0; sz--) {
                String s = queue.poll();
                int i = 0;
                // find char position i which are not the same
                while (s.charAt(i) == B.charAt(i)) {
                    i++;
                }
                // from i + 1, find another different positions
                for (int j = i + 1; j < s.length(); j++) {
                    // if i not j position char, swap meaningless, continue
                    if (s.charAt(j) == B.charAt(j) || s.charAt(i) != B.charAt(j)) {
                        continue;
                    }

                    String temp = swap(s, i, j);
                    if (temp.equals(B)) {
                        return K;
                    }

                    if (visited.add(temp)) {
                        queue.add(temp);
                    }
                }
            }
        }

        return K;
    }

    public String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        return new String(chars);
    }
}