/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or ..
A . means it can represent any one letter.

For example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.

idea:
http://www.jiuzhang.com/solutions/add-and-search-word/
use Trie data structure
a little change:
because '.' can be any character, so once '.', need to check all children (by all children, it means 26 letters),
as long as there is one returning true, all search function return true
*/

class TrieNode {
	TrieNode[] children;
	boolean hasWord;

	public TrieNode() {
		this.children = new TrieNode[26];
		for (int i = 0; i < 26; i++) {
			this.children[i] = null;
		}
		this.hasWord = false;
	}
}

// Thu Jul  4 21:50:28 2019
public class WordDictionary {
	private TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	public void addWord(String word) {
		TrieNode node = root;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c - 'a'] == null) {
				node.children[c - 'a'] = new TrieNode();
			}
			node = node.children[c - 'a'];
		}
		node.hasWord = true;
	}

	public boolean search(String word) {
		return dfs(word, 0, root);
	}

	// 不为null是继续往下找的关键
	public boolean dfs(String word, int idx, TrieNode node) {
		if (idx == word.length()) {
			return node.hasWord;
		}

		char c = word.charAt(idx);
		if (c == '.') {
			// test against all 26 children
			for (int i = 0; i < 26; i++) {
				if (node.children[i] != null) {
					if (dfs(word, idx + 1, node.children[i])) {
						return true;
					}
				}
			}

			return false;
		} else if (node.children[c - 'a'] != null) {
			return dfs(word, idx + 1, node.children[c - 'a']);
		} else {
			return false;
		}
	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");