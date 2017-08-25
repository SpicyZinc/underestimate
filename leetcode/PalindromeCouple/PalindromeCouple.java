import java.util.*;

public class PalindromeCouple {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("cigar");
        list.add("tragic");
        list.add("none");
        list.add("xenon");
        list.add("abcd");
        list.add("dcba");
        list.add("lls");
        list.add("sssll");

        PalindromeCouple sol = new PalindromeCouple();
        System.out.println(sol.pairPalindrome(list));
    }
     
    public List<List<String>> pairPalindrome(List<String> words){
        List<List<String>> res = new ArrayList<List<String>>();
        if (words == null || words.size() == 0) {
            return res;
        }
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        HashSet<String> hashset = new HashSet<String>();
        for (int i = 0; i < words.size(); i++) {
            hm.put(words.get(i), i);
            hashset.add(words.get(i));
        }
        for (String word : words) {
            int N = word.length();
            for (int i = 0; i < N; i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i, N);
                String reverseSuffix = reverse(suffix);
                if (isPalindrome(prefix) && hashset.contains(reverseSuffix)) {
                    List<String> sol = new ArrayList<String>();
                    if (hm.get(reverseSuffix) != hm.get(word)) {
                        sol.add(reverseSuffix);
                        sol.add(word);
                        res.add(sol);
                    }
                }
            }
        }

        return res;
    }
     
    private boolean isPalindrome(String s){
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
}