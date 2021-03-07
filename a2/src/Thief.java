import java.util.ArrayList;
public class Thief extends MovableActor {
    private boolean consuming;

    public Thief(int x, int y, String fileName, String type) {
        super(x, y, fileName, type);
        this.consuming = false;
        setDirection(Direction.UP);
    }

    /**
     * This method updates the status of a gatherer in a tick, implementing
     * the abstract class in the parent class
     * @param actors arraylist of all actors in the world
     */
    @Override
    public void update(ArrayList<Actor> actors){
        // move the actor at the start of the tick
        if (isActive() && !isPerformed()) {
            changePos();
        }

        // iterate through the list and check if the thief is on other actors
        for (int i=0; i<actors.size(); i++){
            String type = actors.get(i).getType();

            if (this.samePosition(actors.get(i))) {
                // on a pool, create a new thief
                if (type.equals("Pool")) {
                    Thief duplicateThief = new Thief(getX(), getY(), "res/images/thief.png", "Thief");
                    /* instead of creating another new thief and destroy the current
                    thief, change the direction of the current thief. */
                    duplicateThief.setDirection(getReverseDirection());
                    rotateAntiClockwise();
                    changePos();
                    duplicateThief.changePos();
                    // avoid the new gatherer to move again
                    duplicateThief.setPerform(true);
                    actors.add(duplicateThief);
                }

                // if there are fruits on the tree, decrease the number of fruits by one
                if(type.equals("Tree") && !isCarrying()){
                    if (((Tree) actors.get(i)).getNumFruit() >= 1) {
                        ((Tree) actors.get(i)).setNumFruit(((Tree) actors.get(i)).getNumFruit() - 1);
                        setCarrying(true);

                    }
                }
                // on a golden tree and not carrying, set to carrying and reverse direction
                if(type.equals("GoldenTree") && !isCarrying()){
                    setCarrying(true);
                }

                // on a sign, change the direction
                if (type.equals("Sign")) {
                    Sign sign = (Sign) actors.get(i);
                    setDirection(sign.getDirection());
                }

                // on a pad, set consuming to true
                if (type.equals("Pad")) {
                    consuming = true;
                }

                // on a gatherer, rotate anti-clockwise
                if (type.equals("Gatherer")) {
                    rotateAntiClockwise();
                }

                // on a hoard
                if (type.equals("Hoard")) {
                    if (consuming) {
                        consuming = false;
                        if (!isCarrying()) {
                            // if not consuming and the hoard has fruit, decrease number of fruit by 1
                            if (((Hoard) actors.get(i)).getNumFruit() >= 1) {
                                setCarrying(true);
                                ((Hoard) actors.get(i)).setNumFruit(((Hoard) actors.get(i)).getNumFruit() - 1);
                            // other wise rotate clockwise
                            } else {
                                rotateClockwise();
                            }
                        }
                    // if the thief is carrying, increment the number of fruit by 1
                    } else if (isCarrying()) {
                        setCarrying(false);
                        ((Hoard) actors.get(i)).setNumFruit(((Hoard) actors.get(i)).getNumFruit() + 1);
                        rotateClockwise();
                    }
                }

                // on stockpile
                if (type.equals("Stockpile")) {
                    // if not carrying, steal one fruit from stockpile.
                    if (!isCarrying()) {

                        if (((Stockpile) actors.get(i)).getNumFruit() >= 1) {
                            setCarrying(true);
                            consuming = false;
                            ((Stockpile) actors.get(i)).setNumFruit(((Stockpile) actors.get(i)).getNumFruit() - 1);
                            rotateClockwise();
                        }
                    } else {
                        rotateClockwise();
                    }
                }
                // on a fence, set to inactive.
                if (type.equals("Fence")){
                    setActive(false);
                }


                setPerform(true);
            }

        }
    }
}
