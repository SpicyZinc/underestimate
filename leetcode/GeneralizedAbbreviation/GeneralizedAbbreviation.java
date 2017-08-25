/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

idea:
https://www.cnblogs.com/grandyang/p/5261569.html
https://discuss.leetcode.com/topic/32171/9-line-easy-java-solution/6
https://discuss.leetcode.com/topic/32270/java-backtracking-solution/2
1. bitwise
2. recursion
*/
import java.util.*;
public class GeneralizedAbbreviation {
	public static void main(String[] args) {
		GeneralizedAbbreviation eg = new GeneralizedAbbreviation();
		List<String> result = eg.generateAbbreviations("word");
		for (String abbr : result) {
			System.out.print(abbr + " ");
		}
		System.out.println();
	}
	// bitwise
    public List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
    	if (word.length() == 0 || word == null) {
    		return result;
    	}

    	int size = (int) Math.pow(2.0, word.length());
    	for (int i = 0; i < size; i++) {
    		String path = "";
    		int cnt = 0;
            int num = i;
            for (int j = 0; j < word.length(); j++) {
                if ((num & 1) == 1) {
                	cnt++;
                	if (j == word.length() - 1) {
                		path += cnt;
                	}
                }
                else {
                	if (cnt != 0) {
                		path += cnt;
                		cnt = 0;
                	}
                	path += word.charAt(j);
                }
                num >>= 1;
            }
            result.add(path);
    	}

    	return result;
    }
    // recursion
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
    	return result;
    }
    // easy to understand
    public List<String> generateAbbreviations(String word) {
    	List<String> result = new ArrayList<String>();
    	backtrack("", 0, 0, word, result);

    	return result;
    }
    // 2 ways, either keep the character or abbreviate the character
    private void backtrack(String cur, int start, int count, String s, List<String> result) {
    	if (start == s.length()) {
    		cur += count > 0 ? count : "";
    		result.add(cur);
    	}
    	else {
    		backtrack(cur + (count > 0 ? count : "") + s.charAt(start), start + 1, 0, s, result); // keep the character, not abbreviate it
    		backtrack(cur, start + 1, count + 1, s, result); // abbreviate, increase the count
    	}
    }
}