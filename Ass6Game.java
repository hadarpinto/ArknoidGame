import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hadar Pinto
 * ID: 316460146
 */
public class Ass6Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Hadar's Arkanoid";
    /**
     * Main method for running the arkanoid game.
     * @param args no use
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        } else {
            for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(new Level1());
                } else if (arg.equals("2")) {
                    levels.add(new Level2());
                } else if (arg.equals("3")) {
                    levels.add(new Level3());
                } else if (arg.equals("4")) {
                    levels.add(new Level4());
                }
            }
        }
        GUI gui = new GUI(TITLE, WIDTH, HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow game = new GameFlow(ar, ks, gui);
        game.runLevels(levels);
        gui.close();
    }
}
