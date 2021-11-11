/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * CollisionInfo holds information of the collision point, and the
 * object that we collided into.
 */
public class CollisionInfo {
    //fields
    private Point collisionPoint;
    private Collidable object;
    // constructor
    /**
     * constructor with information needed about a collision.
     *
     * @param p point of collision.
     * @param object the object we collided into.
     */
    public CollisionInfo(Point p, Collidable object) {
        this.collisionPoint = p;
        this.object = object;
    }
    /**
     * get collision point.
     *
     * @return point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    /**
     * get collidable object.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}