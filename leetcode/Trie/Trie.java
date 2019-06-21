/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

idea:
TrieNode root has no value
go deep to find a complete word
trie structure is to find some common prefix

http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
http://www.cnblogs.com/tonyluis/p/4563990.html

也可以用 HashMap as children

class TrieNode {
	char c;
	Map<Character, TrieNode> children = new HashMap<>();
	boolean isLeaf;

	public TrieNode(char c) {
		this.c = c;
	}
}

https://www.geeksforgeeks.org/auto-complete-feature-using-trie/
*/

import java.util.*;

class TrieNode {
	char c;
	TrieNode[] children;
	boolean isLeaf;

	public TrieNode() {
		this.children = new TrieNode[26];
		this.isLeaf = false;
	}

	public TrieNode(char c) {
		this.c = c;
		this.children = new TrieNode[26];
		this.isLeaf = false;
	}
}

public class Trie {
	TrieNode root;
	/** Initialize your data structure here. */
	public Trie() {
		root = new TrieNode();
	}
	
	/** Inserts a word into the trie. */
	public void insert(String word) {
		if (word.length() == 0 || word == null) {
			return;
		}

		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int pos = c - 'a';

			if (node.children[pos] == null) {
				node.children[pos] = new TrieNode(c);
			}
			node = node.children[pos];
		}
		node.isLeaf = true;
	}
	
	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode node = deepInTrie(word);
		return node == null ? false : node.isLeaf;
	}
	
	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		TrieNode node = deepInTrie(prefix);
		return node != null;
	}

	// helper to go deep in the trie
	private TrieNode deepInTrie(String s) {
		TrieNode node = root;
		for (int i = 0; i < s.length(); i++) {
			int pos = s.charAt(i) - 'a';
			if (node.children[pos] == null) return null;
			node = node.children[pos];
		}

		return node;
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("something");
		trie.insert("sometime");
		trie.insert("someday");
		trie.insert("som");

		boolean x = trie.search("key");       // false
		boolean y = trie.search("something"); // true
		boolean z = trie.search("some");      // false
		boolean m = trie.startsWith("somet"); // true
		boolean n = trie.startsWith("some");  // true
		System.out.println(x + "  " + y + "  " + z + "  " + m + "  " + n );

		trie.printSuggestions(trie, "some");
	}

	// boolean to check if this is last node
	public boolean isLastNode(TrieNode node) {
		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				return false;
			}
		}

		return true;
	}

	// print all matches for a query
	public void autoSuggest(TrieNode node, String prefix, List<String> suggestions) {
		// reach a complete word
		if (node.isLeaf) {
			suggestions.add(prefix);
		}
		if (isLastNode(node)) {
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				String possibleWord = prefix + (char) (i + 'a');
				autoSuggest(node.children[i], possibleWord, suggestions);
			}
		}
	}

	public void printSuggestions(Trie trie, String query) {
		// if query is exactly
		if (trie.search(query)) {
			System.out.println(query);
		} else {
			List<String> suggestions = new ArrayList<>();
			TrieNode root = trie.root;
			autoSuggest(root, query, suggestions);
			System.out.println(suggestions);
		}
	}
}