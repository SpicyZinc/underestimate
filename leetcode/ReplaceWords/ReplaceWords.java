/*
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor.
For example, the root an, followed by other, which can form another word another.
Now, given a dictionary consisting of many roots and a sentence.
You need to replace all the successor in the sentence with the root forming it.
If a successor has many roots can form it, replace it with the root with the shortest length.
You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
 
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

idea:
1. see description, directly find prefix, as soon as in roots, break early
2. Trie
*/

class Solution {
	class TrieNode {
		char c;
		TrieNode[] children;
		boolean isWord;

		public TrieNode() {
			this.children = new TrieNode[26];
			this.isWord = false;
		}
	}

	public String replaceWords(List<String> roots, String sentence) {
		// at first, build trie based on roots
		TrieNode root = new TrieNode();

		for (String word : roots) {
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
	
		// replace words with root
		StringBuilder sb = new StringBuilder();

		for (String word : sentence.split("\\s+")) {
			TrieNode node = root;

			if (sb.length() > 0) {
				sb.append(" ");
			}

			StringBuilder rootWord = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				// 用光了root 但是没有run out of word
				if (node.children[c - 'a'] == null || node.isWord) {
					break;
				}
				rootWord.append(c);

				node = node.children[c - 'a'];
			}

			sb.append(node.isWord ? rootWord.toString() : word);
		}

		return sb.toString();
	}

    public String replaceWords(List<String> roots, String sentence) {
        Set<String> hs = new HashSet();
        for (String root: roots) {
        	hs.add(root);
        }

        StringBuilder sb = new StringBuilder();
        for (String word: sentence.split("\\s+")) {
            String prefix = "";
            // generate the shortest possible root
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
				// replace it with the root with the shortest length.
                if (hs.contains(prefix)) {
                	break;
                }
            }

            sb.append(sb.length() > 0 ? " " + prefix : prefix);
        }

        return sb.toString();
    }
}