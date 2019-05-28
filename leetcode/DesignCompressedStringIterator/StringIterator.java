/*
Design and implement a data structure for a compressed string iterator.
It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter
followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases.
Please see here for more details.

Example:
StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

idea:
1. my method Memory Limit Exceeded [["a1234567890b1234567890"],[],[],[],[],[],[],[],[],[]]
2. No need to convert to literal string
*/

public class StringIterator {
	int idx;
	String s;

	public StringIterator(String compressedString) {
		this.idx = 0;

		StringBuilder sb = new StringBuilder();
		char lastChar = ' ';
		for (int i = 0; i < compressedString.length();) {
			int val = 0;
			char c = compressedString.charAt(i); 

			if (Character.isLetter(c)) {
				lastChar = c;
				i++;
			} else if (Character.isDigit(c)) {
				while (i < compressedString.length() && Character.isDigit(compressedString.charAt(i))) {
					val = val * 10 + compressedString.charAt(i) - '0';
					i++;
				}
				for (int j = 0; j < val; j++) {
					sb.append(lastChar);
				}
			}
	    }
	    this.s = sb.toString();
	}
	
	public char next() {
		if (idx == s.length()) {
			return ' ';
		} else {
			return s.charAt(idx++);
		}
	}
	
	public boolean hasNext() {
		return idx < s.length();
	}
}

// passed test
public class StringIterator {
	int i;
	long repetitions;
	char ch;
	String compressedString;
	
	public StringIterator(String compressedString) {
		this.i = 0;
		this.repetitions = 0;
		this.ch = ' ';
		this.compressedString = compressedString;
	}

	public char next() {
		if (repetitions > 0) {
			repetitions--;

			return ch;
		} else if (i < compressedString.length()) {
			ch = compressedString.charAt(i);
			int j = i + 1;
			while (j < compressedString.length() && Character.isDigit(compressedString.charAt(j))) {
				j++;
			}
			repetitions = Long.parseLong(compressedString.substring(i + 1, j)) - 1;
			i = j;

			return ch;
		} else {
			return ' ';
		}
	}

	public boolean hasNext() {
		return repetitions > 0 || i < compressedString.length();
	}
}