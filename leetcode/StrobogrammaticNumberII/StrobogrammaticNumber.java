/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example, Given n = 2, return ["11","69","88","96"].

idea:
https://segmentfault.com/a/1190000003787462

insert from the middle
typical recursion in for loop
convert string to stringbuilder, pass it to append(), then toString() to recursively call build
*/
import java.util.*;

public class StrobogrammaticNumber {
	public static void main(String[] args) {
		StrobogrammaticNumber eg = new StrobogrammaticNumber();
		List<String> res = eg.findStrobogrammatic(3);
		// List<String> res = eg.findStrobogrammatic(2);
		// List<String> res = eg.findStrobogrammatic(1);
		for (String s : res) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

    char[] table = {'0', '1', '8', '6', '9'};

    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<String>();
        build(n, "", res);
        return res;
    }

    public void build(int n, String sNumber, List<String> res) {
    	if (n == sNumber.length()) {
    		res.add(sNumber);
    		return;
    	}
    	boolean isLast = n - sNumber.length() == 1;
    	for (int i = 0; i < table.length; i++) {
    		char c = table[i];
    		// two cases no need to append c
    		// first character cannot be '0', how to detect? sNumber.length() == 0 && c == '0', n == 1 no need to satisfy these
            // last character cannot be 6 or 9, because no matching part
    		if ( (sNumber.length() == 0 && c == '0' && n != 1) || isLast && (c == '6' || c == '9') ) {
    			continue;
    		}
    		StringBuilder sb = new StringBuilder(sNumber);
    		append(isLast, c, sb);
    		build(n, sb.toString(), res);
    	}
    }
    
    public void append(boolean isLast, char c, StringBuilder sb) {
        if (c == '6') {
            sb.insert(sb.length()/2, "69");
        } else if (c == '9') {
            sb.insert(sb.length()/2, "96");
        } else {
            sb.insert(sb.length()/2, isLast ? c : "" + c + c);
        }
    }
}