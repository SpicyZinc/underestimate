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
method 1:
use two queues to traverse the tree, one stores the nodes, the other stores the step numbers. 
remember to remove found word in set


method 2:
queue offer() and poll()
	  
class Node to wrap each word in the hashset
l stands for transformation sequence length, start word is already 1

one place is not clear, does the hashset dictionary contain "start" and "end" two words?
it seems to contain "start" and "end" in the dict
in case there is no such two words in the hashset, individually create two "Node" class objects "from" "to"

Queue<Node> queue = new LinkedList<Node>(); FIFO
1. offer two nodes first: insert "start" and "end" two nodes into queue first
2. any intermediate words are added at the end of queue as node, peek at the first node of queue, poll() it
return when word.l != 0 (all intermediate words in hashset l is 0 in beginning)

use substring(0, i) substring(i+1) to realize one letter replace  

when string c meets "a string" which c can change to, and this string is inserted into the queue already,
by checking its length != 0 (length is figured out already)
meaning the transformation process is finished

absolute value of these two length is total length of words to convert transformation.
use +1 and -1 to indicate two directions, 
start --- a --- b --- end, it terminates when 'a' can be converted to 'b', and b is already in queue, length is figured out
*/

class Node { 
	String word;
	int l;
	boolean isVisited;
	public Node(String word, int l) {
		this.word = word;            
		this.l = l;
		isVisited = false;
	}
}

public class WordLadder {	
	// method 1, best version easy to understand, recently changed hashset to list
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

    // method 2
    public int ladderLength(String start, String end, HashSet<String> dict) {
		// it seems to contain "start" and "end" in the dict
		if (!dict.contains(start) || !dict.contains(end)) {
			return 0;
		}

		Map<String, Node> map = new HashMap<String, Node>();

		for (String w : dict) {
			Node n = null;
			if (w.equals(start)) {
				n = new Node(w, 1);
			}
			else if (w.equals(end)) {
				n = new Node(w, -1);
			}
			else {
				n = new Node(w, 0);
			}
			map.put(w, n);
		}
		/*
		These two comments area together also work	
		Node from = new Node(start, 1);
		map.put(start, from);
		Node to = new Node(end, -1);
		map.put(end, to);
		*/
		Queue<Node> queue = new LinkedList<Node>();
		Node n = map.get(start);
		queue.offer(n);
		n = map.get(end);
		queue.offer(n);
		
		// queue.offer(from);
		// queue.offer(to);
		
		while (queue.peek() != null) {
			Node c = queue.poll();
			if (c.isVisited) {
				continue;
			}
			c.isVisited = true;
			String w = c.word;
			for (int i = 0; i < w.length(); i++) {
				String f = w.substring(0, i);
				String e = w.substring(i + 1);
				for (int j = (int)'a'; j <= (int)'z'; j++) {
					String newS = f + (char)j + e;
					if (dict.contains(newS)) {
						Node node = map.get(newS);
						// before, not in queue
						if (node.l == 0) {
							if (c.l >= 0) 
								node.l = c.l + 1;
							else 
								node.l = c.l - 1;
							queue.offer(node);
						} else { // meets
							if ((node.l > 0 && c.l < 0) || (c.l > 0 && node.l < 0)) {
								return Math.abs(node.l) + Math.abs(c.l);
							}
						}
					}
				}
			}
		}

		return 0;
    }
}