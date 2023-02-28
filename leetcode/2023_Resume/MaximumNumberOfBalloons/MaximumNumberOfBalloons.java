/*
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.


Example 1:

Input: text = "nlaebolko"
Output: 1

Example 2:

Input: text = "loonbalxballpoon"
Output: 2

Example 3:
Input: text = "leetcode"
Output: 0

Constraints:
1 <= text.length <= 104
text consists of lower case English letters only.

idea:
letters count
*/

class MaximumNumberOfBalloons {
    int[] balloonAlph;
    public int maxNumberOfBalloons(String text) {
        if (text.length() < 7) return 0;
        int[] alphabet = new int[26];
        for (char c : text.toCharArray()) alphabet[c - 'a']++;
        balloonAlph = balloonAlph == null ? createBalloonAlph() : balloonAlph;
        int count = 0;
        while (containsBalloon(alphabet)) count++;
        return count;
    }

    private boolean containsBalloon(int[] alphabet) {
        for (int i = 0; i < balloonAlph.length; i++) {
            int count = balloonAlph[i], countA = alphabet[i];
            if (countA < count) return false;
            else alphabet[i] -= count;
        }

        return true;
    }

    private int[] createBalloonAlph() {
        String s = "balloon";
        int[] alphabet = new int[26];
        for (char c : s.toCharArray()) 
            alphabet[c - 'a']++;
        return alphabet;
    }
}
