/*
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point.
For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).

Example 1:
rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]
Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:
rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]
Return false. Because there is a gap between the two rectangular regions.

Example 3:
rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]
Return false. Because there is a gap in the top center.

Example 4:
rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]
Return false. Because two of the rectangles overlap with each other.

idea:
A->B->C->D
A----D
|    |   
B----C

4 points in the final rectangle only appear once in all the given points
so use hashset to add and remove duplicate points, all duplicate point must appear in pair
and total area must be all small rectangles' area sum together

count the only 4 point did not pass OJ because object address is different
*/


public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int blX = Integer.MAX_VALUE;                
        int blY = Integer.MAX_VALUE;                
        int trX = Integer.MIN_VALUE;                
        int trY = Integer.MIN_VALUE;

        int totalArea = 0;
        HashSet<String> hs = new HashSet<String>();
        for (int i = 0; i < rectangles.length; i++) {
            int[] rectangle = rectangles[i];
            totalArea += getRectangleArea(rectangle);

            blX = Math.min(blX, rectangle[0]);
            blY = Math.min(blY, rectangle[1]);
            trX = Math.max(trX, rectangle[2]);
            trY = Math.max(trY, rectangle[3]);
            
            String A = representPoint(rectangle[0], rectangle[3]);
            String B = representPoint(rectangle[0], rectangle[1]);
            String C = representPoint(rectangle[2], rectangle[1]);
            String D = representPoint(rectangle[2], rectangle[3]);

            String[] points = new String[] {A, B, C, D};
            for (int idx = 0; idx < points.length; idx++) {
                if (hs.contains(points[idx])) {
                    hs.remove(points[idx]);
                }
                else {
                    hs.add(points[idx]);

                }
            }
        }


        if (hs.size() == 4 && 
            hs.contains(representPoint(blX, trY)) &&
            hs.contains(representPoint(blX, blY)) &&
            hs.contains(representPoint(trX, blY)) &&
            hs.contains(representPoint(trX, trY))
        ) {
            return totalArea == (trX - blX) * (trY - blY);
        }

        return false;
    }

    public int getRectangleArea(int[] rectangle) {
        int length = rectangle[2] - rectangle[0];
        int width = rectangle[3] - rectangle[1];
        return length * width;
    }

    public String representPoint(int x, int y) {
        return x + "-" + y;
    }
}