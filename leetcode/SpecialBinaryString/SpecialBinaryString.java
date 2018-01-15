/*
Special binary strings are binary strings with the following two properties:

The number of 0's is equal to the number of 1's.
Every prefix of the binary string has at least as many 1's as 0's.
Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)

At the end of any number of moves, what is the lexicographically largest resulting string possible?

Example 1:
Input: S = "11011000"
Output: "11100100"
Explanation:
The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
This is the lexicographically largest string possible after some number of swaps.

Note:
S has length at most 50.
S is guaranteed to be a special binary string as defined above.

idea:
think of valid parenthesis
need to revisit it 
*/


class SpecialBinaryString {
	public String makeLargestSpecial(String S) {
		return dfs(S, 0);
	}

	public String dfs(String s, int index) {
		String result = "";
		List<String> tokens = new ArrayList<String>();
		while (index < s.length() && result.length() == 0) {
			if (s.charAt(index) == '1') {
                index++;
				tokens.add(dfs(s, index));
			} else {
				result += "1";
			}
		}

		int prefix = result.length();
		Collections.sort(tokens, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
        
		for (int i = 0; i < tokens.size(); i++) {
			result += tokens.get(i);
		}
		if (prefix != 0) {
			result += '0';
		}

		return result;
	}
}