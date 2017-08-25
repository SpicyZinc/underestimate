/*
Shuffle a set of numbers without duplicates.

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

idea:
http://yuanhsh.iteye.com/blog/2181938

make a copy of the array
use swap to do the shuffle
note: every i shuffle with the elements after it, randomly pick one after i

Random.nextInt(target) the specified value (exclusive)
*/

public class ShuffleAnArray {
    int[] nums;
    int[] copy;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        this.copy = Arrays.copyOf(nums, nums.length);
        this.random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = copy.length;
        for (int i = 0; i < n; i++) {
            int randomIdx = random.nextInt(n - i);
            swap(copy, i, i + randomIdx);
        }
        return copy;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */