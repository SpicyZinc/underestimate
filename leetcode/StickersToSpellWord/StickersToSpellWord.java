/*
We are given N different types of stickers. Each sticker has a lowercase English word on it.
You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.


Example 1:
Input:
["with", "example", "science"], "thehat"
Output:
3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.

Example 2:
Input:
["notice", "possible"], "basicbasic"
Output:
-1
Explanation:
We can't form the target "basicbasic" from cutting letters from the given stickers.

Note:
stickers has length in the range [1, 50].
stickers consists of lowercase English words (without apostrophes).
target has length in the range [1, 15], and consists of lowercase English letters.
In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.

idea:
memo + DFS
need to come back
*/

class StickersToSpellWord {
	public int minStickers(String[] stickers, String target) {
		int size = stickers.length;
		int[][] memo = new int[size][26];
		Map<String, Integer> hm = new HashMap<>();
		
		for (int i = 0; i < size; i++) {
			String sticker = stickers[i];
			for (int j = 0; j < sticker.length(); j++) {
				char c = sticker.charAt(j);
				memo[i][c - 'a']++;
			}
		}
		
		hm.put("", 0);
		
		return dfs(hm, memo, target);
	}
	
	public int dfs(Map<String, Integer> hm, int[][] memo, String target) {
		if (hm.containsKey(target)) {
			return hm.get(target);
		}

		int size = memo.length;
		int cnt = Integer.MAX_VALUE;
		int[] letters = new int[26];

		for (int i = 0; i < target.length(); i++) {
			letters[target.charAt(i) - 'a']++;
		}

		// try every sticker
		for (int i = 0; i < size; i++) {
			char first = target.charAt(0);

			if (memo[i][first - 'a'] == 0) {
				continue;
			}
			
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j < 26; j++) {
				char c = (char) (j + 'a');

				if (letters[j] > 0 ) {
					for (int k = 0; k < Math.max(0, letters[j] - memo[i][j]); k++) {
						sb.append(c);
					}
				}
			}
			
			String str = sb.toString();
			int nextCnt = dfs(hm, memo, str);

			if (nextCnt != -1) {
				cnt = Math.min(cnt, nextCnt + 1);
			}
		}

		int count = cnt == Integer.MAX_VALUE ? -1 : cnt;

		hm.put(target, count);

		return count;
	}
}