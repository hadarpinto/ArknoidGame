import java.util.List;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * This interface hold information about the levels in the game.
 */
public interface LevelInformation {
    /**
     * number of balls.
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * The initialized velocity of the balls.
     * @return list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle speed getter.
     * @return speed.
     */
    int paddleSpeed();

    /**
     * width getter.
     * @return width.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite.
     */
    Sprite getBackground();

    /**
     *  The Blocks that make up this level, each block contains
     *      its size, color and location.
     * @return list.
     */
    List<Block> blocks();

    /**
     *  Number of blocks that should be removed
     *      before the level is considered to be "cleared".
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}
