/*
Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.
Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.
A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.

idea:
for making it lexicographical smaller non-palindromic after replacing a character , we will simply look for a character which is not 'a' from i = 0 to i < n/2 .
1.If such character exists , we will simply put palindrome[i]='a' and exists out of the loop while making flag=1 (there is such character exists)
2. If such character doesn't exist then flag will remain equal to 0 .In this case , we will simply put palindrome[n-1]='a';
*/
// Thu Mar  2 22:34:54 2023
class BreakAPalindrome {
    public String breakPalindrome(String palindrome) {
        int size = palindrome.length();

        if (size <= 1) {
            return "";
        }

        char[] chars = palindrome.toCharArray();
        int i = 0;
        for (; i < size / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                break;
            }
        }
        // All are 'a', only need to change last position to be 'b'
        if (i == size / 2) {
            chars[size - 1] = 'b';
        }

        return new String(chars);
    }
}
