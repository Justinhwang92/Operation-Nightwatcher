package Operation_Nightwatcher.Activity.ProblemClasses;

import java.math.BigDecimal;

public abstract class AbstractQuestions {

    String myQuestion;
    String myAnswer;
    protected BigDecimal format = new BigDecimal("0.00");

    public abstract String getQuestion();

    public abstract String getAnswer();

    public void getQuestions(){

    }
}
