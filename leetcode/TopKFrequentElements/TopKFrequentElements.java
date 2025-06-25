/*
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

idea:
use count sorting or bucket sorting

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
    // 2025
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer>[] counts = new List[n + 1];

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int num = entry.getKey();
            int times = entry.getValue();

            counts[times] = counts[times] == null ? new ArrayList<>() : counts[times];
            counts[times].add(num);
        }

        int[] result = new int[k];
        int idx = 0;
        for (int i = n; i >= 0; i--) {
            if (counts[i] != null) {
                for (int j = 0; j < counts[i].size(); j++) {
                    if (idx >= k) {
                        break;
                    }
                    result[idx++] = counts[i].get(j);

                }
            }
        }

        return result;
    }
    // Tue Apr 30 00:29:19 2024
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);
        }
        // at most 一个数出现 n + 1
        List<Integer>[] count = new List[n + 1];
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (count[value] == null) {
                count[value] = new ArrayList<>();
            }
            // 出现value次的所有num
            count[value].add(key);
        }
        
        int[] ans = new int[k];
        int pos = 0;
        // 从后往前 保证了 最frequent
        for (int i = n; i >= 0; i--) {
            if (count[i] != null) {
                // 比如说 出现过三次的 5 和 7
                List<Integer> values = count[i];
                for (int j = 0; j < values.size() && pos < k; j++) {
                    ans[pos++] = values.get(j);
                }
            }
        }

        return ans;
    }

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
                // 根据frequency排序
                return hm.get(b) - hm.get(a);
            }
        });

        return keys.subList(0, k);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] frequencyToElements = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int frequency = entry.getValue();
            int element = entry.getKey();
            if (frequencyToElements[frequency] == null) {
                frequencyToElements[frequency] = new ArrayList<Integer>();
            }
            frequencyToElements[frequency].add(element);
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int i = nums.length; i >= 1 && result.size() < k; i--) {
            if (frequencyToElements[i] != null) {
                result.addAll(frequencyToElements[i]);
            }
        }

        return result;
    }

    // Wed Mar 15 00:57:24 2023
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> result = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && result.size() < k; pos--) {
            if (bucket[pos] != null) {
                result.addAll(bucket[pos]);
            }
        }

        return result;
    }
}