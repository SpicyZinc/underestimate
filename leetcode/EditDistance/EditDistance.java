/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:
a) Insert a character
b) Delete a character
c) Replace a character

idea:
Edit Distance is also called levenshtein distance (Russian)
http://en.wikipedia.org/wiki/Levenshtein_distance
http://blog.unieagle.net/2012/09/19/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Aedit-distance%EF%BC%8C%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B9%8B%E9%97%B4%E7%9A%84%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB%EF%BC%8C%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/

dp[i][j] == the number of edits needed
to make word1[0, i - 1] (first i chars) equals word2[0, j - 1] (first j chars)

time cost O(mn)

2, Recursive with memoization
*/
public class EditDistance {
	public static void main(String[] args) {
		EditDistance eg = new EditDistance();
		String word1 = "sea";
		String word2 = "eat";
		System.out.println("Min edits of from " + word1 + " to " + word2 + " == " + eg.minDistance(word1, word2));
	}
	// 02/092019
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[][] dp = new int[m + 1][n + 1];
		// initialization
		dp[0][0] = 0;
		// first i chars in word1 to empty string needs i steps
		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		// first i chars in word2 to empty string needs i steps
		for (int i = 1; i <= n; i++) {
			dp[0][i] = i;
		}
		// rest
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				char c1 = word1.charAt(i - 1);
				char c2 = word2.charAt(j - 1);
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int insert = dp[i][j - 1] + 1;
					int remove = dp[i - 1][j] + 1;
					int replace = dp[i - 1][j - 1] + 1;

					dp[i][j] = Math.min(insert, Math.min(remove, replace));
				}
			}
		}

		return dp[m][n];
	}
    // 08/25/2018
    public int minDistance(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();
        
        int[][] dp = new int[size1 + 1][size2 + 1];
        // empty to empty string, 0 change
        dp[0][0] = 0;
        // i chars to empty string, i times of changes
        for (int i = 1; i <= size1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= size2; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1] + 1;
                    int remove = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    // min of insertion, deletion, replacement
                    dp[i][j] = Math.min(replace, Math.min(insert, remove));
                }
            }
        }
        
        return dp[size1][size2];
    }

    // recursive
    // 12/01/2018
    public int minDistance(String word1, String word2) {
        Map<String, Integer> editMemo = new HashMap<String, Integer>();
        return dfs(word1, 0, word2, 0, editMemo);
    }
    
    private int dfs(String word1, int i, String word2, int j, Map<String, Integer> editMemo) {
        String key = i + "-" + j;
        
        if (editMemo.containsKey(key)) {
            return editMemo.get(key);
        }
        
        int m = word1.length();
        int n = word2.length();
        
        int editions = 0;
        if (j == n && i < m) {
            editions = m - i;
        } else if (i == m && j < n) {
            editions = n - j;
        } else if (i < m && j < n) {
            if (word1.charAt(i) == word2.charAt(j)) {
                editions = dfs(word1, i + 1, word2, j + 1, editMemo);
            } else {
                int insert = dfs(word1, i + 1, word2, j, editMemo) + 1;
                int delete = dfs(word1, i, word2, j + 1, editMemo) + 1;
                int replace = dfs(word1, i + 1, word2, j + 1, editMemo) + 1;
                
                editions = Math.min(insert, Math.min(delete, replace));
            }
        }
        
        editMemo.put(key, editions);
        
        return editions;
    }
}