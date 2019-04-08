/*
A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
Given a starting point (sx, sy) and a target point (tx, ty),
return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty).
Otherwise, return False.

Examples:

Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:
sx, sy, tx, ty will all be integers in the range [1, 10^9].

idea:
hard to think from start x and y
think from target x and y
case 1, tx > ty
keep subtracting ty from tx by mod ty, special case ty == sy
case 2, tx <= ty
keep subtracting tx from ty by mod tx, special case tx == sx
*/

class ReachingPoints {
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		while (tx >= sx && ty >= sy) {
			// 谁大先缩小谁
			if (tx > ty) {
				// sy == ty, 因为sx + (sy + sy + sy + ...) = tx => sx + (ty + ty + ty + ...) = tx
				if (sy == ty) {
					return (tx - sx) % ty == 0;
				}
				// how to decrease tx
				tx %= ty;
			} else {
				if (sx == tx) {
					return (ty - sy) % tx == 0;
				}
				ty %= tx;
			}
		}

		return false;
	}
}