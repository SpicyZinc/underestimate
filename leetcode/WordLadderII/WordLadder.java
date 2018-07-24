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
Rung class with prev Rung pointer
minLevel是记录最短路径的长度, 
这样的好处是, 
如果某条路径的长度超过了已有的最短路径的长度, 
那么舍弃, 
这样会提高运行速度, 
相当于一种 prune.
*/

import java.util.*;

public class WordLadder {
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

    // latest best method
    class Rung {
        String word;
        int level;
        Rung previous;

        public Rung(String word, int level) {
            this.word = word;
            this.level = level;
            this.previous = null;
        }
    }

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        // move words into hashset
        Set<String> dict = new HashSet<String>();
        dict.add(start);
        for (String word : wordList) {
            dict.add(word);
        }

        List<List<String>> result = new ArrayList<List<String>>();        
        List<Rung> ladderPath = new ArrayList<Rung>();

        Queue<Rung> queue = new LinkedList<Rung>();
        queue.add(new Rung(start, 0));
        int minLevel = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Rung curr = queue.poll();
            if (curr.level > minLevel) {
                break;
            }

            dict.remove(curr.word);
            char[] chars = curr.word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char origin = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    // found one path
                    // note, this is different from lintcode version
                    if (newWord.equals(end) && dict.contains(newWord)) {
                        minLevel = curr.level;
                        Rung ladderPathEnd = new Rung(newWord, curr.level + 1);
                        ladderPathEnd.previous = curr;
                        ladderPath.add(ladderPathEnd);
                        break;
                    } else if (dict.contains(newWord)) {
                        Rung next = new Rung(newWord, curr.level + 1);
                        next.previous = curr;
                        queue.add(next);
                    }
                }
                chars[i] = origin;
            }
        }
        
        for (Rung rung : ladderPath) {
            List<String> list = new ArrayList<String>();
            while (rung != null) {
                list.add(0, rung.word);
                rung = rung.previous;
            }

            result.add(list);
        }
        
        return result;
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
        // move words into hashset
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
            // currentLevel to hold all strings in queue
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
                        } else if (!curWord.equals(newWord) && wordList.contains(newWord)) {
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