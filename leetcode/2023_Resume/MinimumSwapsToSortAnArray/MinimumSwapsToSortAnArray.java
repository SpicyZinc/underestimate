
// Given an array of N distinct elements, find the minimum number of swaps required to sort the array.
// https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/

import java.util.*;

class Pair {
    int value;
    int index;

    public Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

class MinimumSwapsToSortAnArray {
    public static void main(String[] args) {
        int[] a = { 1, 5, 4, 3, 2 };
        MinimumSwapsToSortAnArray eg = new MinimumSwapsToSortAnArray();
        System.out.println(eg.minSwaps(a));
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
  
        Arrays.sort(nums);
  
        // To keep track of visited elements. Initialize
        // all elements as not visited or false.
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
  
        int ans = 0;
  
        for (int i = 0; i < n; i++) {
            // already swapped and corrected or already present at correct pos
            if (visited[i] || map.get(nums[i]) == i) {
                continue;
            }
  
            // find out the number of node in this cycle and add in ans
            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                // move to next node
                j = map.get(nums[j]);
                cycleSize++;
            }
  
            // Update answer by adding current cycle.
            if (cycleSize > 0) {
                ans += cycleSize - 1;
            }
        }
  
        return ans;
    }
}