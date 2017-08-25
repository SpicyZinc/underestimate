/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input: "tree"
Output: "eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: "cccaaa"
Output: "cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: "Aabb"
Output: "bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

idea:
direct thought passed, just be careful, many steps
*/

import java.util.*;

public class SortCharactersByFrequency {
	public static void main(String[] args) {
		SortCharactersByFrequency eg = new SortCharactersByFrequency();
		String tt = eg.frequencySort("tree");	
		// String tt = eg.frequencySort("Aabb");	
		System.out.println(tt);
	}

    public String frequencySort(String s) {
        if (s.length() == 0 || s == null) {
        	return "";
        }

        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (hash.containsKey(c)) {
        		hash.put(c, hash.get(c) + 1);
        	}
        	else {
        		hash.put(c, 1);
        	}
        }

        HashMap<Integer, ArrayList<Character>> hm = new HashMap<Integer, ArrayList<Character>>();
        List<Integer> frequencies = new ArrayList<Integer>();
        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
        	Character c = entry.getKey();
        	Integer frequency = entry.getValue();
        	if (frequency > 0) {
        		frequencies.add(frequency);
	        	if (hm.containsKey( frequency )) {
	        		ArrayList<Character> list = hm.get(frequency);
	        		list.add(c);
	        		hm.put(frequency, list);
	        	}
	        	else {
	        		ArrayList<Character> list = new ArrayList<Character>();
	        		list.add(c);
	        		hm.put(frequency, list);
	        	}
        	}
        }
        Collections.sort(frequencies);
        StringBuilder sb = new StringBuilder();
        sb.append( getChars(hm.get(frequencies.get(0)), frequencies.get(0)) );
        for (int i = 1; i < frequencies.size(); i++) {
        	int fre = frequencies.get(i);
        	int preFre = frequencies.get(i - 1);
        	if (fre != preFre) {
        		sb.append( getChars( hm.get(fre), fre ) );
        	}
        }

        return sb.reverse().toString();
    }

    private String getChars(ArrayList<Character> list, int times) {
    	String s = "";
    	for (int i = 0; i < list.size(); i++) {
    		int t = 0;
    		char[] chars = new char[times];
    		while (t < times) {
    			chars[t] = list.get(i);
    			t++;
    		}
    		s += new String(chars);
    	}

    	return s;
    }
}
