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
*/

class TrieNode {
    char val;
    TrieNode[] children;
    boolean isEnd;
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
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
                TrieNode temp = new TrieNode();
                temp.val = c;
                node.children[pos] = temp;
            }
            node = node.children[pos];
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0 || word == null) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (node.children[pos] != null) {
                node = node.children[pos];
            } else {
                return false;
            }
        }

        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0 || prefix == null) {
            return false;
        }

        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int pos = c - 'a';
            if (node.children[pos] != null) {
                node = node.children[pos];
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("something");
        trie.insert("sometime");
        trie.insert("someday");
        boolean x = trie.search("key");       // false
        boolean y = trie.search("something"); // true
        boolean z = trie.search("some");      // false
        boolean m = trie.startsWith("somet"); // true
        boolean n = trie.startsWith("some");  // true
        System.out.println(x + "  " + y + "  " + z + "  " + m + "  " + n );
    }
}


// class TrieNode {
//     char c;
//     HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
//     boolean isLeaf;
 
//     public TrieNode() {}
 
//     public TrieNode(char c) {
//         this.c = c;
//     }
// }
// public class Trie {
//     private TrieNode root;
//     public Trie() {
//         root = new TrieNode();
//     }
//     // Inserts a word into the trie.
//     public void insert(String word) {
//         HashMap<Character, TrieNode> children = root.children;
//         for (int i = 0; i < word.length(); i++) {
//             char c = word.charAt(i);
//             TrieNode t;
//             if (children.containsKey(c)) {
//                 t = children.get(c);
//             }
//             else {
//                 t = new TrieNode(c);
//                 children.put(c, t);
//             }
//             children = t.children;
//             // set leaf node
//             if (i == word.length()-1) {
//                 t.isLeaf = true;    
//             }
//         }
//     }
//     // Returns if the word is in the trie.
//     public boolean search(String word) {
//         TrieNode t = searchNode(word);
//         if (t != null && t.isLeaf) {
//             return true;
//         }
//         else {
//             return false;
//         }
//     }
 
//     // Returns if there is any word in the trie that starts with the given prefix.
//     public boolean startsWith(String prefix) {
//         if (searchNode(prefix) == null) {
//             return false;
//         }
//         else {
//             return true;
//         }
//     }
//     // helper method search node
//     public TrieNode searchNode(String str) {
//         Map<Character, TrieNode> children = root.children; 
//         TrieNode t = null;
//         for (int i = 0; i < str.length(); i++) {
//             char c = str.charAt(i);
//             if (children.containsKey(c)) {
//                 t = children.get(c);
//                 children = t.children;
//             }
//             else {
//                 return null;
//             }
//         }
 
//         return t;
//     }
// }