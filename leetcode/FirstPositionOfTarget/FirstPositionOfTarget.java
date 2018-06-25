/*
For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.
If the target number does not exist in the array, return -1.

idea:
https://www.zhihu.com/question/36132386
binary
note: duplicates, 有可能targe 不在数组中 要最后判断一下

循环不变式:

如果key存在于数组, 那么key第一次出现的下标x一定在[left,right]中, 且有array[left]<=key, array[right]>=key


初始化:

第一轮循环开始之前, 处理的数组是[0,n-1], 这时显然成立


保持:

每次循环开始前, 如果key存在于原数组, 那么x存在于待处理数组array[left, …, right]中


对于array[mid]<key, array[left, …, mid]均小于key, x只可能存在于array[mid+1, …, right]中
数组减少的长度为mid-left+1, 至少为1


否则, array[mid]>=key, array[mid]是array[mid, …, right]中第一个大于等于key的元素, 后续的等于key的元素（如果有）不可能对应于下标x, 舍去
此时x在[left, …, mid]之中
数组减少的长度为right-(mid+1)+1, 即right-mid, 根据while的条件, 当right==mid时为0
此时right==left, 循环结束


终止:

此时left>=right
在每次循环结束时, left总是x的第一个可能下标, array[right]总是第一个等于key或者大于key的元素


那么对应于left==right的情况, 检查array[left]即可获得key是否存在, 若存在则下标为x;

对于left>right的情况, 其实是不用考虑的
因为left==上一次循环的mid+1, 而mid<=right
若mid+1>right, 意味着mid == right, 但此时必有left == right, 这一轮循环从开始就不可能进入

*/

public class FirstPositionOfTarget {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    public int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (nums[low] == target) {
            return low;    
        }
        return -1;
    }
}