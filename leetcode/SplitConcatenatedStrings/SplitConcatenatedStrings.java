/*
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not.
Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases: 

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint. 
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".

Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.

idea:
https://www.cnblogs.com/grandyang/p/6887140.html

"abc", "xyz"
cba - zyx

如果 cut point is at |
def - cb|a - zyx

1. sb.append(a in cba)
2. sb.append(all other strings after cba)
3. sb.append(all other strings before cba)
4. sb.append(cb in cba)
*/

public class SplitConcatenatedStrings {
	public static void main(String[] args) {
		SplitConcatenatedStrings eg = new SplitConcatenatedStrings();
		String[] strs = {"abc", "xyz"};
		String result = eg.splitLoopedString(strs);
		System.out.println(result);
	}

	// method 1, TLE
	String result = "";

	public String splitLoopedString(String[] strs) {
		dfs(strs, "", 0);
		return result;
	}

	public void dfs(String[] strs, String s, int i) {
		int n = strs.length;
		if (i < n) {
			dfs(strs, s + strs[i], i + 1);
			dfs(strs, s + new StringBuilder(strs[i]).reverse().toString(), i + 1);
		} else {
			for (int j = 0; j < s.length(); j++) {
				// choose different cut points by looping j in s.length
				String t = s.substring(j) + s.substring(0, j);
				if (t.compareTo(result) > 0) {
					result = t;
				}
			}
		}
	}
	// method 2
	public String splitLoopedString(String[] strs) {  
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			String reversedStr = new StringBuilder(str).reverse().toString();  

			if (str.compareTo(reversedStr) < 0) {  
				strs[i] = reversedStr;
			}
		}

		String biggestLexic = "a";

		for (int i = 0; i < strs.length; i++) {
			String current = strs[i];
			String reversed = new StringBuilder(current).reverse().toString();
			String[] curAndRev = new String[] {current, reversed};

			for (String str : curAndRev) {
				for (int k = 0; k < str.length(); k++) {
					// find the cut point where lexicographically bigger
					if (str.charAt(k) >= biggestLexic.charAt(0)) {

						StringBuilder sb = new StringBuilder(str.substring(k));

						// append all other strings, note the order, 题目中描述就是这样
						for (int j = i + 1; j < strs.length; j++) {
							sb.append(strs[j]);
						}
						for (int j = 0; j < i; j++) {
							sb.append(strs[j]);
						}

						sb.append(str.substring(0, k));

						if (biggestLexic.compareTo(sb.toString()) < 0) {
							biggestLexic = sb.toString();
						}
					}
				}
			}
		}

		return biggestLexic;
	}
}