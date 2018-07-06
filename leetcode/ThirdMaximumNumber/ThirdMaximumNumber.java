/*
Given an array of integers, return the 3rd Maximum Number in this array, if it doesn't exist, return the Maximum Number.
The time complexity must be O(n) or less.

idea:
1. first thought of getKth
2. find the first three maximum numbers
3. damn overflow
note: (num >= third) don't forget equal sign
*/

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        int cnt = 0;

        for (int num : nums) {
            long number = (long) num;
            if (number == first || number == second) {
                continue;
            }

            if (number > first) {
                third = second;
                second = first;
                first = number;

                cnt++;
            } else if (number > second) {
                third = second;
                second = number;

                cnt++;
            } else if (number >= third) {
                third = number;
                cnt++;
            }
        }
        
        return cnt >= 3 ? (int) third : (int) first;
    }
}