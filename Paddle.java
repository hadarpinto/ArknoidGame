import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    public static final int PADDLE_SPEED = 10;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_WIDTH = 170;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation information;
    private Rectangle paddle;
    private int screenWidth;
    private int screenHeight;

    //constructor
    /**
     * constructor that is expecting keyboard press of user, and object of a game.
     *  @param keyboard keyboard.
     * @param game game.
     * @param info info.
     */
    public Paddle(KeyboardSensor keyboard, GameLevel game, LevelInformation info) {
        this.keyboard = keyboard;
        this.information = info;
        addToGame(game);
        double y = screenHeight - GameLevel.BORDER_THICKNESS - PADDLE_HEIGHT;
        double x = (screenWidth - info.paddleWidth()) / 2.0;
        this.paddle = new Rectangle(new Point(x, y), info.paddleWidth(), PADDLE_HEIGHT);
    }

    /**
     * A method from Sprite interface.
     * The paddle knows to make the next step when called.
     *
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed((KeyboardSensor.RIGHT_KEY))) {
            moveRight();
        }
    }

    /**
     * A method from Sprite interface.
     * The paddle knows to draw the itself on the given DrawSurface.
     *
     * @param d surface of GUI
     */
    public void drawOn(DrawSurface d) {
        recToPad().drawOn(d);
    }

    /**
     * A method from Collidable interface.
     * Return the "collision shape" of the object.
     *
     * @return the paddle's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return recToPad().getCollisionRectangle();
    }
    /**
     * A method from Collidable interface.
     * notify the object that we collided with it at collisionPoint with
     * a given velocity. For the paddle there are five unique parts, that the ball
     * is changing it's trajectory angle according to the part on the paddle that the collision
     * point is on it.
     *
     * @param collisionPoint collision point.
     * @param currentVelocity current velocity.
     * @param ball ball.
     *
     * @return new velocity according to the location of the collision point.
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        double cx = collisionPoint.getX();
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        double dis = paddle.getWidth() / 5;
        double xValue = paddle.getUpperLeft().getX();
        double distanceSquare = ((dx) * (dx)) + ((dy) * (dy));
        double speed = Math.sqrt(distanceSquare);
        double angle;

        Velocity v = new Velocity(dx, dy);

        if (cx < xValue + dis) {  // 1/5
            angle = 300;
        } else if (cx < xValue + 2 * dis) { // 2/5
            angle = 330;
        } else if (cx < xValue + 3 * dis) { // 3/5
            return new Velocity(dx, -dy);
        } else if (cx < xValue + 4 * dis) { // 4/5
            angle = 30;
        } else {    // 5/5
            angle = 60;
        }
        return v.fromAngleAndSpeed(angle, speed);
    }

    /**
     * A method to move the paddle left in the screen.
     *
     */
    public void moveLeft() {
        if (!(paddle.getUpperLeft().getX() <= GameLevel.BORDER_THICKNESS)) {
            double x = paddle.getUpperLeft().getX() - information.paddleSpeed();
            double y = paddle.getUpperLeft().getY();
            this.paddle = new Rectangle(new Point(x, y), information.paddleWidth(), PADDLE_HEIGHT);
        }
    }
    /**
     * A method to move the paddle right in the screen.
     *
     */
    public void moveRight() {
        if (!(paddle.getUpperLeft().getX() + paddle.getWidth() >= screenWidth - GameLevel.BORDER_THICKNESS)) {
            double x = paddle.getUpperLeft().getX() + information.paddleSpeed();
            double y = paddle.getUpperLeft().getY();
            this.paddle = new Rectangle(new Point(x, y), information.paddleWidth(), PADDLE_HEIGHT);
        }
    }


    /**
     * adding the paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        screenWidth = g.returnWidth();
        screenHeight = g.returnHeight();
    }

    /**
     * converting the paddle rectangle to a block.
     *
     * @return  paddle as a block.
     */
    public Block recToPad() {
        return new Block(this.paddle, Color.YELLOW);
    }
}