/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * A method to return the "collision shape" of the object.
     *
     * @return the rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param currentVelocity current velocity.
     * @param collisionPoint collision point.
     * @param hitter ball.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}