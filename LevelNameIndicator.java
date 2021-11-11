import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * A class to help us track the user's progress in game, and
 * to show to the user level's name he is playing.
 */
public class LevelNameIndicator implements Sprite {
    private Point point;
    private String name;

    /**
     * constructor.
     * @param p point.
     * @param s name.
     */
    public LevelNameIndicator(Point p, String s) {
        this.point = p;
        this.name = s;
    }

    /**
     * Draws on surface.
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) point.getX(), (int) point.getY(), "Level: " + name, 15);
    }

    /**
     * notifying time has passed and ready for next move.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add it self to the game.
     * @param game game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
