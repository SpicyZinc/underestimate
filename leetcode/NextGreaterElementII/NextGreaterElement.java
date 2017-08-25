/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element.
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number.
If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.

idea:
The same idea as Next Greater Element I.
The difference is that a circular array, 
1. startPosition + 1 to end of array,
2. start of array to startPosition - 1 
*/

public class NextGreaterElement {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = find(i + 1, nums[i], nums);
        }

        return result;
    }

    private int find(int start, int target, int[] nums) {
        int newStart = start % nums.length;
        while (newStart < nums.length) {
            if (nums[newStart] > target) {
                return nums[newStart];
            }
            newStart++;
        }
        int second = newStart % nums.length;
        while (second < start - 1) {
            if (nums[second] > target) {
                return nums[second];
            }
            second++;
        }
        return -1;
    }
}