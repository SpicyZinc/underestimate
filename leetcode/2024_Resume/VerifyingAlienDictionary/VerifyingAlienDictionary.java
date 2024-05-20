
/*
Check if a list of words is sorted lexicographically by self-defined dictionary.

idea:
The words are sorted lexicographically if and only if adjacent words are sorted. This is because order is transitive i:e if a <= b and b <= c, implies a <= c. So our goal it to check whether all adjacent words a and b have a <= b. To check whether for two adjacent words a and b, a <= b holds we can find their first difference. For example, “seen” and “scene” have a first difference of e and c. After this, we compare these characters to the index in order. We have to deal with the blank character effectively. If for example, we are comparing “add” to “addition”, this is a first difference of (NULL) vs “i”.
*/

import java.util.*;

class VerifyingAlienDictionary {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        // words.add("word");
        // words.add("world");
        // words.add("row");
        words.add("hello");
        words.add("leetcode");
        // String order = "abcworldefghijkmnpqstuvxyz";
        String order = "hlabcdefgijkmnopqrstuvwxyz";

        VerifyingAlienDictionary eg = new VerifyingAlienDictionary();

        if (eg.isOrderSorted(words, order)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    public boolean areTwoWordsSorted(String s1, String s2, String alphabet) {
        int l1 = s1.length();
        int l2 = s2.length();
        int minLen = Math.min(l1, l2);

        int i = 0;
        for (i = 0; i < minLen; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                int order1 = alphabet.indexOf(c1);
                int order2 = alphabet.indexOf(c2);

                return order1 < order2;
            }
        }

        return l1 <= l2;
    }

    public boolean isOrderSorted(List<String> words, String order) {
        int size = words.size();

        for (int i = 0; i < size - 1; i++) {
            String current = words.get(i);
            String next = words.get(i + 1);

            if (!areTwoWordsSorted(current, next, order)) {
                return false;
            }
        }

        return true;
    }


    public List<String> sort(List<String> words, List<String> alphabet) {
        Comparator<String> comparator = (o1, o2) -> {
            String[] s1 = o1.split("\\s");
            String[] s2 = o2.split("\\s");
            int minLen = Math.min(s1.length, s2.length);

            for (int i = 0; i < minLen; i++) {
                int order1 = alphabet.indexOf(s1[i]);
                int order2 = alphabet.indexOf(s2[i]);
                if (order1 < order2) return -1;
                else if (order1 > order2) return 1;
            }

            return s1.length < s2.length ? -1 : 1;
        };

        Collections.sort(words, comparator);

        return words.stream().map(el -> el.replaceAll("\\s", "")).collect(Collectors.toList());
    }
}
