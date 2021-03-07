/**
 * This class represents a sign object
 */
public class Sign extends Actor{
    private final int direction;

    /**
     * This is the constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of the image
     * @param type type of the actor
     * @param direction direction of the sign
     */
    public Sign(int x, int y, String fileName, String type, int direction) {
        super(x, y, fileName, type);
        this.direction = direction;
    }

    /**
     * This is the getter for the direction
     * @return the direction of the sign
     */
    public int getDirection(){
        return direction;
    }
}
