/*
Given two words (start and end), and a dictionary, 
find the length of shortest transformation sequence from start to end, such that:
Only one letter can be changed at a time, each intermediate word must exist in the dictionary

For example,
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]

As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

idea:
set to save initial word
从这个word出发 用26个字母替换每个位置 如果reached word 在 dict中
距离+1 
外层是 while 只到 wordsReached 为空

https://www.cnblogs.com/grandyang/p/4539768.html
*/

public class WordLadder {
    // 2025
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> hs = new HashSet<>(wordList);
        if (!hs.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char origin = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String nextWord = new String(chars);
                        if (nextWord.equals(endWord)) {
                            return steps + 1;
                        }
                        if (hs.contains(nextWord)) {
                            queue.offer(nextWord);
                            hs.remove(nextWord);
                        }
                    }

                    chars[j] = origin;
                }
            }

            steps++;
        }

        return 0;
    }
    // 03/11/2019
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // use set to save time when looking up
        Set<String> hs = new HashSet<>();
        for (String word : wordList) {
            hs.add(word);
        }

        Set<String> wordsReached = new HashSet<>();
        wordsReached.add(beginWord);
        int distance = 1;

        while (!wordsReached.contains(endWord)) {
            Set<String> wordsToReach = new HashSet<>();

            for (String wordReached : wordsReached) {
                for (int i = 0; i < wordReached.length(); i++) {
                    char ch = wordReached.charAt(i);
                    for (char c = 'a'; c <= 'z'; c++) {
                        String possibleWord = wordReached.substring(0, i) + c + wordReached.substring(i + 1);

                        if (hs.contains(possibleWord)) {
                            wordsToReach.add(possibleWord);
                            hs.remove(possibleWord);
                        }
                    }
                }
            }
            // if no wordsToReach generated, meaning no word ladder path found
            if (wordsToReach.size() == 0) {
                return 0;
            }

            wordsReached = wordsToReach;
            distance += 1;
        }

        return distance;
    }
    // 03/10/2019
    // 01/10/2019
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> hs = new HashSet<>();
        for (String word : wordList) {
            hs.add(word);
        }

        Set<String> wordsReached = new HashSet<>();
        wordsReached.add(beginWord);
        int distance = 1;

        while (!wordsReached.contains(endWord)) {
            Set<String> wordsToReach = new HashSet<>();

            for (String wordReached : wordsReached) {
                for (int i = 0; i < wordReached.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String next = wordReached.substring(0, i) + c + wordReached.substring(i + 1);
                        if (hs.contains(next)) {
                            wordsToReach.add(next);
                            hs.remove(next);
                        }
                    }
                }
            }
            if (wordsToReach.size() == 0) {
                return 0;
            }

            distance++;
            wordsReached = wordsToReach;
        }

        return distance;
    }

    // method 1, easy to understand
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<String>();
        for (String word : wordList) {
            dict.add(word);
        }

        Set<String> wordsReached = new HashSet<String>();
        // start not in dict, need to have some initial point to start BFS
        wordsReached.add(beginWord);

        int distance = 1;
        while (!wordsReached.contains(endWord)) {
            Set<String> wordsToReach = new HashSet<String>();
            for (String wordReached : wordsReached) {
                for (int i = 0; i < wordReached.length(); i++) {
                    // where to toCharArray is crucial
                    // 每个位置有26个可能性
                    char[] chars = wordReached.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String next = new String(chars);
                        if (dict.contains(next)) {
                            wordsToReach.add(next);
                            dict.remove(next);
                        }
                    }
                }
            }
            // no new words can be reached, early return
            if (wordsToReach.size() == 0) {
                return 0;
            }

            distance++;
            wordsReached = wordsToReach;
        }
        
        return distance;
    }
}
