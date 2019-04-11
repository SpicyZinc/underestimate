/*
input:
[
	[1,1,1,1,1,1]
	[0,0,1,0,1,0]
	[1,1,1,0,1,0]
	[1,0,0,1,1,1]
]

output:
[[1,0,2,1],
[1,3,3,3],
[2,5,3,5],
[4,1,4,2]]


follow up 是如果array里有line跟Point的话怎么过滤掉

*/

import java.util.*;

class Pinterest {
	public static void main(String[] args) {
		Pinterest eg = new Pinterest();
		int[][] matrix = {
			{1,1,1,1,1,1},
			{0,0,1,0,1,0},
			{1,1,1,0,1,0},
			{1,0,0,1,1,1},
		};

		// int[][] matrix = {
		// 	{1,1,0,1,1,1},
		// 	{1,1,0,0,1,1}
		// };

	}


	public List<String> letterCasePermutation(String S) {
		LinkedList<String> result = new LinkedList<>();

		if (S.length() == 0 || S == null) {
			return result;
		}

		result.add(S);

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			
			if (Character.isLetter(c)) {
				int size = result.size();
				for (int j = 0; j < size; j++) {
					String str = result.poll();

					String lowerCaseStr = str.substring(0, i) + Character.toLowerCase(c) + str.substring(i + 1);
					String upperCaseStr = str.substring(0, i) + Character.toUpperCase(c) + str.substring(i + 1);

					result.add(lowerCaseStr);
					result.add(upperCaseStr);
				}
			}
		}

		return result;
	}
}
