import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 *  The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private double numOfSecond;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Counter counter;
    private boolean stop;

    /**
     * constructor.
     * @param numOfSeconds wait time.
     * @param countFrom number.
     * @param gameScreen screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.counter = new Counter();
        this.stop = false;
        for (int i = 0; i < countFrom; i++) {
            counter.increase(1);
        }
    }
    /**
     * Running one frame to the gui.
     * @param d drawface
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        String secondPresented = counter.getValue() + "";
        d.setColor(Color.white);
        d.drawText(400, 300, secondPresented, 30);
        Sleeper sleeper = new Sleeper();
        double wait = numOfSecond / (double) countFrom;
        if (counter.getValue() != countFrom) {
            sleeper.sleepFor((long) (wait * 1000));
        }
        counter.decrease(1);
        if (counter.getValue() == -1) {
            stop = true;
        }
    }
    /**
     * Changing the condition to stop frame.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
