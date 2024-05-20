/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
int countWordsEqualTo(String word) Returns the number of instances of the string word in the trie.
int countWordsStartingWith(String prefix) Returns the number of strings in the trie that have the string prefix as a prefix.
void erase(String word) Erases the string word from the trie.

Example 1:
Input
["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
[[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
Output
[null, null, null, 2, 2, null, 1, 1, null, 0]

Explanation
Trie trie = new Trie();
trie.insert("apple");               // Inserts "apple".
trie.insert("apple");               // Inserts another "apple".
trie.countWordsEqualTo("apple");    // There are two instances of "apple" so return 2.
trie.countWordsStartingWith("app"); // "app" is a prefix of "apple" so return 2.
trie.erase("apple");                // Erases one "apple".
trie.countWordsEqualTo("apple");    // Now there is only one instance of "apple" so return 1.
trie.countWordsStartingWith("app"); // return 1
trie.erase("apple");                // Erases "apple". Now the trie is empty.
trie.countWordsStartingWith("app"); // return 0

Constraints:
1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, countWordsEqualTo, countWordsStartingWith, and erase.
It is guaranteed that for any function call to erase, the string word will exist in the trie.

idea:
just similar to I, but have to add two count
endCount
soFarCount to this end
*/

class TrieNode {
    char c;
    TrieNode[] children;
    // to this Node, how many words or prefix
    int count;
    // how many words ending at this node
    int endCount;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.count = 0;
        this.endCount = 0;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();        
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (current.children[pos] == null) {
                current.children[pos] = new TrieNode();
            }
            current = current.children[pos];
            current.count++;
        }
        current.endCount++;

        return;
    }
    
    public int countWordsEqualTo(String word) {
        TrieNode node = findInTrie(word);
        return node == null ? 0 : node.endCount;
    }
    
    public int countWordsStartingWith(String prefix) {
        TrieNode node = findInTrie(prefix);
        return node == null ? 0 : node.count;   
    }
    // erase cannot use the common function because it has to decrease both count and endCount
    public void erase(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            node = node.children[pos];
            node.count--;
        }
        node.endCount--;
    }

    // Helper to go deep in the trie
    private TrieNode findInTrie(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            if (node.children[pos] == null) {
                return null;
            }

            node = node.children[pos];
        }

        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */