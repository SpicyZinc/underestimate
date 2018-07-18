/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. 
If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

idea:
http://www.hrwhisper.me/leetcode-maximum-product-of-word-lengths/

1. use character - 'a' which is between [0, 25] as index
value is the frequency, the count of appearing times
2. bit operation
elements[i] |= 1 << (words[i][j] - 'a')
(elements[i] & elements[j]) == 0: bitwise AND, if zero, meaning no shared characters
parenthesis is needed, precedence of '&' is lower than '==' seemingly
*/

public class MaximumProductOfWordLengths {
	public static void main(String[] args) {
		MaximumProductOfWordLengths eg = new MaximumProductOfWordLengths();
		String[] words = {"abcc", "baz", "foo", "bar", "xtft", "abcdef"};
		System.out.println( eg.maxProduct(words) );
	}
    public int maxProduct(String[] words) {
        if ( words == null || words.length == 0 ) {
        	return 0;
        }
        int n = words.length;
        int[][] elements = new int[n][26];
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				elements[i][words[i].charAt(j) - 'a'] += 1;
			}
		}

        int maxProduct = 0;
        for ( int i = 0; i < n; i++ ) {
        	for ( int j = i + 1; j < n; j++ ) {
        		boolean flag = true;
        		for ( int k = 0; k < 26; k++ ) {
        			if ( elements[i][k] != 0 && elements[j][k] != 0 ) {
        				flag = false;
        				break;
        			}
        		}
        		if ( flag && words[i].length() * words[j].length() > maxProduct ) {
        			maxProduct = words[i].length() * words[j].length();
        		}
	        }
        }

        return maxProduct;
    }

    // bitwise operation
    public int maxProduct(String[] words) {
        if ( words.length == 0  || words == null ) {
            return 0;
        }
        int n = words.length;
        int[] elements = new int[n];
        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < words[i].length(); j++ ) {
                elements[i] |= (1 << words[i].charAt(j) - 'a');
            }    
        }
        int maxProduct = 0;
        for ( int i = 0; i < n; i++ ) {
            for ( int j = i + 1; j < n; j++ ) {
                if ( (elements[i] & elements[j]) == 0 ) {
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        
        return maxProduct;
    }
}