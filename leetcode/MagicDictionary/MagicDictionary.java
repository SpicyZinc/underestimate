/*
Implement a magic directory with buildDict, and search methods.
For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word,
the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False

Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases.

idea:
HashMap<String, List<int[]>>
key is word with removed char at some position
value is a list of two-element arrays, in an array, position 0 is index, position 1 is removed char - 'a'
condition is removed char's index should be the same, but value must not be the same
*/

class MagicDictionary {
	Map<String, List<int[]>> hm;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        hm = new HashMap<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
        	int len = word.length();
        	for (int i = 0; i < len; i++) {
	        	String key = word.substring(0, i) + word.substring(i + 1);
	        	int[] positions = new int[] {i, word.charAt(i) - 'a'};
	        	List<int[]> list = hm.getOrDefault(key, new ArrayList<int[]>());
				list.add(positions);

				hm.put(key, list);
        	}
		}
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
		for (int i = 0; i < word.length(); i++) {
			String key = word.substring(0, i) + word.substring(i + 1);
			if (hm.containsKey(key)) {
				List<int[]> positions = hm.get(key);
				for (int j = 0; j < positions.size(); j++) {
					int[] position = positions.get(j);
					// note, this char needs to be different from original char because this is problem requires, modified
					if (position[0] == i && word.charAt(i) - 'a' != position[1]) {
						return true;
					}
				}
			}
		}

		return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */