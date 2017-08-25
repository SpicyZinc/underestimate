/* 
A string such as "word" contains the following abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary,
find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").

idea:
1. generate all abbreviations of target
2. sort them in a ascending order
3. use validWordAbbreviation() to see if one abbr is valid for ALL words in dictionary
4. if fails one in the dictionary, then this abbr is not min abbr
*/

import java.util.*;

public class MinimumUniqueWordAbbreviation {
	public static void main(String[] args) {
		MinimumUniqueWordAbbreviation eg = new MinimumUniqueWordAbbreviation();
		String target = "apple";
		// String[] dictionary = {"blade"};
		String[] dictionary = {"plain", "amber", "blade"};
		String result = eg.minAbbreviation(target, dictionary);

		System.out.println(result);
	}

	public String minAbbreviation(String target, String[] dictionary) {
		if (dictionary.length == 0 || dictionary == null) {
			return target.length() + "";
		}

		List<String> list = generateAbbreviations(target);
		for (int i = 0; i < list.size(); i++) {
			String abbr = list.get(i);
			boolean conflict = false;
			for (String word : dictionary) {
				if (validWordAbbreviation(word, abbr)) {
					conflict = true;
				}
            }
            if (!conflict) {
            	return abbr;
            }
		}
		return "";
    }

	public List<String> generateAbbreviations(String word) {
    	List<String> result = new ArrayList<String>();
    	int len = word.length();
    	result.add(len == 0 ? "" : len + "");
    	for (int i = 0; i < len; i++) {
    		for (String right : generateAbbreviations(word.substring(i + 1))) {
    			String leftNum = i > 0 ? i + "" : "";
    			result.add( leftNum + word.substring(i, i + 1) + right );
    		}
    	}

    	Collections.sort(result, new Comparator<String>() {
	        @Override
	        public int compare(String s1, String s2) {
	            return  s1.length() - s2.length();
	        }
	    });

    	return result;
    }

	public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
		int wl = word.length();
		int al = abbr.length();
		while (i < wl && j < al) {
			char c1 = word.charAt(i);
			char c2 = abbr.charAt(j);
			if (c2 >= '0' && c2 <= '9') {
				if (c2 == '0') {
					return false;
				}
				int val = 0;
				while (j < al && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
					val = val * 10 + abbr.charAt(j) - '0';
					j++;
				}
				i += val;
			} else {
				if (c1 != c2) {
					return false;
				}
				else {
					i++;
					j++;
				}
			}
		}

		return i == wl && j == al;
    }
}