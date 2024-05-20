/*
Given two integers n and k, return an array of all the integers of length n where the difference between every two consecutive digits is k. You may return the answer in any order.
Note that the integers should not have leading zeros. Integers as 02 and 043 are not allowed.

Example 1:
Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.

Example 2:
Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Constraints:
2 <= n <= 9
0 <= k <= 9

idea:
dfs

*/

class NumbersWithSameConsecutiveDifferences {
    List<Integer> result = new ArrayList<>();

    public int[] numsSameConsecDiff(int n, int k) {
        for (int i = 1; i < 10; i++) {
            dfs(0, i, 0, n, k);
        }

        int size = result.size();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = result.get(i);
        }

        return nums;
    }

    public void dfs(int num, int digit, int currentSize, int n, int k) {
        if (currentSize == n) {
            if (!result.contains(num)) {
                result.add(num);
            }

            return;
        }

        if (digit < 10 && digit >= 0) {
            num = (num * 10) + digit;

            int biggerDigit = digit + k;
            int smallerDigit = digit - k;

            dfs(num, biggerDigit, currentSize + 1, n, k);
            dfs(num, smallerDigit, currentSize + 1, n, k);
        }
    }
}
