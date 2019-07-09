/*
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`.
We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed.
Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
	A
   / \
  D   E
 / \ / \
X   Y   Z
This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.

Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.

Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

idea:
recursion,
bottom layer becomes above layer
iteratively
*/

import java.util.*;

class PyramidTransitionMatrix {
	public static void main(String[] args) {
		String bottom = "BCD";
		String[] allowedPattern = {"BCG", "CDE", "GEA", "FFF"};

		List<String> allowed = Arrays.asList(allowedPattern);

		PyramidTransitionMatrix eg = new PyramidTransitionMatrix();
		eg.pyramidTransition(bottom, allowed);
	}

	public boolean pyramidTransition(String bottom, List<String> allowed) {
		Map<String, Set<Character>> hm = new HashMap<>();

		for (String allow : allowed) {
			// get base
			String base = allow.substring(0, 2);
			// get top
			char top = allow.charAt(2);
			hm.computeIfAbsent(base, x -> new HashSet<>()).add(top);
		}

		return dfs(bottom, "", hm);
	}

	public boolean dfs(String base, String above, Map<String, Set<Character>> hm) {
		// 已经构造完成一个 pyramid
		if (base.length() == 2 && above.length() == 1) {
			return true;
		}
		// base 层 比上一次 多 1 个 就是要 重新 换 base 了
		if (base.length() - 1 == above.length()) {
			// move to above level, above becomes base
			return dfs(above, "", hm);
		}

		// 根据 上一层填充的长度 来 选择 base
		int pos = above.length();
		String baseLine = base.substring(pos, pos + 2);

		if (hm.containsKey(baseLine)) {
			for (char c : hm.get(baseLine)) {
				if (dfs(base, above + c, hm)) {
					return true;
				}
			}
		}

		return false;
	}
}