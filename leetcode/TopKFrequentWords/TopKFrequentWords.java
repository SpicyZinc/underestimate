/*
Given a non-empty list of words, return the k most frequent elements.
Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

idea:
the same as Top K Frequent Elements
array of list, index is the frequency of each work, at most it cannot exceed the number of all words
from the biggest frequency to loop, addAll() to result and then sort
but order not correct

simple way is the method 1

*/
class TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> candidates = new ArrayList(count.keySet());

        Collections.sort(candidates, new Comparator<String>() {
        	@Override
        	public int compare(String a, String b) {
        		int aTimes = count.get(a);
        		int bTimes = count.get(b);

        		return aTimes == bTimes ? a.compareTo(b) : bTimes - aTimes;
        	}
        });

        return candidates.subList(0, k);
	}
	// 结果次序不对
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> hm = new HashMap<String, Integer>();
		for (String word : words) {
			hm.put(word, hm.getOrDefault(word, 0) + 1);
		}

		List<String>[] freqToWords = new ArrayList[words.length + 1];
		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			int freq = entry.getValue();
			if (freqToWords[freq] == null) {
				freqToWords[freq] = new ArrayList<String>();
			}
			freqToWords[freq].add(entry.getKey());
		}

		List<String> result = new ArrayList<String>();
		for (int i = words.length; i >= 1 && result.size() < k; i--) {
			if (freqToWords[i] != null) {
				result.addAll(freqToWords[i]);
			}
		}
		Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

		return result;
	}
}