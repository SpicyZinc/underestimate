/*
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.

Example 1:
Input: nums = [21,4,7]
Output: 32
Explanation: 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.

Example 2:
Input: nums = [21,21]
Output: 64

Example 3:
Input: nums = [1,2,3,4,5]
Output: 0

Constraints:
1 <= nums.length <= 104
1 <= nums[i] <= 105

idea:
Any number n greater than 1 already have two divisors: 1 and n. So, for a number to have exact 4 divisors, there should be only one pair of divisors d and n / d.
Catch: if d == n / d, the number has 3 divisors, not four.
*/

class FourDivisors {
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            int lastDivisor = 0;
            for (int d = 2; d * d <= num; d++) {
                if (num % d == 0) {
                    if (lastDivisor == 0) {
                        lastDivisor = d;
                    } else {
                        lastDivisor = 0;
                        break;
                    }
                }
            }

            if (lastDivisor > 0 && lastDivisor != num / lastDivisor) {
                sum += 1 + num + lastDivisor + num / lastDivisor;
            }            
        }

        return sum;
    }

    // A little slower
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            int lastDivisor = 0;
            for (int d = 2; d <= num / d; d++) {
                if (num % d == 0) {
                    if (lastDivisor == 0) {
                        lastDivisor = d;
                    } else {
                        // Having more than 1 of lastDivisor, so break
                        lastDivisor = 0;
                        break;
                    }
                }
            }

            if (lastDivisor > 0 && lastDivisor != num / lastDivisor) {
                sum += 1 + num + lastDivisor + num / lastDivisor;
            }            
        }

        return sum;
    }
}
