/*
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words.
If there is more than one possible answer, return the longest word with the smallest lexicographical order.
If there is no answer, return the empty string.

Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".

Note:
All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

idea:
simple, but I did not think of
first sort array of strings
Set<String> builtPath to keep record of ALL possible words to the longest
*/

class LongestWordInDictionary {
	// Sun Jun 16 03:17:12 2019
	public String longestWord(String[] words) {
		Arrays.sort(words, (a, b) -> a.compareTo(b));

		String longest = "";
		
		Set<String> buildPath = new HashSet<>();
		
		for (int i = 0; i < words.length; i++) {
			String curr = words[i];
			String prev = curr.substring(0, curr.length() - 1);
			
			if (curr.length() == 1 || buildPath.contains(prev)) {
				buildPath.add(curr);
				longest = curr.length() > longest.length() ? curr : longest;
			}
		}

		return longest;
	}

	public String longestWord(String[] words) {
		String longest = "";
		Arrays.sort(words);

		// all words path to the longest
		Set<String> builtPath = new HashSet<String>();

		for (String word : words) {
			String prev = word.substring(0, word.length() - 1);
			if (word.length() == 1 || builtPath.contains(prev)) {
				builtPath.add(word);
				// once update, next word, same length, would NOT update, but it will update the longest, we want alphabetically smaller one
				longest = word.length() > longest.length() ? word : longest;
			}
		}

		return longest;
	}
}