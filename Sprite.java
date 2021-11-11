import biuoop.DrawSurface;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * This interface is to execute methods on the game's objects that
 * are drawn on the scree.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adding the sprite to the game.
     * @param game game.
     */
    void addToGame(GameLevel game);
}