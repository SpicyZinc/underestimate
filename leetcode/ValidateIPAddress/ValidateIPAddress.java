/*
In this problem, your job to write a function to check whether a input string is a valid IPv4 address or IPv6 address or neither.
IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
Besides, you need to keep in mind that leading zeros in the IPv4 is illegal. For example, the address 172.16.254.01 is illegal.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one.
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.
Note: You could assume there is no extra space in the test cases and there may some special characters in the input string.

Example 1:
Input: "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:
Input: "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.

idea:
1. direct parse
2. regex
*/

public class ValidateIPAddress {
	public static void main(String[] args) {
		char a = 48; // 0
		char b = 57; // 9
		char c = 65; // A
		char d = 70; // F
		char e = 97; // a
		char f = 102; // f

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
	}

	public String validIPAddress(String IP) {
    	if (isValidIPv4(IP)) {
    		return "IPv4";
    	}
    	else if (isValidIPv6(IP)) {
    		return "IPv6";
    	}
    	else {
        	return "Neither";
    	}
    }
    // final working version with regex in Java
    public String validIPAddress(String IP) {
		Pattern ip4 = Pattern.compile("^([1-9]\\d{0,2}|0)(?:\\.([1-9]\\d{0,2}|0)){3}$", Pattern.CASE_INSENSITIVE);
		Pattern ip6 = Pattern.compile("^([0-9a-fA-F]{1,4})(:[0-9a-fA-F]{1,4}){7}$", Pattern.CASE_INSENSITIVE);

        Matcher ip4Matcher = ip4.matcher(IP);
        Matcher ip6Matcher = ip6.matcher(IP);

		if ( ip4Matcher.find() && Integer.parseInt(ip4Matcher.group(1)) < 256 && Integer.parseInt(ip4Matcher.group(2)) < 256 ) {
			return "IPv4";
		}
		else if (ip6Matcher.find()) {
			return "IPv6";
		}
		else {
			return "Neither";
		}
    }

    private boolean isValidIPv4(String ip) {
        // 7 == 1.1.1.1
    	if (ip.length() < 7 || ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
    		return false;
    	}
    	String[] matches = ip.split("\\.");
    	if (matches.length != 4) {
    		return false;
    	}
    	for (String match : matches) {
    		if (!isValidIPv4Token(match)) {
    			return false;
    		}
    	}

    	return true;
    }

    private boolean isValidIPv4Token(String token) {
    	if (token.startsWith("0") && token.length() > 1) {
    		return false;
    	}
    	try {
    		int intValue = Integer.parseInt(token);
	     	if (intValue < 0 || intValue > 255) {
	     		return false;
	     	}
	     	if (intValue == 0 && token.charAt(0) != '0') {
	     		return false;
	     	}
    	}
    	catch (NumberFormatException nfe) {
    		return false;
    	}
    	return true;
    }

    private boolean isValidIPv6(String ip) {
    	// 15 == 0:0:0:0:0:0:0:0
    	if (ip.length() < 15 || ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') {
    		return false;
    	}
    	String[] matches = ip.split(":");
    	if (matches.length != 8) {
    		return false;
    	}
    	for (String match : matches) {
    		if (!isValidIPv6Token(match)) {
    			return false;
    		}
    	}

    	return true;
    }

    private boolean isValidIPv6Token(String token) {
    	if (token.length() > 4 || token.length() == 0) {
    		return false;
    	}
    	for (int i = 0; i < token.length(); i++) {
    		char c = token.charAt(i);
    		boolean isDigit = c >= 48 && c <= 57;
    		boolean isUppercaseAF = c >= 65 && c <= 70;
    		boolean isLowercaseAF = c >= 97 && c <= 102;
    		if ( !(isDigit || isUppercaseAF || isLowercaseAF) ) {
    			return false;
    		}
    	}

    	return true;
    }
}
