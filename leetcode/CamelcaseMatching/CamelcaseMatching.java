/*
A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query.
(We may insert each character at any position, and may insert 0 characters.)

Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.

Example 1:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
Output: [true,false,true,true,false]
Explanation: 
"FooBar" can be generated like this "F" + "oo" + "B" + "ar".
"FootBall" can be generated like this "F" + "oot" + "B" + "all".
"FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".

Example 2:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
Output: [true,false,true,false,false]
Explanation: 
"FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
"FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".

Example 3:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
Output: [false,true,false,false,false]
Explanation: 
"FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".

Note:
1 <= queries.length <= 100
1 <= queries[i].length <= 100
1 <= pattern.length <= 100
All strings consists only of lower and upper case English letters.

idea:
这道题就是 isSubsequence() variation
我把简单问题复杂化了
*/

import java.util.*;

class CamelcaseMatching {
	public static void main(String[] args) {
		CamelcaseMatching eg = new CamelcaseMatching();
		String[] queries = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		String pattern = "FoBa";
		
		List<Boolean> result = eg.camelMatch(queries, pattern);

		System.out.println(result);
	}
	// Tue Apr 23 23:22:10 2019
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> list = new ArrayList<>();

		for (String query : queries) {
			list.add(isSubsequence(query, pattern));
		}

		return list;
	}

	public boolean isSubsequence(String query, String pattern) {
		int j = 0;

		for (int i = 0; i < query.length(); i++) {
			char a = query.charAt(i);

			if (j < pattern.length() && a == pattern.charAt(j)) {
				j++;
			} else if (a >= 'A' && a <= 'Z') {
				return false;
			}
		}

		return j == pattern.length();
	}

	// this is wrong answer, invented by myself
	public List<Boolean> camelMatch(String[] queries, String pattern) {
		int n = queries.length;

		List<Boolean> list = new ArrayList<>();
		List<String> patterns = getPattern(pattern);

		for (String query : queries) {
			List<String> queryPattern = getPattern(query);

			if (queryPattern.size() != patterns.size()) {
				list.add(false);
			} else {
				boolean isPattern = true;
				for (int i = 0; i < Math.min(queryPattern.size(), patterns.size()); i++) {
					String a = queryPattern.get(i);
					String b = patterns.get(i);

					if (a.indexOf(b) != 0) {
						isPattern = false;
						break;
					}
				}
				list.add(isPattern);
			}
		}

		return list;
	}

	public List<String> getPattern(String s) {
		List<String> list = new ArrayList<>();

		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				int start = i++;
				while (i < s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
					i++;
				}

				list.add(s.substring(start, i));
			}
		}

		return list;
	}
}