/*
An array is squareful if the sum of every pair of adjacent elements is a perfect square.
Given an integer array nums, return the number of permutations of nums that are squareful.
Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].

Example 1:
Input: nums = [1,17,8]
Output: 2
Explanation: [1,8,17] and [17,8,1] are the valid permutations.

Example 2:
Input: nums = [2,2,2]
Output: 1

Constraints:
1 <= nums.length <= 12
0 <= nums[i] <= 10^9

idea:
typical dfs to generate permutation, then isValid to filter
*/

class NumberOfSquarefulArrays {
    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> perms = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        dfs(nums, perms, result, visited);

        return result.size();
    }

    private void dfs(int[] nums, List<Integer> perms, List<List<Integer>> result, boolean[] visited) {
        // base case
        if (perms.size() == nums.length) {
            result.add(new ArrayList<>(perms));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (!visited[index] &&
                (index == 0 || nums[index] != nums[index - 1] || visited[index-1]) &&
                (perms.isEmpty() || isValid(perms.get(perms.size() - 1), nums[index]))
            ) {
                visited[index] = true;
                perms.add(nums[index]);
                dfs(nums, perms, result, visited);
                perms.removeLast();
                visited[index] = false;
            }
        }
    }

    // if the sum of 2 adjacent elements is a perfect square
    private boolean isValid(int a, int b) {
        int sum = a + b;
        int sqrt = (int) Math.sqrt(sum);
        return sqrt * sqrt == sum;
    }
}