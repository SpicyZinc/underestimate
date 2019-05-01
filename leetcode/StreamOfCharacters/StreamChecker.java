/*
Implement the StreamChecker class as follows:
StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1,
the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.

Example:
StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist

Note:
1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.

idea:
reverse trie
从后面 i = sb.length() - 1
*/

class TrieNode {
	boolean isWord;
	TrieNode[] children;

	public TrieNode() {
		this.isWord = false;
		this.children = new TrieNode[26];
	}
}

class StreamChecker {
	TrieNode root;
	StringBuilder sb;

	public StreamChecker(String[] words) {
		this.root = createTrie(words);
		this.sb = new StringBuilder();
	}

	public boolean query(char letter) {
		sb.append(letter);

		// check if exist in trie from the behind
		TrieNode node = root;
		for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
			char c = sb.charAt(i);
			node = node.children[c - 'a'];
			if (node != null && node.isWord) {
				return true;
			}
		}

		return false;
	}

	public TrieNode createTrie(String[] words) {
		TrieNode root = new TrieNode();

		for (String word : words) {
			TrieNode node = root;
			for (int i = word.length() - 1; i >= 0; i--) {
				char c = word.charAt(i);

				if (node.children[c - 'a'] == null) {
					node.children[c - 'a'] = new TrieNode();
				}
				node = node.children[c - 'a'];
			}
			node.isWord = true;
		}

		return root;
	}
}