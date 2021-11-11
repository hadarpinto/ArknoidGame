import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * The class incharge of the game flow, switching between levels, tracking scores
 * and progress of the player.
 */
public class GameFlow {
    private final GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score = new Counter();

    /**
     * constructor.
     * @param ar animation runner.
     * @param ks key.
     * @param gui gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.gui = gui;
    }

    /**
     * Running the levels given as arguments / in main method.
     * @param levels levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score, this.gui);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getBallCounter() == 0) {
                displayEndScreen(false);
                return;
            }
        }
        displayEndScreen(true);
    }

    /**
     * Displaying win screen or lose screen.
     * @param winning true or false.
     */
    private void displayEndScreen(boolean winning) {
        Animation endScreen = new EndScreen(score, winning);
        String key = KeyboardSensor.SPACE_KEY;
        Animation keyStoppable = new KeyPressStoppableAnimation(keyboardSensor, key, endScreen);
        animationRunner.run(keyStoppable);
    }
}

