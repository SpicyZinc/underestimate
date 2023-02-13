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

public class AddBinary {
	public static void main(String[] args) {
		AddBinary eg = new AddBinary();
		String result = eg.addBinary("100", "1100");
		System.out.println(result);
	}

	// Wed Jun 19 01:24:36 2019
	public String addBinary(String a, String b) {
        int sizeA = a.length();
        int sizeB = b.length();
        
        int i = sizeA - 1;
        int j = sizeB - 1;
        
        int carry = 0;
        int val = carry;
        
        StringBuilder sb = new StringBuilder();
        
        while (i >= 0 && j >= 0) {
            char cA = a.charAt(i--);
            char cB = b.charAt(j--);
            
            val = carry + (cA - '0' + cB - '0');
            sb.append(val % 2);
            carry = val / 2;
        }
        
        while (i >= 0) {
            char cA = a.charAt(i--);
            
            val = carry + (cA - '0');
            sb.append(val % 2);
            carry = val / 2;
        }
        
        while (j >= 0) {
            char cB = b.charAt(j--);
            
            val = carry + (cB - '0');
            sb.append(val % 2);
            carry = val / 2;
        }
        
        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

	public String addBinary(String a, String b) {
		char[] aa = new StringBuilder(a).reverse().toString().toCharArray();
		char[] bb = new StringBuilder(b).reverse().toString().toCharArray();
		
		int A = a.length();
		int B = b.length();
		
		// bigger length + 1
		int length = A >= B ? A : B;
		int[] result = new int[length + 1];
		
		for (int i = 0; i < length + 1; i++) {
			int currentA = i >= A ? 0 : (aa[i] - '0');
			int currentB = i >= B ? 0 : (bb[i] - '0');

			result[i] = currentA + currentB;
		}

		// recalculate
		for (int i = 0; i < length; i++) {
			if (result[i] >= 2) {
				result[i + 1] += result[i] / 2;
				result[i] %= 2;
			}
		}

		// omit first "0" if there is
		// only starting from "1"
		StringBuilder sum = new StringBuilder();
		for (int i = length; i >= 0; i--) {
			if (i == length && result[i] == 0) {
				continue;
			}

			sum.append(result[i]);
		}

		return sum.toString();
	}
}