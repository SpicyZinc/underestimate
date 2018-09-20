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
    	children = new TrieNode[26];
    	for (int i = 0; i < 26; i++) {
    		children[i] = null;
    	}
    	hasWord = false;
    }
}

public class WordDictionary {
	private TrieNode root;
	public WordDictionary() {
		root = new TrieNode();
	}
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
    	for (int i = 0; i < word.length(); i++) {
    		char c = word.charAt(i);
    		if (node.children[c - 'a'] == null) {
    			node.children[c - 'a'] = new TrieNode();
    		}
    		// node point to next child
    		node = node.children[c - 'a'];
    	}
    	node.hasWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    
    // helper function for search()
    private boolean dfs(String word, int index, TrieNode node) {
    	if (index == word.length()) {
    		return node.hasWord;
    	}

    	char c = word.charAt(index);
    	if (c == '.') {
    		// test against all 26 children
    		for (int i = 0; i < 26; i++) {
    			if (node.children[i] != null) {
	    			if (dfs(word, index + 1, node.children[i])) {
	    				return true;
	    			}
    			}
    		}
    		return false;
    	} else if (node.children[c - 'a'] != null) { // only this one
    		return dfs(word, index + 1, node.children[c - 'a']);
    	} else {
    		return false;
    	}
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");