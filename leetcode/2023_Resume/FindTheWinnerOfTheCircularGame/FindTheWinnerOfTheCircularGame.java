/*
There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order.
More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

The rules of the game are as follows:

Start at the 1st friend.
Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
The last friend you counted leaves the circle and loses the game.
If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
Else, the last friend in the circle wins the game.
Given the number of friends, n, and an integer k, return the winner of the game.



Example 1:
https://assets.leetcode.com/uploads/2021/03/25/ic234-q2-ex11.png

Input: n = 5, k = 2
Output: 3
Explanation: Here are the steps of the game:
1) Start at friend 1.
2) Count 2 friends clockwise, which are friends 1 and 2.
3) Friend 2 leaves the circle. Next start is friend 3.
4) Count 2 friends clockwise, which are friends 3 and 4.
5) Friend 4 leaves the circle. Next start is friend 5.
6) Count 2 friends clockwise, which are friends 5 and 1.
7) Friend 1 leaves the circle. Next start is friend 3.
8) Count 2 friends clockwise, which are friends 3 and 5.
9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.

Example 2:
Input: n = 6, k = 5
Output: 1
Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.

Constraints:
1 <= k <= n <= 500

Follow up:
Could you solve this problem in linear time with constant space?

idea:
https://leetcode.com/problems/find-the-winner-of-the-circular-game/solutions/1157717/java-full-solution-and-explanation/

image this
把count到的但不是目标的 加到 队列的后面
这个用for loop 完成
到了目标 就 remove

就是严格模拟 但是把 ring 换成一个 queue
*/

class FindTheWinnerOfTheCircularGame {
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        while (queue.size() > 1) {
            for (int i = 0; i < k - 1; i++) {
                queue.offer(queue.remove());
            }
            queue.remove();
        }
        // zero index based
        return queue.peek() + 1;
    }

    // Recursion with list
    public int findTheWinner(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return dfs(list, k, 0);
    }

    public int dfs(List<Integer> list, int k, int index) {
        int size = list.size();
        if (size == 1) {
            return list.get(0);
        }

        index = (index + k - 1) % size;

        list.remove(index);

        return dfs(list, k, index);
    }
}
