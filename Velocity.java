/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx;
    private double dy;
    /**
     * constructor with configurable dx and dy.
     *
     * @param dx change x position
     * @param dy change y position
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p the point to move.
     * @return 'Point' of the new position.
     */
    public Point applyToPoint(Point p) {
        double x = this.dx + p.getX();
        double y = this.dy + p.getY();

        return new Point(x, y);
    }
    /**
     * Gets dx.
     *
     * @return dx of velocity.
     */
    public double getDX() {
        return this.dx;
    }
    /**
     * Gets dy.
     *
     * @return dy of velocity.
     */
    public double getDY() {
        return this.dy;
    }
    /**
     * Creating velocity with angle and speed.
     *
     * @param angle angle
     * @param speed speed
     * @return 'Velocity' with with new settings.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radiusAngle = Math.toRadians(angle); // convert degrees to radians
        double newDx = speed * (Math.sin(radiusAngle));
        double newDy = speed * (Math.cos(radiusAngle));
        return new Velocity(newDx, -newDy);
    }
}