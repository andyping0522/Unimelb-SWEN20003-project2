/**
 * This is the class creating a gatherer object, inherit from MovableActor class
 */

import java.util.ArrayList;
public class Gatherer extends MovableActor {

    /**
     * This is the constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param fileName filename of the image
     * @param type type of the actor
     */
    public Gatherer(int x, int y, String fileName, String type) {
        super(x, y, fileName, type);
        setDirection(Direction.LEFT);

    }

    /**
     * This method updates the status of a gatherer in a tick, implementing
     * the abstract class in the parent class
     * @param actors arraylist of all actors in the world
     */
    @Override
    public void update(ArrayList<Actor> actors) {
        // move the actor first
        if(isActive() && !isPerformed()){
            changePos();
        }

        // iterate through the list and check if the gatherer is on other actors
        for (int i=0; i<actors.size(); i++){
            String type = actors.get(i).getType();

            if (this.samePosition(actors.get(i))){
                // on a pool, create a new gatherer
                if (type.equals("Pool")){
                    Gatherer duplicateGatherer = new Gatherer(getX(), getY(),
                            "res/images/gatherer.png" ,"Gatherer");

                    /* instead of creating another new gatherer and destroy the current
                    gatherer, change the direction of the current gatherer. */
                    rotateAntiClockwise();
                    duplicateGatherer.setDirection(getReverseDirection());
                    changePos();
                    duplicateGatherer.changePos();

                    // avoid the new gatherer to move again
                    duplicateGatherer.setPerform(true);
                    // add it to the array of actors
                    actors.add(duplicateGatherer);

                }

                // on a sign, change the direction
                if (type.equals("Sign")){
                    Sign sign = (Sign) actors.get(i);
                    setDirection(sign.getDirection());

                }

                // on a tree
                if(type.equals ("Tree") && !isCarrying()) {

                    // if there are fruits on the tree, decrease the number of fruits by one
                    if (((Tree) actors.get(i)).getNumFruit() >= 1) {
                        ((Tree) actors.get(i)).setNumFruit(((Tree) actors.get(i)).getNumFruit() - 1);
                        // set the status to carrying and reverse its direction
                        setCarrying(true);
                        reverseDirection();
                    }
                }

                // on a golden tree and not carrying, set to carrying and reverse direction
                if(type.equals("GoldenTree") && !isCarrying()){
                    setCarrying(true);
                    reverseDirection();
                }

                // on a stockpile or hoard, set to not carrying
                if(type.equals("Stockpile") || actors.get(i).getType().equals("Hoard")){
                    if (isCarrying()){
                        setCarrying(false);
                        // increment the number of fruits by 1
                        ((ActorWithFruit) actors.get(i)).setNumFruit(((ActorWithFruit) actors.get(i)).getNumFruit()
                                + 1);

                    }

                    reverseDirection();
                }

                // if on a tree, set to inactive
                if(type.equals("Fence")){
                    setActive(false);
                    moveBack();
                    setPerform(true);
                    break;
                }


            }

        }
        // set the actor to performed
        setPerform(true);
    }


}
