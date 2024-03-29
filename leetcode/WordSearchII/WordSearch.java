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
use Trie structure to store dictionary words, and implement insert(), boolean search() (ifExist()), startsWith()
then in findWords() use dfs() to return all words in a hashset to avoid duplicates

note: first insert word to trie, then you can search some word in the trie 
when and where to set visited[i][j] = true

trie那条树干 而不是 枝叶 代表 char

*/

class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        this.children = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            this.children[i] = null;
        }

        this.isWord = false;
    }
}


class Trie {
    TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    // populate Trie by inserting word
    public void insert(String word) {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        
        node.isWord = true;
    }
    // search trie for a specific word
    public boolean search(String word) {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        
        return node.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        // till here, prefix is through
        return true;
    }
}

class WordSearch {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (words.length == 0 || words == null) {
            return result;
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Trie trie = new Trie();
        // populate the Trie for future search
        for (String word : words) {
            trie.insert(word);
        }
        // populate the result
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, trie, visited, "", result);
            }
        }
        
        return result;
    }
    
    public void dfs(char[][] board, int x, int y, Trie trie, boolean[][] visited, String path, List<String> result) {
        int m = board.length;
        int n = board[0].length;
        int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
        };
        
        if (x >= m || x < 0 || y >= n || y < 0) {
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
            // avoid duplicates or use hashset
            if (!result.contains(path)) {
                result.add(path);
            }
        }

        visited[x][y] = true;
        for (int[] dir : directions) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            dfs(board, nextX, nextY, trie, visited, path, result);
        }
        visited[x][y] = false;
    }
}