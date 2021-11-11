import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * Hold information about the level in the game.
 */
public class Level3  implements LevelInformation {
    public static final int BALLS_NUM = 2;
    public static final int PAD_SPEED = 15;
    public static final int PAD_WIDTH = 100;
    public static final int ANGLE = 30;
    public static final int BALL_SPEED = 3;
    public static final double BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCKS_NUM = 40;
    public static final int WIDTH = 800;
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
        for (int i = 1; i <= BALLS_NUM / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(ANGLE * i, BALL_SPEED));
        }
        for (int i = 1; i <= BALLS_NUM / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-ANGLE * i, BALL_SPEED));
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
        return "Green 3";
    }
    /**
     * Creating the background.
     * @return sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackGround3();
    }

    /**
     * Side method to create blocks.
     * @param p point.
     * @param blocksNum number of blocks.
     * @return list.
     */
    private List<Rectangle> createBlocks(Point p, int blocksNum) {
        List<Rectangle> blocks = new ArrayList<>();
        List<Point> upperLefts = new ArrayList<>();

        double x = p.getX();
        double y = p.getY();

        for (int i = 0; i < blocksNum; i++) {
            upperLefts.add(new Point(x, y));
            x = x + BLOCK_WIDTH;
        }
        for (int i = 0; i < blocksNum; i++) {
            blocks.add(new Rectangle(upperLefts.get(i), BLOCK_WIDTH, BLOCK_HEIGHT));
        }
        return blocks;
    }
    /**
     * creating blocks.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        final int numRows = 5;
        final int blockAmount = 6;
        final double startY = 250;
        final double startX = (WIDTH - BLOCK_HEIGHT - blockAmount * BLOCK_WIDTH);
        final Color[] colors = {Color.white, Color.blue, Color.yellow, Color.red, Color.darkGray};
        for (int row = 0; row < numRows; row++) {
            Point rowStart = new Point(startX - row * BLOCK_WIDTH, startY - row * BLOCK_HEIGHT);
            List<Rectangle> blockPositions = createBlocks(rowStart, blockAmount + row);
            for (int i = 0; i < blockPositions.size(); i++) {
                Block block = new Block(blockPositions.get(i), colors[row]);
                list.add(block);
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
