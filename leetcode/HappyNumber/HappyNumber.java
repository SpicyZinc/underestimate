/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

idea:
1. helper method to sum all digits of a number
number % 10 is the rightmost digit
number / 10 is move to left by one digit

2. use a hash set to save the sum of each round
if a sum ever exists in the hash set,
that means the number generating this sum can never be a happy number,
because this case will fall into a endless loop, so return false to end the while loop to say this number is not HAPPY
otherwise, if it is the case where sum == 1, return true
*/

import java.util.*;

public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber eg = new HappyNumber();
        boolean test = eg.isHappy(19);
        System.out.println(test);
    }

    // 07/10/2018
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        Set<Integer> hs = new HashSet<Integer>();
        int happyCalculation = sumOfDigitSquare(n);

        // 巧妙利用 hs.add()
        while (hs.add(happyCalculation)) {
            if (happyCalculation == 1) {
                return true;
            }
            happyCalculation = sumOfDigitSquare(happyCalculation);
        }

        return false;
    }

    public int sumOfDigitSquare(int n) {
        int sum = 0;
        if (n == 0) {
            return sum;
        }

        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }

        return sum;
    }

    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        Set<Integer> hs = new HashSet<>();
        int happyCalculation = sumOfDigitSquare(n);
        while (!hs.contains(happyCalculation)) {
            if (happyCalculation == 1) {
                return true;
            }
            hs.add(happyCalculation);
            happyCalculation = sumOfDigitSquare(happyCalculation);
        }

        return false;
    }
}
