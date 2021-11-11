import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * Hold information about the level in the game.
 */
public class Level2 implements LevelInformation {
    public static final int BALLS_NUM = 10;
    public static final int PAD_SPEED = 5;
    public static final int PAD_WIDTH = 400;
    public static final int ANGLE = 16;
    public static final int BALL_SPEED = 3;
    public static final int START_X = 25;
    public static final int START_Y = 250;
    public static final double BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BLOCKS_NUM = 15;

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
        Velocity v = new Velocity(0, 0);

        for (int i = 1; i <= BALLS_NUM / 2; i++) {
            velocities.add(v.fromAngleAndSpeed(ANGLE * i, BALL_SPEED));
        }
        for (int i = 1; i <= BALLS_NUM / 2; i++) {
            velocities.add(v.fromAngleAndSpeed(-ANGLE * i, BALL_SPEED));
        }
        return velocities;
    }
    /**
     * Paddle speed getter.
     * @return paddle speed.
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
        return "Wide Easy";
    }
    /**
     * Creating the background.
     * @return sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackGround2();
    }
    /**
     * creating blocks.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int counter = 0;
        final Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != Color.green) {
                for (int j = 0; j < 2; j++) {
                    Point point = new Point(START_X + counter * BLOCK_WIDTH, START_Y);
                    Rectangle rectangle = new Rectangle(point, BLOCK_WIDTH, BLOCK_HEIGHT);
                    Block b = new Block(rectangle, colors[i]);
                    blocks.add(b);
                    counter += 1;
                }
            } else {
                for (int k = 0; k < 3; k++) {
                    Point point = new Point(START_X + counter * BLOCK_WIDTH, START_Y);
                    Rectangle rectangle = new Rectangle(point, BLOCK_WIDTH, BLOCK_HEIGHT);
                    Block b = new Block(rectangle, colors[i]);
                    blocks.add(b);
                    counter += 1;
                }
            }
        }
        return blocks;
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