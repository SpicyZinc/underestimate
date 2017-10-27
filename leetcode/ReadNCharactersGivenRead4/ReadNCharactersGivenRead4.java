/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.

idea:
note: use read4 to read n characters, buf can contain more than n or less than n character
read4 is read 4 characters from source file to a temporary holder

if read4 returns zero meaning reading is finished
read4 returns the number of characters actually left in the file
*/

/** The read4 API is defined in the parent class Reader4. int read4(char[] buf); */

public class ReadNCharactersGivenRead4 extends Reader4 {
	public int read(char[] buf, int n) {
		int size = 0;
		char[] tempHolder = new char[4];
		while (true) {
			int len = read4(tempHolder);
			// if no characters left, return 0
			if (len == 0) {
				return size;
			}
			// require read n characters so size must < n
			for (int i = 0; i < len && size < n; i++) {
				buf[size++] = tempHolder[i];
			}
		}
		// till here, buf has been populated
		return size;
	}




	int read(char *buf, int n) {
        char _buf[4];   // the buffer for read4()
        int _n = 0;     // the return for read4()
        int len = 0;    // total buffer read from read4()
        int size = 0;   // how many bytes need be copied from '_buf' to 'buf'
        while ((_n = read4(_buf)) > 0) {
            // check the space of 'buf' whether enough or not
            size = len + _n > n ? n-len : _n;
            strncpy(buf + len, _buf, size);
            len += size;
            // buffer is full
            if (len >= n) {
                break;
            }
        }

        return len;
    }
}