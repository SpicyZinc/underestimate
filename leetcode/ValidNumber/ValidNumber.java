/*
Validate if a given string is numeric.
Some examples:
"0" 	=> true
" 0.1 " => true
"abc" 	=> false
"1 a"	=> false
"2e10" 	=> true

idea:

1. regular expression
http://blog.csdn.net/fightforyourdream/article/details/12900751
"[+-]?(\\d+\\.? | \\.?\\d+)\\d*(e[+-]?\\d+)?"

2. normal thought to consider every corner case
	only need 3 boolean variables
	boolean hasE = false;
	boolean hasDot = false;
	boolean hasNumber = false;
	return hasNumber

	0. s = s.trim()
	1. e cannot be the first character
	2. e cannot be replicated nor placed before number    hasE
	3. '.' can neither be replicated nor placed after 'e' hasDot
	4. check whether numbers are included                 hasNumber
	5. the sign can only be placed at the beginning or after 'e'
	6. no other characters except '+', '-', '.', and 'e'
	7. '+', '-', and 'e' cannot be the last character

3. modularized  functionalities
https://github.com/rffffffff007/leetcode/blob/master/Valid%20Number.java

<NUMBER> ::= <DECIMAL> ['e' <INTEGER>]
<DECIMAL> ::= <SIGN> <DECIMAL_POSITIVE>
<DECIMAL_POSITIVE> ::= INTEGER_POSITIVE ['.' (<INTEGER_POSITIVE> | ¦Å)]  |  '.' <INTEGER_POSITIVE>
<INTEGER> ::= <SIGN> <INTEGER_POSITIVE>
<INTEGER_POSITIVE> ::= [0-9]+
<SIGN> ::= '+' | '-' | ¦Å

4. http://rleetcode.blogspot.com/2014/01/valid-number-java.html
*/
public class ValidNumber {
	public static void main(String[] args) {
		ValidNumber eg = new ValidNumber();
		System.out.println("0 => " + eg.isNumber("0"));
		System.out.println("0.1 => " + eg.isNumber(" 0.1 "));
		System.out.println("abc => " + eg.isNumber("abc"));
		System.out.println("2e10 => " + eg.isNumber("2e10"));
		System.out.println("1 a => " + eg.isNumber("1 a"));
		System.out.printf("-.80 => %20b\n", eg.isNumber("-.80"));    

		System.out.println("Is abc via isNumeric  " + eg.isNumber("abc"));
		System.out.println("Is 2e10 via isNumeric  " + eg.isNumber("2e10"));
		System.out.println("Is 0.1 via isNumeric  " + eg.isNumber("0.1"));    
	}

	// Sun Jul  7 20:28:04 2019
	public boolean isNumber(String s) {
		s = s.trim();
		
		if (s.length() == 0 || s == null) {
			return false;
		}

		boolean hasDot = false;
		boolean hasE = false;
		boolean hasNum = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '.') {
				if (hasDot || hasE) {
					return false;
				} else {
					hasDot = true;
				}
			}
			
			if (c == 'e') {
				if (hasE || !hasNum) {
					return false;
				} else {
					hasE = true;
				}
			}


			if (Character.isDigit(c)) {
				hasNum = true;
			}

			// 如果 正负号 在 e 的前面 必定不是 valid
			if (i > 0 && (c == '+' || c == '-') && s.charAt(i - 1) != 'e') {
				return false;
			}
			// any invalid character other than digit, 'e', '.' , '+', '-'
			if (!Character.isDigit(c) && c != 'e' && c != '.' && c != '+' && c != '-') {
				return false;
			}

