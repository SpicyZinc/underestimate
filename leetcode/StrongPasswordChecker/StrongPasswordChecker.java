/*
A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.

Insertion, deletion or replace of any one character are all considered as one change.

idea:
https://discuss.leetcode.com/topic/63185/java-easy-solution-with-explanation
https://discuss.leetcode.com/topic/65158/c-0ms-o-n-35-lines-solution-with-detailed-explanation
*/

public class StrongPasswordChecker  {
	public int strongPasswordChecker(String s) {
        	int deleteTarget = Math.max(0, s.length() - 20);
            int addTarget = Math.max(0, 6 - s.length());
    
            int toDelete = 0, toAdd = 0, toReplace = 0, needUpper = 1, needLower = 1, needDigit = 1;
            
            for (int l = 0, r = 0; r < s.length(); r++) {
            	char c = s.charAt(r);
                if ( Character.isUpperCase(c) ) { needUpper = 0; }               
                if ( Character.isLowerCase(c) ) { needLower = 0; }
                if ( Character.isDigit(c) )     { needDigit = 0; }
                
                if (r - l == 2) {                               
                    if (s.charAt(l) == s.charAt(l + 1) && s.charAt(r) == s.charAt(l + 1)) {
                        if (toAdd < addTarget) { 
                        	toAdd++;
                        	l = r;
                        }  
                        else { 
                        	toReplace++;
                        	l = r + 1; 
                        }           
                    }
                    else { 
                    	l++; 
                    }
                }
            }
            if (s.length() <= 20) { 
            	return Math.max(addTarget + toReplace, needUpper + needLower + needDigit); 
            }
            
            int len = 0;
            toReplace = 0;                                         
            List<HashMap<Integer, Integer>> lenCnts = new ArrayList<HashMap<Integer, Integer>>(3);
            for (int l = 0, r = 0; r <= s.length(); r++) {    
                if ( r == s.length() || s.charAt(l) != s.charAt(r) ) {
                	len = r - l;
                	if (len > 2) {
                    	HashMap<Integer, Integer> temp = lenCnts.get(len % 3);
                    	int ttt = temp.get(len);
                    	temp.put(len, ++ttt);
                    } 
                    l = r;
                }
            }
            
            int numLetters = 0;
            int dec = 0;
            for (int i = 0; i < 3; i++) {
            	HashMap<Integer, Integer> hm = lenCnts.get(i);
            	for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
    			    Integer key = entry.getKey();
    			    Integer value = entry.getValue();
    			    if (i < 2) {
                        numLetters = i + 1;
                        dec = Math.min(value, (deleteTarget - toDelete) / numLetters);
                        toDelete += dec * numLetters;
                        value -= dec;                          
                        if (key - numLetters > 2) {
                        	Integer tempVal = lenCnts.get(2).get(key - numLetters) + dec;
                        	lenCnts.get(2).put(key - numLetters, tempVal);
                        }   
                    }
                    toReplace += value * (key / 3);
    			}
            }
    
            int decAgain = (deleteTarget - toDelete) / 3;                
            toReplace -= decAgain;
            toDelete -= decAgain * 3;
            return deleteTarget + Math.max(toReplace, needUpper + needLower + needDigit);
    }
}
