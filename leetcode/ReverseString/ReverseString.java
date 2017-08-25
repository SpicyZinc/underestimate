/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

idea:
1. convert to char array, swap, return new String(chars)
2. use StringBuilder, reverse() function
3. recursively
*/
public class ReverseString {
    public static void main(String[] args) {
        ReverseString eg = new ReverseString();
        System.out.println(eg.reverseString("abc"));

    }
    // method 2: use swap method
    public String reverseString(String s) {
        if (s.length() == 0 || s == null) {
            return s;
        }

        int length = s.length();
        char[] chars = s.toCharArray();
        for (int L = 0, R = length - 1; L < R; L++, R--) {
            char temp = chars[L];
            chars[L] = chars[R];
            chars[R] = temp;
        }

        return new String(chars);
    }

    public String reverseString(String s){
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int begin = 0, end = s.length() - 1;
        while (begin <= end) {
            char c = chars[begin];
            chars[begin] = chars[end];
            chars[end] = c;
            begin++;
            end--;
        }
        
        return new String(chars);
    }

    // method 1, use StringBuilder reverse()
    public String reverseString(String s) {
        StringBuilder str = new StringBuilder(s);
        return str.reverse().toString();
    }
    // method 3, recursively reverse
    //  Memory Limit Exceeded
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return reverseString(s.substring(1)) + s.substring(0, 1);
    }
}
