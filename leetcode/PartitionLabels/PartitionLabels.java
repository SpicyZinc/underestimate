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
	public List<Integer> partitionLabels(String S) {
		int[] lastOccurrenceIndex = new int[26];
		for (int i = 0; i < S.length(); i++) {
			lastOccurrenceIndex[S.charAt(i) - 'a'] = i;
		}

		int anchor = 0;
		int right = 0;
		List<Integer> answers = new ArrayList<Integer>();
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			right = Math.max(right, lastOccurrenceIndex[c - 'a']);
			// once right pointer equals i
			// right is the most right, i reaches right, meaning find a partition
			if (i == right) {
				answers.add(right - anchor + 1);
				// update
				anchor = right + 1;
			}
		}

		return answers;
	}
}