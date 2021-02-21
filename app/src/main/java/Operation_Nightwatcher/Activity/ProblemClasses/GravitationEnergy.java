package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

public class GravitationEnergy {

    private double GRAVITATIONAL_CONSTANT = 9.8;

    Random rnd;
    double mass;
    double height;
    BigDecimal energy;
    String myQue;
    String ans;

    public GravitationEnergy(){
        rnd = new Random();
        generateQuestion();
    }

    private void generateQuestion() {
        BigDecimal temp = new BigDecimal("0.100");
        BigDecimal a = temp.multiply(new BigDecimal(rnd.nextInt(10)+1+""));
//        mass = (rnd.nextInt(10)+1)*0.1;
        height = rnd.nextInt(12)+1;
        mass = a.doubleValue();
        BigDecimal t = new BigDecimal(GRAVITATIONAL_CONSTANT+"");
        BigDecimal energy = t.multiply(new BigDecimal(mass*height+"")).setScale(2, BigDecimal.ROUND_CEILING);
//        energy = mass*height*GRAVITATIONAL_CONSTANT;
//        energy = an.doubleValue();
//        System.out.println("mass "+mass+" height "+ height);

        BigDecimal format = new BigDecimal("0.00");
//        BigDecimal add = format.subtract(new BigDecimal(mass*height+""));
//        System.out.println(add);
        switch (rnd.nextInt(2)){

            case 0:
                myQue = "An aircraft is taking a group of skydivers up into the air. Evan is dressed in his parachuting outfit," +
                        "which brings his mass to a total of "+mass+" kg. The aircraft takes the group to a height of "+height+"m " +
                        "before the jump. How much GPE does Evan gain before jumping?";
                ans = energy+"";
                break;
            case 1:
                myQue = "An owl has a mass of "+mass+" kg. It dives to catch a mouse, losing "+energy+" J of its GPE. What was the " +
                        "starting height of the owl, in meters?";
                ans = height+"";
                break;
            case 2:
                myQue = "One of the tallest radio towers is in Fargo, North Dakota. The tower is "+height+" tall. If a bird lands on" +
                        "top of the tower, so that the gravitational potential energy associated with the bird is "+energy+" J, what" +
                        "is its mass, in kilograms?";
                ans = mass+"";
                break;
            default:
                break;
        }
    }

    public String getQuestion(){
        return myQue;
    }

    public String getAnswer(){
        return ans+"";
    }
}
