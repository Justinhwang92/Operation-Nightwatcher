package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class QuadraticEquation {

    Random rnd;
    int a, b, c;
    private String myQue;
    BigDecimal solOne;
    BigDecimal solTwo;

    public QuadraticEquation(){
        rnd = new Random();
        generateQuestion();
    }

    private void generateQuestion() {
         a = rnd.nextInt(2)+1;
         b = rnd.nextInt(6)+5;
         c = rnd.nextInt(4)+1;

         myQue = "Find the solutions for the given quadratic equation: \n"+a + "x\u00B2 + " +b+ "x + " + c+" = 0";
//        System.out.println(myQue);
         double temp = (-1*b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
         solOne = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_CEILING);
         temp = (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
         solTwo = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_CEILING);
    }

    public String getQuestion(){
        return myQue;
    }

    public String getAnswer(){
        return (solOne+","+solTwo+"");
    }
}
