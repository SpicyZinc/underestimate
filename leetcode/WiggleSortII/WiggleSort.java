/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

3 3 2 2 1 1
2    1    1
  3    3    2

1 1 2 2 3 3
1    1    2
   2   3    3

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
answer is here https://discuss.leetcode.com/topic/41464/step-by-step-explanation-of-index-mapping-in-java/9

idea:
https://www.cnblogs.com/grandyang/p/5139057.html
https://www.hrwhisper.me/leetcode-wiggle-sort-ii/

sort the array, then reverse it
find the mid point, separate the array to two parts
take the last elements from both 1st half and 2nd half respectively to create the new array
if odd length, 2nd half should be one element more than 1st half

2. no sorting reversely, but pointer starts from end part of the array
right = (n + 1) / 2, then --right

note: depends the parity of the length
this is wiggle sort II, NOT allowing equal sign
*/
import java.util.*;

public class WiggleSort {
    public static void main(String[] args) {
        WiggleSort eg = new WiggleSort();
        int[] nums = {1, 5, 1, 1, 6, 4};
        // int[] nums = {1, 3, 2, 2, 3, 1};
        eg.wiggleSort(nums);
        for (int num : nums)  {
            System.out.print(num + "    ");
        }
        System.out.println();
    }

    public void wiggleSort(int[] nums) {
        if (nums.length <= 1 || nums == null) {
            return;
        }
        int n = nums.length;
        Integer[] copy = new Integer[n];
        for (int i = 0; i < n; i++) {
            copy[i] = new Integer(nums[i]);
        }
        Arrays.sort(copy, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        int pointerFirstHalf = n / 2;
        int pointerSecondHalf = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = i % 2 == 0 ? copy[pointerFirstHalf++] : copy[pointerSecondHalf++];
        }
    }

    public void wiggleSort(int[] nums) {
        if (nums.length <= 1 || nums == null) {
            return;   
        }
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        int leftPtr = (n + 1) / 2;
        int rightPtr = n;
        // left part size always equal or 1 smaller than right part
        // take 7 as an example
        // 0 1 2 3 4 5 6
        // 4 even numbers, 3 odd numbers in term of indexes
        // rightPtr one more than leftPtr, so even index goes to right right
        // what i said partially right, low high low high, so leftPtr
        for (int i = 0; i < n; i++) {
            nums[i] = (i % 2 == 0) ? copy[--leftPtr] : copy[--rightPtr];
        }
    }
}