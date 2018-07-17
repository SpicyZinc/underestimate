/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

idea:
either use hashmap or use loop find position, waste space or waste time
use stack understand now, nums1 is subset of nums2
hash 是nums2中每个数字 与比它大的第一个数的映射
*/

public class NextGreaterElement {
    // stack
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] result = new int[findNums.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> hm = new HashMap<>();

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num) {
                hm.put(stack.pop(), num);
            }

            stack.push(num);
        }

        for (int i = 0; i < findNums.length; i++) {
            int num = findNums[i];
            result[i] = hm.containsKey(num) ? hm.get(num) : -1;
        }

        return result;
    }


    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] result = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            int target = findNums[i];
            int nextPosition = nextPos(target, nums);
            result[i] = nextGreater(nextPosition, target, nums);
        }
        return result;
    }

    private int nextPos(int target, int[] nums) {
        int nextPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                nextPos = i + 1;
                if (nextPos == nums.length) {
                    nextPos = -1;
                }
            }
        }
        return nextPos;
    }

    private int nextGreater(int pos, int target, int[] nums) {
        if (pos == -1) {
            return pos;
        }
        int start = pos;
        while (start < nums.length) {
            if (nums[start] > target ) {
                return nums[start];
            }
            start++;
        }
        return -1;
    }
}