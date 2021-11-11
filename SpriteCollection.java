import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Hadar Pinto
 * ID: 316460146
 *
 * A class to hold and conduct method on all the sprties object toghether.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * constructor to initialize new list to hold objects.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * add sprite to the list.
     * @param s object.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tmpArr = new ArrayList<Sprite>(this.spriteList);
        for (int i = 0; i < tmpArr.size(); i++) {
            tmpArr.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }

    /**
     * Remove sprite from list.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}