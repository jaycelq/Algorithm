/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {
    
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopOrder();
    private class SlopOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            if (slopeTo(p) == slopeTo(q))
                return 0;
            else if (slopeTo(p) < slopeTo(q))
                return -1;
            else
                return 1;
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (y == that.y && x == that.x)
            return Double.NEGATIVE_INFINITY;
        else if (y == that.y)
            return +0.0;
        else if (x == that.x)
            return Double.POSITIVE_INFINITY;
        else
            return ((double) (that.y - y)) / ((double) (that.x - x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (y < that.y)
            return -1;
        else if (y == that.y && x < that.x)
            return -1;
        else if (y == that.y && x == that.x)
            return 0;
        else
            return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Point [] p = new Point[N];
        
        /* YOUR CODE HERE */
        for (int i = 0; i < N; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            p[i] = new Point(x, y);
        }
        
        Arrays.sort(p, p[0].SLOPE_ORDER);
        
        for (int i = 0; i < N; i++) {
            StdOut.println(p[i].toString());
        }
        
        Arrays.sort(p);
        
        for (int i = 0; i < N; i++) {
            StdOut.println(p[i].toString());
        }
        
    }
}
