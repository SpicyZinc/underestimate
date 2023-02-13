/*
https://imgur.com/a/ygK9wfB

idea:
规律
swap p, q 可以 得到新坐标
{5, 7}
{7, 5}
{4, 2}
{2, 4}

or p.x q.y 组合得到新坐标


note, /4

Adam's AIM online testing, failed

https://stackoverflow.com/questions/2303278/find-if-4-points-on-a-plane-form-a-rectangle
https://www.geeksforgeeks.org/find-number-of-rectangles-that-can-be-formed-from-a-given-set-of-coordinates/

*/

import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if (this.x == p.x) {
            return this.y - p.y;
        }

        return this.x - p.x;
    }
}

class FindNumberOfRectanglesFromGivenPoints {
    public static void main(String[] args) {
        Point[] points = new Point[6];
        points[0] = new Point(-3, 0);
        points[1] = new Point(0, -3);
        points[2] = new Point(0, 3);
        points[3] = new Point(3, 0);
        points[4] = new Point(0, 0);
        points[5] = new Point(3, 3);

        FindNumberOfRectanglesFromGivenPoints eg = new FindNumberOfRectanglesFromGivenPoints();
        int count = eg.countRectangles(points);

        System.out.println("Rectangles count == " + count);
    }

    public int countRectangles(Point[] points) {

        TreeSet<Point> ts = new TreeSet<>();
        for (Point p : points) {
            ts.add(p);
        }

        int len = points.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                Point pi = points[i];
                Point pj = points[j];
                // The other two points
                Point p3 = new Point(pi.x, pj.y);
                Point p4 = new Point(pj.x, pi.y);

                // Another possible two points
                Point p1 = new Point(pi.y, pi.x);
                Point p2 = new Point(pj.y, pj.x);
                // if not the same point
                if (pi.x == pj.x && pi.y != pj.y) {
                    if (ts.contains(p3) && ts.contains(p4) || ts.contains(p1) && ts.contains(p2)) {
                        count++;
                    }
                }
            }
        }

        return count / 4;
    }

    public int getDistance(Point a, Point b) {
        return (a.y - b.y) * (a.y - b.y) + (a.x - b.x) * (a.x - b.x);
    }
}
