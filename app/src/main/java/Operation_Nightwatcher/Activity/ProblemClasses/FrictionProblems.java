package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;

public class FrictionProblems extends AbstractQuestions {

    /**
     * Random generator to generate random int values.
     */
    Random rnd;

    /**
     * String to store current question
     */
    String myQue;

    /**
     * String value to store correct answer in 2 decimal points
     */
    String ans;

    /**
     * Double values to store static force, friction force and weight of the object
     */
    double force_static;
    double force_friction;
    double weighing;

    /**
     * BigDecimal values to store static friction coefficient and kinetic coefficient
     */
    BigDecimal mu_s;
    BigDecimal mu_k;

    /**
     * Constructor to initialize the random object
     */
    public FrictionProblems(){
        rnd = new Random();
        generateQuestions();
    }

    /**
     * Method to generate the different questions
     */
    private void generateQuestions() {

        weighing = (rnd.nextInt(20)+1)*10;
        force_static = (rnd.nextInt(10)+1)*10;
        force_friction = (rnd.nextInt(10)+1)*10;

        mu_s = ((new BigDecimal(weighing/force_static))).setScale(2, BigDecimal.ROUND_CEILING);
        mu_k = (new BigDecimal(weighing/force_friction)).setScale(2, BigDecimal.ROUND_CEILING);
//        System.out.println(""+ weighing +" "+ force_static +" "+force_friction +" "+mu_s+ " " + mu_k);

        myQue = "A block weighing "+ weighing + " N is pushed along a surface. If it takes "+ force_static +" N to get the block moving and "
                +force_friction+" N to keep the block moving at a constant velocity,";

        int choice = rnd.nextInt(2);
        switch (choice){
            case 0:
                myQue += " what is the coefficient of static friction μs?";
                ans = mu_s+"";
                break;
            case 1:
                myQue+=" what is the coefficient of kinetic friction μk?";
                ans = mu_k+"";
                break;
            default:
                break;
        }
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
        return ans;
    }
}
