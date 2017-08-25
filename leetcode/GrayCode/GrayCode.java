/*
Deci  Gray   Binary    Deci  Gray         Deci  Gray                          	Gray
 0    000    000        0    00            3    10                             	10 + 100 = 110  
 1    001    001        1    01   --->     2    11  ---> + 1<<(3-1) == 100     	11 + 100 = 111
 2    011    010        2    11            1    01                        	   	01 + 100 = 101 
 3    010    011        3    10            0    00                        		00 + 100 = 100 
 ----------------------
 4    110    100
 5    111    101
 6    101    110
 7    100    111

idea:
see the transformation as above
mirror + prefix
grayCode(n) = [reverse grayCode(n-1) + (1 << n-1)] + grayCode(n-1)
*/

import java.util.*;

public class GrayCode {
	public static void main(String[] args) {
		new GrayCode();
	}
	public GrayCode() {		
		// ArrayList<Integer> ret = grayCode(3);
		ArrayList<Integer> ret = grayCode_neat(3);
		for (int i=0; i<ret.size(); i++) {
			System.out.print(ret.get(i) + "  ");
		}
	}

	// even simpler version
	// use this one
	public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (n < 0) {
            return ret;
        }
        if (n == 0) {
            ret.add(0);
            return ret;
        }
        if (n == 1) {
            ret.add(0);
            ret.add(1);
            return ret;
        }
 
        ArrayList<Integer> r0 = grayCode(n - 1);
        ArrayList<Integer> r1 = reverse(r0);
        int x = 1 << (n-1);
        for (int i = 0; i < r1.size(); i++) {
            r1.set( i, r1.get(i) + x );
        }
 
        r0.addAll(r1);
        return r0; 
    }
    
    private ArrayList<Integer> reverse(ArrayList<Integer> r) {
        ArrayList<Integer> rev = new ArrayList<Integer>(); 
        for(int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
	// easy-to-understand version
    public ArrayList<Integer> grayCode(int n) {
		if (n < 0) {
            return new ArrayList<Integer>();
        }
        if (n == 0) {
            ArrayList<Integer> ret = new ArrayList<Integer>();
            ret.add(0);
            return ret;
        }
        if (n == 1) {
            ArrayList<Integer> ret = new ArrayList<Integer>();
            ret.add(0);
            ret.add(1);
            return ret;
        }
 
        ArrayList<Integer> r0 = grayCode(n - 1);
        ArrayList<Integer> r1 = reverse(r0);
        int x = 1 << (n-1);
        for (int i = 0; i < r1.size(); i++) {
            r1.set( i, r1.get(i) + x );
        }
 
        r0.addAll(r1);
        return r0; 
    }
 
    public ArrayList<Integer> reverse(ArrayList<Integer> r) {
        ArrayList<Integer> rev = new ArrayList<Integer>(); 
        for(int i = r.size() - 1; i >= 0; i--) {
            rev.add(r.get(i));
        }
        return rev;
    }
	// neat version 
	public ArrayList<Integer> grayCode_neat(int n) {  
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(0);  
		/* 
		// both work
		for(int i=1; i<=n; i++) {  
			int highestBit = 1<<(i-1);
		*/
		for (int i = 0; i < n; i++) {  
			int highestBit = 1<<i;  
			int len = result.size();  
			for (int j = len-1; j >= 0; j--) {
				// arraylist result keep growing
				// append on previous base
				// append mirror part
				result.add(highestBit + result.get(j));  
			}  
		}  
		return result;  
	}

	// http://blog.csdn.net/u010500263/article/details/18209669
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (n == 0) {
            res.add(0);
            return res;
        }
        
        ArrayList<Integer> preRes = grayCode(n-1);
        res.addAll(preRes);
        for (int i = preRes.size()-1; i >= 0; i--) {
            res.add( preRes.get(i) + (int)Math.pow(2, n-1) );
        }
        return res;
    }
}