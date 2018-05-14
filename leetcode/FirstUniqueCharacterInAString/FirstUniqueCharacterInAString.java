/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:
s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

idea:
direct thought with 26 length of array, equal to hashmap
loop again, find the 1st unique character
*/
public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        FirstUniqueCharacterInAString eg = new FirstUniqueCharacterInAString();
        int firstUniqChar = eg.firstUniqChar("leetcode");
        System.out.println(firstUniqChar);
    }

    public int firstUniqChar(String s) {
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            letters[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (letters[ch - 'a'] == 1) {
                return i;
            }

        }
        return -1;
    }
}