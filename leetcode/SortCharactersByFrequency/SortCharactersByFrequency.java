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
    // best, but TLE with only one
    public String frequencySort(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        
        List<Character> keys = new ArrayList<Character>(frequency.keySet());
        Collections.sort(keys, new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return frequency.get(b) - frequency.get(a);
            }
        });

        String result = "";
        for (int i = 0; i < keys.size(); i++) {
            char c = keys.get(i);
            for (int j = 0; j < frequency.get(c); j++) {
                result += c;
            }
        }
        
        return result;
    }
    // method 1
    public String frequencySort(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        Map<Integer, List<Character>> hm = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            
            List<Character> chars = new ArrayList<Character>();
            if (hm.containsKey(value)) {
                chars = hm.get(value);
            }
            chars.add(key);
            hm.put(value, chars);
        }
        
        List<Integer> values = new ArrayList<Integer>();
        for (int val : hm.keySet()) {
            values.add(val);
        }
        Collections.sort(values, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        
        String result = "";
        for (int i = 0; i <= values.size() - 1; i++) {
            int fre = values.get(i);
            List<Character> letters = hm.get(fre);
            result += compose(letters, fre);
        }
        
        return result;
    }

    public String compose(List<Character> letters, int times) {
        StringBuilder sb = new StringBuilder();
        for (char letter : letters) {
            for (int i = 0; i < times; i++) {
                sb.append(letter);
            }
        }
        
        return sb.toString();
    }
    // method 2
    public String frequencySort(String s) {
        if (s.length() == 0 || s == null) {
        	return "";
        }

        Map<Character, Integer> hash = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
            hash.put(c, hash.getOrDefault(c, 0) + 1);
        }

        Map<Integer, ArrayList<Character>> hm = new HashMap<Integer, ArrayList<Character>>();
        List<Integer> frequencies = new ArrayList<Integer>();
        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
        	Character c = entry.getKey();
        	Integer frequency = entry.getValue();
        	if (frequency > 0) {
        		frequencies.add(frequency);
                ArrayList<Character> list = new ArrayList<Character>();
	        	if (hm.containsKey( frequency )) {
	        		list = hm.get(frequency);
	        	}
                list.add(c);
                hm.put(frequency, list);
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
