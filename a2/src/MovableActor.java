/**
 * This is the parent class for movable actors(gatherers and
 * thieves) and it is abstract
 */

import java.util.ArrayList;
public abstract class MovableActor extends Actor {
    private boolean carrying = false;
    private int direction;
    private boolean active = true;

    private static final int RANGE = 64;
    private static final int TWO_SEVENTY_DEGREES = 3;

    /**
     * This is the constructor
     * @param x the x coordinate
     * @param y the y coordinate
     * @param fileName the filename of its image
     * @param type the type of the actor
     */
    public MovableActor(int x, int y, String fileName, String type) {
        super(x, y, fileName, type);

        this.direction = Direction.getRandom();
    }

    /**
     * This is a abstract method to be implemented according to the
     * algorithm of gatherers and thieves
     * @param actors arraylist of all actors in the world
     */
    public abstract void update(ArrayList<Actor> actors);


    /**
     * This is the setter for the direction attribute
     * @param newDirection new direction
     */

    public void setDirection(int newDirection){
        direction = newDirection;
    }

    /**
     * This is the setter for the carrying attribute
     * @param carrying
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * This is the setter for active attribute
     * @param active
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This is the getter for carrying attribute
     * @return a boolean indicating whether the actor is carrying
     */

    public boolean isCarrying() {
        return carrying;
    }

    /**
     * This is the getter for active attribute
     * @return a boolean indicating whether the actor is active
     */

    public boolean isActive() {
        return active;
    }

    /** This is the getter for direction attribute
     * @return a integer indicating actor's current direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * This method rotate current direction by 180 degrees
     */

    public void reverseDirection(){
        if (direction == Direction.UP || direction == Direction.LEFT){
            setDirection(direction + 1);
        }else{
            setDirection(direction - 1);
        }
    }

    /**
     * This method get the opposite direction the actor is currently heading
     * @return a integer indicating actor's opposite direction
     */
    public int getReverseDirection(){
        if (direction == Direction.UP || direction == Direction.LEFT){
            return direction + 1;
        }else{
            return direction - 1;
        }
    }


    /**
     * This method changes the position of the actor, also keep track of the
     * position of previous tick
     * @param deltaX change in x coordinate
     * @param deltaY change in y coordinate
     */
    public void move(int deltaX, int deltaY) {
        setPrevX(getX());
        setPrevY(getY());
        setX(getX()+deltaX);
        setY(getY()+deltaY);

    }

    /**
     * This method move the actor back to the location on the previous tick,
     * and update the previous location with the current location.
     */

    public void moveBack(){
        int tempX = getX(), tempY = getY();
        setX(getPrevX());
        setY(getPrevY());
        setPrevX(tempX);
        setPrevY(tempY);
    }

    /**
     * This method rotate the actor's direction by 90 degrees clockwise
     */

    public void rotateClockwise(){
        if(direction == Direction.UP){
            setDirection(Direction.RIGHT);
        }else if(direction == Direction.DOWN){
            setDirection(Direction.LEFT);
        }else if(direction == Direction.RIGHT){
            setDirection(Direction.DOWN);
        }else if(direction == Direction.LEFT){
            setDirection(Direction.UP);
        }
    }

    /**
     * This method rotate the actor's direction by 90 degrees anti-clockwise
     */

    public void rotateAntiClockwise(){
        // rotate anti-clockwise is equivalent to rotate clockwise three times
        for (int i=0; i<TWO_SEVENTY_DEGREES; i++){
            rotateClockwise();
        }
    }


    /**
     * This method change the location of actor according its current location
     */
    public void changePos(){
        switch (getDirection()) {
            case Direction.UP:
                move(0, -RANGE);
                break;
            case Direction.DOWN:
                move(0, RANGE);
                break;
            case Direction.LEFT:
                move(-RANGE, 0);
                break;
            case Direction.RIGHT:
                move(RANGE, 0);
                break;
        }
    }
}
