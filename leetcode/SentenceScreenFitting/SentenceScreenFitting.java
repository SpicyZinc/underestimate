/*
Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.
Note:
A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word won't exceed 10.
1 ≤ rows, cols ≤ 20,000.

Example 1:
Input:
rows = 2, cols = 8, sentence = ["hello", "world"]
Output: 
1
Explanation:
hello---
world---
The character '-' signifies an empty space on the screen.

Example 2:
Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
Output: 
2
Explanation:
a-bcd- 
e-a---
bcd-e-
The character '-' signifies an empty space on the screen.

Example 3:
Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
Output: 
1
Explanation:
I-had
apple
pie-I
had--
The character '-' signifies an empty space on the screen.

idea:
hard to describe, loop each row
for each row, colsRemaining = cols
*/

import java.util.*;

class SentenceScreenFitting {
	public static void main(String[] args) {
		SentenceScreenFitting eg = new SentenceScreenFitting();
		// String[] sentence = {"a", "bcd", "e"};
		String[] sentence = {"I", "had", "apple", "pie"};
		// int rows = 3;
		int rows = 4;
		// int cols = 6;
		int cols = 5;
		int cnt = eg.wordsTyping(sentence, rows, cols);

		System.out.println(cnt);
	}

	public int wordsTyping(String[] sentence, int rows, int cols) {
		String finalSentence = "";
		for (String word : sentence) {
			finalSentence += (word + " ");
		}

		int len = finalSentence.length();
		int n = sentence.length;
		int fittingCnt = 0;
		int idx = 0;

		for (int i = 0; i < rows; i++) {
			int colsRemaining = cols;

			while (colsRemaining > 0) {
				if (sentence[idx].length() <= colsRemaining) {
					colsRemaining -= sentence[idx].length();
					// minus one space
					if (colsRemaining > 0) {
						colsRemaining -= 1;
					}
					// go to next word in sentence
					idx++;
					// if finish fitting one time of the sentence
					if (idx >= n) {
						fittingCnt += (colsRemaining / len + 1);
						colsRemaining %= len;
						idx = 0;
					}
				} else {
					break;
				}
			}
		}

		return fittingCnt;
	}

	// https://medium.com/@rebeccahezhang/leetcode-418-sentence-screen-fitting-9d6258ce116e
	// hard to understand
	public int wordsTyping(String[] sentence, int rows, int cols) {
		String totalSentence = "";
		for (String word : sentence) {
			totalSentence += (word + " ");
		}

		int len = totalSentence.length();
		int start = 0; // pointer, refers to 能装下句子包括空格的总长度

		for (int i = 0; i < rows; i++) {
            start += cols;
            if (totalSentence.charAt(start % len) == ' ') {
                start++;
            } else {
                while (start > 0 && totalSentence.charAt((start - 1) % len) != ' ') {
                    start--;
                }
            }
        }

        return start / len;
	}
}