/*
Edit Distance
Recursive with memoization
idea:
*/
import java.util.*;

public class EditDistanceRecursive {
	public int minDistance(String word1, String word2) {
		private Map<String, Integer> editMemo = new HashMap<String, Integer>();
		return helper(word1, 0, word2, 0, editMemo);
	}

	private int helper(String word1, int i, String word2, int j, Map<String, Integer> editMemo) {  
		String key = Integer.toString(i) + "," + Integer.toString(j);
		if (editMemo.containsKey(key)) {
			return editMemo.get(key);
		}

		int edit = 0;
		if (j == word2.length() && i < word1.length()) {
			edit = word1.length() - i;
		} else if (i == word1.length() && j < word2.length()) {
			edit = word2.length() - j;
		} else if (i < word1.length() && j < word2.length()) {
			if (word1.charAt(i) == word2.charAt(j)) {
				edit = helper(word1, i + 1, word2, j + 1, editMemo);
			} else {
				// insert
				int if_insert = helper(word1, i, word2, j + 1, editMemo) + 1;
				// replace
				int if_replace = helper(word1, i + 1, word2, j + 1, editMemo) + 1;
				// delete
				int if_delete = helper(word1, i + 1, word2, j, editMemo) + 1;
				edit = Math.min(if_delete, Math.min(if_insert, if_replace));
			}
		}
		editMemo.put(key, edit);
		
		return edit;
	}
}
