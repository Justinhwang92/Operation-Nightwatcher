package Operation_Nightwatcher.Activity.ProblemClasses;

import java.util.Random;

public class EquationsofMotion extends AbstractQuestions {

    /* Equations used in this class,..
    (1) x = x0 + v0*t + ½a*t^2 -> findDistance() method
    (2) v = v0 + at -> findVelocity() and howLongitTookwithGivenVelocity(velocity) methods
    (3) v2 = v02 + 2a(x – x0) -> findVelocity(velocity)
    */

    /**
     * Random generator to generate random int values.
     */
    protected Random rnd;

    /**
     * String to store main question
     */
    protected String myQue;

    /**
     * String to store sub problem related with current main question
     */
    protected String subQue;

    /**
     * Double value to store correct current answer
     */
    protected double currentAns;

    /**
     * int value for initial distance - x0,
     */
    protected int x0;

    /**
     * int value for initial velocity,
     */
    protected int v0;

    /**
     * int value for new velocity
     */
    protected int v;

    /**
     * int value for acceleration,
     */
    protected int a;

    /**
     * The int value for time
     */
    protected int t;

    /**
     * Double value for new distance to be given
     */
    protected double newDistance;

    /**
     * Constructor to initialize the random object
     */
    public EquationsofMotion(){
        rnd = new Random();
        generateValues();
        randomQueSelection();
    }

    /**
     * To select one from the different sub problems
     */
    protected void randomQueSelection(){
        int choice = rnd.nextInt(4);

        switch (choice){
            case 0:
                findDistance();
                break;

            case 1:
                findVelocity(rnd.nextInt(4)+1);
                break;

            case 2:
                findDistanceWithGivenVelocity(v);
                break;

            case 3:
                findDistanceWithGivenVelocity(v);
                howLongitTookwithGivenVelocity(v);
                break;

            default:
                break;
        }
    }

    /**
     * To generate the random values of the variable used in the main equations
     */
    protected void  generateValues(){

        t = rnd.nextInt(5)+1;
        a = (rnd.nextInt(4) + 1)*2; // mutiple of 2 for velocity equation
        v0 = rnd.nextInt(6)+1;
        x0 = rnd.nextInt(6)+1;

        v = rnd.nextInt(5) + v0; // to avoid from getting negative distance

        //testing purpose
        t = 2;
        a = 2;
        x0 = 5;
        v0 = 3;

        myQue = "A block slides along a frictionless surface with a constant acceleration of " + a +" m/s\u00B2." +
                "At time t = 0 s the block is at x = "+ x0 +"m and travelling with a velocity of "+ v0+" m/s.";
    }

    /**
     * One of the sub problem to compute the distance
     */
    public void findDistance(){

        subQue = "Where is the block at t = "+ t +" seconds? ";
        currentAns = x0 + v0*t + (a*(t*t))/2.0;
    }

    /**
     * One of the subproblem to find the velocity after the given amount of time
     * @param time int time after which velocity is to be compute
     */
    public void findVelocity(int time){

        subQue = "What is the block's velocity (m/s) at t = " + time + " seconds? ";
        currentAns = v0 + a*time;
    }

    /**
     * One of the subproblem to find the distance after the given velocity
     * @param time int velocity after which distance is to be compute
     */
    public void findDistanceWithGivenVelocity(int theVelocity){

        subQue = "Where is the block when it’s velocity is "+ theVelocity +" m/s?";
        currentAns = ((double) ((theVelocity*theVelocity)-(v0*v0))/(2*a)) + x0;
        newDistance = currentAns;
    }

    /**
     * One of the subproblem to find the time to cover the given distance with the given velocity
     * @param time int velocity with what the time is to be compute
     */
    public void howLongitTookwithGivenVelocity(int theVelocity){

        subQue = "How long did it take to get to "+ newDistance +" point with velocity of "+theVelocity+"m/s?";
        currentAns = (double) (theVelocity - v0)/a;
    }

    /**
     * To get the question
     * @return String myQue
     */
    @Override
    public String getQuestion() {
        return myQue+"\n"+subQue;
    }

    /**
     * To get the answer
     * @return String the correct answer
     */
    @Override
    public String getAnswer() {

        //if the current answer is integer then, we should remove decimal 0's as user will not enter value with decimal 0's.
        if(currentAns == Math.floor(currentAns)) {
            int newIntAns = (int) currentAns;
            return newIntAns+"";
        }

        return currentAns+"";
    }
}
