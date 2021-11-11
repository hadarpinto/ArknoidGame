import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The background for level 1.
 */
public class BackGround1 implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 162, 30);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 142, 400, 25);
    }
    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
    /**
     * Adding the sprite to the game.
     * @param game game.
     */
    @Override
    public void addToGame(GameLevel game) {
    }
}
