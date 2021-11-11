/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * interface indicate that objects that implement it are notified about hits.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit block
     * @param hitter ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}