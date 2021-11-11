import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * Rectangle class holds features about a rectangle. The class holds information about
 * rectangles lines, the upperleft point and with a given trajectory line, will return list of
 * intersection points.
 */
public class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * constructor to create a new rectangle with location and width/height.
     *
     * @param upperLeft upper left point.
     * @param width width size.
     * @param height height size.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    /**
     * A method that checks intersection points of a given line with
     * this rectangle.
     *
     * @param line simple line.
     *
     * @return list of intersection point with this rectangle.
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] rectLines = new Line[4];
        // set all lines in array
        rectLines[0] = this.getUpperLine();
        rectLines[1] = this.getLowerLine();
        rectLines[2] = this.getLeftLine();
        rectLines[3] = this.getRightLine();

        List intersectionPointsArr = new ArrayList<Point>();
        for (Line rectLine : rectLines) {
            if (rectLine.isIntersecting(line)) {
                intersectionPointsArr.add(rectLine.intersectionWith(line));
            }
        }
        return intersectionPointsArr;
    }

    /**
     * get width of this rectangle.
     *
     * @return width size.
    */
    public double getWidth() {
        return width;
    }
    /**
     * get height of this rectangle.
     *
     * @return height size.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Returns the line of the rectangle's upper segment.
     * @return a line.
     */
    public Line getUpperLine() {
        Point start = getUpperLeft();
        // end point
        double x = this.upperLeft.getX() + width;
        double y = this.upperLeft.getY();
        Point end = new Point(x, y);

        return new Line(start, end);
    }
    /**
     * Returns the line of the rectangle's lower segment.
     * @return a line.
     */
    public Line getLowerLine() {
        //start point
        double x1 = this.upperLeft.getX();
        double y1 = this.upperLeft.getY() + height;
        //end point
        double x2 = x1 + width;
        double y2 = y1;

        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        return new Line(start, end);
    }
    /**
     * Returns the line of the rectangle's left segment.
     * @return a line.
     */
    public Line getLeftLine() {
        Point start = getUpperLeft();
        //end point
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY() + height;
        Point end = new Point(x, y);

        return new Line(start, end);
    }
    /**
     * Returns the line of the rectangle's right segment.
     * @return a line.
     */
    public Line getRightLine() {
        //start point
        double x1 = this.upperLeft.getX() + width;
        double y1 = this.upperLeft.getY();
        //end point
        double x2 = x1;
        double y2 = y1 + height;

        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        return new Line(start, end);
    }
}