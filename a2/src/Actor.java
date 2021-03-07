/**
 * This is the super class for all type of actors
 */

import bagel.*;

public class Actor {
    private int x;
    private int y;
    private final Image image;
    private final String type;
    private int prevX;
    private int prevY;
    private boolean performed;


    /**
     * This is the constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of the image
     * @param type type of the actor
     */
    public Actor(int x, int y, String fileName, String type){
        this.x = x;
        this.y = y;
        this.image = new Image(fileName);
        this.type = type;
        this.prevX = x;
        this.prevY = y;
        this.performed = false;
    }

    /**
     * This method render(draw) the image of the actor
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /**
     * This is the getter of "performed" attribute
     * @return a boolean indicating whether the actor has performed its
     * tick.
     */
    public boolean isPerformed(){
        return performed;
    }

    /**
     * This is the setter of "performed" attribute
     * @param perform a boolean variable that the attribute should be
     * assigned to.
     */
    public void setPerform(boolean perform){
        performed = perform;
    }

    /**
     * This method changes the position of the actor, also keep track of the
     * position of previous tick
     * @param deltaX change in x coordinate
     * @param deltaY change in y coordinate
     */



    /**
     * This is the getter for actor's x coordinate
     * @return x coordinate
     */

    public int getX(){
        return x;
    }

    /**
     * This is the getter for actor's y coordinate
     * @return y coordinate
     */

    public int getY() {
        return y;
    }

    /**
     * This is the setter for x coordinate
     * @param newX new x coordinate
     */
    public void setX(int newX){
        x = newX;
    }

    /**
     * This is the setter for y coordinate
     * @param newY new y coordinate
     */
    public void setY(int newY){
        y = newY;
    }

    /**
     * This is the setter for previous x coordinate
     * @param newX new previous x coordinate
     */
    public void setPrevX(int newX){
        prevX = newX;
    }

    /**
     * This is the setter for previous y coordinate
     * @param newY new previous y coordinate
     */
    public void setPrevY(int newY){
        prevY = newY;
    }

    /**
     * This is the getter for previous y coordinate
     * @return the previous y coordinate
     */

    public int getPrevX(){
        return prevX;
    }

    /**
     * This is the getter for previous x coordinate
     * @return the previous x coordinate
     */

    public int getPrevY(){
        return prevY;
    }

    /**
     * This is the getter for the type of the actor
     * @return a string indicating the type of the actor
     */
    public String getType() {
        return type;
    }

    /**
     * This method to compare the position of two actors
     * @param other another actor to be compared with
     * @return a boolean indicating whether they are on the same
     * position.
     */

    public boolean samePosition(Actor other){
        return this.x == other.getX() && this.y == other.getY();
    }



}
