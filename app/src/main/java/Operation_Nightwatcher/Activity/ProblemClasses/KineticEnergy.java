package Operation_Nightwatcher.Activity.ProblemClasses;

import java.util.Random;

public class KineticEnergy extends AbstractQuestions {


    protected int mass;
    protected int velocity;
    protected int energy;
    private Random rnd;
    protected int ans;
    protected String myQue;

    public KineticEnergy(){
        rnd = new Random();
        generateQuestion();
    }

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

    @Override
    public String getQuestion() {
        return myQue;
    }

    @Override
    public String getAnswer() {
        return ans+"";
    }
}
