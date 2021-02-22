package Operation_Nightwatcher.Activity.ProblemClasses;

import java.util.Random;

public class EquationsofMotion extends AbstractQuestions {

    /* Equations are,..

    (1) x = x0 + v0*t + ½a*t^2 -> findDistance() method
    (2) v = v0 + at -> findVelocity() and howLongitTookwithGivenVelocity(velocity) methods
    (3) v2 = v02 + 2a(x – x0) -> findVelocity(velocity)

    */

    protected Random rnd = new Random();

    protected double currentAns;
    protected String myQue;
    protected String subQue;
    protected int x0;
    protected int v0;
    protected int v;
    protected int a;
    protected int t;
    protected double newDistance;

    public EquationsofMotion(){
        super();
        generateValues();
        randomQueSelection();
    }

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

    public void findDistance(){

        subQue = "Where is the block at t = "+ t +" seconds? ";
        currentAns = x0 + v0*t + (a*(t*t))/2.0;
    }

    public void findVelocity(int time){

        subQue = "What is the block's velocity (m/s) at t = " + time + " seconds? ";
        currentAns = v0 + a*time;
    }

    public void findDistanceWithGivenVelocity(int theVelocity){

        subQue = "Where is the block when it’s velocity is "+ theVelocity +" m/s?";
        currentAns = ((double) ((theVelocity*theVelocity)-(v0*v0))/(2*a)) + x0;
        newDistance = currentAns;
    }

    public void howLongitTookwithGivenVelocity(int theVelocity){

        subQue = "How long did it take to get to "+ newDistance +" point with velocity of "+theVelocity+"m/s?";
        currentAns = (double) (theVelocity - v0)/a;
    }

    @Override
    public String getQuestion() {
        return myQue+"\n"+subQue;
    }

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
