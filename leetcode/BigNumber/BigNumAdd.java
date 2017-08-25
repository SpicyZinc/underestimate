/**
Big Number Add
Big Number is not represented in list, in String or char[]

idea:


*/

public class BigNumAdd {
	// constructor
	public BigNumAdd() {
		String m = "990";
		String n = "9990";
		add(m, n);
	}
	
	public static void main(String[] args) {
		new BigNumAdd();
	}

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
		
		StringBuffer sum = new StringBuffer();
		// in order to fina consecutive "0"
		boolean flag = true;
		for(int index=length; index>=0; index--) {
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