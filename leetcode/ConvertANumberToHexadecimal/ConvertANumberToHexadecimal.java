/*
Given an integer, write an algorithm to convert it to hexadecimal.
For negative integer, two’s complement method is used.

Note:
All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.

Example 1:
Input: 26
Output: "1a"

Example 2:
Input: -1
Output: "ffffffff"


idea:
Arithmetic right shift (>>) is sign-preserving
Logical right shift (>>>)

1. Integer.toHexString()
2. implementation with right shift
3. generalize it to covert to n-nary

*/

public class ConvertANumberToHexadecimal {
    // method 1
    public String toHex(int num) {
        return Integer.toHexString(num);
    }
    // method 2
    public String toHex(int num) {
        String digits = "0123456789abcdef";
        if (num == 0) {
            return "0";
        }
        String result = "";
        while (num != 0) {
            result = digits.charAt(num & 15) + result;
            num = (num >>> 4);
        }
        return result;
    } 
    // method 3, only applies to positive number
    // not passed negative
    public String toHex(int num) {
        String digits = "0123456789abcdef";
        if (num <= 0) {
            return "0";
        }
        String hex = "";
        int base = 16;
        while (num > 0) {
            int digit = num % base;
            hex = digits.charAt(digit) + hex;
            num = num / base;
        }

        return hex;
    }
}


    StringBuilder res = new StringBuilder();
      
      while (dec != 0) {
          int digit = dec & 0xf;
          res.append(digit < 10 ? (char)(digit + '0') : (char)(digit - 10 + 'a'));
          dec >>>= 4;
      }
    
// class ConvertANumberToHexadecimal {
//     public static void main(String[] args) {
//         ConvertANumberToHexadecimal eg = new ConvertANumberToHexadecimal();
//         String result = eg.toNary(8, 2);
//         System.out.println(result);
//     }

//     public String toNary(int num, int base) {
//         if (num == 0) {
//             return "0";
//         }

//         String result = "";
//         while (num > 0) {
//             int r = num % base;
//             result = r + result;
//             num = num / base;
//         }

//         return result;
//     }
// }

