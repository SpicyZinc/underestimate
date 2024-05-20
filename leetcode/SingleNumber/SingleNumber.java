/*
Given an array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

idea:
1. 0 ^ any number == any number
2. number ^ itself = 0

so if a number shows twice, after bit manipulation, it becomes 0
the only left number will be the one appearing once

2. sort first, then find
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
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i]) {
                cnt++;
            } else {
                if (cnt == 1) return nums[i];
                cnt = 1;
            }
        }

        return nums[nums.length - 1];
    }

    // Tue Apr 23 19:00:00 2024 新方法每次移动两个 不等的话 前一个就是
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int size = nums.length;

        while (i < size - 1) {
            if (nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
