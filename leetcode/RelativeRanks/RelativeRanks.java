/*
Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.

idea:
note: how to deep copy array in java
int[] copy = Arrays.copyOf(nums, N);

*/

public class RelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int N = nums.length;
        int[] copy = Arrays.copyOf(nums, N);
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        Arrays.sort(copy);
        for (int i = 0; i < N; i++) {
            if (N - i == 1) {
                hm.put(copy[i], "Gold Medal");
            }
            else if (N - i == 2) {
                hm.put(copy[i], "Silver Medal");
            }
            else if (N - i == 3) {
                hm.put(copy[i], "Bronze Medal");
            }
            else {
                hm.put(copy[i], "" + (N - i));
            }
        }
        String[] result = new String[N];
        for (int i = 0; i < N; i++) {
            result[i] = hm.get(nums[i]);
        }

        return result;
    }
}