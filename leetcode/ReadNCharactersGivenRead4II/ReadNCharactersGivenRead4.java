/*
The API: int read4(char *buf) reads 4 characters at a time from a file. The return value is the actual number of characters read.
For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

idea:
The difference between this question and the first version is that the read() function is called multiple times.
The trouble here will be as the following example if using the first version solution:
file: "abcdefg"
read(3) read(2) read(2) should be "abc" "de" "fg"
but using first version solution it will print "abc" "ef" ""

using a buf4[] to store the characters read by using read4 and also a buf4Size and buf4Index
to keep track of the size of the buf4 and the next character to use in buf4[].

https://cheonhyangzhang.wordpress.com/2016/12/22/158-leetcode-java-read-n-characters-given-read4-ii-call-multiple-times-add-to-list-questioneditorial-solution-hard/

buf Destination buffer
n   Maximum number of characters to read
*/
public class Solution extends Reader4 {
	char[] temp = new char[4];
	int buf4Size = 4;
	int buf4Index = 4;

	// return the number of characters read
	public int read(char[] buf, int n) {
		int size = 0;

		while (size < n) {
			// 有的时候 buf4Index 受n 限制 没有到 4 所以要到下一轮 call read()
			// finishing transferring from buf4 to buf
			if (buf4Index >= buf4Size) {
				buf4Index = 0;
				
				buf4Size = read4(temp);
				if (buf4Size == 0) {
					break;
				}
			}
			buf[size++] = temp[buf4Index++];
		}

		return size;
	}
}