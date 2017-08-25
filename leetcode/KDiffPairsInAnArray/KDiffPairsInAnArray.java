/*
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

idea:
note, k == 0 is very tricky situation, fuck
1. use two hashsets
2. use one hashmap
*/
public class KDiffPairsInAnArray {
    // method 1
    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || nums == null || k < 0) {
            return 0;
        }
        HashSet<Integer> hs = new HashSet<Integer>();
        HashSet<Integer> starters = new HashSet<Integer>();

        for (int num : nums) {
            // two possibilities
            // int starter = num - k;
            // int starter = num;
            if (hs.contains(num - k)) {
                starters.add(num - k);
            }
            if (hs.contains(num + k)) {
                starters.add(num);
            }
            hs.add(num);
        }

        return starters.size();
    }
    // method 2
    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || nums == null || k < 0) {
            return 0;
        }
        int count = 0;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (k == 0) {
                if (value >= 2) {
                    count++;
                }
            }
            else {
                // both working
                // if (hm.containsKey(key - k)) {
                if (hm.containsKey(key + k)) {
                    count++;
                }
            }
        }

        return count;
    }
    // method 2 simplification
    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || nums == null || k < 0) {
            return 0;
        }
        int count = 0;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (k == 0 && value >= 2 || k != 0 && hm.containsKey(key + k))  {
                count++;
            }
        }

        return count;
    }
}