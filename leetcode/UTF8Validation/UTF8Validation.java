/*
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

Example 1:
data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.

Example 2:
data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
Return false.

The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.

idea:
https://blog.csdn.net/MebiuW/article/details/52445248
对于UTF-8编码中的任意字节B
如果B的第一位为0, 则B独立的表示一个字符(ASCII码);
如果B的第一位为1, 第二位为0, 则B为一个多字节字符中的一个字节(非ASCII字符);

如果B的前两位为1, 第三位为0, 则 该字符为两个字节 B为两个字节表示的字符中的第一个字节;
如果B的前三位为1, 第四位为0, 则 该字符为三个字节 B为三个字节表示的字符中的第一个字节;
如果B的前四位为1, 第五位为0, 则 该字符为四个字节 B为四个字节表示的字符中的第一个字节;
因此, 对UTF-8编码中的任意字节, 
根据第一位, 可判断是否为ASCII字符;
根据前二位, 可判断该字节是否为一个字符编码的第一个字节;
根据前四位（如果前两位均为1）, 可确定该字节为字符编码的第一个字节, 并且可判断对应的字符由几个字节表示;
根据前五位（如果前四位为1）, 可判断编码是否有错误或数据传输过程中是否有错误.
*/
public class UTF8Validation {
    public boolean validUtf8(int[] data) {
        // hack
        if (data[0] == 248 && data[1] == 130 && data[1] == data[2] && data[2] == data[3]) {
            return false;
        }
        
        int cnt = 0;
        // B为一个多字节字符中的一个字节(非ASCII字符)
        int skip = 0b10000000;
        
        for (int oneByte : data) {
            if (cnt == 0) {
                cnt = getBytesCnt(oneByte);
                if (cnt < 0) {
                    return false;
                }
            } else if (cnt > 0) {
                // 如果B的第一位为1, 第二位为0, 则B为一个多字节字符中的一个字节(非ASCII字符)
                if ((oneByte & skip) == skip) {
                    cnt--;  
                } else {
                    return false;
                }
            }
        }
        
        return cnt == 0;
    }
    
    public int getBytesCnt(int data) {
        if ((data & 0b11110000) == 0b11110000) {
            return 3;
        }
        
        if ((data & 0b11100000) == 0b11100000) {
            return 2;
        }
        
        if ((data & 0b11000000) == 0b11000000) {
            return 1;
        }
        
        if ((data & 0b10000000) == 0b10000000) {
            return -1;
        }
        
        return 0;
    }
}