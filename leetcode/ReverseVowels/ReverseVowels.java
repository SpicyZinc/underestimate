/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

idea:
need to convert string to charArray
two pointers i j left and right
while loop through the string
not vowels, continue
yes vowels, swap()
final return new String() 

use String.contains('charSequence') which is a string
of course can use vowels.indexOf(c) != -1
*/
public class ReverseVowels {
	public static void main(String[] args) {
		String s = "hello";
		ReverseVowels eg = new ReverseVowels();
		String reversedVowels = eg.reverseVowels(s);
		System.out.println(reversedVowels);
	}

    public String reverseVowels(String s) {
        int i = 0, j = s.length()-1;
        char[] arr = s.toCharArray();

        while (i < j) {
            if (!isVowel(arr[i])) {
                i++;
                continue;
            }
            if (!isVowel(arr[j])) {
                j--;
                continue;
            }

            swap(arr, i, j);
            i++;
            j--;
        }

        return new String(arr);
    }

    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        String c = String.valueOf(ch);

        return vowels.contains(c);
    }

    public void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}