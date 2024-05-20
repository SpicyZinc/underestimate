/*
Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
and all the 0's and all the 1's in these substrings are grouped consecutively.
Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
Note:

s.length will be between 1 and 50,000.
s will only consist of "0" or "1" characters.

idea: prev, curr = 1
if not continuous same elements, updating prev = curr, curr = 1;
curr >= prev, this is a qualifying substring
*/

class CountBinarySubstrings {
	// TLE
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j <= n; j++) {
        		String substr = s.substring(i, j);
        		if ((j - i) % 2 == 0) {
        			if (consecutiveSame(substr)) {
        				cnt++;
        			}
        		}
        	}
        }

        return cnt;
    }

    public boolean consecutiveSame(String s) {
    	String left = s.substring(0, s.length() / 2);
    	String right = s.substring(s.length() / 2, s.length());

    	if (sum(left) == 0 && sum(right) == 1 * right.length()) {
    		return true;
    	}
    	if (sum(right) == 0 && sum(left) == 1 * left.length()) {
    		return true;
    	}

    	return false;
    }

    public int sum(String s) {
    	int result = 0;
    	for (int i = 0; i < s.length(); i++) {
    		result += (s.charAt(i) - '0');
    	}

    	return result;
    }

    // it should be simple this way
    public int countBinarySubstrings(String s) {
    	int prevRunLen = 0;
    	int currRunLen = 1;
    	int cnt = 0;

    	for (int i = 1; i < s.length(); i++) {
    		if (s.charAt(i) == s.charAt(i - 1)) {
    			currRunLen += 1;
    		} else {
    			prevRunLen = currRunLen;
    			currRunLen = 1;
    		}
    		// one qualifying substring
    		if (prevRunLen >= currRunLen) {
    			cnt++;
    		}
    	}

    	return cnt;
    }
}