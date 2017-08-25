/*
Substring with Concatenation of All Words

a string, S, 
a list of words, L, that are all of the same length. 
Find all starting indices of substring(s) in S 
that is a concatenation of each word in L exactly once and without any intervening characters.

Attention: concatenation of each word
		   each word in the concatenation shows exactly once
		   exactly once

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].

idea:
one substring of length m * n moves from left to right, 
starting from each i position, step length is 1, get a substring containing m * n length,
judge if each word in words[] only appears one time 

Attention: words[] have duplicate words
hashmap must record their appearing times exactly
duplicate words are counted as different words, they must appear the same times as in words[]

note: can repetitively count word appearing in last substring to be a word in a new substring
break is important
*/
import java.util.*;

public class SubstringWithConcatenationOfAllWords {
	private String S;
	private String[] L;

	// constructor
	public SubstringWithConcatenationOfAllWords(String string, String[] list) {
		S = string;
		L = list;
		System.out.println(findSubstring(S, L).toString());
	}
	public static void main(String[] args) {
		// String S = "barfoothefoobarmanbarfoo";
		// String[] L = {"foo", "bar"};
		String S = "aaaa";
		String[] L = {"a", "a"};
		new SubstringWithConcatenationOfAllWords(S, L);
	}
	
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        if (L == null || L.length == 0) {
			return null;
        }
		int l = S.length();	
        int n = L.length;
		int m = L[0].length();		
        ArrayList<Integer> res = new ArrayList<Integer>();        
        Map<String, Integer> covered = new HashMap<String, Integer>();
        for (int j=0; j<n; j++) {
            if (covered.containsKey(L[j])) {
                covered.put(L[j], covered.get(L[j]) + 1);
            }
			else {
                covered.put(L[j], 1);
            }
        }
        // pointer walks through the String S
        int i = 0;
        while (l-i >= m * n) {
			// Constructs a new HashMap with the same mappings as the specified Map
            // HashMap repetitively used to decide when to add the index to retured list
            // so need to construct every time 
            Map<String, Integer> temp = new HashMap<String, Integer>(covered);
			// pick up n substrings of length m
            for (int j=0; j<n; j++) {
				// first pick up a substring of length m
                String tmpString = S.substring(i + m * j, i + m * (j + 1));
                if (temp.containsKey(tmpString)) {
                    if (temp.get(tmpString) == 1) {
                        temp.remove(tmpString);
                    }
                    else {
                        temp.put(tmpString, temp.get(tmpString) - 1);
                    }
                }
				else
                    break;
            }
			if (temp.size() == 0) {
				res.add(i);
			}
			i++;
			/*
			// it is just another way to understand this question
			// a word appearing in last substring cannot be counted a word in a new substring
			// no need to think so be careful
            if (temp.size() == 0) {
				res.add(i);
				i += m*n;
			}
			else {
				i++;
			} 
			*/           
        }
        return res; 		
    }

    // self written, passed test
    public List<Integer> findSubstring(String S, String[] L) {
        int l = S.length();
        int n = L.length;
        int m = L[0].length();
        
        Map<String, Integer> discovered = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            if (discovered.containsKey(L[i])) {
                discovered.put(L[i], discovered.get(L[i]) + 1);
            }
            else {
                discovered.put(L[i], 1);
            }
        }
        
        int i = 0;
        List<Integer> ret = new ArrayList<Integer>();
        while (l - i >= m * n) {
            Map<String, Integer> covered = new HashMap<String, Integer>(discovered);
            for (int j = 0; j < n; j++) {
                String tempString = S.substring( i + m * j, i + m * (j + 1) );
                if (covered.containsKey(tempString)) {
                    if (covered.get(tempString) == 1) {
                        covered.remove(tempString);
                    }
                    else {
                        covered.put(tempString, covered.get(tempString) - 1);
                    }
                }
                else {
                    break;
                }
            }
            if (covered.size() == 0) {
                ret.add(i);
            }
            i++;
        }
        return ret;
    }
    // recent self written again
    public List<Integer> findSubstring(String s, String[] words) {
        int size = s.length();
        int n = words.length;
        int m = words[0].length();
        
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> result = new ArrayList<Integer>();
        int i = 0;
        while (size - i >= m * n) {
            HashMap<String, Integer> copy = new HashMap<String, Integer>(hm);
            for (int j = 0; j < n; j++) {
                String possibleWord = s.substring(i + m * j, i + m * (j + 1));
                if (copy.containsKey(possibleWord)) {
                    if (copy.get(possibleWord) == 1) {
                        copy.remove(possibleWord);
                    }
                    else {
                        copy.put(possibleWord, copy.get(possibleWord) - 1);
                    }
                }
                else {
                    break; // note, this saves a lot of time
                }
            }
            if (copy.size() == 0) {
                result.add(i);
            }
            i++;
        }
        return result;
    }
}
