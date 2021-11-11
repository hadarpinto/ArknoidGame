import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * A class to help us track the user's score in game, and
 * to show to the user his score live.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Point point;

    /**
     * constructor for the class.
     * @param score score
     * @param point point
     */
    public ScoreIndicator(Counter score, Point point) {
        this.score = score;
        this.point = point;
    }

    /**
     * draw the sprite to the screen.
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String scoreSTR = Integer.toString(score.getValue());
        d.drawText((int) point.getX(), (int) point.getY(), "Score: " + scoreSTR, 15);

    }
    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
    /**
     * adding the sprite to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
