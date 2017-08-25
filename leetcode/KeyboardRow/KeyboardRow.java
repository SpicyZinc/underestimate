/*
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

idea:
build hashmap character to line number
for each word, find each character of this word corresponding line, if not the same line, immediately return false
*/
public class KeyboardRow {
    public String[] findWords(String[] words) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        String[] strings = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < strings.length; i++) {
        	for (int j = 0; j < strings[i].length(); j++) {
        		char c = strings[i].charAt(j);
        		hm.put(c, i + 1);
        	}
        }
        List<String> temp = new ArrayList<String>();
        for (String word : words) {
        	if (sameLine(hm, word)) {
        		temp.add(word);
        	}
        }
        String[] result = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
        	result[i] = temp.get(i);
        }

        return result;
    }

    private boolean sameLine(HashMap<Character, Integer> hm, String word) {
    	for (int i = 0; i < word.length() - 1; i++) {
    		char c = Character.toLowerCase(word.charAt(i));
    		int line = hm.get(c);
    		if (line != hm.get( Character.toLowerCase( word.charAt(i+1) ) )) {
    			return false;
    		}
    	}
    	return true;
    }
}