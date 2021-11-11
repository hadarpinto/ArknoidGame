/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * A line connects two points - a start point and an end point. Lines have lengths,
 * and may intersect with other lines. It can also tell if it is the same as another line segment.
 */
public class Line {
    public static final int CO_LINEAR = 0;
    public static final int CLOCK_WISE = 1;
    public static final int COUNTER_CLOCK_WISE = 2;
    //fields
    private Point start;
    private Point end;
    /**
     * constructor with start and end point of a line.
     *
     * @param start start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * constructor of assigning points with x and y values.
     *
     * @param x1 x value of start
     * @param y1 y value of start
     * @param x2 x value of end
     * @param y2 y value of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The length of a line segment.
     *
     * @return 'double' length of a line.
     */
    public double length() {
        double len = start.distance(end);
        return len;
    }

    /**
     * The middle point on line segment.
     *
     * @return 'Point' middle point on segment.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * The start point of a segment.
     *
     * @return the start point of a segment
     */
    public Point start() {
        return start;
    }

    /**
     * The end point of a segment.
     *
     * @return the end point of a segment
     */
    public Point end() {
        return end;
    }

    /**
     * The slope of this line segment.
     *
     * @return the slope of this line segment
     */
    public double slope() {
        double slope;
        if (start.getX() == end.getX()) {
            slope = Double.POSITIVE_INFINITY;
        } else { // m = (y2-y1) / (x2-x1)
            slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
        return slope;
    }

    /**
     * Check if a point is on segment between two points.
     *
     * @param a start point of segment
     * @param b the point that we check
     * @param c end point of a segment
     * @return 'true' if point b is on segment ac including point a and point c
     */
    public boolean onSegment(Point a, Point b, Point c) {
        if (b.getX() <= Math.max(a.getX(), c.getX())
                && b.getX() >= Math.min(a.getX(), c.getX())
                && (b.getY() <= Math.max(a.getY(), c.getY()))
                && b.getY() >= Math.min(a.getY(), c.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Check if a point is on segment between two points, for parallel case.
     *
     * @param a start point of segment
     * @param b the point that we check
     * @param c end point of a segment
     * @return 'true' if point b is on segment ac not including point a and point c
     */
    public boolean isBetween(Point a, Point b, Point c) {
        if (b.getX() <= Math.max(a.getX(), c.getX())
                && b.getX() >= Math.min(a.getX(), c.getX())
                && (b.getY() < Math.max(a.getY(), c.getY()))
                && b.getY() > Math.min(a.getY(), c.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Relation of 3 points. Relation between 3 points can be co-linear, clock wise,
     * or counter-clock wise.
     *
     * @param a first point
     * @param b second point
     * @param c third point
     * @return '0' if the relation is co-linear
     * '1' if the relation is clock wise
     * '2' if the relation is
     */
    public int relation(Point a, Point b, Point c) {
        double value = (b.getY() - a.getY()) * (c.getX() - b.getX())
                - (b.getX() - a.getX()) * (c.getY() - b.getY());

        if (value == 0) { //co-linear
            return CO_LINEAR;
        }
        return (value > 0) ? CLOCK_WISE : COUNTER_CLOCK_WISE; // clock or counter-clock wise
    }

    /**
     * Check if this line and other line are intersecting, using
     * relations between 3 points from the lines.
     * lines will intersect if :
     * – (this.start, this.end, other.end) and (this.start, this.end, other.start) have different relations
     * and
     * – (other.start, other.end, this.start) and (other.start,other.end, this.end) have different relations
     *
     * @param other the line to check intersection with
     * @return 'true' if lines are intersecting
     */
    public boolean isIntersecting(Line other) {
        //find the four relations needed
        int r1 = relation(start, end, other.start);
        int r2 = relation(start, end, other.end);
        int r3 = relation(other.start, other.end, start);
        int r4 = relation(other.start, other.end, end);

        // main statement of intersection
        if (r1 != r2 && r3 != r4) {
            return true;
        }
        // Special Cases - lines may lie on each other
        if (r1 == 0 && onSegment(start, other.start, end)) {
            return true;
        }
        if (r2 == 0 && onSegment(start, other.end, end)) {
            return true;
        }
        if (r3 == 0 && onSegment(other.start, start, other.end)) {
            return true;
        }
        if (r4 == 0 && onSegment(other.start, end, other.end)) {
            return true;
        }
        return false; // Doesn't fall in any of the above cases
    }

    /**
     * Calculates b from the line equation y = mx + b.
     *
     * @return 'double' b from linear equation
     */
    public double linearEquation() {
        //linear equation y=mx+b
        double m = this.slope();
        double x = start.getX();
        double y = start.getY();

        return y - (m * x);
    }

    /**
     * For two intersecting vertical segments, will return
     * point of intersection only if segments are intersecting with one point.
     *
     * @param other line
     * @return 'Point' of intersection
     */
    public Point equalSlopeIntersection(Line other) {
        if (isEdge(other.start)
                && !isBetween(start, other.end, end)
                && !isBetween(other.start, start, other.end)
                && !isBetween(other.start, end, other.end)) {
            return other.start;
            // line are intersecting, check if they have one point of intersection
        } else if (isEdge(other.end)
                && !isBetween(start, other.start, end)
                && !isBetween(other.start, start, other.end)
                && !isBetween(other.start, end, other.end)) {
            return other.end;
        }
        return null;
    }

    /**
     * The point of intersection between two lines.
     * if lines lie on each other, there is no intersection point.
     *
     * @param other . the other lines to check intersection point with.
     * @return 'Point' of intersection.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }

        double b1 = this.linearEquation();
        double m1 = this.slope();
        double b2 = other.linearEquation();
        double m2 = other.slope();

        // when the segment other is a point
        if (other.start.equals(other.end)) {
            return other.start;
        }
        // when this segment is a point
        if (start.equals(end)) {
            return start;
        }
        // when slopes are equal and not infinity
        // line are intersecting, check if they have one point of intersection
        if (m1 == m2 && m1 != Double.POSITIVE_INFINITY) {
            return equalSlopeIntersection(other);
        }
        // when this segment parallel to y
        if (m1 == Double.POSITIVE_INFINITY) {
            // intersection exist, check if they overlap
            if (m2 != Double.POSITIVE_INFINITY) {
                // segment crossing parallel line
                return calculatePoint(start.getX(), m2, b2);
            }
            // line are intersecting, check if they have one point of intersection
            return equalSlopeIntersection(other);
        }
        // when other segment parallel to y
        if (m2 == Double.POSITIVE_INFINITY) {
            return calculatePoint(other.start.getX(), m1, b1);
        }
        // general case
        double interX = (b2 - b1) / (m1 - m2);
        return calculatePoint(interX, m1, b1);
    }

    /**
     * Check if given point is the start or end point of this segment.
     *
     * @param point point
     * @return 'true' if point is equal to start or end.
     */
    public boolean isEdge(Point point) {
        return start.equals(point) || end.equals(point);
    }

    /**
     * Calculate point for vertical case.
     *
     * @param x of vertical segment
     * @param m slope
     * @param b b
     * @return 'Point' of intersection.
     */
    public Point calculatePoint(double x, double m, double b) {
        double y = m * x + b;
        return new Point(x, y);
    }

    /**
     * Check if this line equals to other line,
     * if line is a point will return false.
     *
     * @param other the line to check equality with.
     * @return 'true' if lines are equal.
     */
    public boolean equals(Line other) {
        return (((start.equals(other.start)) && (end.equals(other.end))
                && start.distance(end) != 0 && other.start.distance(other.end) != 0)
                || ((start.equals(other.end)) && (end.equals(other.start)) && start.distance(end) != 0
                && other.start.distance(other.end) != 0));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect rectangle.
     * @return 'Point' closest point of intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(start, end);
        //get list of intersection points with rect
        java.util.List<Point> points = rect.intersectionPoints(line);
        // no object means no intersections
        if (points.isEmpty()) {
            return null;
        }
        // finding closest point using distance
        double minDis = start.distance(points.get(0));
        Point closestPoint = points.get(0);
        for (Point point: points) {
            if (minDis > start.distance(point)) {
                minDis = start.distance(point);
                closestPoint = point;
            }
        }
        return closestPoint;
    }
    /**
     * Check if a given point is on this line.
     *
     * @param point point.
     * @return 'true' if point on this line.
     */
    public boolean pointOnLine(Point point) {
        return onSegment(this.start, point, this.end);
    }
}