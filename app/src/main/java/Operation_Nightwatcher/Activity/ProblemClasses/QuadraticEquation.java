package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Class to generate quadratic equations
 */
public class QuadraticEquation {

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
    private BigDecimal solOne;
    private BigDecimal solTwo;

    /**
     * int coefficient of a,b,c
     */
    private int a, b, c;

    /**
     * Constructor to initialize random generator object
     */
    public QuadraticEquation(){
        rnd = new Random();
        generateQuestion();
    }

    /**
     * Private method to generate random values for the different variables
     * Generates question and correct answer
     */
    private void generateQuestion() {
         a = rnd.nextInt(2)+1;
         b = rnd.nextInt(6)+5;
         c = rnd.nextInt(4)+1;

         myQue = "Find the solutions for the given quadratic equation: \n"+a + "x\u00B2 + " +b+ "x + " + c+" = 0 \n Provide only one !";
//        System.out.println(myQue);
         double temp = (-1*b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
         solOne = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_CEILING);
         temp = (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
         solTwo = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_CEILING);
    }

    /**
     * To get the question
     * @return String myQue
     */
    public String getQuestion(){
        return myQue;
    }

    /**
     * To get the answer
     * @return String the correct answer
     */
    public String getAnswer(){
        return (solOne+","+solTwo+"");
    }
}
