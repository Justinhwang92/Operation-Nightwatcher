package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;

public class FrictionProblems extends AbstractQuestions {

    double force_static;
    double force_friction;
    double weighing;
    BigDecimal mu_s;
    BigDecimal mu_k;
    Random rnd;
    String myQue;
    String ans;

    public FrictionProblems(){
        rnd = new Random();
        generateQuestions();
    }

    private void generateQuestions() {

        weighing = (rnd.nextInt(20)+1)*10;
        force_static = (rnd.nextInt(10)+1)*10;
        force_friction = (rnd.nextInt(10)+1)*10;

        mu_s = ((new BigDecimal(weighing/force_static)).subtract((super.format))).setScale(2, BigDecimal.ROUND_CEILING);
        mu_k = ((super.format).add(new BigDecimal(weighing/force_friction))).setScale(2, BigDecimal.ROUND_CEILING);
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


    @Override
    public String getQuestion() {
        return myQue;
    }

    @Override
    public String getAnswer() {
        return ans;
    }
}
