/*
Given two binary strings, return their sum (also a binary string).

a = "11"
b = "1"
Return "100". 

idea:

borrow the general case of Big Number addition

1. reverse both strings and convert to char array
2. length is the longer array's length + 1
3. start from index 0, add
   attention ret[i] i>Alength or i>Blength ret[i]=0
4. recalculate for carry
5. start from last to append to the final result

BTW, '0' == 48
*/

public class AddBinarySolution {
	public static void main(String[] args) {
		AddBinarySolution test = new AddBinarySolution();
		test.addBinary("100", "1100");
	}

    public String addBinary(String a, String b) {
		char[] aa = new StringBuilder(a).reverse().toString().toCharArray();
		char[] bb = new StringBuilder(b).reverse().toString().toCharArray();
		
		int A = a.length();
		int B = b.length();
		
		// bigger length + 1
		int length = (A >= B ? A : B);
		int[] ret = new int[length + 1];
		
		for (int i=0; i<length+1; i++) {
			int currentA = (i > (A-1) ? 0 : aa[i] - '0');
			int currentB = (i > (B-1) ? 0 : bb[i] - '0');
			ret[i] = currentA + currentB;
		}
		// recalculate
		for (int i=0; i<length; i++) {
			if (ret[i] >= 2) {
				ret[i+1] += ret[i] / 2;
				ret[i] %= 2;
			}
		}
		// omit first "0" if there is
		// only starting from "1"
		StringBuilder sum = new StringBuilder();
		for (int i=length; i>=0; i--) {
			if (i == length && ret[i] == 0) {
				continue;
			}
			sum.append(ret[i]);
		}

        return sum.toString();
	}

	public String addBinary(String a, String b) {
		char[] aa = new StringBuilder(a).reverse().toString().toCharArray();
		char[] bb = new StringBuilder(b).reverse().toString().toCharArray();
		
		int la = a.length();
		int lb = b.length();
		
		int length = la >= lb ? (la) : (lb);
		int[] res = new int[length + 1];
		
		for (int i = 0; i < length+1; i++) {
		    int tempA = (i > (la-1) ? 0 : aa[i] - '0');
		    int tempB = (i > (lb-1) ? 0 : bb[i] - '0');
		    res[i] = tempA + tempB;
		}
		
		
		for (int i = 0; i < length; i++) {
		    if (res[i] >= 2) {
		        res[i+1] += res[i] / 2;
		        res[i] %= 2;
		    }
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = length; i >= 0; i--) {
		    if (i == length && res[i] == 0) {
		        continue;
		    }
		    sb.append(res[i]);
		}
		
		return sb.toString();
	}
}