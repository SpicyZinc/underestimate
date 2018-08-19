/*
We are given two sentences A and B. 
(A sentence is a string of space separated words. Each word consists only of lowercase letters.)
A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words.
You may return the list in any order.

Example 1:
Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]

Example 2:
Input: A = "apple apple", B = "banana"
Output: ["banana"]
 
Note:
0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.

idea:
hashmap for two sentences, those words which appeared only once will be the answer
*/

class UncommonWordsFromTwoSentences {
	public String[] uncommonFromSentences(String A, String B) {
		Map<String, Integer> hm = new HashMap<String, Integer>();
		
		String[] wordsA = A.split("\\s+");
		String[] wordsB = B.split("\\s+");

		for (String word : wordsA) {
			hm.put(word, hm.getOrDefault(word, 0) + 1);
		}

		for (String word : wordsB) {
			hm.put(word, hm.getOrDefault(word, 0) + 1);
		}

		List<String> result = new ArrayList<>();
		for (String word : hm.keySet()) {
			if (hm.get(word) == 1) {
				result.add(word);
			}
		}

		return result.toArray(new String[result.size()]);
	}

	// my original method failed
	public String[] uncommonFromSentences(String A, String B) {
		int size = A.length();

		List<String> list = new ArrayList<String>();

		int i = 0;
		int start = 0;

		while (i < size) {
			if (A.charAt(i) == ' ') {
				while (A.charAt(i) == ' ') {
					i++;
				}
				start = i;
			} else {
				while (i < size && Character.isLetter(A.charAt(i))) {
					i++;
				}
				list.add(A.substring(start, i));
			}
		}

		List<String> result = new ArrayList<String>();
		String[] words = B.split("\\s+");
		for (String word : words) {
			if (!list.contains(word)) {
				result.add(word);
			}
            
            if (list.contains(word)) {
				list.remove(word);
			}
		}
        
        result.addAll(list);

		String[] answer = new String[result.size()];

		for (int j = 0; j < answer.length; j++) {
			answer[j] = result.get(j);
		}

		return answer;
	}
}