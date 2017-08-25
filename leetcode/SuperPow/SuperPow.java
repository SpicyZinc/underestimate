/*

Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024


idea:
implement self pow, otherwise overflow
in self pow, just handle the two basic cases, and recursion integer and reminder parts
since b[] is array of integer representation, for loop, 10 involved

a strange solution
http://m.2cto.com/px/201607/526095.html
*/

public class SuperPow {
    public int superPow(int a, int[] b) {
        int res = 1;
        for (int i = 0; i < b.length; i++) {
        	res = pow(res, 10) * pow(a, b[i]) % 1337;
        }

        return res;
    }

    public int pow(int x, int n) {
    	if (n == 0) {
    		return 1;
    	}
    	if (n == 1) {
    		return x % 1337;
    	}

    	return pow(x % 1337, n / 2) * pow(x % 1337, n - n / 2) % 1337;
    }
}