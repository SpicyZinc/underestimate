/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line.

Pad extra spaces ' ' when necessary so that each line has exactly L characters.
Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.
Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.


idea:
direct algorithm, but have to take care of every corner case

use while() to walk line by line in the return ArrayList 
for each line, stop at length of such pairs of word-oneSpace > L,
then current index (ci) go backwards by one,
then divide two cases to code
two cases:
1. one line with one word
2. one line with multiple words.

Attention to last line, left-justified, and pad spaces at the right of the line
Attention to spaces not equally assigned 
the empty slots on the left will be assigned more spaces than the slots on the right.
*/

import java.util.*;

public class TextJustification {
	public static void main(String[] args) {
		String[] words = new String[7];
		words[0] = "this";
		words[1] = "is";
		words[2] = "an";
		words[3] = "example";
		words[4] = "of";
		words[5] = "text";
		words[6] = "justification.";
		int L = 16;
		
		TextJustification aTest = new TextJustification();
		ArrayList<String> res = aTest.fullJustify(words, L);
		
		for (int i=0; i<res.size(); i++) {
			System.out.println(res.get(i));
		}		
	}
	
    public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> ret = new ArrayList<String>();
		// simple case empty words array
		if (words.length == 0) {
			return ret;
		}
			
		// StringBuffer sb = new StringBuffer(); also works
		StringBuilder sb = new StringBuilder();
		int charCnt = 0; // count of chars for one line
		int wi = 0; // words index
		int ci = wi; // current index, for each line in the resultant arraylist
		while (wi < words.length) {
			for (ci=wi; ci<words.length; ci++) {			
				if (charCnt + (ci-wi) + words[ci].length() > L) { // each word is followed by a space
					break;
				}
				charCnt += (words[ci].length());
			}
			// since ci makes charCnt > L, go backwards by one
			// this is where index in words array for one line
			ci--; 
			// when ci becomes wi-1 
			// in case for loop breaks at first, then ci--, may cause ci < wi, but this word has been taken care of
			if (wi > ci) {
				ci = wi;
			}
			// start to generate a line
			// only one word in this line
			// append word first, add spaces
			if (wi >= ci) {
				sb.append(words[wi]);
				// count space, loop to append
				for (int s=0; s<(L-words[wi].length()); s++) {
					sb.append(' ');
				}				
				ret.add(sb.toString());
			}
			// multiple words in this line
			else {
				int baseSpaces = (L - charCnt) / (ci - wi); // ci-wi is cnt of spaces between words
				int leftSpaces = (L - charCnt) % (ci - wi);
				boolean ifLastLine = (ci == (words.length - 1));
				// for the last line of text, it should be left justified and no extra space is inserted between words.
				// one space between words is necessary
				if (ifLastLine) {
					baseSpaces = 1;
					leftSpaces = 0;
				}
				
				// append the first word into sb				
				sb.append( words[wi] );
				// then in a format of spaces - word
				for (int j=wi+1; j<=ci; j++) {
					// append spaces
					int actualSpaces = baseSpaces;
					leftSpaces--;
					if (leftSpaces >= 0) {
						actualSpaces++;
						for (int s=0; s<actualSpaces; s++) {
							sb.append(' ');						
						}
					}
					else {
						for (int s=0; s<baseSpaces; s++) {
							sb.append(' ');						
						}
					}					
					sb.append(words[j]);
				}
				// if last line, pad spaces at the right to get L chars
				if (ifLastLine) {
					for (int s = 0; s < L - (charCnt + ci-wi); s++) {
						sb.append(' ');	
					}
				}
				ret.add(sb.toString());
			}
			// progress for next line
			wi = ++ci;
			charCnt = 0;
			// sb = new StringBuilder();
			sb.setLength(0);
		}
		return ret;
    }
}

// even simpler version, use this when interview 
public class TextJustification {
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification"};
		int L = 16;
		
		TextJustification aTest = new TextJustification();
		ArrayList<String> res = aTest.fullJustify(words, L);
		
