import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * Block are "made" from rectangle, and color. Hitting a block will
 * lead to change of direction in the ball course.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields
    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color;
    // constructor
    /**
     * constructor with block's features.
     *
     * @param rectangle x point.
     * @param color color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    // implements
    /**
     * A method from Collidable interface.
     * Return the "collision shape" of the object.
     *
     * @return the block's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * A method from Collidable interface.
     * notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint collision point.
     * @param currentVelocity current velocity.
     * @param hitter ball.
     *
     * @return new velocity according to the location of the collision point.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        if (rectangle.getUpperLine().pointOnLine(collisionPoint)
                || rectangle.getLowerLine().pointOnLine(collisionPoint)) {
            dy = -dy;
        }
        if (rectangle.getRightLine().pointOnLine(collisionPoint)
                || rectangle.getLeftLine().pointOnLine(collisionPoint)) {
            dx = -dx;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    /**
     * A method from Sprite interface.
     * The block knows to draw the itself on the given DrawSurface.
     *
     * @param surface of GUI
     */
    public void drawOn(DrawSurface surface) {
        int x1 = (int) rectangle.getUpperLeft().getX();
        int y1 = (int) rectangle.getUpperLeft().getY();
        int width = (int) (rectangle.getWidth());
        int height = (int) (rectangle.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle(x1, y1, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x1, y1, width, height);
    }
    /**
     * A method from Sprite interface.
     * Will conduct the next move of the block.
     *
     */
    @Override
    public void timePassed() {

    }
    /**
     * adding the block to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Adds a listener to the list.
     * @param hl hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a listener from the list.
     * @param hl hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies the listener classes about a hit.
     * @param hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Removes this block from game.
     * @param game game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}

