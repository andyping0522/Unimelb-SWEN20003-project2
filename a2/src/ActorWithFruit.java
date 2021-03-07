/**
 * This is the parent class for all actors that have finite number of fruits
 */

import bagel.*;

public class ActorWithFruit extends Actor {
    private int numFruit;
    private static final int DISTANCE = 32;
    private Font font = new Font("res/VeraMono.ttf", 48);

    /**
     * This is the constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of its image
     * @param type type of actor
     * @param num number of fruits held
     */
    public ActorWithFruit(int x, int y, String fileName, String type, int num){

        super(x,y,fileName,type);
        this.numFruit = num;
    }

    /**
     * This method draws out the number of fruit remaining
     */
    public void drawNumber(){
        font.drawString(String.format("%d", numFruit), super.getX() - DISTANCE, super.getY() - DISTANCE);
    }

    /**
     * This is getter for the numFruit attribute
     * @return number of fruits held by this actor
     */
    public int getNumFruit() {
        return numFruit;
    }

    /**
     * This is the setter for the numFruit attribute
     * @param numFruit new number of fruits
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }
}