		for (int i=0; i<res.size(); i++) {
			System.out.println(res.get(i));
		}		
	}

    public ArrayList<String> fullJustify(String[] words, int L) {
    	int length = words.length;
        ArrayList<String> result = new ArrayList<String>();
        if (words == null || length == 0) {
            return result;
        }

        int begin = 0, end = 0; // words[begin...end-1] as a line
        while (begin < length) {
            // determine end such that words[begin...end-1] fit in a line and words[begin...end] do not.
            int currentLength = words[begin].length();
            for (end = begin + 1; end < length; end++) {
                if (currentLength + 1 + words[end].length() <= L) {
                    currentLength += 1 + words[end].length();
                }
                else {
                    break;
                }
            }
            // construct a justified line with words[begin...end-1]
            StringBuilder temp = new StringBuilder();
            temp.append(words[begin]);
            // Last line or a line with only one word
            if (end == length || end == begin + 1) {
                // left justified
                for (int i = begin + 1; i < end; i++) {
                    temp.append(' ');
                    temp.append(words[i]);
                }
                for (int i = 0; i < L - currentLength; i++) {
                    temp.append(' ');
                }
            } 
            else { // regular lines which is fully justified
                int spaceInBetween = end - 1 - begin;
                // collect all left spaces, and reassign them 
                double spacesLeftOver = L - currentLength + spaceInBetween;
                for (int i = begin + 1; i < end; i++) {
                    for (int j = 0; j < spacesLeftOver / spaceInBetween; j++) {
                        temp.append(' ');
                    }
                    spacesLeftOver -= Math.ceil(spacesLeftOver / spaceInBetween);
                    spaceInBetween--;
                    temp.append(words[i]);
                }
            }
            // ddd the line to the resulting list, and slide the window to the next position
            result.add(temp.toString());
            begin = end;
        }

        return result;
    }


    // self written version finally passed test
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> ret = new ArrayList<String>();
        int size = words.length;
        if (size == 0 || words == null) {
            return ret;
        }
        
        int start = 0;
        int end = 0;
        
        while (start < size) {
            int currentSize = words[start].length();
            for (end = start + 1; end < size; end++) {
                if (currentSize + 1 + words[end].length() <= L) {
                    currentSize += (1 + words[end].length());
                }
                else {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[start]);
            // last line or line with only one word
            // left justify
            if (end == size || end == start + 1) {
                for (int i = start + 1; i < end; i++) {
                    sb.append(' ');   
                    sb.append(words[i]);
                }
                for (int i = 0; i < L - currentSize; i++) {
                    sb.append(' ');
                }
            }
            else {
                int cntSpacesInBetween = end - 1 - start;
                // collect all spaces and reassign them
                double spacesLeftOver = L - currentSize + cntSpacesInBetween;
                for (int i = start + 1; i < end; i++) {
                    for (int j = 0; j < spacesLeftOver / cntSpacesInBetween; j++) {
                       sb.append(' '); 
                    }
                    sb.append(words[i]);
                    spacesLeftOver -= Math.ceil(spacesLeftOver / cntSpacesInBetween);
                    cntSpacesInBetween--;
                }
                
            }

            ret.add(sb.toString());
            start = end;
        }
        
        return ret;
    }
}

// the best so far
public List<String> fullJustify(String[] words, int maxWidth) {
	List<String> lines = new ArrayList<String>();

	int start = 0;
	int size = words.length;
	while (start < size) {
		int lineWidth = words[start].length();
		int last = start + 1;
		while (last < size) {
			if (lineWidth + words[last].length() + 1 > maxWidth) {
				break;
			}
			lineWidth += words[last].length() + 1;
			last++;
		}
		StringBuilder sb = new StringBuilder();
		int gaps = last - start - 1;
		// last line or line with only one word, left justified
		if (last == size || gaps == 0) {
			for (int i = start; i <= last - 1; i++) {
				// sb.append(words[i] + " ");
				sb.append(words[i]);
				// note, last word has no following space
				if (i < last - 1) {
					sb.append(" ");
				}
			}
			for (int i = sb.length(); i < maxWidth; i++) {
				sb.append(" ");
			}
		}
		else { // regular justified
			int spacesInBetween = (maxWidth - lineWidth) / gaps; // note: this lineWidth contains word + " "
			int remaining = (maxWidth - lineWidth) % gaps;
			for (int i = start; i <= last - 1; i++) {
				sb.append(words[i]);
				if (i < last - 1) {
					int reCalculatedSpaces = spacesInBetween + ((i - start) < remaining ? 1 : 0); 
					for (int j = 0; j <= reCalculatedSpaces; j++) { // note: equal means those spaces in lineWidth have to be added again
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