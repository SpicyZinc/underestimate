/*
In a string S of lowercase letters, these letters form consecutive groups of the same character.
For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
Call a group large if it has 3 or more characters.
We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

Example 1:
Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.

Example 2:
Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.

Example 3:
Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]

Note:  1 <= S.length <= 1000

idea:
直接做
note, cnt >= 2
*/
import java.util.*;

class PositionsOfLargeGroups {
	public static void main(String[] args) {
		PositionsOfLargeGroups eg = new PositionsOfLargeGroups();
		String s = "abcdddeeeeaabbbcd";
		List<List<Integer>> result = eg.largeGroupPositions(s);
		for (List<Integer> path : result) {
			System.out.println(path);
		}
	}

	public List<List<Integer>> largeGroupPositions(String S) {
		List<List<Integer>> result = new ArrayList<>();
		if (S.length() == 0 || S == null) {
			return result;
		}

		int n = S.length();
		int start = 0;

		while (start < n) {
			int cnt = 0;
			int i = start;

			if (i + 1 < n && S.charAt(i) == S.charAt(i + 1)) {
				while (i + 1 < n && S.charAt(i) == S.charAt(i + 1)) {
					cnt++;
					i++;
				}
			}
			if (cnt >= 2) {
				result.add(Arrays.asList(start, i));
			}

			start = i + 1;
		}

		return result;
	}

	// neat code, but not that faster than my code
	public List<List<Integer>> largeGroupPositions(String S) {
		int i = 0, j = 0, N = S.length();
		List<List<Integer>> res = new ArrayList<>();
		while (j < N) {
			while (j < N && S.charAt(j) == S.charAt(i)) j++;
			if (j - i >= 3) res.add(Arrays.asList(i, j - 1));
			i = j;
		}

		return res;
	}
}