/*

Find if two rectangles overlap
Given two rectangles, find if the given two rectangles overlap or not.

Note that a rectangle can be represented by two coordinates, top left and bottom right. So mainly we are given following four coordinates.
l1: Top Left coordinate of first rectangle.
r1: Bottom Right coordinate of first rectangle.
l2: Top Left coordinate of second rectangle.
r2: Bottom Right coordinate of second rectangle.

idea:
http://www.geeksforgeeks.org/find-two-rectangles-overlap/

a rectangle is determined by two points top left and bottom right

Two rectangles do not overlap if one of the following conditions is true.
1) One rectangle is above top edge of other rectangle.
2) One rectangle is on left side of left edge of other rectangle.

reverse thinking
*/

public class TwoRectanglesOverlap {
	public static void main(String[] args) {
			
	}

	public boolean isOverlap(Point RectA_TopLeft, Point RectA_BotRight, Point RectB_TopLeft, Point RectB_BotRight) {
		// one rectangle is on left side of the other
		if (RectA_BotRight.x < RectB_TopLeft.x || RectA_TopLeft.x > RectB_BotRight.x) {
			return false;
		}
		// one rectangle is on top of the other 
		if (RectA_BotRight.y > RectB_TopLeft.y || RectA_TopLeft.y < RectB_BotRight.y) {
			return false;
		}
		return true;
	}
}

class Point {
	int x;
	int y;
}