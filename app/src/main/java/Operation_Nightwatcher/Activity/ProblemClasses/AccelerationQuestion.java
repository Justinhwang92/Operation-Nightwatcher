package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Physics Question class
 * Generates one dimensional Kinematics related question
 */
public class AccelerationQuestion extends AbstractQuestions{

    // intern folder
    //One Dimensional Kinematics _ horizontal motion pdf
    //question 3

    /**
     * Random generator to generate random int values.
     */
    Random rnd;

    /**
     * String to store current question
     */
    private String myQue;

    /**
     * BigDecimal value to store correct answer in 2 decimal points
     */
    private BigDecimal ans;

    /**
     * speed: int value to store the speed of the object
     * acceleration : int to store acceleration of the object
     * time_interval : int to define time will be taken by object
     */
    int speed, acceleration, time_interval;

    /**
     * variables to generate answer
     * int value
     */
    int newTime;


    /**
     * Constructor to initialize the random object
     */
    public AccelerationQuestion(){
        rnd = new Random();
        generateQuestion();
    }

    /**
     * Private method to generate random values for the different variables
     * Generates question and correct answer
     */
    private void generateQuestion(){
//        speed = (rnd.nextInt(5)+1)*10;
        acceleration = (rnd.nextInt(5)+1)*(-1);
        time_interval = (rnd.nextInt(6)+1)*10;

        //testing purpose - answer should be 200m
//        acceleration = -1;
//        time_interval = 40;

        speed = ((time_interval)*(-1*acceleration))/2;

        myQue = "A train is traveling down a straight track at "+ speed +" \uD835\uDC5A/s" +
                " when the engineer applies the brakes, resulting in an " +
                "acceleration of "+ acceleration + " m/s\u00B2 "+
                "as long as the train is in motion. How far does the train move during a "+time_interval+"-s time interval " +
                "starting at the instant the brakes are applied?";

        System.out.println(myQue);

        newTime = (-speed)/acceleration;

        double temp = speed*newTime + (acceleration*(newTime*newTime))/2.0 ;
        ans = new BigDecimal(temp+"").setScale(2, BigDecimal.ROUND_CEILING);

        System.out.println("answer is ; "+ans);
    }

    /**
     * To get the question
     * @return String myQue
     */
    @Override
    public String getQuestion() {
        return myQue;
    }

    /**
     * To get the answer
     * @return String the correct answer
     */
    @Override
    public String getAnswer() {
        return ans.toString();
    }
}
