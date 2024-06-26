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

new idea:

Given 2 segment (left1, right1), (left2, right2), how can we check whether they overlap?
If these two intervals overlap, it should exist an number x,

left1 < x < right1 && left2 < x < right2
left1 < x < right2 && left2 < x < right1
=>

left1 < right2 && left2 < right1


     |------------|
     |            |
|----|-----|      |
|    |     |      |
|    |     |      |
|     -----|------
|--------|

*/

class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int left1 = rec1[0];
        int right1 = rec1[2];
        int left2 = rec2[0];
        int right2 = rec2[2];

        int top1 = rec1[3];
        int bottom1 = rec1[1];
        int top2 = rec2[3];
        int bottom2 = rec2[1];

        return left1 < right2 &&
                left2 < right1 &&
                bottom1 < top2 &&
                bottom2 < top1;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int OneBotLeftX = rec1[0];
        int OneBotLeftY = rec1[1];
        int OneTopRightX = rec1[2];
        int OneTopRightY = rec1[3];

        int TwoBotLeftX = rec2[0];
        int TwoBotLeftY = rec2[1];
        int TwoTopRightX = rec2[2];
        int TwoTopRightY = rec2[3];

        return !(OneBotLeftX >= TwoTopRightX ||
            OneTopRightX <= TwoBotLeftX ||
            OneBotLeftY >= TwoTopRightY ||
            OneTopRightY <= TwoBotLeftY);
    }
}
