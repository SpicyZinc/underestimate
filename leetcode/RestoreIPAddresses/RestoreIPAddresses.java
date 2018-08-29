/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

idea:
1. dfs
2. iterative, brute force, find all possibilities, get rid of invalid ip address
helper method isValidIp() to help judge if it is a valid ip
*/

import java.util.*;

public class RestoreIPAddresses {
    // dfs
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();

        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        dfs(s, 0, "", result);
        return result;
    }

    public void dfs(String s, int count, String path, List<String> result) {
        if (count == 3 && isValidIP(s)) {
            result.add(path + s);

            return;
        }

        for (int i = 1; i < 4 && i < s.length(); i++) {
            // 1 of 4 parts in xxx.xxx.xxx.xxx
            String part = s.substring(0, i);
            if (isValidIP(part)) {
                dfs(s.substring(i), count + 1, path + part + ".", result);
            }
        }
    }

    private boolean isValidIP(String s) {
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }

        int num = Integer.parseInt(s);
        return num > 0 && num <= 255;
    }

    // iteration
    public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<String>();        
        if (s.length() > 12 || s.length() < 4) {
            return result;
        }

        int n = s.length(); 
        int i = 0;
        for (int j = i+1; j < n; j++) {
            for (int k = j+1; k < n; k++) {
                for (int l = k+1; l < n; l++) {
                    String a = s.substring(i, j), b = s.substring(j, k), c = s.substring(k, l), d = s.substring(l);
                    if (isValidIp(a, b, c, d)) {
                        result.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }
 
        return result;
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