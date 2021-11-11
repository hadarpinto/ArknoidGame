import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * Hold information about the level in the game.
 */
public class Level1 implements LevelInformation {
    private final int ballsNum = 1;
    private final int paddleSpeed = 10;
    private final int paddleWidth = 70;
    public static final int ANGLE = 0;
    public static final int BALL_SPEED = 3;

    /**
     * Number of balls getter.
     * @return balls number.
     */
    @Override
    public int numberOfBalls() {
        return ballsNum;
    }

    /**
     * Initializing velocities for the balls.
     * @return list.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(ANGLE, BALL_SPEED));
        return velocities;
    }

    /**
     * Paddle speed getter.
     * @return paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }
    /**
     * Paddle width getter.
     * @return
     */
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }
    /**
     * Level's name.
     * @return name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Creating the background.
     * @return sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackGround1();
    }
    /**
     * creating blocks.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p = new Point(385, 150);
        Rectangle r = new Rectangle(p, 30, 30);
        Block b = new Block(r, Color.RED);
        blocks.add(b);
        return blocks;
    }
    /**
     * number of blocks getter.
     * @return number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
