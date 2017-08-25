/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231
Find the maximum result of ai XOR aj, where 0 ≤ i, j < n
Could you do this in O(n) runtime

Example
Input: [3, 10, 5, 25, 2, 8
Output: 2
Explanation: The maximum result is 5 ^ 25 = 28

idea:
https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/3

To iteratively determine what would be each bit of the final result from left to right.
And it narrows down the candidate group iteration by iteration. e.g. assume input are a,b,c,d,...z, 26 integers in total.
In first iteration, if you found that a, d, e, h, u differs on the MSB(most significant bit), so you are sure your final result's MSB is set.
Now in second iteration, you try to see if among a, d, e, h, u there are at least two numbers make the 2nd MSB differs, if yes, then definitely, the 2nd MSB will be set in the final result.
And maybe at this point the candidate group shrinks from a,d,e,h,u to a, e, h.
Implicitly, every iteration, you are narrowing down the candidate group, but you don't need to track how the group is shrinking, you only cares about the final result.

note: if a^b=c, then a^c=b, b^c=a

now find out if there are two prefix with different the i-th bit
if there is, the new max should be current max with one 1 bit at i-th position, which is candidate
for the two prefix, say A and B, satisfies:
A ^ B = candidate
so we also have A ^ candidate = B or B ^ candidate = A
thus we can use this method to find out if such A and B exists in the set which are different at i-th position

example to help understand:
Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010]
Since the MSB is 3, start from i = 3 
i = 3, set = {1000, 0000}, max = 1000
i = 2, set = {1100, 1000, 0100, 0000}, max = 1100
i = 1, set = {1110, 1010, 0110, 0010}, max = 1100
i = 0, set = {1110, 1011, 0111, 0010}, max = 1100
*/

public class MaximumXORofTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        // start from most significant bit MSB
        for (int i = 31; i >= 0; i--) {
            HashSet<Integer> set = new HashSet<Integer>();
            mask = mask | (1 << i);
            for (int num : nums) {
                set.add(num & mask); // reserve Left bits and ignore Right bits
            }

            int candidate = max | (1 << i); // in each iteration, there are pairs whose Left bits can XOR to max
            for (int prefix : set) {
            	// as long as there is one pair of prefix, it should be enough
                if (set.contains(candidate ^ prefix)) {
                    max = candidate;
                    break;
                }
            }
        }
        return max;
    }
}