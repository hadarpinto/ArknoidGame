import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * During the game, there are going to be many objects a Ball can collide with.
 * The GameEnvironment class will be a collection of such things
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidablesArr;

    //constructor
    /**
     * constructor to initialize a list in the field.
     */
    public GameEnvironment() {
        this.collidablesArr = new ArrayList<>();
    }
    /**
     * add the given collidable to the environment.
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidablesArr.add(c);
    }
    /**
     * Method for return the information about the closest collision that is going to occur.
     *
     * @param trajectory object course.
     *
     * @return collision info of the closest point of collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisions = new ArrayList<>();
        List<Collidable> blocks = new ArrayList<>();
        List<Collidable> tmpArr = new ArrayList<>(this.collidablesArr);
        // save collision point, and block with two arrays on the same index
        for (int i = 0; i < tmpArr.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(tmpArr.get(i).getCollisionRectangle()) != null) {
                collisions.add(trajectory
                        .closestIntersectionToStartOfLine(tmpArr.get(i).getCollisionRectangle()));
                blocks.add(tmpArr.get(i));
            }
        }
        if (collisions.isEmpty()) { // no collisions
            return null;
        }
        Collidable closestBlock = blocks.get(0);
        Point closestPoint = collisions.get(0);
        double distance = trajectory.start().distance(closestPoint);
        // look for closest collision point
        for (int j = 0; j < collisions.size(); j++) {
            if (trajectory.start().distance(collisions.get(j)) < distance) {
                closestPoint = collisions.get(j);
                closestBlock = blocks.get(j);
            }
        }
        return new CollisionInfo(closestPoint, closestBlock);
    }

    /**
     * Removes collidable from this environment.
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesArr.remove(c);
    }
}