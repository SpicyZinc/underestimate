/*
idea:

first add 1 at the end of array, which is small significant bit
sequentially
carry to bigger significant bit
if(carry > 0)
create a new array of length of old length+1
newDigits[0] = 1
*/

class PlusOne {
	
	public static void main(String[] args) {
		int[] a = {9,9,9,9,9,9};
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
		a = plusOne_rewrite(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}
	
	public static int[] plusOne(int[] digits) {
		if (digits.length == 0) {
            return digits;
        }
 
        int n = digits.length, carry = 1;
		
        for (int i=n-1; i>=0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
 
        if (carry == 0) {
            return digits;
        }
        else {
            int[] newReturn = new int[n+1];
            newReturn[0] = 1;
            for (int i=1; i<n+1; i++) {
                newReturn[i] = digits[i-1];
            }
            return newReturn;
        }
	}
	
	public static int[] plusOne_rewrite(int[] digits) {
		int carry = 0;
		int i = digits.length - 1;
		
		digits[i] += 1;
		while (digits[i] >= 10) {
			digits[i--] %= 10;
			if (i >= 0) {
				digits[i] += 1;
			}
			else {
				carry = 1;
				break;
			}
		}
		if(carry == 0) return digits;
		int[] newDigits = new int[digits.length + 1];
		for (int k=1; k<newDigits.length; k++) {
			newDigits[k] = digits[k-1];
		}
		newDigits[0] = carry;	
		return newDigits;
	}


	public int[] plusOneSelf(int[] digits) {
        int carry = 0;
        int tmp = 0;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i != digits.length - 1) {
                tmp = digits[i] + carry;
            }
            else {
                tmp = digits[i] + 1 + carry;
            }
            
            digits[i] = tmp % 10;
            carry = tmp / 10;
        }
        
        if (carry > 0) {
            int[] ret = new int[digits.length + 1];
            for (int j = 0; j < digits.length; j++) {
                ret[j+1] = digits[j];
            }
            ret[0] = 1;
            
            return ret;
        }
        else {
            return digits;
        }
    }
}