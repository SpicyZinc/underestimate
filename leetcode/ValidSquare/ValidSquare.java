/*
Given the coordinates of four points in 2D space, return whether the four points could construct a square.
The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True

Note:
All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.

idea:
diagonalSide should have 2
the other side should be the same of length 
*/
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] lengths = new int[6];
        lengths[0] = getLength(p1, p2);
        lengths[1] = getLength(p1, p3);
        lengths[2] = getLength(p1, p4);
        lengths[3] = getLength(p2, p3);
        lengths[4] = getLength(p2, p4);
        lengths[5] = getLength(p3, p4);

        int diagonalSide = Integer.MIN_VALUE;
        int side = 0;
        for (int l : lengths) {
        	diagonalSide = Math.max(diagonalSide, l);
        }
        int maxCnt = 0;
        for (int l : lengths) {
        	if (l == diagonalSide) {
        		maxCnt++;
        	}
        	else {
        		side = l;
        	}
        }
        if (maxCnt != 2) {
        	return false;
        }
        for (int l : lengths) {
        	if (l != diagonalSide && l != side) {
        		return false;
        	}
        }

        return true;
    }

    private int getLength(int[] s, int[] t) {
    	int sx = s[0];
    	int sy = s[1];
    	int tx = t[0];
    	int ty = t[1];

    	return (int) Math.pow((tx - sx), 2) + (int) Math.pow((ty - sy), 2);
    }
}