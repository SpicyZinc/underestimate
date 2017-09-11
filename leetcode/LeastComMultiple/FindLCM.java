/*
find the least common multiple

idea:
x divides y == y is divisible by x

find bigger one of the two number
a * b will be common multiple for sure
but starting from 1 to <= smaller one of the two number
first multiple of bigger one also divisible by smaller one will be the result
*/

public class FindLCM {
    public static int determineLCM(int a, int b) {
        int bigger, smaller;
        if (a > b) {
            bigger = a;
            smaller = b;
        } else {
            bigger = b;
            smaller = a;
        }
        for (int i = 1; i <= smaller; i++) {
			// the first multiple of the larger number of the two 
			// which can also be divisible by the smaller number	
			if ((bigger * i) % smaller == 0) { 
				return bigger * i;
			}
        }
        return a * b;
    }
}