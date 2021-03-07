/**
 * This class represents a stockpile object
 */
public class Stockpile extends ActorWithFruit{
    /**
     * This is the constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of the image
     * @param type type of the actor
     * @param num number of fruits held
     */
    public Stockpile(int x, int y, String fileName, String type, int num) {

        super(x, y, fileName, type, num);
    }
}
