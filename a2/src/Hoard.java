/**
 * This class represents a Hoard object
 */
public class Hoard extends ActorWithFruit{
    /**
     * This is the constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of the image
     * @param type type of the actor
     * @param num number of fruits held
     */
    public Hoard(int x, int y, String fileName, String type, int num) {
        super(x, y, fileName, type, num);
    }
}

