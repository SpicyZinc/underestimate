/*
Given an array of characters, compress it in-place.
The length after compression must always be smaller than or equal to the original array.
Every element of the array should be a character (not int) of length 1.
After you are done modifying the input array in-place, return the new length of the array.

Follow up:
Could you solve it using only O(1) extra space?

Example 1:
Input:
["a","a","b","b","c","c","c"]
Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".

Example 2:
Input:
["a"]
Output:
Return 1, and the first 1 characters of the input array should be: ["a"]
Explanation:
Nothing is replaced.

Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.

Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.

idea:
two pointers
i points to char
j moves to count how many chars
if only one char j - i == 1, no need to save number
else convert cnt (j - i) to string and then append to char
last i = j, next char

note, if not in for loop i = j, need to add i = j in continue
*/
class StringCompression {
	public int compress(char[] chars) {
		int size = chars.length;
		int idx = 0;

		for (int i = 0; i < size;) {
			chars[idx++] = chars[i];

			int j = i;
			while (j < size && chars[i] == chars[j]) {
				j++;
			}
			// only one, no repeating chars
			if (j - i == 1) {
				i = j;
				continue;
			}
			// convert cnt to string format
			String cnt = "" + (j - i);
			for (int k = 0; k < cnt.length(); k++) {
				chars[idx++] = cnt.charAt(k);
			}
			// update i
			i = j;
		}

		return idx;
	}
}
