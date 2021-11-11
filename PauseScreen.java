import biuoop.DrawSurface;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * Hold the animation to pause the game.
 */
public class PauseScreen implements Animation {
    /**
     * do one frame.
     * @param d drawface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * changing boolean statement.
     * @return true / false.
     */
    public boolean shouldStop() {
        return false;
    }
}
