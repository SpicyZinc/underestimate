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
        TrieNode now = root;
    	for (int i = 0; i < word.length(); i++) {
    		char ch = word.charAt(i);
    		if (now.children[ch-'a'] == null) {
    			now.children[ch-'a'] = new TrieNode();
    		}
    		// now point to next child
    		now = now.children[ch-'a'];
    	}
    	now.hasWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return find(word, 0, root);
    }
    
    // helper function for search()
    private boolean find(String word, int index, TrieNode now) {
    	if (index == word.length()) {
    		return now.hasWord;
    	}
    	char c = word.charAt(index);
    	if (c == '.') {
    		// all children
    		for (int i = 0; i < 26; i++) {
    			if (now.children[i] != null) {
	    			if (find(word, index + 1, now.children[i])) {
	    				return true;
	    			}
    			}
    		}
    		return false;
    	}
    	else if (now.children[c - 'a'] != null) { // only this one
    		return find(word, index + 1, now.children[c - 'a']);
    	}
    	else {
    		return false;
    	}
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");