/**
Big Number Multiply
*/

public class BigNumMultiply {
	// constructor
	public BigNumMultiply() {
		String m = "-990";
		String n = "9990";
		multiply(m, n);
		// bigNumberSimpleMulti(m, n);
	}
	
	public static void main(String[] args) {
		new BigNumMultiply();
	}

	public void multiply(String aa, String bb) {
		// first check sign at 0 position
		char signA = aa.charAt(0);
		char signB = bb.charAt(0);
		char signP = '+'; // positive sign
		char signN = '-'; // negative sign
		char sign = '+'; // result sign
		// number a
		if(signA == signP || signA == signN) {
			sign = signA;
			aa = aa.substring(1); // the rest are the number
		}
		// number b
		if(signB == signP || signB == signN) {
			// same sign
			if(sign == signB) {
				sign = signP;
			}
			else {
				sign = signN;
			}
			bb = bb.substring(1);
		}
		
		// reverse the char arry to favor the computation
		// class is StringBuffer, change to String first
		// use String class method toCharArray()
		char[] a = new StringBuffer(aa).reverse().toString().toCharArray();
		char[] b = new StringBuffer(bb).reverse().toString().toCharArray();
		
		// start computing
		int[] res = new int[a.length + b.length];
		// i and j put i+j position
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<b.length; j++) {
				res[i+j] += (int)(a[i] - '0') * (int)(b[j] - '0');
			}
		}
		// recalculate the result
		for(int i=0; i<res.length; i++) {
			if(res[i] > 10) {
				// these two clauses order matters
				res[i+1] += res[i] / 10;
				res[i] %= 10;
			}			
		}
		// check if pre '0', get rid of zero
		// also the zeros must be consecutive
		boolean flag = true;
		StringBuffer multiply = new StringBuffer();
		for(int i=(a.length + b.length - 1); i>=0; i--) {
			if(res[i] == 0 && flag) {
				continue; // not included into the result multiply
			}
			// once there is position in between not zero, flag turns to false
			// thus all the rest will be included to result multiply
			else {
				flag = false;
				// multiply.append(res[i]);
			}
			multiply.append(res[i]);
			// System.out.print(res[i] + " ");
		}
		// last to judge if null or not 
		if(!multiply.toString().equals("")) {
			// if negative
			if(sign == '-') {
				multiply.insert(0, sign);
			}
		}
		// null String means 0 is result
		else {
			multiply.append(0);
		}
		
		System.out.println("The multiply result is " + multiply.toString());
	}
}