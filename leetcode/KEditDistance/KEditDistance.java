/*
Given a set of strings which just has lower case letters and a target string,
output all the strings for each the edit distance with the target no greater than k.

You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example
Given words = ["abc", "abd", "abcd", "adc"] and target = "ac", k = 1
Return ["abc", "adc"]

idea:
https://massivealgorithms.blogspot.com/2015/11/buttercola-airbnb-k-edit-distance.html

dp[i][j] 代表着 word1[0:i]到word2[0:j]的编辑距离,
并且很容易知道这样的转移方程：
if (word1.charAt(i - 1) == word2.char(j - 1)) {
dp[i][j] = dp[i - 1][j - 1]; // 字符相同,
不需要编辑
} else {
dp[i][j] = 1 + Math.min(dp[i - 1][j - 1]
, Math.min(dp[i][j - 1], dp[i - 1][j]));
// 字符不一,
那么存在insertion或subsitution,

// 意味着编辑距离为之前最小可能的编辑距离+1	
}
捋清了这个,
再来理解dp和next就很容易：
我们注意到上面的转移方程任意时候都只需要至多前一步的信息,
所以我们只需要两个数组,
一个标记前一步的情形,
一个标记现在的情形 i.e. 用dp[j] 标记 以前的dp[i - 1][j], 以next[j] 代表以前的dp[i][j].
那么也必然有：
if (word1.charAt(i - 1) == word2.char(j - 1)) {
next[j] = dp[j - 1]; // 老的dp[i][j] => next[j],
//老的dp[i - 1][j - 1] => dp[j - 1]
} else {
next[j]= 1 + Math.min(dp[j - 1],
Math.min(next[j - 1], dp[j]));
// 老的dp[i][j-1] => next[j - 1],
//老的dp[i - 1][j] => dp[j]
}

need go back again
*/

class TrieNode {
	public TrieNode[] children;
	public boolean isWord;
	public String word;

	public TrieNode() {
		children = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			children[i] = null;
		}

		isWord = false;
	}

	public void insert(String word, TrieNode root) {
		TrieNode node = root;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNode();
			}
			node = node.children[c - 'a'];
		}

		node.word = word;
		node.isWord = true;
	}
}

public class KEditDistance {
	public List<String> kDistance(String[] words, String target, int k) {
		List<String> result = new ArrayList<>();
		
		TrieNode root = new TrieNode();
		for (String word : words) {
			root.insert(word, root);
		}
		
		int n = target.length();
		// dp[i] refers to min edit distance between prefix from root to current trie node
		// and first i chars in target
		int[] dp = new int[n + 1];
		// initialization
		for (int i = 0; i <= n; i++) {
			dp[i] = i;
		}
		
		dfs(root, dp, target, k, result);

		return result;
	}

	public void dfs(TrieNode node, int[] dp, String target, int k, List<String> result) {
		int n = target.length();

		if (node.isWord && dp[n] <= k) {
			result.add(node.word);
		}

		int[] next = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			next[i] = 0;
		}

		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				next[0] = dp[0] + 1;
				for (int j = 1; j <= n; j++) {
					if (target.charAt(j - 1) - 'a' == i) {
						next[j] = Math.min(dp[j - 1], Math.min(next[j - 1] + 1, dp[j] + 1));
					} else {
						next[j] = Math.min(dp[j - 1], Math.min(next[j - 1], dp[j])) + 1; 
					}
				}
				dfs(node.children[i], next, target, k, result);
			}
		}
	}
}

