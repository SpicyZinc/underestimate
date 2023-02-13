/*
We are given an array asteroids of integers representing asteroids in a row.
For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions.
If two asteroids meet, the smaller one will explode.
If both are the same size, both will explode.
Two asteroids moving in the same direction will never meet.

 
Example 1:
Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Example 2:
Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.

Example 3:
Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.

Example 4:
Input: asteroids = [-2,-1,1,2]
Output: [-2,-1,1,2]
Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 

Constraints:
2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

idea:
brute force

*/

class AsteroidCollision {
    // Sun May  2 22:54:21 2021
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            // Pushing all + asteroids
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                // For negative asteroid
                // 1. if > the most recent previous asteroid, keep offset that (不断抵消)
                // 2. 同为 negative or empty stack, pushing to stack

                // Remove all positive asteroids before our current asteroid
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(asteroid)) {
                    stack.pop();
                }
                // Checking if the stack is empty or the recent asteroid is negative
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                } else if (stack.peek() == Math.abs(asteroid)) { // if equal we pop it
                    stack.pop();
                }
            }
        }

        int[] result = new int[stack.size()];   
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
    // Sun May  2 23:24:15 2021
    public int[] asteroidCollision(int[] asteroids) {
        // Note, must be LinkedList
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];

            if (asteroid < 0) {
                if (stack.size() == 0 || stack.peekLast() < 0) {
                    stack.add(asteroid);
                } else if (Math.abs(stack.peekLast()) <= -1 * asteroid) {
                    if (Math.abs(stack.peekLast()) < -1 * asteroid) {
                        // 回撤一位 这个negative可能继续吃掉前面的
                        i--;
                    }
                    stack.pollLast();
                }
            } else {
                stack.add(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }
        return result;
    }

    // Wrong self method
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        int i = 1;

        List<Integer> list = new ArrayList<>();

        while (i < n) {
            int curr = asteroids[i - 1];
            int next = asteroids[i];

            if (curr * next >= 0) {
                list.add(curr);
                list.add(next);
            } else {
                if (Math.abs(curr) != Math.abs(next)) {
                    if (curr > next) {
                        list.add(curr);
                    } else {
                        list.add(next);
                    }
                }
            }
            i+=2;
        }

        int[] result = new int[list.size()];
        for (int ii = 0; ii < result.length; ii++) {
            result[ii] = list.get(ii);
        }

        return result;
    }
}