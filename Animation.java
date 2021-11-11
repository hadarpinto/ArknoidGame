import biuoop.DrawSurface;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The interface will set the methods controlling the animations
 * running in the game.
 */
public interface Animation {
    /**
     * Running one frame to the gui.
     * @param d drawface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Changing the condition to stop frame.
     * @return true or false.
     */
    boolean shouldStop();
}
