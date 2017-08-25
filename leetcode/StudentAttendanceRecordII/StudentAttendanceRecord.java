/*
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

idea:
1. my idea is memory limit, or TLE
2. possibilities[i] represents the length of i without 'A' string's result
i up to n-1, why, 'A' is also accounted as 1, http://blog.csdn.net/quiteen/article/details/71128911
3. personally, best answer https://andrew-algorithm.blogspot.com/2017/05/leetcode-oj-student-attendance-record-ii.html
	a: 0 'A' and ends with 0 'L'
	b: 0 'A' and ends with 1 'L'
	c: 0 'A' and ends with 2 'L'
	d: 1 'A' and ends with 0 'L'
	e: 1 'A' and ends with 1 'L'
	f: 1 'A' and ends with 2 'L'
*/

import java.util.*;

public class StudentAttendanceRecord {
	public static void main(String[] args) {
		StudentAttendanceRecord eg = new StudentAttendanceRecord();
		// int rewardable = eg.checkRecord(12);
		int rewardable = eg.checkRecord(2);
		System.out.println(rewardable);
	}

	// method 2
	public int checkRecord(int n) {
		int MOD = 1000000007;
		int[] possibilities = new int[n + 1];
		possibilities[0] = 1;
		possibilities[1] = 2;
		possibilities[2] = 4;
		for (int i = 2; i < n; i++) {
			possibilities[i] = (possibilities[i - 2] + possibilities[i - 1] + possibilities[i]) % MOD;
			System.out.println("possibilities[i] == " + i + " " + possibilities[i]);
		}
		int result = possibilities[n];
		for (int i = 0; i < n; i++) {
			result = ( result + possibilities[i] * possibilities[n-i-1] ) % MOD;
		}

		return result;
	}

	// method 3
	public int checkRecord(int n) {
        if (n == 0) {
            return 0;
        }
        
        int a = 1;
        int b = 1;
        int c = 0;
        int d = 1;
        int e = 0;
        int f = 0;
        for (int i = 1; i < n; i++) {
            int newA = sum(a, sum(b, c));
            int newB = a;
            int newC = b;
            int newD = sum(a, sum(b, sum(c, sum(d, sum(e,f)))));
            int newE = d;
            int newF = e;
            
            a = newA;
            b = newB;
            c = newC;
            d = newD;
            e = newE;
            f = newF;
        }
        return sum(a, sum(b, sum(c, sum(d, sum(e,f)))));
    }
    
    private int sum(int x, int y) {
        int MOD = 1000000007;
        return (x + y) % MOD;
    }

    // method 1
    public int checkRecord(int n) {
    	if (n == 0) {
    		return n;
    	}
    	if (n == 1) {
    		return 3;
    	}

        char[] chars = {'A', 'L', 'P'};
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        getRecords(chars, sb, n, result);

        int cnt = 0;
        for (int i = 0; i < result.size(); i++) {
        	String s = result.get(i);
        	if (checkRecord(s)) {
        		cnt++;
        	}
        }
        return cnt % 1000000007;
    }

    public void getRecords(char[] chars, StringBuilder sb, int n, List<String> result) {
    	if (sb.length() == n) {
    		result.add(sb.toString());
    		return;
    	}
    	for (int i = 0; i < chars.length; i++) {
    		sb.append(chars[i]);
    		getRecords(chars, sb, n, result);
    		sb.deleteCharAt(sb.length() - 1);
    	}
    }

    public boolean checkRecord(String s) {
        int[] chars = new int[256];
        chars[s.charAt(0) - '0']++;
        for (int i = 1; i < s.length(); i++) {
        	char prev = s.charAt(i - 1);
        	char curr = s.charAt(i);
        	chars[curr - '0']++;
        	if (prev == 'L' && i < s.length() - 1 && prev == curr && curr == s.charAt(i + 1)) {
        		return false;
        	}
        }
        return chars['A' - '0'] >= 2 ? false : true;
    }
}