/*
Restore IP Addresses
Given a string containing only digits,
restore it by returning all possible valid IP address combinations.

Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 

idea:
iterative, brute force, find all possibilities, get rid of invalid ip address
helper method isValidIp() to help judge if it is a valid ip
==
recursion, do not understand

*/

import java.util.*;

public class RestoreIPAddresses {
    public ArrayList<String> restoreIpAddresses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
		ArrayList<String> res = new ArrayList<String>();        
        if (s.length() > 12 || s.length() < 4) {
            return res;
        } 
        int n = s.length();
 
        int i = 0;
        for (int j = i+1; j < n; j++) {
            for (int k = j+1; k < n; k++) {
                for (int l = k+1; l < n; l++) {
                    String a = s.substring(i, j), b = s.substring(j, k), c = s.substring(k, l), d = s.substring(l);
                    if (isValidIp(a, b, c, d)) {
                        res.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }
 
        return res;
    }
 
    public boolean isValidIp(String a, String b, String c, String d) {
        boolean lenVal = a.length() < 5 && b.length() < 5 && c.length() < 5 && d.length() < 5;
        if (!lenVal) {
            return false;
        }
 
        boolean zVal = (a.length() == 1 || a.charAt(0) != '0') && 
						(b.length() == 1 || b.charAt(0) != '0') &&
						(c.length() == 1 || c.charAt(0) != '0') && 
						(d.length() == 1 || d.charAt(0) != '0');
 
        if (!zVal) {
            return false;
        }   
 
        int aV = Integer.parseInt(a);
		int bV = Integer.parseInt(b);
		int cV = Integer.parseInt(c);
		int dV = Integer.parseInt(d);
 
		boolean aVBool = aV <= 255 ? true : false;
		boolean bVBool = bV <= 255 ? true : false;
		boolean cVBool = cV <= 255 ? true : false;
		boolean dVBool = dV <= 255 ? true : false;
		boolean vVal = aVBool && bVBool && cVBool && dVBool;
		
        // boolean vVal = (aV >> 8) == 0 && (bV >> 8) == 0 && (cV >> 8) == 0 && (dV >> 8) == 0;
 
        return vVal;
    }
}
// this recursion is too hard, not understand
class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function		
		ArrayList<String> res = restoreIPAddresses(s, 4);
        if(res == null) 
			res = new ArrayList<String>();
        return res;
    }
    public ArrayList<String> restoreIPAddresses(String s, int k){
        assert(k <= 4 && k >= 1);
        if(s == null || s.length() < k || s.length() > 3 * k) 
			return null;
		
        ArrayList<String> res = new ArrayList<String>();        
        
        for(int i=0; i<Math.min(s.length(), 3); i++) {
            String num = s.substring(0, i+1);
            if((i == 0 || num.charAt(0) > '0') && Integer.parseInt(num) <= 255) {
                if (k == 1) {
                    if(i == s.length() - 1) 
                        res.add(num);
                }
				else {
                    ArrayList<String> remain = restoreIPAddresses(s.substring(i+1), k-1);
                    if (remain != null) {
                        for (String r : remain) {
                            String temp = num + '.' + r;
                            res.add(temp);
                        }
                    }
                }
            }
			else
                break;
        }
        return res;
    }
}

// recursion
class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {		
		ArrayList<String> res = new ArrayList<String>();
		String tmp = new String();
		ip (s, tmp, res, 0, 0); 
		if (res == null) 
			return new ArrayList<String>();
		return res;
	}
		
	private void ip(String s, String tmp, ArrayList<String> res, int startPos, int partition) {
        if (startPos == s.length() && partition == 4) {  
            res.add(tmp);
			return;
        }
		
        int x = 0;  
        for (int i = startPos; i < startPos+4 && i < s.length(); i++) {  
            x = x * 10 + s.charAt(i) - '0';  
            if (x > 255 || x < 0)
                break;  
              
            String str = tmp;  
            if (partition > 0)  
                // str.append(1, '.');
				str += '.';  
              
            String st = s.substring(startPos, i-startPos+1);
			// judge if it is a valid ip address		
            if (st.length() > 1 && st.charAt(0) == '0')  
                break;  
                  
            str += st;  
            ip (s, str, res, i+1, partition+1);
		}  
    }
}