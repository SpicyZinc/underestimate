/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:
A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.

Example 1:
Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:
Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.

Example 3:
Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

idea:
direct algorithm, but have to take care of every corner case

use while() to walk line by line in the returned list 
for each line, stop at length of such pairs of word - oneSpace > L,
then current index (ci) go backwards by one,
then divide two cases to code
two cases:
1. a line with one word
2. a line with multiple words.

note, last line, left-justified, and pad spaces at the right of the line
note, spaces not equally assigned, the empty slots on the left will be assigned more spaces than the slots on the right.
*/

import java.util.*;

public class TextJustification {
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification"};
		int L = 16;
		
		TextJustification eg = new TextJustification();
		List<String> result = eg.fullJustify(words, L);

		for (String line : result) {
			System.out.println(line);
		}
	}
	// 01/21/2019
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> lines = new ArrayList<String>();
		
		int size = words.length;
		int start = 0;

		while (start < size) {
			int currentLineLen = words[start].length();
			int currentIdx = start + 1;

			while (currentIdx < size) {
				// note, where to check and how to check > maxWidth
				// 要break后 仍然保持 currentLineLen 没有超过 maxWidth
				if (currentLineLen + 1 + words[currentIdx].length() > maxWidth) {
					break;
				}
				currentLineLen += 1 + words[currentIdx++].length(); // word0 + (" " + word1)
			}
			// two conditions to break, currentIdx reaches size or currentLineLen > maxWidth
			// since break here, inclusive words at positions before will be on this line
			// [start, currentIdx - 1]
			
			int gaps = currentIdx - 1 - start;

			StringBuilder sb = new StringBuilder();

			// if this line is last line or only has one word
			if (currentIdx == size || gaps == 0) {
				for (int i = start; i <= currentIdx - 1; i++) {
					sb.append(i == currentIdx - 1 ? words[i] : words[i] + " ");
				}
				// append spaces, because left justified, so at last append spaces
				appendSpaces(sb, maxWidth - sb.length());
			} else { // regular justified, last one must be a word
				int spacesPerGap = (maxWidth - currentLineLen) / gaps;
				int remainingSpaces = (maxWidth - currentLineLen) % gaps; // 不够所有gaps 所以先即左边 填完为止

				// loop through gaps
				for (int i = start; i <= currentIdx - 1; i++) {
					// at first, append word
					sb.append(words[i]);
					if (i < currentIdx - 1) {
						int reCalculatedSpacesPerGap = (spacesPerGap + 1) + ((i - start) < remainingSpaces ? 1 : 0);
						appendSpaces(sb, reCalculatedSpacesPerGap);
					}
				}
			}

			lines.add(sb.toString());
			start = currentIdx;
		}

		return lines;
	}

	public void appendSpaces(StringBuilder sb, int cnt) {
		for (int i = 0; i < cnt; i++) {
			sb.append(" ");
		}
	}

	// 09/28/2018
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> lines = new ArrayList<String>();

		int size = words.length;
		int start = 0;

		while (start < size) {
			int currLineWidth = words[start].length();
			int last = start + 1;

			while (last < size) {
				// word + " "
				if (currLineWidth + words[last].length() + 1 > maxWidth) {
					break;
				}
				currLineWidth += words[last].length() + 1;
				last++;
			}

			StringBuilder sb = new StringBuilder();
			// each line the number of gaps among words
			// actually (last - 1) is the last idx for this line
			int gaps = last - start - 1;
			// last line or line with only one word, left justified
			if (last == size || gaps == 0) {
				for (int i = start; i <= last - 1; i++) {
					// note, last word does not have following space
					sb.append(i == last - 1 ? words[i] : words[i] + " ");
				}
				// pad the rest with spaces
				for (int i = sb.length(); i < maxWidth; i++) {
					sb.append(" ");
				}
			} else { // regular justified
				int spacesInBetween = (maxWidth - currLineWidth) / gaps; // note: this currLineWidth contains word + " "
				int remaining = (maxWidth - currLineWidth) % gaps;
				for (int i = start; i <= last - 1; i++) {
					sb.append(words[i]);
					if (i < last - 1) {
						// left justified, 平分剩下的spaces, 先即左边
						int reCalculatedSpaces = spacesInBetween + ((i - start) < remaining ? 1 : 0);

						for (int j = 0; j <= reCalculatedSpaces; j++) { // note: equal means those spaces in currLineWidth have to be added again
							sb.append(" ");
						}
					}
				}
			}

			lines.add(sb.toString());
			start = last;
		}

		return lines;
	}
}