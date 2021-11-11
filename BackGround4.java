import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The background for level 4.
 */
public class BackGround4 implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#1788d0"));
        d.fillRectangle(0, 25, d.getWidth(), d.getHeight());
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
        game.addSprite(this);

    }
}
