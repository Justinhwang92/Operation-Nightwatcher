package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;
import java.util.Random;

/**
 * A class and parent class of all possible physics/math questions
 */
public class AbstractQuestions {


    /**
     * String variable to store the current question
     */
    String myQuestion;
    /**
     * String representation of the answers of the current question
     */
    String myAnswer;

    public AbstractQuestions(){

    }

    /**
     * Format specifier to round down the large decimal points into two.
     */
//    protected BigDecimal format = new BigDecimal("0.00");

    /**
     * to get question
     * @return string myQuestion
     */
    public String getQuestion(){
        return myQuestion;
    }

    /**
     * to get correct answer
     * @return String current answer
     */
    public String getAnswer(){
        return myAnswer;
    }

    /**
     * To randomly generate questions - will create objects of its child classes
     */
    public void generateQuestionChoice(){

        Random rnd = new Random();
        int c = rnd.nextInt(9);
        System.out.println("choice : "+c+"\n");
//    c=8;
        switch (c) {

            case 0:
                EquationsofMotion que = new EquationsofMotion();
              myQuestion = que.getQuestion();
              myAnswer = que.getAnswer();
              break;

            case 1:
                KineticEnergy ke = new KineticEnergy();
                myQuestion = ke.getQuestion();
                myAnswer = ke.getAnswer();
                break;

            case 2:
                GravitationEnergy ge = new GravitationEnergy();
                myQuestion = ge.getQuestion();
                myAnswer = ge.getAnswer();
                break;

            case 3:
                FrictionProblems fr = new FrictionProblems();
                myQuestion = fr.getQuestion();
                myAnswer = fr.getAnswer();
                break;

            case 4:
                QuadraticEquation qe = new QuadraticEquation();
                myQuestion = qe.getQuestion();
                myAnswer = qe.getAnswer();
                break;

            case 5:
                AccelerationQuestion aq = new AccelerationQuestion();
                myQuestion = aq.getQuestion();
                myAnswer = aq.getAnswer();
                break;

            case 6:
            //needs to work on negative value generated for asnwer
                WorkDoneQuestions wd = new WorkDoneQuestions();
                myQuestion = wd.getQuestion();
                myAnswer = wd.getAnswer();
                break;

            case 7:
                MagneticField mg = new MagneticField();
                myQuestion = mg.getQuestion();
                myAnswer = mg.getAnswer();
                break;

            case 8:
                Integration ai = new Integration();
                myQuestion = ai.getQuestion();
                myAnswer = ai.getAnswer();
                break;

            default:
                break;
        }

    }
}
