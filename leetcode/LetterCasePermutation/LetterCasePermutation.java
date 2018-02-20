/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]

Note:
S will be a string with length at most 12.
S will consist only of letters or digits.

idea:
BFS permutation
*/

class LetterCasePermutation {
	public List<String> letterCasePermutation(String S) {
		LinkedList<String> queue = new LinkedList<String>();
        queue.add(S);
		if (S.length() == 0 || S == null) {
			return queue;
		}
		
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (Character.isLetter(c)) {
				int size = queue.size();
				for (int j = 0; j < size; j++) {
					String s = queue.poll();
					String lowercase = s.substring(0, i) + Character.toLowerCase(c) + s.substring(i + 1);
					String uppercase = s.substring(0, i) + Character.toUpperCase(c) + s.substring(i + 1);
					queue.add(lowercase);
					queue.add(uppercase);
				}
			}
		}

		return queue;
	}
}