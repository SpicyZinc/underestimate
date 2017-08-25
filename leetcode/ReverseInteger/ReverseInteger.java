/**
 * Reverse digits of an integer
 * 
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321 
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
 * then the reverse of 1000000003 overflows. How should you handle such cases?
 * 
 * 
 * idea:
 * % and / are used to get each digit from bit to thousand, high bit
 * then save them into list
 * now list from the beginning has already reversed integer 
 * walk through the list, use multiply times of ten according to how many zeros
 * 
 * */
import java.util.*;

public class ReverseInteger {
	public static void main(String[] args) {
		ReverseInteger aTest = new ReverseInteger(); 
	}
	public ReverseInteger() {
		int x = reverse(123);
		int y = reverse(-123);
		System.out.println(x);
		System.out.println(y);
	}
    public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	boolean negative = false;
    	if(x < 0) {
    		x = (-1) * x;
    		negative = true;
    	}
    		
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	int res = 0;
    	while(x > 0) {
    		list.add(x % 10);
    		x /= 10;    		
    	}
    	int s = list.size();
        for(int i=0; i<s; i++) {
        	res += list.get(i) * Math.pow(10, s-i-1);
        	// System.out.print(list.get(i) + " ");
        }
        
        if(negative)
        	return res * (-1);
        else 
        	return res;
    }    
}