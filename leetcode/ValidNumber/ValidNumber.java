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
    // 1. regular expression
    public boolean isNumber(String s) {
        if (s.trim().isEmpty()) {
            return false;
        }
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        return s.trim().matches(regex);
    }
    // 2. all corner cases
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }

        boolean hasE = false;
        boolean hasDot = false;
        boolean hasNumber = false;
        for (int i = 0; i < s.length(); i++) {
            // e cannot be the first character
            if (i == 0 && s.charAt(i) == 'e') {
                return false;
            }
            if (s.charAt(i) == 'e') {
                // e can neither be replicated nor placed before number
                if (hasE == true || hasNumber == false) {
                    return false;
                } 
                else {
                    hasE = true;
                }
            } 
            if (s.charAt(i) == '.') {
                // '.' cannot be replicated nor placed after 'e'
                if (hasDot == true || hasE == true) {
                    return false;
                } 
                else {
                    hasDot = true;
                }
            }
            // check whether numbers are included.
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                hasNumber = true;
            }
            // the sign can only be placed at the beginning or after 'e'
            if (i != 0 && s.charAt(i - 1) != 'e' && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                return false;
            }
            // no other characters except '+', '-', '.', and 'e'
            if ((s.charAt(i) > '9' || s.charAt(i) < '0') && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '.' && s.charAt(i) != 'e') {
                return false;
            }
        }
        // '+', '-', and 'e' cannot be the last character
        if (s.charAt(s.length() - 1) == '-' || s.charAt(s.length() - 1) == '+' || s.charAt(s.length() - 1) == 'e') {
            return false;
        }

        return hasNumber;
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
        }
        else {
            return isDecimals(cs, s, e);
        }
    }
    
    private boolean isDecimals(char[] cs, int s, int e) {
        if (s >= e) {
            return false;
        }
        if (cs[s] == '-' || cs[s] == '+') {
            return isDecimalsPositive(cs, s + 1, e);
        }
        else {
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
        }
        else {
            return isIntegerPositive(cs, s, e);
        }
    }
    
    private boolean isInteger(char[] cs, int s, int e) {
        if (s >= e) {
            return false;
        }
        if (cs[s] == '-' || cs[s] == '+') {
            return isIntegerPositive(cs, s + 1, e);
        }
        else {
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

        boolean hasNum=false;
        boolean canSign=true;
        boolean canDot=true;
        boolean canE=false;
        boolean hasE=false;
        
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
            }
            else {
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