/*
A sentence S is given, composed of words separated by spaces.
Each word consists of lowercase and uppercase letters only.
We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

Example 1:
Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

Example 2:
Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 
Notes:
S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.

idea:
direct
*/

class GoatLatin {
    public String toGoatLatin(String S) {
        String[] matches = S.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.length; i++) {
            String s = matches[i];
            String newStr = "";
            if (isVowel(s.charAt(0))) {
                newStr = s + "ma";
            } else {
                newStr = s.substring(1) + s.substring(0, 1) + "ma";
            }

            sb.append(newStr + repeatA(i + 1) + (i == matches.length - 1 ? "" : " "));
        }

        return sb.toString();
    }
    // 2025
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split("\\s");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String newStr = transform(words[i]);
            sb.append(newStr + repeatA(i + 1) + (i == words.length - 1 ? "" : " "));
        }

        return sb.toString();
    }

    public String transform(String s) {
        char[] chars = s.toCharArray();
        if (isVowel(chars[0])) {
            return s + "ma";
        } else {
            return s.substring(1) + chars[0] + "ma";
        }
    }


    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        String c = String.valueOf(ch);
        return vowels.contains(c);
    }

    public String repeatA(int num) {
        String s = "";
        for (int i = 0; i < num; i++) {
            s += 'a';
        }

        return s;
    }

    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        String c = String.valueOf(ch);
        return vowels.contains(c);
    }

    public String repeatA(int num) {
        String s = "";
        for (int i = 0; i < num; i++) {
            s += 'a';
        }

        return s;
    }
}
