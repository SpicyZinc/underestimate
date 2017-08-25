/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord,
such that:
Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
Return [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
]

Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

idea:
http://standalone.iteye.com/blog/1847367

this answer passed 13/39
impossible to ask in interview
*/

import java.util.*;

public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII eg = new WordLadderII();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        List<List<String>> result = eg.findLadders(beginWord, endWord, wordList);
        for (List<String> path : result) {
            System.out.println(path);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord.length() == 0 || beginWord == null || endWord.length() == 0 || endWord == null) {
            return res;
        }
        List<String> path = new ArrayList<String>();
        // if beginWord equals endWord, directly return
        if (beginWord.equals(endWord)) {
            path.add(beginWord);
            path.add(endWord);
            res.add(path);
            return res;
        }

        Set<String> dict = new HashSet<String>();
        for (String word : wordList) {
            dict.add(word);
        }
        
        // build a map for all pre strings to each word in the word list
        Map<String, ArrayList<String>> preWords = new HashMap<String, ArrayList<String>>();
        preWords.put(beginWord, new ArrayList<String>());
        preWords.put(endWord, new ArrayList<String>());
        for (String s : wordList) {
            preWords.put(s, new ArrayList<String>());
        }
        
        // BFS, once found the end, end while(), this way makes sure shortest
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        
        while (!queue.isEmpty()) {
            // currentleve to hold all strings in queue
            List<String> currentLevel = new ArrayList<String>();
            for (int i = 0; i < queue.size(); i++) {
                String word = queue.poll();
                if (wordList.contains(word)) {
                    wordList.remove(word);
                }
                currentLevel.add(word);
            }
            // loop through all words in currentLevel
            for (String curWord : currentLevel) {
                for (int i = 0; i < curWord.length(); i++) {
                    char[] charArray = curWord.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        charArray[i] = j;
                        String newWord = new String(charArray);
                        if (!curWord.equals(beginWord) && newWord.equals(endWord)) {
                            queue.offer(newWord);
                            preWords.get(endWord).add(curWord);
                        }
                        else if (!curWord.equals(newWord) && wordList.contains(newWord)) {
                            if (!queue.contains(newWord)) {
                                queue.offer(newWord);
                            }
                            preWords.get(newWord).add(curWord);
                        }
                    }    
                }
            }
            if (queue.contains(endWord)) {
                break;
            }
        }
        path.add(endWord);
        buildPath(beginWord, endWord, preWords, path, res);

        return res;
    }
    // based on pre words of one word, generate path
    public void buildPath(String beginWord, String endWord, Map<String, ArrayList<String>> preWords, List<String> path, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            List<String> onePath = new ArrayList<String>(path);
            Collections.reverse(onePath);
            res.add(onePath);
            return;
        }
        List<String> pre = preWords.get(endWord);
        for (String s : pre) {
            path.add(s);
            buildPath(beginWord, s, preWords, path, res);
            path.remove(path.size() - 1);
        }
    }
}