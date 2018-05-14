/*
remember 
trim() first

then do what I want to do naturally

*/

public class ReverseWordsInAString  {

    public static void main(String[] args) {
        new ReverseWordsInAString();
    }

    // constructor
    public ReverseWordsInAString() {
        String s = " 1";
        String ret = reverseWords(s);

        System.out.println("|" + ret + "|");
    }
    public String reverseWords(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }

        s = s.trim();
        String[] ss = s.split("\\s+");

        String ret = "";        
        for (int i = ss.length - 1; i >= 0; i--) {
            ret += ss[i] + (i != 0 ? " " : "");
        }

        return ret;
    }

    public String reverseWords(String s) {
        s = s.trim();
        int len = s.length();
        int idx = 0;
        int start = 0;
        
        List<String> words = new ArrayList<String>();
        
        while (idx < len) {
            if (s.charAt(idx) == ' ') {
                while (s.charAt(idx) == ' ') {
                    idx++;
                }
                String word = s.substring(start, idx);
                words.add(word);
                start = idx;
            } else {
                idx++;
            }
        }
        // Don't forget the last word
        String lastWord = s.substring(start, len);
        words.add(lastWord);
        
        String result = "";
        for (int i = words.size() - 1; i >= 0; i--) {
            result += words.get(i) + (i > 0 ? " " : "");
        }
        
        return result;
    }
}