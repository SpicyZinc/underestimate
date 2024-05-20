
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.
A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.

For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.

Example 1:
Input: s = "is2 sentence4 This1 a3"
Output: "This is a sentence"
Explanation: Sort the words in s to their original positions "This1 is2 a3 sentence4", then remove the numbers.

Example 2:
Input: s = "Myself2 Me1 I4 and3"
Output: "Me Myself and I"
Explanation: Sort the words in s to their original positions "Me1 Myself2 and3 I4", then remove the numbers.

Constraints:
2 <= s.length <= 200
s consists of lowercase and uppercase English letters, spaces, and digits from 1 to 9.
The number of words in s is between 1 and 9.
The words in s are separated by a single space.
s contains no leading or trailing spaces.

class SortingTheSentence {
    // much faster
    public String sortSentence(String s) {
        // String[] str = s.split("\\s+");
        // much faster than reg
        String[] str = s.split(" ");
        String[] res = new String[str.length];
        int i = 0;
        for (String elem : str) {
            i = (int) (elem.charAt(elem.length() - 1) - '0');  // This  will extract the digit present at the end of each String and store it in i (i = index of the word in res[])
            res[i - 1] = elem.substring(0, elem.length() - 1); // This gives the substring of each String except for the digit in the end and stores the word at an appropriate index (i - 1) in res[]
        }

        StringBuilder sb = new StringBuilder();
        // append the words from res[] to StringBuilder object to form a sentence
        for (i = 0; i < res.length - 1; i++) {
            sb.append(res[i]).append(" ");
        }
        sb.append(res[i]);

        return sb.toString();
    }

    public String sortSentence(String s) {
        String[] list = s.split("\\s+");
        String[] result = new String[list.length];
        for (String ss: list) {
            System.out.println(ss);
            int idx = Integer.parseInt(ss.substring(ss.length() - 1)) - 1;
            result[idx] = ss.substring(0, ss.length() - 1);
        }

        return String.join(" ", result);
    }
}
