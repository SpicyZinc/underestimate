/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Note: Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

idea:
this is lintcode version, different from leetcode version.
start, end no need to be in the dict

要么是提前加入 dict.add(end);
要么是
if (next.equals(end)) {
    return distance + 1;
}
*/

public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        // dict.add(end);

        Set<String> wordsReached = new HashSet<String>();
        // start not in dict, need to have some initial point to start BFS
        wordsReached.add(start);

        int distance = 1;
        while (!wordsReached.contains(end)) {
            Set<String> wordsToReach = new HashSet<String>();
            for (String reachedWord : wordsReached) {
                for (int i = 0; i < reachedWord.length(); i++) {
                    // where to toCharArray is crucial
                    // 每个位置有26个可能性
                    char[] chars = reachedWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String next = new String(chars);
                        // handle lintcode version
                        if (next.equals(end)) {
                            return distance + 1;
                        }
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