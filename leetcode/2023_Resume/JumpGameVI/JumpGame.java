/*
idea:

Let dp[i] is the maximum score we can get when ending at index i.
Base case: dp[0] = nums[0], we start at index 0
State transfer equation:
If we have already computed dp[0], dp[1], ..., dp[i-1], how can we compute dp[i]?
Since we can jump at most k steps, to arrive index i, we must jump from one of indices [i-k, i-k+1, ..., i-1].
So dp[i] = max(dp[i-k], dp[i-k+1], ..., dp[i-1]) + nums[i].
Finally, dp[n-1] is the maximum score when reaching the last index of the array, index n-1.

need to come back
*/

class JumpGame {
    // TLE
    public int maxResult(int[] nums, int k) {
        return maxResult(nums, k, 0);
    }

    public int maxResult(int[] nums, int k, int i) {
        int size = nums.length;
        if (i >= size - 1) return nums[size - 1];
        int score = Integer.MIN_VALUE;
        for (int j = 1; j <= k; j++) {
            score = Math.max(score, nums[i] + maxResult(nums, k, i + j));
        }

        return score;
    }

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 1; i < n; ++i)
            for (int j = Math.max(0, i - k); j < i; ++j)
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
        return dp[n-1];
    }

    // Sun Apr  2 20:06:13 2023
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // store index of `nums` elements, elements is in decreasing order, the front is the maximum element
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);

        for (int i = 1; i < n; ++i) {
            // nums[i] = max(nums[i-k], nums[i-k+1], .., nums[i-1]) + nums[i] = nums[dq.front()] + nums[i]
            nums[i] = nums[dq.peekFirst()] + nums[i];

            // Add nums[i] to our deque
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast(); // Eliminate elements less or equal to nums[i], which will never be chosen in the future
            dq.offerLast(i);

            // Remove if the last element is out of window size k
            if (i - dq.peekFirst() >= k) dq.pollFirst();
        }

        return nums[n - 1];
    }
}
