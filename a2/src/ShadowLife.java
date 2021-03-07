/** This is the submission for project2B of SWEN20003, which is a simple
 * simulation of fruit gathering.
 * @author Jiachen Ping, Student id: 1083347
 * @version 1.0
 */



import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShadowLife extends AbstractGame {

    private ArrayList<Actor> actors;
    private final String fileName;
    private final int tickRate;
    private final int maxTick;
    private long prevTime;
    private final Image background = new Image("res/images/background.png");
    private static int numTicks = 0;

    private static final int INITIAL_TREE = 3;
    private static final int INITIAL_CHERRIES = 0;

    /**
     * This is the constructor for the Shadowlife class.
     * @param args Array of command line arguments
     */
    public ShadowLife(String[] args){
        // check for validity of input
        if (args.length != 3){
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        this.fileName = args[2];
        this.tickRate = Integer.parseInt(args[0]);
        this.maxTick = Integer.parseInt(args[1]);
        // keep track of the current system time for calculating ticks
        prevTime = System.currentTimeMillis();
        loadActors(fileName);
    }

    /**
     * This method reads the csv file and create the actors in the array list.
     * @param of the filename passed from command line arguments
     */
    private void loadActors(String of){
        try (BufferedReader reader = new BufferedReader(new FileReader(of))) {
            actors = new ArrayList<Actor>();

            String line;
            while ((line = reader.readLine()) != null) {
                int i = 1;
                String[] parts = line.split(",");
                // check for validity of each  line.
                if (parts.length < 3){
                    System.out.println("error: in file " + fileName + " at line " + i);
                    System.exit(-1);
                }
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                // Create the actor
                switch (type) {
                    case "Tree":
                        actors.add(new Tree(x, y, "res/images/tree.png", "Tree", INITIAL_TREE));
                        break;
                    case "Gatherer":
                        actors.add(new Gatherer(x, y, "res/images/gatherer.png", "Gatherer"));
                        break;
                    case "Fence":
                        actors.add(new Fence(x, y, "res/images/fence.png", "Fence"));
                        break;
                    case "GoldenTree":
                        actors.add(new GoldenTree(x, y, "res/images/gold-tree.png", "GoldenTree"));
                        break;
                    case "Hoard":
                        actors.add(new Hoard(x, y, "res/images/hoard.png", "Hoard", INITIAL_CHERRIES));
                        break;
                    case "Pad":
                        actors.add(new Pad(x, y, "res/images/pad.png", "Pad"));
                        break;
                    case "SignRight":
                        actors.add(new Sign(x, y, "res/images/right.png", "Sign", Direction.RIGHT ));
                        break;
                    case "SignDown":
                        actors.add(new Sign(x, y, "res/images/down.png", "Sign", Direction.DOWN ));
                        break;
                    case "SignLeft":

                        actors.add(new Sign(x, y, "res/images/left.png", "Sign", Direction.LEFT ));
                        break;
                    case "SignUp":
                        actors.add(new Sign(x, y, "res/images/up.png", "Sign", Direction.UP ));
                        break;
                    case "Stockpile":
                        actors.add(new Stockpile(x, y, "res/images/cherries.png", "Stockpile",
                                INITIAL_CHERRIES));
                        break;
                    case "Thief":
                        actors.add(new Thief(x, y, "res/images/thief.png", "Thief"));
                        break;
                    case "Pool":
                        actors.add(new Pool(x, y, "res/images/pool.png", "Pool"));
                        break;
                }
                i++;
            }

        } catch (IOException e) {
            System.out.println("error: file "+ fileName + " not found");
            System.exit(-1);
        }
    }


    /**
     * This method determines the changes made to the simulation, called every frame,
     * draw all the actors and the background. Perform location update on each "tick"
     * @param input keyboard or mouse input
     */
    @Override
    protected void update(Input input) {
        long currTime = System.currentTimeMillis();
        /* determine if the difference of current time and previous time
        recorded exceeds the tick rate*/
        if (currTime - prevTime >= tickRate){
            // record the time of this tick
            prevTime = currTime;
            for (int i=0; i<actors.size();i++){
                /*perform status update on movable actors, draw the number of fruits
                for actors with finite fruits */
                String type = actors.get(i).getType();

                if (type.equals("Gatherer")){
                    ((Gatherer) actors.get(i)).update(actors);
                }else if(type.equals("Thief")){
                    ((Thief) actors.get(i)).update(actors);
                }else if(type.equals("Hoard") || type.equals("Stockpile") || type.equals("Tree")){
                    ((ActorWithFruit) actors.get(i)).drawNumber();
                }else if(type.equals("Thief")){
                    ((Thief) actors.get(i)).update(actors);
                }
                // draw the actor
                actors.get(i).render();


            }
            // increase the counter of ticks elapsed
            numTicks ++;
            // reset all actors to unperformed.
            resetPerform(actors);
        }

        // not a tick yet, still draw all the actors, background, and fonts
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            String type = actor.getType();
            if (actor != null) {
                actor.render();
            }
            if(type.equals("Hoard") || type.equals("Stockpile") || type.equals("Tree")){
                ((ActorWithFruit) actor).drawNumber();
            }
        }

        // timed out

        if(numTicks == maxTick){
            System.out.println("Timed out");
            System.exit(-1);
        }

        // simulation has finished, halt.

        if(isFinished(actors)){
            halt();
        }
    }

    /**
     * this is the main method which starts the simulation
     * @param args
     */
    public static void main(String[] args) {
        new ShadowLife(args).run();
    }


    /**
     * this method reset all the actors to unperformed, ready for next
     * tick
     * @param actors an arraylist of actors in the world
     */

    private void resetPerform(ArrayList<Actor> actors){
        for (int i=0; i<actors.size(); i++){
            actors.get(i).setPerform(false);
        }
    }

    /**
     * this method determines whether the current state of simulation satisfies
     * the criteria to halt.
     * @param actors an arraylist of actors in the world
     * @return a boolean indicates whether the simulation should halt.
     */
    private boolean isFinished(ArrayList<Actor> actors){
        // check if all the movable actors have become inactive.
        for (Actor actor:actors){
            if(actor.getType().equals("Gatherer") || actor.getType().equals("Thief")){
                // simulations carries on if some actor is still active
                if(((MovableActor) actor).isActive()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method prints out the total number of ticks elapsed, prints
     * out the number of fruit held on each hoard and stockpile in the order
     * they are presented in the world file, and end the simulation
     */
    private void halt(){
        System.out.println(numTicks + " ticks");
        for (Actor actor:actors){
            if (actor.getType().equals("Hoard") || actor.getType().equals("Stockpile")){
                System.out.println(((ActorWithFruit) actor).getNumFruit());
            }
        }
        System.exit(0);
    }
}
