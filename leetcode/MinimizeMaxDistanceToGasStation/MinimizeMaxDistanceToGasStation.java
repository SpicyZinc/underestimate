/*
On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1],
where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
Return the smallest possible value of D.

Example:
Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000

Note:
stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.


idea:
how come binary search
https://www.cnblogs.com/grandyang/p/8970057.html
https://blog.csdn.net/u014688145/article/details/79195114

direct method, good to understand, TLE
but remember count[i] ith interval has # of parts

need to go back for binary search
*/

class MinimizeMaxDistanceToGasStation {
	public double minmaxGasDist(int[] stations, int K) {
		int N = stations.length;
        double[] deltas = new double[N - 1];
        for (int i = 0; i < N - 1; i++) {
            deltas[i] = stations[i + 1] - stations[i];
        }
        // how many parts count[i] the ith (original) interval has become
        // ith interval has become count[i] parts
        // initialize as 1 which is correct
        int[] count = new int[N - 1];
        Arrays.fill(count, 1);

        // 把间隔较大的都分成了小的
        for (int k = 0; k < K; k++) {
            // Find interval with largest part size
            // the interval after being inserted a gas, find the split part which is the max
            int best = 0;
            for (int i = 0; i < N - 1; i++) {
                if (deltas[i] / count[i] > deltas[best] / count[best]) {
                    best = i;
                }
            }
            // Add gas station to best interval
            count[best]++;
        }
        // 然后再在插入完 K 个 gas stations 后 找最大的间距
        double max = 0;
        for (int i = 0; i < N - 1; i++) {
            max = Math.max(max, deltas[i] / count[i]);
        }

        return max;
	}
}