			if (i == s.length() - 1) {
				if (c == 'e' || c == '+' || c == '-') {
					return false;
				}
			}
		}

		return hasNum;
	}

	// Sun Jun 30 20:49:16 2019
	public boolean isNumber(String s) {
		s = s.trim();
		
		if (s.length() == 0 || s == null) {
			return false;
		}

		boolean hasNum = false;
		boolean hasE = false;
		boolean hasDot = false;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (c == 'e') {
				if (hasE || !hasNum) {
					return false;
				} else {
					hasE = true;
				}
			}
			
			if (c == '.') {
				if (hasDot || hasE) {
					return false;
				} else {
					hasDot = true;
				}
			}
			
			if (Character.isDigit(c)) {
				hasNum = true;
			}
			// in middle of the string, i > 0
			// current char is sign, c == '+' || c == '-'
			// previous char is not e, s.charAt(i - 1) != 'e'
			if (i > 0 && (c == '+' || c == '-') && s.charAt(i - 1) != 'e') {
				return false;
			}
			
			// other illegal char will return false
			if (!Character.isDigit(c) && c != 'e' && c != '+' && c != '-' && c != '.') {
				return false;
			}
			
			// last char cannot be +, -, or e, otherwise return false
			if (i == s.length() - 1) {
				char lastChar = c;
				if (lastChar == '+' || lastChar == '-' || lastChar == 'e') {
					return false;
				}
			}
		}
		
		return hasNum;
	}
	// 1. regular expression
	public boolean isNumber(String s) {
		if (s.trim().isEmpty()) {
			return false;
		}

		String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";

		return s.trim().matches(regex);
	}
	// 07/11/2018
	// 2. all corner cases
	public boolean isNumber(String s) {
		s = s.trim();
		
		if (s.isEmpty()) {
			return false;
		}
		
		int n = s.length();

		boolean hasE = false;
		boolean hasDot = false;
		boolean hasNum = false;
		
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			// e can neither be replicated nor placed before number
			if (c == 'e') {
				if (hasE || !hasNum) {
					return false;
				} else {
					hasE = true;
				}
			}
			// '.' cannot be replicated nor placed after 'e'
			if (c == '.') {
				if (hasDot || hasE) {
					return false;
				} else {
					hasDot = true;
				}
			}
			// check whether numbers are included
			if (Character.isDigit(c)) {
				hasNum = true;
			}
			// the sign can only be placed at the beginning or after 'e'
			// sign cannot be in the middle; if it can, must follow 'e'
			if (i > 0 && s.charAt(i - 1) != 'e' && (c == '+' || c == '-')) {
				return false;
			}
			// no other characters except '+', '-', '.', and 'e'
			if (!Character.isDigit(c) &&
				c != '+' && c != '-' &&
				c != 'e' && c != '.'
			) {
				return false;
			}
		}
		// '+', '-', and 'e' cannot be the last character
		char last = s.charAt(n - 1);
		if (last == '+' || last == '-' || last == 'e') {
			return false;
		}
		
		return hasNum;
	}

	// 3. modularized  functionalities
	public boolean isNumber(String s) {
		s = s.trim();
		char[] cs = s.toCharArray();
		return isNumber(cs, 0, cs.length);
	}

	private boolean isNumber(char[] cs, int s, int e){
		int eIndex = -1;
		for (int i = s; i < e; i++) {
			if (cs[i] == 'e') {
				eIndex = i;
			}
		}
		if (eIndex >= 0) {
			return isDecimals(cs, s, eIndex) && isInteger(cs, eIndex + 1, e);
		} else {
			return isDecimals(cs, s, e);
		}
	}
	
	private boolean isDecimals(char[] cs, int s, int e) {
		if (s >= e) {
			return false;
		}
		if (cs[s] == '-' || cs[s] == '+') {
			return isDecimalsPositive(cs, s + 1, e);
		} else {
			return isDecimalsPositive(cs, s, e);
		}
	}
	
	private boolean isDecimalsPositive(char[] cs, int s, int e) {
		int dotIndex = -1;
		for (int i = s; i < e; i++) {
			if (cs[i] == '.') {
				dotIndex = i;
				break;
			}
		}
		if (dotIndex >= 0) {
			return e - s > 1 && (isIntegerPositive(cs, s, dotIndex) || s == dotIndex) && (isIntegerPositive(cs, dotIndex + 1, e) || dotIndex + 1 == e);
		} else {
			return isIntegerPositive(cs, s, e);
		}
	}
	
	private boolean isInteger(char[] cs, int s, int e) {
		if (s >= e) {
			return false;
		}
		if (cs[s] == '-' || cs[s] == '+') {
			return isIntegerPositive(cs, s + 1, e);
		} else {
			return isIntegerPositive(cs, s, e);
		}
	}
	
	private boolean isIntegerPositive(char[] cs, int s, int e) {
		if (s >= e) {
			return false;
		}
		for (int i = s; i < e; i++) {
			if (!isDigit(cs[i])) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	// 4. another direct method to consider all edge cases
	public boolean isNumber(String s) {
		if (s == null ) {
			return false;
		} 
		s = s.trim();
		if (s.length() == 0) {
			return false;
		} 

		boolean hasNum = false;
		boolean canSign = true;
		boolean canDot = true;
		boolean canE = false;
		boolean hasE = false;
		
		int i=0;
		while (i<s.length()) {
			char c = s.charAt(i++);
			if (c == ' ') {
			   return false;
			}
			
			if (c=='+' || c=='-') {
				if (!canSign) {
					return false;
				}
				canSign=false;
				
				continue;
			}
			
			if (c=='.') {
				if (!canDot) {
					return false;
				}
				canDot=false;
				canSign=false;
				continue;
			}
			
			if (c=='e') {
				if (!canE||hasE) {
					return false;
				}
				canE=false;
				hasE=true;
				hasNum=false;
				canDot=false;
				canSign=true;
				
				continue;
			}
			if (c>='0' && c<='9') {
				hasNum=true;
				
				if (!canE&&!hasE) {
					canE=true;
				}
				
				canSign=false;
			} else {
				return false;
			}
		}
		
		return hasNum;
	}
	// self written version
	public boolean isNumber(String s) {
		if (s.trim().isEmpty()) {
			return false;
		}
		s = s.trim();
		boolean hasE = false;
		boolean hasDot = false;
		boolean hasNumber = false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == 'e') {
				return false;
			}
			if (s.charAt(i) == 'e') {
				if (hasE || !hasNumber) {
					return false;
				}
				else {
					hasE = true;
				}
			}
			if (s.charAt(i) == '.') {
				if (hasDot || hasE) {
					return false;
				}
				else {
					hasDot = true;
				}
			}
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				hasNumber = true;
			}
			if ( i != 0 && s.charAt(i - 1) != 'e' && (s.charAt(i) == '+' || s.charAt(i) == '-') ) {
				return false;
			}
			if ((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != '-' && s.charAt(i) != '+' && s.charAt(i) != 'e' && s.charAt(i) != '.') {
				return false;
			}
		}
		int last = s.length() - 1;
		if (s.charAt(last) == '+' || s.charAt(last) == '-' || s.charAt(last) == 'e') {
			return false;
		}

		return hasNumber;
	}
}