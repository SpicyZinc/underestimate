/*
Anagrams
Given an array of strings, 
return all groups of strings that are anagrams.
Note: All inputs will be in lower-case.

make sure to know what anagram is:

A gentleman = Elegant man
Debit card = Bad credit
Dormitory = Dirty room

idea:
if in the string array, there are two or more than two strings have the same character, order does not matter
only there is one string in the array, it is not anagram.

Hashmap<String, ArrayList<String>> ascendingStringToList = new Hashmap<String, ArrayList<String>>();
sort each string in the array of strings
use a hashmap to save <key : value> pair like <aet : ate->aet->tea>
as long as the list in the hashmap size() >= 2, then we can say these >= 2 strings are anagrams

For example:
S = ["abc", "bca", "bac", "bbb", "bbca", "abcb"]
solution:
["abc", "bca", "bac", "bbca", "abcb"]

only "bbb" does not have anagrams
*/

import java.util.*;

public class Anagrams {
    public ArrayList<String> anagrams(String[] strs) {
		ArrayList<String> res = new ArrayList<String>();
		HashMap<String, ArrayList<String>> ascendingStringToList = new HashMap<String, ArrayList<String>>();    

		for (int i = 0; i < strs.length; i++) {
			String key = sortArray(strs[i]);
			if (!ascendingStringToList.containsKey(key)) {
				ArrayList<String> values = new ArrayList<String>();				
				values.add(strs[i]);
				ascendingStringToList.put(key, values);
			}
			else {
				ArrayList<String> values = ascendingStringToList.get(key);
				values.add(strs[i]);
				ascendingStringToList.put(key, values);
			}
		}
		
		for (ArrayList<String> list : ascendingStringToList.values()) {
			if (list.size() >= 2) {
				res.addAll(list);
			}
		}

		return res;
    }
	
	private String sortArray(String s) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}

	// self practice passed test
	public ArrayList<String> anagrams(String[] strs) {
        
        HashMap<String, ArrayList<String>> stringToListStrings = new HashMap<String, ArrayList<String>>();
        ArrayList<String> ret = new ArrayList<String>();
        
        for (int i = 0; i < strs.length; i++) {
            String temp = strs[i].toLowerCase();
            String ascendingString = sortString(temp);
            
            if (!stringToListStrings.containsKey(ascendingString)) {
                ArrayList<String> values = new ArrayList<String>();
                values.add(temp);
                stringToListStrings.put(ascendingString, values);
            }
            else {
                ArrayList<String> values = stringToListStrings.get(ascendingString);
                values.add(temp);
                stringToListStrings.put(ascendingString, values);
            }
        }
        
        for (ArrayList<String> list : stringToListStrings.values()) {
            if (list.size() >= 2) {
                ret.addAll(list);
            }
        }
        
        return ret;
        
    }
    
    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        
        return new String(chars);
    }
}