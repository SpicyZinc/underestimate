/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Notice
All words have the same length.
All words contain only lowercase alphabetic characters.

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
[
	["hit","hot","dot","dog","cog"],
	["hit","hot","lot","log","cog"]
]

idea:
this is lintcode version
*/

public class WordLadder {
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

	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(start);

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
                    // note, this is different from leetcode version
                    if (newWord.equals(end)) {
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
}