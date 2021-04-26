/**
You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive (i.e., n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.
Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's number.
For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will be put in the box number 1 + 0 = 1.

Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.


Example 1:
Input: lowLimit = 1, highLimit = 10
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  2 1 1 1 1 1 1 1 1 0  0  ...
Box 1 has the most number of balls with 2 balls.

Example 2:
Input: lowLimit = 5, highLimit = 15
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  1 1 1 1 2 2 1 1 1 0  0  ...
Boxes 5 and 6 have the most number of balls with 2 balls in each.

Example 3:
Input: lowLimit = 19, highLimit = 28
Output: 2
Explanation:
Box Number:  1 2 3 4 5 6 7 8 9 10 11 12 ...
Ball Count:  0 1 1 1 1 1 1 1 1 2  0  0  ...
Box 10 has the most number of balls with 2 balls.

Constraints:
1 <= lowLimit <= highLimit <= 105

idea:
1. build a map, key is box position number, value is the count of balls in the position
2. loop to find the biggest count of ball

level: EASY
*/

import java.util.*;

class MaximumNumberOfBallsInABox {
    public static void main(String[] args) {
        MaximumNumberOfBallsInABox eg = new MaximumNumberOfBallsInABox();
        int lowLimit = 5;
        int highLimit = 15;

        int maxCount = eg.countBalls(lowLimit, highLimit);
        System.out.println(maxCount);
    }

    public int countBalls(int lowLimit, int highLimit) {
        // map of box index number -> ball count
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int boxNumber = digitSum(i);
            hm.put(boxNumber, hm.getOrDefault(boxNumber, 0) + 1);
        }
        
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int value = entry.getValue();
            if (value > maxCount) {
                maxCount = value;
            }
        }
        
        return maxCount;
    }
    // Get sum of digits of a number
    public int digitSum(int n) {
        int m = n;
        int sum = 0;
        while (m > 0) {
            sum += m % 10;
            m /= 10;
        }

        return sum;
    }
}