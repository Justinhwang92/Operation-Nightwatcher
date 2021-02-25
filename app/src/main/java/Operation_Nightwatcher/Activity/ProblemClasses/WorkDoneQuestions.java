package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

//https://www.sparknotes.com/physics/workenergypower/workpower/problems/

/**
 * Class to generate question related work done by an object
 */
public class WorkDoneQuestions extends AbstractQuestions {

    /**
     * Constant double value for gravitation constant which is 9.8 m/s^2
     */
    double GRAVITATIONAL_CONSTANT = 9.8;

    /**
     * Random generator to generate random int values.
     */
    Random rnd;

    /**
     * String to store current question
     */
    String myQue;

    /**
     * BigDecimal value to store correct answer in 2 decimal points
     */
    BigDecimal ans;


    /**
     * Double type values
     * angle in degree, angleRadian in radian value
     */
    double angleRadian, angle;

    /**
     * int values to store distance and weight of an object
     */
    int distance, weight;

    /**
     * Double values to store coefficient of friction and force to be needed on object
     */
    double mu_friction, force;

    /**
     * Constructor to initialize the random object
     */
    public WorkDoneQuestions(){
        rnd = new Random();
        generateQuestion();

    }

    /**
     * Private method to generate random values for the different variables
     * Generates question and correct answer
     */
    private void generateQuestion(){

        angle = rnd.nextInt(22)+ 25.0;
        force = (rnd.nextInt(15)+5)*10 + 2*(weight*GRAVITATIONAL_CONSTANT);
        weight = (rnd.nextInt(4)+1)*5;
        angleRadian = Math.toRadians(angle);

        double[] possibleMu = {0.25, 0.5, 0.2, 0.75, 0.15};
        mu_friction = possibleMu[rnd.nextInt(5)];
        distance = (rnd.nextInt(5)+1)*10;

        //testing purpose
//        angle = 34;
//        angleRadian = Math.toRadians(angle);
//        weight = 15;
//        force = 110 + (weight*GRAVITATIONAL_CONSTANT);
//        distance = 10;
//        mu_friction = 0.5;

        myQue = "A "+weight+" kg block is moved up a "+angle+" degree incline by a force of "+force+" N, parallel to the incline. " +
                "The coefficient of kinetic friction between the block and the incline is "+ mu_friction +". " +
                "What is the total work done on the block over the "+ distance +" distance? (consider g as 9.8)";

        generateAnswer();
    }

    /**
     * To generate the correct answer for the question
     */
    private void generateAnswer(){

        double F = force - (weight*GRAVITATIONAL_CONSTANT)*Math.sin(angleRadian) - mu_friction*(weight*GRAVITATIONAL_CONSTANT)*Math.cos(angleRadian);
//        System.out.println("Force sin "+ (weight*GRAVITATIONAL_CONSTANT)*Math.sin(angleRadian));
//        System.out.println("Force cos "+ mu_friction*(weight*GRAVITATIONAL_CONSTANT)*Math.cos(angleRadian));

        double W = F*distance;

        ans = new BigDecimal(W+"").setScale(2, BigDecimal.ROUND_CEILING);
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
        return ans+"";
    }
}
