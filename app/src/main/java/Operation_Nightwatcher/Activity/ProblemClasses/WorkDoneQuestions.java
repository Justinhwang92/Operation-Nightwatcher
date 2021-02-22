package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

public class WorkDoneQuestions extends AbstractQuestions {

    double GRAVITATIONAL_CONSTANT = 9.8;

    Random rnd;
    String myQue;
    BigDecimal ans;
    double angleRadian;
    double angle;
    int distance, weight;
    double mu_friction, force;

    public WorkDoneQuestions(){
        rnd = new Random();
        generateQuestion();

    }

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

    private void generateAnswer(){

        double F = force - (weight*GRAVITATIONAL_CONSTANT)*Math.sin(angleRadian) - mu_friction*(weight*GRAVITATIONAL_CONSTANT)*Math.cos(angleRadian);
//        System.out.println("Force sin "+ (weight*GRAVITATIONAL_CONSTANT)*Math.sin(angleRadian));
//        System.out.println("Force cos "+ mu_friction*(weight*GRAVITATIONAL_CONSTANT)*Math.cos(angleRadian));

        double W = F*distance;

        ans = new BigDecimal(W+"").setScale(2, BigDecimal.ROUND_CEILING);
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
