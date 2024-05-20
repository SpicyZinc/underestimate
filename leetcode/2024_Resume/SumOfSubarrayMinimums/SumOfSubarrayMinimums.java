/*

idea:
https://leetcode.com/problems/sum-of-subarray-minimums/solutions/4595335/beats-100-c-java-python-js-explained-with-video-monotonic-stack

left[i]: the index of the first smaller element to the left of arr[i]
right[i]: the index of the first element that is less than or equal to the right of arr[i]

以i为最小的subarray 的 个数 ==  (i - left[i]) * (right[i] - i)
*/

class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        int size = arr.length;
        int[] leftSmallerIndexes = new int[size];
        int[] rightSmallerIndexes = new int[size];

        Arrays.fill(leftSmallerIndexes, -1);
        Arrays.fill(rightSmallerIndexes, size);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                leftSmallerIndexes[i] = stack.peek();
            }

            stack.push(i);
        }

        // Do not forget to clear the stack
        stack.clear();

        for (int i = size - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                rightSmallerIndexes[i] = stack.peek();
            }

            stack.push(i);
        }

        int mod = (int) 1e9 + 7;
        long answer = 0;
      
        for (int i = 0; i < size; i++) {
            answer += (long) (i - leftSmallerIndexes[i]) * (rightSmallerIndexes[i] - i) % mod * arr[i] % mod;
            answer %= mod;
        }

        return (int) answer;
    }
}