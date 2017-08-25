/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
Output: "apple"

Example 2:
Input: s = "abpcplea", d = ["a","b","c"]
Output: "a"

Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

idea:
the reason we can use this idea is because the relative sequence
does not change
sort the list first, how to sort
length, if length is the same, lexicographical order
Collections.sort

for each dictionary word, loop it, if found matching in s, i++
of course loop through all characters in s
*/

public class Longest WordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
        	@Override
        	public int compare(String s, String t) {
        		if (s.length() != t.length()) {
        			return t.length() - s.length();
        		}
        		return s.compareTo(t);
        	}
        });

        for (String dicWord : d) {
        	int i = 0;
        	for (char c : s.toCharArray()) {
	        	if (dicWord.charAt(i) == c) {
	        		i++;
	        	}
	        	if (i == dicWord.length()) {
	        		return dicWord;
	        	}
        	}
        }

        return "";
    }
}