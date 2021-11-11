import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * Balls have size (radius), color, and location (a Point). Balls also know
 * the object the collide to, and change their path accordingly.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Paddle paddle = null;
    private GameEnvironment gameEnvironment;

    /**
     * constructor with balls features.
     *
     * @param x     x point
     * @param y     y point
     * @param r     radius
     * @param color color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.color = color;
        this.radius = r;
    }

    /**
     * constructor with balls features. receives it's point
     * with a point type.
     *
     * @param center point
     * @param r      radius
     * @param color  color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this(center.getX(), center.getY(), r, color);
    }
    /**
     * A method from Sprite interface.
     * The ball knows to draw the itself on the given DrawSurface.
     *
     * @param surface of GUI
     */
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int r = this.getSize();
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, r);
    }
    /**
     * A method from Sprite interface.
     * The ball knows to make the next step when called.
     *
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the game environment, allowing the ball to receive information of
     * object inside the game.
     *
     * @param environment the game environment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * set paddle in the ball's fields.
     * using this information, the balls will recognize when it hits the paddle
     * specifically.
     *
     * @param p the paddle of the game.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * get X of ball center.
     *
     * @return x of ball center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * get Y of ball center.
     *
     * @return y of ball center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * get radius size of ball.
     *
     * @return radius size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * get color of ball.
     *
     * @return color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * set new velocity of ball.
     *
     * @param v new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set new velocity.
     *
     * @param dx dx
     * @param dy dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * get velocity of a ball.
     *
     * @return this velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move the ball another step on the surface.
     */
    public void moveOneStep() {
        Point newCenter = velocity.applyToPoint(center);
        Line trajectory = new Line(center, newCenter);
        double newX = newCenter.getX();
        double newY = newCenter.getY();
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo != null) {
            newX = collisionInfo.collisionPoint().getX();
            newY = collisionInfo.collisionPoint().getY();
            if (newX > center.getX()) { //hitting left bound of block
                newX = newX - 1;
            } else if (newX < center.getX()) { //hitting right bound of block
                newX = newX + 1;
            }

            Collidable object = collisionInfo.collisionObject(); // when ball is inside paddle
            if (paddle != null && object == paddle) {
                newY = paddle.getCollisionRectangle().getUpperLeft().getY() - 1;
            } else if (newY > center.getY()) { //hitting upper bound of block
                newY = newY - 1;
            } else if (newY < center.getY()) { //hitting lower bound of block
                newY = newY + 1;
            }
            velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
        }

        center = new Point(newX, newY);
    }
    /**
     * adding the ball to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing ball from the game.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}