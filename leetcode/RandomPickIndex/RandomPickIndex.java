/*
Given an array of integers with possible duplicates, randomly output the index of a given target number.
You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

idea:
1. hashmap
2. no hashmap, only Random
3. reservoir sampling 
*/

// method 1
public class RandomPickIndex {
    // Sun May  5 23:35:25 2019
    Map<Integer, List<Integer>> hm;
    Random random;

    public Solution(int[] nums) {
        this.hm = new HashMap<>();
        this.random = new Random();

        for (int i = 0; i < nums.length; i++) {
            hm.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = hm.get(target);
        int randomIndex = random.nextInt(list.size());

        return list.get(randomIndex);
    }
}

// method 2
public class RandomPickIndex {
    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }
    
    public int pick(int target) {
        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < this.nums.length; i++) {
            if (nums[i] == target) {
                targets.add(i);
            }
        }

        return targets.get(random.nextInt(targets.size()));
    }
}

// method 3
// http://www.sigmainfy.com/blog/reservoir-sampling-learning-notes.html
public class Solution {
    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;        
        this.random = new Random();
    }

    public int pick(int target) {
        int index = -1;
        int count = 0;

        for (int i = 0; i < this.nums.length; i++) {
            if (this.nums[i] != target) {
                continue;
            }
            if (random.nextInt(++count) == 0) {
                index = i;
            }
        }

        return index;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */