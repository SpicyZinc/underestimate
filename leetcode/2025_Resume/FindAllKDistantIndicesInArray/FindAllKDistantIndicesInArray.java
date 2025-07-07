/*
Find all indices that are within k units away from values that are equal to key.

idea:
complicated description
just direct thought
*/

class FindAllKDistantIndicesInArray {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        boolean[] marked = new boolean[n]; // mark valid indices
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                int start = Math.max(0, i - k);
                int end = Math.min(n - 1, i + k);
                for (int j = start; j <= end; j++) {
                    marked[j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (marked[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
