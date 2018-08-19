/*
A zero-indexed array A consisting of N different integers is given.
The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:
S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.
Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of array A is an integer within the range [0, N-1].

idea:
based on description, for each number, do this nested stuff to count
find the maxCnt each iteration
TLE, add hash to pass test

other idea: https://www.cnblogs.com/grandyang/p/6932727.html
*/
public class ArrayNesting {
    // 07/28/2018 simpler version to TLE
    public int arrayNesting(int[] nums) {
        int max = 1;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, getSetLength(nums, i));
        }

        return max;
    }
    
    public int getSetLength(int[] nums, int K) {
        int cnt = 0;
        // note, this is assign K to val
        int val = K;

        while (cnt == 0 || val != K) {
            val = nums[val];
            cnt++;
        }

        return cnt;
    }
	// self written, 855 / 856 test cases passed
    public int arrayNesting(int[] nums) {
        int max = 1;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, getSetLength(nums, i));
        }

        return max;
    }
    
    public int getSetLength(int[] nums, int K) {
        int cnt = 1;
        int val = nums[K];
        if (val == K) {
            return cnt;
        }

        while (val != K) {
            val = nums[val];
            cnt++;
        }

        return cnt;
    }
    // add a visited map to reduce time
    // why continue, because this number was visited previously, skip it;
    // if not skip, size < the size of the path found before
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        
        int max = 1;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue; 
            }
            max = Math.max(max, getLength(nums, i, visited));
        }

        return max;
    }
    
    public int getLength(int[] nums, int K, boolean[] visited) {
        int cnt = 0;
        int val = K;
        while (cnt == 0 || val != K) {
            visited[val] = true;
            val = nums[val];
            cnt++;
        }
        return cnt;
    }

    public int arrayNesting(int[] nums) {
        int n = nums.size();
        int res = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 1;
            while (nums[i] != i && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
                cnt++;
            }

            res = Math.max(res, cnt);
        }

        return res;
    }
}