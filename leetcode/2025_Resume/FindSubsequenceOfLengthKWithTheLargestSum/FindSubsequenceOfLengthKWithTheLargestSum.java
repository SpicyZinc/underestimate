/*
You are given an integer array nums and an integer k.
You want to find a subsequence of nums of length k that has the largest sum.
Return any such subsequence as an integer array of length k.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

idea:
build an array with value being index and nums[i]
in order to keep index
then sort
*/
class FindSubsequenceOfLengthKWithTheLargestSum {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] map = new int[n][2];

        for (int i = 0; i < n; i++) {
            map[i] = new int[] {i, nums[i]};
        }

        Arrays.sort(map, (a, b) -> b[1] - a[1]);

        List<int[]> topK = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topK.add(map[i]);
        }

        // Sort topK by original index to restore order
        topK.sort((a, b) -> a[0] - b[0]);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = topK.get(i)[0];

            result[i] = nums[idx];
        }

        return result;
    }
}