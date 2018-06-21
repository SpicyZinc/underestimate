/*
A rectangle is represented as a list [x1, y1, x2, y2],
where (x1, y1) are the coordinates of its bottom-left corner,
and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.
To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two rectangles, return whether they overlap.

Example 1:
Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true

Example 2:
Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false

Notes:
Both rectangles rec1 and rec2 are lists of 4 integers.
All coordinates in rectangles will be between -10^9 and 10^9.

idea:
one rectangle is either on top of or to the left of the other rectangle
exclude the possibilities
*/

class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // bottom-left
        // top-right
        int x1Left = rec1[0];
        int y1Bottom = rec1[1];
        int x1Right = rec1[2];
        int y1Top = rec1[3];
        
        int x2Left = rec2[0];
        int y2Bottom = rec2[1];
        int x2Right = rec2[2];
        int y2Top = rec2[3];
        
        return !(x1Right <= x2Left || x2Right <= x1Left || y1Top <= y2Bottom || y2Top <= y1Bottom);
    }
}