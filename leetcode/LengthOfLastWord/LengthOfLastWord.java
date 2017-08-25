/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word in the string.
If the last word does not exist, return 0.
Note: A word is defined as a character sequence consists of non-space characters only.

Given s = "Hello World",
return 5. 
===
This problem is easy because the problem is only containing space amony words, no
other character there.

idea:
two while()

1. find the last word last letter by skipping any 'space' or '!' after last word 
   while()
2. until next space, count the character number
   while()
*/
class LengthOfLastWord {
	public static void main(String[] args) {		
		String s1 = "Hello World";
		String s2 = "How are you doing!";
		System.out.println(s1 + " last word is -- ");
		findLastWord(s1);
		System.out.print(" the length is " + lengthOfLastWord(s1) + "\n");
		System.out.println(s2 + " last word is -- ");
		findLastWord(s2);
		System.out.print(" the length is " + lengthOfLastWord(s2) + "\n");
	}
	
	private static void findLastWord(String s) {
		String[] currentLine = s.split("\\s+");
		String lastWord = currentLine[currentLine.length-1];
		char[] lastArray = lastWord.toCharArray();
		if(lastArray[lastArray.length-1] == '!') {
			lastWord = "";
			for(int i=0; i<lastArray.length-1; i++) {
				lastWord += lastArray[i];
			}
		}
		System.out.print(lastWord);
	}
	// this method passed the test
	public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.toLowerCase();
        s = s.trim();
        String[] ss = s.split("\\s");
        
        return ss[ss.length - 1].length();
    }
    // this version also passed test
	private static int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int lastWordLength = 0;
		int i = s.length() - 1;
		// go to the index where the last word last character starts first, 
		// but after last word there may be a space or an exclamation mark
		// get rid of the space or exclamation mark first
		while ( i >= 0 && (s.charAt(i) == ' ' || s.charAt(i) == '!') ) {
			i--;
		}
		// meeting '\0', a space, stop while()
		while (i >= 0 && (s.charAt(i) != ' ')) {
			lastWordLength++;
			i--;
		}		
		return lastWordLength;
	}	
}
