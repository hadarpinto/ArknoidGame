import biuoop.DrawSurface;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The class displays the last screen for the player:
 * a winning screen or losing screen.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean winning;

    /**
     * constructor.
     * @param score score.
     * @param winning true/ false.
     */
    public EndScreen(Counter score, boolean winning) {
        this.score = score.getValue();
        this.winning = winning;
    }
    /**
     * Running one frame to the gui.
     * @param d drawface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        String txt = "You Won! ";
        if (!this.winning) {
            txt = "Game Over. ";
        }
        d.drawText(150, d.getHeight() / 2, txt + "Your score is " + score, 32);
    }
    /**
     * Changing the condition to stop frame.
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
