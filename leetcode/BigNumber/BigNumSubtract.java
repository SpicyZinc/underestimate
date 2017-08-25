/**
Big Number Subtract
*/

public class BigNumSubtract {
	// constructor
	public BigNumSubtract() {
		String m = "990";
		String n = "9990";
		char m0 = m.charAt(0);
		char n0 = n.charAt(0);
		if((m0=='+' || m0!='-') && (n0=='+' || n0!='-')) {
			subtract(m, n);
		}
		else if((m0=='+' || m0!='-') && (n0=='-')) {
			add(m, n.substring(1));
		}
		// 
		else if((m0=='-') && (n0=='+' || n0!='-')) {
			add(m.substring(1), n);
			System.out.println("not sum, is the diff with minus sign");
		}
		else {
			subtract(n.substring(1), m.substring(1));
		}
	}
	
	public static void main(String[] args) {
		new BigNumSubtract();
	}
	// first - second
	public void subtract(String aa, String bb) {
		// to char array and reverse the order to facilitate computing
		char[] a = new StringBuffer(aa).reverse().toString().toCharArray();
		char[] b = new StringBuffer(bb).reverse().toString().toCharArray();
		
		int lengthA = a.length;
		int lengthB = b.length;
		// judge sign
		char sign = '+';
		if(lengthA > lengthB) {
			sign = '+';
		}
		else if(lengthA == lengthB) {
			int i = lengthA-1;
			while(i>=0 && a[i]==b[i]) {
				i--;
			}
			if(a[i] < b[i]) {
				sign = '-';
			}
		}
		else {
			sign = '-';
		}
		
		// result length
		int length = (lengthA > lengthB ? lengthA : lengthB); 
		int[] res = new int[length];
				
		for(int i=0; i<length; i++) {
			int currentA = (i >= lengthA ? 0 : (a[i] - '0'));
			int currentB = (i >= lengthB ? 0 : (b[i] - '0'));
			if(sign == '+') {
				res[i] = currentA - currentB;
			}
			else {
				res[i] = currentB - currentA;
			}			
		}
		// recalculate res[]
		for(int i=0; i<length-1; i++) {
			if(res[i] < 0) {
				res[i+1] -= 1;
				res[i] = 10 + res[i];
			}			
		}
		//
		StringBuffer diff = new StringBuffer();
		boolean flag = true;
		for(int index=length-1; index>=0; index--) {
			if(res[index] == 0 && flag) {
				continue;
			}
			else {
				flag = false;
				diff.append(res[index]);
			}			
		}
		// add sign to the result
		if(diff.equals("")) {
			diff.append(0);
		}
		else {
			if(sign == '-') {
				diff.insert(0, sign);
			}
		}
		System.out.println("The diff is " + diff.toString());
	}
	
	// add
	public void add(String aa, String bb) {
		// to char array and reverse the order to facilitate computing
		char[] a = new StringBuffer(aa).reverse().toString().toCharArray();
		char[] b = new StringBuffer(bb).reverse().toString().toCharArray();
		
		int lengthA = a.length;
		int lengthB = b.length;
		// result length
		int length = (lengthA > lengthB ? lengthA : lengthB); 
		int[] res = new int[length + 1];
		// index wise to add 
		for(int i=0; i<length + 1; i++) {
			int currentA = (i >= lengthA ? 0 : (a[i] - '0'));
			int currentB = (i >= lengthB ? 0 : (b[i] - '0'));
			res[i] = currentA + currentB;
		}
		// recalculate 
		// length VS length+1
		for(int i=0; i<length; i++) {
			if(res[i] > 10) {
				res[i+1] += res[i] / 10;
				res[i] %= 10;
			}			
		}
		//
		StringBuffer sum = new StringBuffer();
		boolean flag = true;
		for(int index=length-1; index>=0; index--) {
			if(res[index] == 0 && flag) {
				continue;
			}
			else {
				flag = false;
				sum.append(res[index]);
			}			
		}
		System.out.println("The sum is " + sum.toString());
	}
}