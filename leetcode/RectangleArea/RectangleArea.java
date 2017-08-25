/*
Find the total area covered by two rectilinear rectangles in a 2D plane.
Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.

idea:
area = total_area - overlap_area
if two rectangles are not overlapped, total area is sum of two rectangles
if overlapped, area = total_area - overlap_area because total_area has 2 overlap_area, must subtract one

top, right get max
bottom, left get min since they are negative numbers

*/
public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int area = (C - A) * (D - B) + (G - E) * (H - F);
		if ( E >= C || F >= D || B >= H || A >= G ) {
			return area;
		}

		return area - ( Math.min(G, C) - Math.max(A, E) ) * ( Math.min(D, H) - Math.max(B, F) );
    }
    // self written
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = (C-A) * (D-B) + (G-E) * (H-F);
        if (A > G || C < E || B > H || D < F) {
            return area;
        }
        
        return area - Math.abs( (Math.min(D, H) - Math.max(B, F)) *  (Math.min(C, G) - Math.max(A, E)) );  
    }
}