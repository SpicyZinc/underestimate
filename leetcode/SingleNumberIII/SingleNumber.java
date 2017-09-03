/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

For example:
Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. 
So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant space complexity?

idea:
1. hashset, use hs.add() return false if hs already contains the element, remove duplicate element, the leftover will be elements appearing once
2. bit operation, find the two once element rightmost bit which is differ, use this to split two groups
one is bit set, one is not bit set, in each group use SingleNumber I to solve the problem

using the rightmost 1-bit is just for ease of coding (diff &= -diff will leave the rightmost 1-bit). 
In fact, you can use any 1-bit. This 1-bit implies that the two single numbers are different at this bit. 
Then we use this bit to split all the remaining numbers into two groups. 
Suppose the two single numbers are a and b and they differ in the third bit (a is 1 at this bit while b is 0). 
After splitting, numbers with 1 in the third bit will fall in the group of a while the remaining ones fall in the group of b. 
Till now, we will be able to get a and b via a simple within-group xor.

diff = diff & ~(diff-1)
Two's complement ~(diff - 1) == -diff
*/

public class SingleNumber {
	// method 1
	public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        Set<Integer> hs = new HashSet<Integer>();
        for (int num : nums) {
            if (!hs.add(num)) {
                // all twice elements are removed
                hs.remove(num);
            }
        }
        int i = 0;
        for (int num : hs) {
            result[i++] = num;
        }

        return result;
    }
    // method 2
    public int[] singleNumber(int[] nums) {
        // get the XOR of the two numbers we need to find
        int XOR = 0;
        for (int num : nums) {
            XOR ^= num;
        }

        int[] result = new int[2];
        XOR &= -XOR; // rightmost bit two appear-once elements differ at
        for (int num : nums) {
            if ((XOR & num) == 0) { // group 1
                result[0] ^= num;
            } else { // group 2
                result[1] ^= num;
            }
        }
        return result;
    }
}
