/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 *
 * A point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point.
 */

public class Point {
    static final double DEF_POINT = 0; // default point
    // fields
    private double xPoint;
    private double yPoint;
    /**
     * constructor with x and y values.
     *
     * @param x value
     * @param y value
     */
    public Point(double x, double y) {
        this.xPoint = x;
        this.yPoint = y;
    }
    /**
     * constructor with default x and y values.
     */
    public Point() {
        this(DEF_POINT, DEF_POINT);
    }

    /**
     * distance between two points.
     *
     * @param other the point to measure distance from.
     * @return 'double distance' this point to the other point
     */
    public double distance(Point other) {
        if (other == null) {
            return Double.POSITIVE_INFINITY;
        }
        double x2 = other.xPoint;
        double y2 = other.yPoint;
        double distanceSquare = ((xPoint - x2) * (xPoint - x2)) + ((yPoint - y2) * (yPoint - y2));
        double distanceRoot = Math.sqrt(distanceSquare);

        return distanceRoot;
    }

    /**
     * check if the points are identical.
     *
     * @param other the point to make the check with
     * @return 'true' if the this point and other are equal.
     */
    public boolean equals(Point other) {
        return ((xPoint == other.xPoint) && (yPoint == other.yPoint));
    }

    /**
     * The top element in the stack.
     *
     * @return x value
     */
    public double getX() {
        return xPoint;
    }
    /**
     * The top element in the stack.
     *
     * @return y value
     */
    public double getY() {
        return yPoint;
    }
}