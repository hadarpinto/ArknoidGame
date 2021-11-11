import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * GameLevel hold the sprites and the collidables, and will be in charge of the animation
 * that the user is playing.
 */
public class GameLevel implements Animation {
    public static final int BALL_RADIUS = 5;
    public static final int BORDER_THICKNESS = 25;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 20;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private int width;
    private int height;
    private String title;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation information;

    /**
     * constructor.
     * @param info information.
     * @param ks key.
     * @param ar animation runner.
     * @param score score representation.
     * @param gui gui.
     */
    public GameLevel(LevelInformation info, KeyboardSensor ks, AnimationRunner ar, Counter score, GUI gui) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.title = "Hadar's Arkanoid";
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.lives = new Counter();
        this.score = score;
        this.gui = gui;
        this.runner = ar;
        this.keyboard = ks;
        this.information = info;
        this.running = true;
    }

    /**
     * adds collidables object to the game environment field.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds sprites object to the game's sprite field.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * creating block to be used as bound of screen.
     *
     * @return list of rectangles.
     */
    private List<Rectangle> screenBounds() {
        Point p1 = new Point(0, BORDER_THICKNESS); // set four upper left points of bounds
        Point p2 = new Point(0, BORDER_THICKNESS);
        Point p3 = new Point(width - BORDER_THICKNESS, BORDER_THICKNESS);

        Rectangle r1 = new Rectangle(p1, width, BORDER_THICKNESS);
        Rectangle r2 = new Rectangle(p2, BORDER_THICKNESS, height - BORDER_THICKNESS);
        Rectangle r3 = new Rectangle(p3, BORDER_THICKNESS, height - 2 * BORDER_THICKNESS + 40);

        List<Rectangle> bounds = new ArrayList<>(); // return list of the rectangle bounds
        bounds.add(r1);
        bounds.add(r2);
        bounds.add(r3);

        return bounds;
    }

    /**
     * creating row of blocks that will be used as obstacles.
     *
     * @param blocksNum number of blocks.
     * @param p         point.
     * @return list of rectangles.
     */
    private List<Rectangle> blocks(Point p, int blocksNum) {
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
     * set the obstacles in the user animation game.
     *
     * @param remover instance of Removeblock.
     * @param s       instance of score indicator.
     */

    private void createBlockInARowInGame(BlockRemover remover, ScoreTrackingListener s) {
        for (Block block : information.blocks()) {
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(s);
            this.blockCounter.increase(1);
        }
    }

    /**
     * set the bounds of the screen.
     */
    private void createBoundsInGame() {
        List<Rectangle> borders = screenBounds();
        for (int i = 0; i < borders.size(); i++) {
            Block block = new Block(borders.get(i), Color.gray);
            block.addToGame(this);
        }
    }

    /**
     * Creates ball.
     * @param point ball point.
     * @param velocity velocity.
     * @param paddle paddle.
     */
    private void createBallInGame(Point point, Velocity velocity, Paddle paddle) {
        Ball ball = new Ball(point, BALL_RADIUS, Color.WHITE);
        ball.setGameEnvironment(this.environment);
        ball.setVelocity(velocity);
        ball.setPaddle(paddle);
        ball.addToGame(this);
        ballCounter.increase(1);
    }

    /**
     * create paddle for the game.
     *
     * @return Paddle.
     */
    private Paddle createPaddleInGame() {
        biuoop.KeyboardSensor key = gui.getKeyboardSensor();
        Paddle user = new Paddle(key, this, information);
        return user;
    }

    /**
     * The bottom block that recognizes ball hit, in order to remove the ball.
     *
     * @param b ball
     */
    private void deathRegion(BallRemover b) {
        Point p = new Point(BORDER_THICKNESS, height - BORDER_THICKNESS);
        Rectangle r = new Rectangle(p, width - BORDER_THICKNESS, BORDER_THICKNESS);
        Block deathRegion = new Block(r, Color.BLACK);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(b);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        sprites.addSprite(this.information.getBackground());
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreTrackingListener scoreTrackingListener =
                new ScoreTrackingListener(this.score);
        // paddle
        Paddle paddle = createPaddleInGame();
        // three balls
        for (Velocity velocity : information.initialBallVelocities()) {
            createBallInGame(new Point(400, 550), velocity, paddle);
        }
        //death region
        deathRegion(ballRemover);
        // bounds
        createBoundsInGame();
        //rows
        createBlockInARowInGame(blockRemover, scoreTrackingListener);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, new Point(370, 15));
        LevelNameIndicator levelNameIndicator =
                new LevelNameIndicator(new Point(600, 15), this.information.levelName());
        levelNameIndicator.addToGame(this);
        scoreIndicator.addToGame(this);
        sprites.addSprite(scoreIndicator);
        sprites.addSprite(levelNameIndicator);
    }

    /**
     * run the game with an animation loop.
     */

    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }
    /**
     * Remove collidable from game.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite from game.
     *
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * returns width value.
     *
     * @return width.
     */
    public int returnWidth() {
        return this.width;
    }

    /**
     * Return height value.
     *
     * @return height.
     */
    public int returnHeight() {
        return this.height;
    }

    /**
     * Running one frame to the gui.
     * @param d drawface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        } else if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }
    /**
     * Changing the condition to stop frame.
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Block counter getter.
     * @return value of blocks.
     */
    public int getBlockCounter() {
        return this.blockCounter.getValue();
    }

    /**
     * Ball counter getter.
     * @return number of balls.
     */
    public int getBallCounter() {
        return this.ballCounter.getValue();
    }
}