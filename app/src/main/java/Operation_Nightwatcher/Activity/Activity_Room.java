package Operation_Nightwatcher.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;

public class Activity_Room  extends AppCompatActivity{

    AbstractQuestions myQuesObject;
    String currentQuestion;
    String currentAnswer;
    int currentScore;

    TextView quesPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_room);

        //Initialize objects and call method to initialize the questions
        currentScore = 0;
        myQuesObject = new AbstractQuestions();
        initializeQuestions();
    }

    /**
     * Method to initialize the question and answer
     */
    private void initializeQuestions(){

        myQuesObject.generateQuestionChoice();
        currentQuestion = myQuesObject.getQuestion();
        currentAnswer = myQuesObject.getAnswer();

        quesPrompt = findViewById(R.id.quePrompt);
        quesPrompt.setText(currentQuestion);
    }

    public void nextQuestion(){
        currentQuestion = myQuesObject.getQuestion();
        currentAnswer = myQuesObject.getAnswer();
    }

    public void onClickFormulaSheet(){
        //display the all formula
    }

    public void onClickCalculator(View view){
        //call calculator layout and put it on the current layout ??
    }

    /**
     * when user submits their answer
     * @param view View as object
     */
    public void onClickSubmit(View view){

        //Get user entered text
        EditText ansText = findViewById(R.id.answerText);
        String userInput = ansText.getText().toString();

        double RightansInDouble = Double.parseDouble(currentAnswer);
        double UseansInDouble = Double.parseDouble(userInput);

        //If the answer is right
        if(Math.abs(RightansInDouble - UseansInDouble) <= 0.2){
            //Do something
            //Increment in points
            TextView score = findViewById(R.id.Currentscore);
            currentScore++;
            score.setText(currentScore+"");

            //new question to be displayed
            nextQuestion();
        }
        else{
            //if the use entered answer is incorrect
            // ?
        }
    }

}
