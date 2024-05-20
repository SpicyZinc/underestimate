/*
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
https://assets.leetcode.com/uploads/2020/04/08/sample_1_1784.png
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
https://assets.leetcode.com/uploads/2020/04/08/sample_2_1784.png
Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= sum(nums[i].length) <= 10^5
1 <= nums[i][j] <= 10^5

idea:
找出规律 相加 小于 n + n - 1
but TLE

diagonal is a constant, use it as key
*/

class DiagonalTraverse {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> hm = new HashMap<>();
        int count = 0;
        int m = nums.size();
        // 注意这个次序
        // iterate through each row from left to right starting with the bottom row
        for (int i = m - 1; i >= 0; i--) {
            int size = nums.get(i).size();
            for (int j = 0; j < size; j++) {
                int diagonalKey = i + j;
                hm.computeIfAbsent(diagonalKey, x -> new ArrayList<Integer>()).add(nums.get(i).get(j));
                count++;
            }
        }

        int[] result = new int[count];
        int idx = 0;
        int diagonalIdx = 0;

        while (hm.containsKey(diagonalIdx)) {
            for (int num : hm.get(diagonalIdx)) {
                result[idx++] = num;
            }
            diagonalIdx++;
        }

        return result;
    }

    // TLE
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int m = nums.size();
        int maxColSize = 0;
        for (int i = 0; i < m; i++) {
            int size = nums.get(i).size();
            maxColSize = Math.max(maxColSize, size);
        }

        int n = Math.max(m, maxColSize);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n + n - 1; i++) {
            for (int j = 0; j <= i; j++) {
                int x = i - j;
                int y = j;
                if (x < nums.size()) {
                    if (y < nums.get(x).size()) {
                        list.add(nums.get(x).get(y));
                    }
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}