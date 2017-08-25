/*
Given an array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

idea:
0 ^ any number == any number
number ^ itself = 0

so if a number shows twice, after bit manipulation, it becomes 0
the only left number will be the one appearing once

*/
public class SingleNumberI {
    public static void main(String[] args) {
        new SingleNumberI();
    }

    // constructor
    public SingleNumberI() {
        int[] a = {2, 3, 3, 1, 1};
        System.out.println("The number appearing once is: " + singleNumber(a));
    }

    public int singleNumber(int[] A) {
        int ret = 0;
        for (int i : A) {
            ret ^= i;
        }

        return ret;
    }
}