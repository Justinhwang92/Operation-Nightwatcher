package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;


//https://oer.galileo.usg.edu/cgi/viewcontent.cgi?article=1001&context=physics-ancillary

/**
 * Class to generate question regarding magnetic fields force
 */
public class MagneticField extends AbstractQuestions{

    /**
     * Random generator to generate random int values.
     */
    Random rnd;

    //Question - answer

    /**
     * String to store current question
     */
    String myQue;

    /**
     * String value to store correct answer in 2 decimal points
     */
    String ans;

    /**
     * BigDecimal value for magnetic_field around the conductor
     */
    BigDecimal magnetic_field;

    /**
     * BigDecimal value to store the magnitude of the force
     */
    BigDecimal force;

    /**
     * int for circle radius of conductor
     */
    int radius;

    /**
     * int value
     * The current passing through the conductor
     */
    int current;

    /**
     * The int length of the conductor
     */
    int length;

    /**
     * Constructor to initialize the random object
     */
    public MagneticField(){

        rnd = new Random();
        generateQuestion();
    }

    /**
     * Private method to generate random values for the different variables
     * Generates question and correct answer
     */
    private void generateQuestion() {

        radius = rnd.nextInt(6)+1;
        current = (rnd.nextInt(8)+1)*radius*10;
        length = (rnd.nextInt(7)+1);

        //testing purpose
//        radius = 5;
//        current = 1000;
//        length = 3;


        myQue = "what is the magnitude of force on at "+ radius +" m far from "+
                "the "+ length +" m conductor carrying current I = "+ current +" A ";

        magnetic_field = new BigDecimal(current/(2*Math.PI*radius)).setScale(2, BigDecimal.ROUND_CEILING);

        System.out.println(magnetic_field);

        //testing purpose
//        magnetic_field = new BigDecimal(2);
//        current = 2;
//        length = 3;

        force = magnetic_field.multiply(new BigDecimal(current*length)).setScale(2, BigDecimal.ROUND_CEILING);
        ans = force.toString();
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
