/*
Given a string s, reverse the string according to the following rules:

All the characters that are not English letters remain in the same position.
All the English letters (lowercase or uppercase) should be reversed.
Return s after reversing it.

Example 1:
Input: s = "ab-cd"
Output: "dc-ba"

Example 2:
Input: s = "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: s = "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

Constraints:

1 <= s.length <= 100
s consists of characters with ASCII values in the range [33, 122].
s does not contain '\"' or '\\'.

idea:
two pointers
*/

class ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char leftChar = chars[left];
            char rightChar = chars[right];

            if (Character.isLetter(leftChar) && Character.isLetter(rightChar)) {
                swap(chars, left, right);
                left++;
                right--;
            } else if (!Character.isLetter(leftChar) && Character.isLetter(rightChar)) {
                left++;
            } else if (Character.isLetter(leftChar) && !Character.isLetter(rightChar)) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return new String(chars);
    }

    public void swap(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }
}