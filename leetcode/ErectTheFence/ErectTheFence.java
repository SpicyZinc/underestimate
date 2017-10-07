/*
There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden.
Your job is to fence the entire garden using the minimum length of rope as it is expensive.
The garden is well fenced only if all the trees are enclosed.
Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.

Example 1:
Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]

Example 2:
Input: [[1,2],[2,2],[4,2]]
Output: [[1,2],[2,2],[4,2]]

Even you only have trees in a line, you need to use rope to enclose them. 

Note:
All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
All input integers will range from 0 to 100.
The garden has at least one tree.
All coordinates are distinct.
Input points have NO order. No order required for output.

idea:
http://blog.csdn.net/u014688145/article/details/72200018

*/
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

class ErectTheFence {
	public List<Point> outerTrees(Point[] points) {
		Set<Point> convex = new HashSet<Point>();
		if (points.length == 1) {
			convex.add(points[0]);
			return new ArrayList<Point>(convex);
		}

		int n = points.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				// line i-j
				int cntLeftSide = 0;
				int cntRightSide = 0;
				for (int k = 0; k < n; k++) {
					if (k != i && k != j) {
						int whichSide = onWhichSide(points[i], points[j], points[k]);
						if (whichSide > 0) {
							cntLeftSide++;
						} else if (whichSide < 0) {
							cntRightSide++;
						}
					}
				}
				if (cntLeftSide == n - 2 || cntLeftSide == 0) {
					convex.add(points[i]);
					convex.add(points[j]);
				}
				if (cntRightSide == n - 2 || cntRightSide == 0) {
					convex.add(points[i]);
					convex.add(points[j]);
				}
			}
		}
		return new ArrayList<Point>(convex);
	}

	// helper to detect c on which side of line a-b
	// >0 left side, <0 right side
	private int onWhichSide(Point a, Point b, Point c) {
		return a.x * b.y + b.x * c.y + c.x * a.y - c.x * b.y - b.x * a.y - a.x * c.y; 
	}
}