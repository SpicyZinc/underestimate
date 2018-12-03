/*
You are given an array A of strings.
Two strings S and T are special-equivalent if after any number of moves, S == T.

A move consists of choosing two indices i and j with i % 2 == j % 2,
and swapping S[i] with S[j].
Now, a group of special-equivalent strings
from A is a non-empty subset S of A such that
any string not in S is not special-equivalent with any string in S.

Return the number of groups of special-equivalent strings from A.

Example 1:
Input: ["a","b","c","a","c","c"]
Output: 3
Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]

Example 2:
Input: ["aa","bb","ab","ba"]
Output: 4
Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]

Example 3:
Input: ["abc","acb","bac","bca","cab","cba"]
Output: 3
Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]

Example 4:
Input: ["abcd","cdab","adcb","cbad"]
Output: 1
Explanation: 1 group ["abcd","cdab","adcb","cbad"]
 
Note:
1 <= A.length <= 1000
1 <= A[i].length <= 20
All A[i] have the same length.
All A[i] consist of only lowercase letters.

idea:
this problem is hard to understand
关键是找到 统一 root string
how? 建立一个52 长的array (i % 2) * 26 来归化得到root
*/

class GroupsOfSpecialEquivalentStrings {
	public int numSpecialEquivGroups(String[] A) {
		Set<String> group = new HashSet<>();
		for (String s : A) {
			group.add(getEquivalentRoot(s));
		}

		return group.size();
	}

	public String getEquivalentRoot(String s) {
		int[] count = new int[52];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			count[c - 'a' + 26 * (i % 2)]++;
		}

		return Arrays.toString(count);
	}
}