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

4 points in the final rectangle only appear only once in all given points
because duplicate points appear in pair, once found hs has this point, remove it; thus finally only 4 points left
there should be 4 points left
totalArea should be equal to sum of all small ones

note: cannot use Point, because address is different

tr == top right
bl == bottom left
*/

public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int minBLX = Integer.MAX_VALUE;
        int minBLY = Integer.MAX_VALUE;
        int maxTRX = Integer.MIN_VALUE;
        int maxTRY = Integer.MIN_VALUE;

        int totalArea = 0;
        Set<String> hs = new HashSet<String>();

        for (int[] rectangle : rectangles) {
            totalArea += area(rectangle);

            int blX = rectangle[0];
            int blY = rectangle[1];
            int trX = rectangle[2];
            int trY = rectangle[3];
            
            String A = representingPoint(blX, blY);
            String B = representingPoint(trX, blY);
            String C = representingPoint(trX, trY);
            String D = representingPoint(blX, trY);

            if (!hs.add(A)) hs.remove(A);
            if (!hs.add(B)) hs.remove(B);
            if (!hs.add(C)) hs.remove(C);
            if (!hs.add(D)) hs.remove(D);

            minBLX = Math.min(minBLX, blX);
            minBLY = Math.min(minBLY, blY);
            maxTRX = Math.max(maxTRX, trX);
            maxTRY = Math.max(maxTRY, trY);
        }

        if (hs.size() == 4 &&
            hs.contains(representingPoint(minBLX, minBLY)) &&
            hs.contains(representingPoint(minBLX, maxTRY)) &&
            hs.contains(representingPoint(maxTRX, minBLY)) &&
            hs.contains(representingPoint(maxTRX, maxTRY))
        ) {
            return totalArea == (maxTRX - minBLX) * (maxTRY - minBLY);
        }

        return false;
    }

    public int area(int[] rectangle) {
        return (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
    }
    
    public String representingPoint(int x, int y) {
        return x + "_" + y;
    }
}