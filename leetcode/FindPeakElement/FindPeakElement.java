/*
A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.

idea:
某个数字小于前面那个数字, 说明前面数字就是一个局部峰值, 返回位置即可
https://www.cnblogs.com/grandyang/p/4217175.html

typical binary search
只是需要找到任意一个峰值, 则在确定二分查找折半后中间那个元素后,
和紧跟的那个元素比较下大小, 如果大于, 则说明峰值在前面, 如果小于则在后面
if (nums[mid] < nums[mid + 1])
只要 > (mid + 1) 里找到 小于 nums[mid + 1]) 就找到一个 peak
所以 start = mid + 1

      |
    | |
  | | |
| | | |

https://siddontang.gitbooks.io/leetcode-solution/content/array/find_peak_element.html
*/

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        // 0, 3
        // mid = 1
        // nums[mid] nums[mid + 1] 
        // 2 3
        // start = 1 + 1 = 2;
        // start < end => 2 < 3
        // mid = 2
        // nums[mid] nums[mid + 1] 
        // 3 1
        // end = 2
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            // peak 一定在后半部分
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else { // peak 在前半部分
                end = mid;
            }
        }

        return start;
    }

    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        // 如果一直没有找到 说明是递增数列 最后一个就是peak
        return nums.length - 1;
    }
}