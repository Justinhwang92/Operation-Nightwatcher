package Operation_Nightwatcher.Activity.ProblemClasses;

import java.util.Random;

public class KineticEnergy extends AbstractQuestions {

    /**
     * Random generator to generate random int values.
     */
    private Random rnd;

    /**
     * String to store current question
     */
    protected String myQue;

    /**
     * int value to store correct answer
     */
    protected int ans;

    /**
     * int values to store mass, velocity and energy
     */
    protected int mass;
    protected int velocity;
    protected int energy;

    /**
     * Constructor to initialize the random object
     */
    public KineticEnergy(){
        rnd = new Random();
        generateQuestion();
    }

    /**
     * Method to generate the different questions
     */
    private void generateQuestion(){
        mass = (rnd.nextInt(10)+1)*10 ;
        velocity = rnd.nextInt(30)+1;
        energy = mass*(velocity*velocity)/2;

        int choice = rnd.nextInt(3);
        switch (choice) {
            case 0:
                myQue = "A " + mass + " kilogram bullet travels at " + velocity + " m/s. How much kinetic energy(J) does it have?";
                ans = energy;
                break;
            case 1:
                int unitChoice = rnd.nextInt(6);
                if (unitChoice % 2 == 0){
                    myQue = "A " + mass + " gram stones has " + energy + "J of kinetic energy. How fast it is moving(m/s)?";
                    ans = velocity;
                }else{
                    myQue = "A " + mass + " gram stones has " + energy + "J of kinetic energy. How fast it is moving(cm/s)?";
                    ans = velocity*100;
                }
                break;
            case 2:
                myQue = "A car traveling at "+velocity+" m/s has "+(energy*10)+"J of kinetic energy. What is the mass of the car(kg)?";
                ans = mass*10;
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
        return ans+"";
    }
}
