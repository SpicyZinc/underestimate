/* 
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

idea:
note: it is unique words pair

for each word in words[]
left = substring(0, k)
right = substring(k, length)
"abece", "ba"
reversedLeft "ba" is contained in the map, right ece is palindrome
"eceab", "ba"
pay attention to the pair order

Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>();
and return new ArrayList<>(ret);
*/

import java.util.*;

public class PalindromePairs {
    public static void main(String[] args) {
        String[] words = {"a", ""};
        // String[] words = {"abcd","dcba","lls","s","sssll"};
        // Cigar? Toss it in a can. It is so tragic.
        PalindromePairs pp = new PalindromePairs();
        pp.palindromePairs(words);

        String s = "abc";
        String ss = s.substring(0,0);
        System.out.println();
        System.out.println(ss + "|");
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<>();
        if (words.length == 0 || words == null) {
            return pairs;
        }
        
        Map<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            hm.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int size = word.length();
            // note, substring() j can be size
            // 1. current word self is palindrome
            // 2. get reversed string of whole current word, case of ["a", ""]
            for (int j = 0; j <= size; j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);
                String reversedLeft = reverse(left);
                String reversedRight = reverse(right);
                
                if (hm.containsKey(reversedLeft) && isPalindrome(right)) {
                    if (i != hm.get(reversedLeft)) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(i);
                        pair.add(hm.get(reversedLeft));
                        pairs.add(pair);
                    }
                }
                // avoid duplicates e.g. case ["abcd", "dcba"]
                if (hm.containsKey(reversedRight) && left.length() > 0 && isPalindrome(left)) {
                    if (i != hm.get(reversedRight)) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(hm.get(reversedRight));
                        pair.add(i);
                        pairs.add(pair);
                    }
                }
            }
        }
        
        return pairs;
    }
    
    public String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    public boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // concise version, but I don't like l, r, confusing
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new LinkedList<>();
        if (words.length == 0 || words == null) {
            return pairs;
        }
        Map<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            hm.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int l = 0, r = 0;
            while (l <= r) {
                String s = word.substring(l, r);
                Integer j = hm.get(new StringBuilder(s).reverse().toString());
                int left, right;
                if (l == 0) {
                    left = r;
                    right = word.length();
                } else {
                    left = 0;
                    right = l;
                }
                String rest = word.substring(left, right);
                if (j != null && j != i && isPalindrome(rest)) {
                    if (l == 0) {
                        pairs.add(Arrays.asList(new Integer[]{i, j}));
                    } else {
                        pairs.add(Arrays.asList(new Integer[]{j, i}));
                    }
                }

                if (r < word.length()) {
                    r++;
                } else {
                    l++;
                }
            }
        }
        
        return pairs;
    }
}