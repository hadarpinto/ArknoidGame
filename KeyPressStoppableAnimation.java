import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Hadar Pinto
 * ID:316460146
 *
 * Class using decorator pattern to display pause screen or end screen.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor pressed key.
     * @param key key.
     * @param animation animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Displaying one frame.
     * @param d drawface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Changing the condition to stop frame.
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}