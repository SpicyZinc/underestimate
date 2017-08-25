/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val.

Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

idea:
1. binary index tree
note: array index + 1 will be index in binary index tree
http://blog.csdn.net/xyt8023y/article/details/49946789

http://www.cnblogs.com/xudong-bupt/p/3484080.html
https://www.hackerearth.com/notes/binary-indexed-tree-made-easy-2/

2. square root method
*/

public class NumArray {
	int[] nums;
    int[] bit;
    public NumArray(int[] nums) {
        this.nums = nums;
        this.bit = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }
    
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val; // nums
        add(i + 1, diff); // bit
    }
    
    public int sumRange(int i, int j) {
        return sum(j + 1) - sum(i);
    }
    
    private void add(int pos, int value) {
        while (pos <= nums.length) {
            bit[pos] += value;
            pos += lowbit(pos);
        }
    }
    
    private int sum(int pos) {
        int res = 0;
        while (pos > 0) {
            res += bit[pos];
            pos -= lowbit(pos);
        }
        return res;
    }
    private int lowbit(int pos) {
        return pos & (-pos);
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);


// square root method
class NumArray {
    int[] nums;
    int[] blocks;
    int cnt;

    public NumArray(int[] nums) {
        this.nums = nums;
        cnt = (int) Math.ceil(Math.sqrt(nums.length));
        blocks = new int[cnt];
        for (int i = 0; i < nums.length; i++) {
            blocks[i / cnt] += nums[i];
        }
    }
    
    public void update(int i, int val) {
        int idx = i / cnt;
        int diff = val - nums[i];
        nums[i] = val;
        blocks[idx] += diff; 
    }
    
    public int sumRange(int i, int j) {
        int sum = 0;
        int start = i / cnt;
        int end = j / cnt;
        if (start == end) {
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
        }
        else {
            for (int k = i; k <= (start + 1) * cnt - 1; k++) sum += nums[k];
            for (int k = start + 1; k <= end - 1; k++) sum += blocks[k];
            for (int k = end * cnt; k <= j; k++) sum += nums[k];
        }
        return sum;
    }
}