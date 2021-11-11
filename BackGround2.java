import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The background for level 2.
 */
public class BackGround2 implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
        d.setColor(Color.yellow);
        for (int i = 0; i < 80; i++) {
            d.drawLine(100, 150, 25 + i * 10, 250);
        }
        d.setColor(Color.getHSBColor(130, 12, 12));
        d.fillCircle(100, 150, 60);
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 150, 40);
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
