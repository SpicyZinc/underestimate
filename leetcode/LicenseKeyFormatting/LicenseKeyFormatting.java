/*
Now you are given a string S, which represents a software license key which we would like to format.
The string S is composed of alphanumerical characters and dashes. The dashes split the alphanumerical characters within the string into groups.
(i.e. if there are M dashes, the string is split into M+1 groups). The dashes in the given string are possibly misplaced.

We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character).
To satisfy this requirement, we will reinsert dashes.
Additionally, all the lower case letters in the string must be converted to upper case.
So, you are given a non-empty string S, representing a license key to format, and an integer K.
And you need to return the license key formatted according to the description above.

Example 1:
Input: S = "2-4A0r7-4k", K = 4
Output: "24A0-R74K"
Explanation: The string S has been split into two parts, each part has 4 characters.

Example 2:
Input: S = "2-4A0r7-4k", K = 3
Output: "24-A0R-74K"
Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.

Note:
The length of string S will not exceed 12,000, and K is a positive integer.
String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
String S is non-empty.

idea:
1. my direct thought, from the end of string, every K characters, then append '-'
note don't forget to remove all '-' in the trailing
2. others' idea, a couple of lines
treat K characters and '-' as a whole, so K + 1 is a unit
note, sb.length(), not S.length()
*/
public class LicenseKeyFormatting {
	public static void main(String[] args) {
		String S = "2-4A0r7-4k";
		int K = 3;
		LicenseKeyFormatting eg = new LicenseKeyFormatting();
		String res = eg.licenseKeyFormatting(S, K);
		
		System.out.println(res);
	}
	// method 1
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = S.length() - 1; i >= 0;) {
        	int k = K;
        	while (k > 0) {
	        	char c = S.charAt(i);
        		if (c != '-') {
	        		sb.append(c);
	        		k--;
        		}
	        	i--;
	        	if (i == -1) {
	        		break;
	        	}
        	}
            
        	sb.append('-');
        	k = K;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);    
        }
        return sb.reverse().toString().toUpperCase();
    }
    // method 2
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
    	for (int i = S.length() - 1; i >= 0; i--) {
    	    char c = S.charAt(i);
    	    if (c != '-') {
    	        sb.append(sb.length() % (K + 1) == K ? "-" : "").append(c);    
    	    }
    	}
    	return sb.reverse().toString().toUpperCase();
    }
    // TLE, but self
    public String licenseKeyFormatting(String S, int K) {
        String[] matches = S.trim().split("-");
        String s = "";
        for (int i = 0; i < matches.length; i++) {
            String match = matches[i];
            s += match;
        }
        
        int len = s.length();
        
        if (len <= K) {
            return s.toUpperCase();
        }
        
        int mod = len % K;
        
        String result = mod == 0 ? "" : s.substring(0, mod) + "-";
        for (int i = mod; i < len; i++) {
            if ((i + 1 - mod) % K == 0) {
                result +=  (i == len - 1) ? s.substring(i + 1 - K , i + 1) : s.substring(i + 1 - K , i + 1) + "-";
            }
        }
        
        return result.toUpperCase();
    }
}