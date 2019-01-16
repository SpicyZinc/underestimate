/*
You are standing at position 0 on an infinite number line. There is a goal at position target.
On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.

Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.

Note:
target will be a non-zero integer in the range [-10^9, 10^9].

idea:
https://www.cnblogs.com/grandyang/p/8456022.html

ith step,
if going a same direction, should steps += i;
if going opposite direction, (steps + i) - (steps - i) = (i * 2)
一正一反里外里 就是 2⃣️ 倍

-|--|---|----|-----|------| the same direction
-|--|---|----|-----|
			 ------|
-|--|---|--- net result of switching right to left direction			 
*/

class ReachANumber {
	public int reachNumber(int target) {
        target = Math.abs(target);
		// find the min which is equal or just greater than target
		int sum = 0;
		int step = 0;
		while (sum < target) {
			sum += ++step;
		}
		// while () to get even number of difference between steps and target
		// 得到偶数的差值 因为里外里 所以除以 2 找到这时的步数
		// 虽然超过了target 但是我们知道只要一个反方向就解决了问题
		while ((sum - target) % 2 == 1) {
			sum += ++step;
		}

		return step;
	}
}