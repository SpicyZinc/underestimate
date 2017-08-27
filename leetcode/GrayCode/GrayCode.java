/*
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
00 - 0
01 - 1
11 - 3
10 - 2

Note:
For a given n, a gray code sequence is not uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

idea:
http://blog.csdn.net/u010500263/article/details/18209669

Deci Binary  Gray           Gray                Gray                        Gray
 0   000     000            00    reversed      10                        10 + 100 = 110  
 1   001     001            01    ------->      11  ---> + 100 (1<<(3-1)) 11 + 100 = 111
 2   010     011            11                  01                        01 + 100 = 101 
 3   011     010            10                  00                        00 + 100 = 100 
 ----------------------
 4   100     110    
 5   101     111    
 6   110     101    
 7   111     100    

prefix + mirror
grayCode(n) = concatenate both grayCode(n-1) and [reversed grayCode(n-1) + (1 << n-1)]
*/

import java.util.*;

public class GrayCode {
	public static void main(String[] args) {
		new GrayCode();
	}
	public GrayCode() {		
		List<Integer> ret = grayCode(3);
		for (int i=0; i<ret.size(); i++) {
			System.out.print(ret.get(i) + "  ");
		}
	}
    // best without recursion
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for (int i = 0; i < n; i++) {
            int size = result.size();
            int highestBit = 1 << i;
            // reversed n - 1 gray code
            for (int j = size - 1; j >= 0; j--) {
                result.add(highestBit + result.get(j));
            }
        }
        
        return result;
    }
    // easy to understand
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if (n == 0) {
            result.add(0);
            return result;
        }
        if (n == 1) {
            Collections.addAll(result, 0, 1);
            return result;
        }
        
        List<Integer> pre = grayCode(n - 1);
        // define size here because pre size is changing
        int size = pre.size();
        int highestBit = 1 << (n - 1);
        // follow the idea that it is reversed
        for (int i = size - 1; i >= 0; i--) {
            pre.add(highestBit + pre.get(i));
        }

        return pre;
    }

    // for fun
    public List<Integer> grayCode(int n) {
        gray("", n);
    }
    // append reverse of order n gray code to prefix string, and print
    public void yarg(String prefix, int n) {
        if (n == 0) {
            System.out.println(prefix);
        } else {
            gray(prefix + "1", n - 1);
            yarg(prefix + "0", n - 1);
        }
    }
    // append order n gray code to end of prefix string, and print
    public void gray(String prefix, int n) {
        if (n == 0) {
            System.out.println(prefix);
        } else {
            gray(prefix + "0", n - 1);
            yarg(prefix + "1", n - 1);
        }
    }
}