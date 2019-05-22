/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

idea:
similar to TopKFrequentWords
List<Integer>[]
use hashmap to record frequency of each value
then frequency as key, the value will be another ArrayList containing all values appear "frequency" times
most frequent elements can never be greater than the length of array
so k must be less than length

the loop through from length of the array, until result.size() < k

Note:
top k, must create the length of nums.length + 1
*/
public class TopKFrequentElements {
    // Tue May 21 23:15:08 2019
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();

        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        List<Integer> keys = new ArrayList<>(hm.keySet());
        
        Collections.sort(keys, (a, b) -> hm.get(b) - hm.get(a));

        return keys.subList(0, k);
    }

    // best
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> keys = new ArrayList(hm.keySet());
        Collections.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return hm.get(b) - hm.get(a);
            }
        });
        
        return keys.subList(0, k);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i : nums) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }
        List<Integer>[] frequencyToElements = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int frequency = entry.getValue();
            if (frequencyToElements[frequency] == null) {
                frequencyToElements[frequency] = new ArrayList<Integer>();
            }
            frequencyToElements[frequency].add(entry.getKey());
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = nums.length; i >= 1 && res.size() < k; i--) {
            if (frequencyToElements[i] != null) {
                res.addAll(frequencyToElements[i]);
            }
        }
        
        return res;
    }
}