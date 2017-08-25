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
            if (i != 0) {
                ret += ss[i] + " ";
            }
            else {
                ret += ss[i] + "";
            }
        }

        return ret;
    }
}