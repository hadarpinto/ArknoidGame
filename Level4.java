import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * Hold information about the level in the game.
 */
public class Level4 implements LevelInformation {
    public static final int BALLS_NUM = 3;
    public static final int PAD_SPEED = 15;
    public static final int PAD_WIDTH = 100;
    public static final int ANGLE = 30;
    public static final int BALL_SPEED = 3;
    public static final double BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BLOCKS_NUM = 105;
    public static final int START_X = 25;
    public static final int START_Y = 200;
    /**
     * Number of balls getter.
     * @return balls number.
     */
    @Override
    public int numberOfBalls() {
        return BALLS_NUM;
    }
    /**
     * Initializing velocities for the balls.
     * @return list.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>(BALLS_NUM);
        for (int i = 0; i < BALLS_NUM; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-ANGLE + i * ANGLE, BALL_SPEED));
        }
        return velocities;
    }

    /**
     * paddle speed getter.
     * @return speed.
     */
    @Override
    public int paddleSpeed() {
        return PAD_SPEED;
    }
    /**
     * Paddle width getter.
     * @return width.
     */
    @Override
    public int paddleWidth() {
        return PAD_WIDTH;
    }

    /**
     * Level's name.
     * @return name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }
    /**
     * Creating the background.
     * @return sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackGround4();
    }

    /**
     * creating blocks.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>(numberOfBlocksToRemove());
        final Color[] colors = {Color.CYAN, Color.pink, Color.white, Color.green, Color.yellow, Color.red,
                Color.darkGray};
        final int numRows = 7;
        final int numBlockInRow = 15;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numBlockInRow; j++) {
                Block b = new Block(new Rectangle(new Point(START_X + j * BLOCK_WIDTH, START_Y - i * BLOCK_HEIGHT),
                        BLOCK_WIDTH, BLOCK_HEIGHT), colors[i]);
                list.add(b);
            }
        }
        return list;
    }
    /**
     * number of blocks getter.
     * @return number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_NUM;
    }
}
