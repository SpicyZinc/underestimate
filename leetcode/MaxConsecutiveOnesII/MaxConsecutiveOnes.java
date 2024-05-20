/*
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
After flipping, the maximum number of consecutive 1s is 4.

Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

Follow up:
What if the input numbers come in one by one as an infinite stream?
In other words, you can't store all numbers coming from the stream as it's too large to hold in memory.
Could you solve it efficiently?

idea:
https://www.cnblogs.com/grandyang/p/6376115.html

can generalize to flip k times, this case k = 1
maintain a window holding zeros, generalize the problem as at most k flips
so far, if the number of zeros <= k, do nothing, meaning flip these 0s
if > k, while () to shrink the left side of the window by left++
update max
*/

import java.util.*;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        MaxConsecutiveOnes eg = new MaxConsecutiveOnes();
        int[] nums = {1,0,1,1,0};
        int cnt = eg.findMaxConsecutiveOnes(nums);

        System.out.println(cnt);
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int k = 1;

        int maxCnt = 0;
        int zeroCnt = 0;

        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCnt++;
            }

            while (zeroCnt > k) {
                if (nums[left] == 0) {
                    // shrink 0 holding window
                    zeroCnt--;
                }
                left++;
            }

            maxCnt = Math.max(maxCnt, right - left + 1);
        }

        return maxCnt;
    }

    // follow up
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCnt = 0;
        Queue<Integer> zeroIndex = new LinkedList<>();

        int k = 1;
        int left = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndex.add(i);
            }

            if (zeroIndex.size() > k) {
                left = zeroIndex.poll() + 1;
            }

            maxCnt = Math.max(maxCnt, i - left + 1);
        }

        return maxCnt;
    }
}