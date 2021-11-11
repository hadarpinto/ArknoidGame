/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * a Counter holds info about remaining items that it counts.
 * We can increase the counting, decrease and get its current value.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     * @param number number
     */

    void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number number.
     */

    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * subtract number from current count.
     * @return int value.
     */
    int getValue() {
        return this.counter;
    }
}
