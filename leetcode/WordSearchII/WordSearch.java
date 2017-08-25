/* 
Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z.

idea:
use Trie structure to store words, and implement insert(), boolean search(), startsWith()
then in findWords() use dfs() to return all words in a hashset to avoid duplicates

note: insert word to trie first, then you can search or if exist, 
do not mix this insert with the final "add" to result list

note: when and where to set visited[i][j] = true
*/

public class WordSearch {
    Set<String> result = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        
        return new ArrayList<String>(result);        
    }
    
    public void dfs(char[][] board, boolean[][] visited, String s, int i, int j, Trie trie) {
        int m = board.length;
        int n = board[0].length;
        
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        s += board[i][j];
        if (!trie.startsWith(s)) {
            return;
        }
        if (trie.ifExist(s)) {
            result.add(s);
        }
        visited[i][j] = true;
        dfs(board, visited, s, i-1, j, trie);
        dfs(board, visited, s, i, j-1, trie);
        dfs(board, visited, s, i+1, j, trie);
        dfs(board, visited, s, i, j+1, trie);
        visited[i][j] = false;
    }
}

class TrieNode {
    public TrieNode[] children;
    public String item;
    
    public TrieNode() {
        children = new TrieNode[26];
        item = "";
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode now = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (now.children[ch - 'a'] == null) {
                now.children[ch - 'a'] = new TrieNode();
            }
            now = now.children[ch - 'a'];
        }
        now.item = word;
    }
    
    public boolean ifExist(String word) {
        TrieNode now = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (now.children[ch - 'a'] == null) {
                return false;
            }
            now = now.children[ch - 'a'];
        }
        return now.item.equals(word);
    }
    
    public boolean startsWith(String prefix) {
        TrieNode now = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (now.children[ch - 'a'] == null) {
                return false;
            }
            now = now.children[ch - 'a'];
        }
        return true;
    }
}

// self written, note: when and where to set visited[i][j] = true
class WordSearch {
    public List<String> findWords(char[][] board, String[] words) {
        // populate the Trie for future search
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        Set<String> hs = new HashSet<String>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, visited, trie, "", hs);
            }
        }
        
        return new ArrayList<String>(hs);
    }
    
    public void dfs(char[][] board, int x, int y, boolean[][] visited, Trie trie, String path, Set<String> hs) {
        int m = board.length;
        int n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        path += board[x][y];
        if (!trie.startsWith(path)) {
            return;
        }
        if (trie.search(path)) {
            hs.add(path);
        }
        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
        };
        visited[x][y] = true;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            dfs(board, newX, newY, visited, trie, path, hs);
        }
        visited[x][y] = false;
    }
}

class TrieNode {
    String word;
    TrieNode[] children;
    public TrieNode() {
        this.word = "";
        this.children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        if (word.length() == 0 || word == null) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (node.children[pos] == null) {
                node.children[pos] = new TrieNode();
            }
            node = node.children[pos];
            if (i == word.length() - 1) {
                node.word = word;
            }
        }
    }
    
    public boolean search(String word) {
        if (word.length() == 0 || word == null) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (node.children[pos] == null) {
                return false;        
            }
            node = node.children[pos];
        }
        return node.word.equals(word);
    }
    
    public boolean startsWith(String s) {
        if (s.length() == 0 || s == null) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int pos = c - 'a';
            if (node.children[pos] == null) {
                return false;
            }
            node = node.children[pos];
        }
        // till here, if word is through, it should be startsWith()
        return true;
    }
}