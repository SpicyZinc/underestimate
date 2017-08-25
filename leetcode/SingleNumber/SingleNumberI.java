/*
Given an array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

idea:

0 ^ any number == any number
number ^ itself = 0

so if a number shows twice, after bit manipulation, it becomes 0
the only left number will be the one appearing once

*/
public class SingleNumber {
    public static void main(String[] args) {
        new SingleNumber();
    }

    // constructor
    public SingleNumber() {
        int[] a = {2, 3, 3, 1, 1};
        System.out.println("The number appearing once is: " + singleNumber(a));
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
    // direct method with sort()
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
            else if (i == nums.length - 1) {
                if (nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }
            else {
                if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }
}
