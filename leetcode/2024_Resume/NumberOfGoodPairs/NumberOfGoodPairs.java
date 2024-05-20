/*
idea:
用到combination n * (n-1) / 2
*/
class NumberOfGoodPairs {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        for (int value : hm.values()) {
            if (value > 1) {
                count += value  * (value - 1) / 2;
            }
        }

        return count;
    }
}
