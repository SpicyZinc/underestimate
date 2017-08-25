/*
Given a string s and a dictionary of words dict, 
add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

idea:
typical DFS
*/

public class WordBreakII {
    // self written version
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> oneList = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return ret;
        }
        
        wordBreakHelper(s, dict, ret, oneList);
        return ret;
    }
    
    public void wordBreakHelper(String s, Set<String> dict, ArrayList<String> ret, ArrayList<String> oneList) {
        if (s.length() == 0) {
            ret.add(convertToString(oneList));
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i+1; j < s.length(); j++) {
                String tmp = s.substring(i, j);
                if (dict.contains(tmp)) {
                    oneList.add(tmp);
                    wordBreakHelper(s.substring(j), dict, ret, oneList);
                    oneList.remove(oneList.size() - 1);
                }
            }
        }
    }
    
    public String convertToString(ArrayList<String> list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
               s += list.get(i) + " "; 
            }
            else {
                s += list.get(i);
            }
        }
        
        return s;
    }

    // timeout
    public ArrayList<String> wordBreak(String s, Set<String> dict) {  
        ArrayList<String> res = new ArrayList<String>();  
        if (s == null || s.length() == 0) {
            return res;
        }
        helper(s, dict, 0, "", res);  
        return res;  
    }  
    private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {  
        if (start >= s.length()) {  
            res.add(item);  
            return;  
        }  
        StringBuilder str = new StringBuilder();  
        for (int i=start; i<s.length(); i++) {  
            str.append(s.charAt(i));  
            if (dict.contains(str.toString())) {  
                String newItem = item.length() > 0 ? (item + " " + str.toString()) : str.toString();  
                helper(s, dict, i+1, newItem, res);  
            }  
        }  
    }

    // timeout
    public List<String> wordBreak(String s, Set<String> dict) {  
        if (s == null || s.length() == 0) {
            return new ArrayList<String>();
        }
        int n = s.length();  
        boolean[] wb = new boolean[n+1];  
        wb[0] = true;  
        List<List<String>> words = new ArrayList<List<String>>();  
        for (int i = 0; i <= n; i++)  
            words.add(new ArrayList<String>());  
        words.get(0).add("");  
        for (int i = 1; i <= n; i++) {  
            for (int j = 0; j < i; j++) {  
                String temp = s.substring(j, i);  
                if (wb[j] && dict.contains(temp)) {  
                    wb[i] = true;  
                    for (String str : words.get(j)) {  
                        if (str.equals("")) {
                            words.get(i).add(String.format("%s", temp));
                        }
                        else {
                            words.get(i).add(String.format("%s %s", str, temp));
                        }
                    }  
                }  
            }  
        }  
        return words.get(n);  
    }

    // passed test
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s, dict, map);
    }
   
    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);   
        }
        ArrayList<String> result = new ArrayList<String>();
        int n = s.length();
        if (n <= 0) {
            return result;   
        }
        for (int len = 1; len <= n; len++) {
            String subfix = s.substring(0, len);
            if (dict.contains(subfix)) {
                if (len == n) {
                    result.add(subfix);
                }
                else {
                    String prefix = s.substring(len);
                    ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                    for (String item : tmp) {
                        item = subfix + " " + item;
                        result.add(item);
                    }
                }  
            }
        }
        memo.put(s, result);
        return result;
    }
}