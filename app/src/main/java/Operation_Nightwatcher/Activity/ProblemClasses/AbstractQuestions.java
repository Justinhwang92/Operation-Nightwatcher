package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;

/**
 * Abstract class and parent class of all possible physics/math questions
 */
public abstract class AbstractQuestions {

    /**
     * String variable to store the current question
     */
    String myQuestion;
    /**
     * String representation of the answers of the current question
     */
    String myAnswer;

    /**
     * Format specifier to round down the large decimal points into two.
     */
    protected BigDecimal format = new BigDecimal("0.00");

    /**
     * to get question
     * @return string myQuestion
     */
    public abstract String getQuestion();

    /**
     * to get correct answer
     * @return String current answer
     */
    public abstract String getAnswer();

    /**
     * To randomly generate questions - will create objects of its child classes
     */
    public void getQuestions(){

    }
}
