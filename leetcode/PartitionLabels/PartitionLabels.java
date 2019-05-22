/*
A string S of lowercase letters is given.
We want to partition this string into as many parts as possible so that each letter appears in at most one part,
and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

idea:
greed
first occurrence of a letter till last occurrence of this letter should be a partition
all letters in between, find each one's last occurrence 
*/

class PartitionLabels {
	// Wed May 22 12:17:22 2019
	public List<Integer> partitionLabels(String S) {
		int[] lastPositions = new int[26];

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);

			lastPositions[c - 'a'] = i;
		}

		List<Integer> partitions = new ArrayList<>();

		int left = 0;
		int rightmost = 0;

		for (int right = 0; right < S.length(); right++) {
			char c = S.charAt(right);

			int lastPostForC = lastPositions[c - 'a'];
			// 目前能到达的最远 right 当然包括了其他最后一个 char 的 rightmost position
			rightmost = Math.max(rightmost, lastPostForC);
			// greedy, 最大的一个 partition
			if (right == rightmost) {
				partitions.add(rightmost - left + 1);
				left = rightmost + 1;
			}
		}

		return partitions;
	}
}