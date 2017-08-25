/* 
Given a list of unique words. 
Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

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
遍历单词列表words, 记当前单词为word, 下标为idx
1). 若当前单词word本身为回文, 且words中存在空串, 则将空串下标bidx与idx加入答案
2). 若当前单词的逆序串在words中, 则将逆序串下标ridx与idx加入答案
3). divide current word two parts: left, right
    3.1) 若left为回文, 并且right的逆序串在words中, 则将right的逆序串下标rridx与idx加入答案
    3.2) 若right为回文, 并且left的逆序串在words中, 则将left的逆序串下标idx与rlidx加入答案

best answer idea:
    the best method
    for each word in words array, substring(0, k)
    we have to consider the rest of the word substring(k, length), which could be first or second part of the string
*/

import java.util.*;

public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = {"a", ""};
        // String[] words = {"abcd","dcba","lls","s","sssll"};
        PalindromePairs pp = new PalindromePairs();
        pp.palindromePairs(words);

        String s = "abc";
        String ss = s.substring(0,0);
        System.out.println();
        System.out.println(ss + "|");
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (words == null || words.length == 0) {
            return res;
        }

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            hm.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int N = word.length();
            String reverseWord = reverse(word);
            List<Integer> sol = new ArrayList<Integer>();
            if (hm.containsKey("") && word != "" && isPalindrome(word)) {
                System.out.println(word);
                sol.add(i);
                sol.add(hm.get(""));
                res.add(sol);
                System.out.println("empty string first" + res.toString());
                sol = new ArrayList<Integer>();
                sol.add(hm.get(""));
                sol.add(i);
                res.add(sol);
                System.out.println("empty string second" + res.toString());
                sol = new ArrayList<Integer>();
            } else if (hm.containsKey(reverseWord)) {
                if (hm.get(reverseWord) != i) {
                    sol.add(i);
                    sol.add(hm.get(reverseWord));
                    res.add(sol);
                }
            } else {
                for (int j = 1; j < N; j++) {
                    String prefix = word.substring(0, j);
                    String suffix = word.substring(j, N);
                    String reversePrefix = reverse(prefix);
                    String reverseSuffix = reverse(suffix);
                    if (isPalindrome(prefix) && hm.containsKey(reverseSuffix)) {
                        if (hm.get(reverseSuffix) != i) {
                            sol.add(hm.get(reverseSuffix));
                            sol.add(i);    
                            res.add(sol);
                            System.out.println(res.toString());
                        }
                    }
                    if (isPalindrome(suffix) && hm.containsKey(reversePrefix)) {
                        if (hm.get(reversePrefix) != i) {
                            sol.add(i);
                            sol.add(hm.get(reversePrefix));
                            res.add(sol);
                            System.out.println(res.toString());
                        }
                    }
                }
            }
            System.out.println(i + ":  " + res.toString());
        }
        System.out.println(res.toString());
        return res;
    }
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
     
    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // self written passed
    // remember to remove duplicates
    // The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];
    // Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be duplicates in the output (consider test case ["abcd", "dcba"]).
    // Therefore I put a str2.length()!=0 to avoid duplicates.
    // Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<>();
        if (words.length == 0 || words == null) {
            return pairs;
        }
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            hm.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();
            for (int j = 0; j <= len; j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);
                String reversedLeft = reverse(left);
                String reversedRight = reverse(right);
                if (hm.containsKey(reversedLeft) && isPalindrome(right)) {
                    if (hm.get(reversedLeft) != i) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(i);
                        pair.add(hm.get(reversedLeft));
                        pairs.add(pair);
                    }
                }
                if (hm.containsKey(reversedRight) && isPalindrome(left) && left.length() > 0) {
                    if (hm.get(reversedRight) != i) {
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

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // concise version, but not like l, r, confusing
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new LinkedList<>();
        if (words.length == 0 || words == null) {
            return pairs;
        }
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
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