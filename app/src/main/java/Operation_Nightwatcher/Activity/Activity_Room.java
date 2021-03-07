package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

import java.util.LinkedList;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;
import Operation_Nightwatcher.Game.Calculator;
import Operation_Nightwatcher.Game.ExpressionBuilder;

public class Activity_Room  extends AppCompatActivity implements View.OnClickListener {

    AbstractQuestions myQuesObject;
    String currentQuestion;
    String currentAnswer;
    int currentScore;

    TextView quesPrompt;

    public static TextView myCalcText;
    public static Button myCalc0;
    public static Button myCalc1;
    public static Button myCalc2;
    public static Button myCalc3;
    public static Button myCalc4;
    public static Button myCalc5;
    public static Button myCalc6;
    public static Button myCalc7;
    public static Button myCalc8;
    public static Button myCalc9;
    public static Button myCalcSin;
    public static Button myCalcCos;
    public static Button myCalcTan;
    public static Button myCalcInv;
    public static Button myCalcLn;
    public static Button myCalcSqrt;
    public static Button myCalcPi;
    public static Button myCalcLP;
    public static Button myCalcRP;
    public static Button myCalcE;
    public static Button myCalcPow;
    public static Button myCalcPeriod;
    public static Button myCalcPlus;
    public static Button myCalcMinus;
    public static Button myCalcTimes;
    public static Button myCalcDivide;
    public static Button myCalcCE;
    public static Button myCalcAC;
    public static Button myCalcEquals;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
//        setContentView(R.layout.activity_room);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.calculator);

        //Initialize objects and call method to initialize the questions
//        currentScore = 0;
//        myQuesObject = new AbstractQuestions();
//        initializeQuestions();
        myCalcText = findViewById(R.id.calcTextView);
        myCalc0 = findViewById(R.id.calc0);
        myCalc1 = findViewById(R.id.calc1);
        myCalc2 = findViewById(R.id.calc2);
        myCalc3 = findViewById(R.id.calc3);
        myCalc4 = findViewById(R.id.calc4);
        myCalc5 = findViewById(R.id.calc5);
        myCalc6 = findViewById(R.id.calc6);
        myCalc7 = findViewById(R.id.calc7);
        myCalc8 = findViewById(R.id.calc8);
        myCalc9 = findViewById(R.id.calc9);
        myCalcSin = findViewById(R.id.calcSin);
        myCalcCos = findViewById(R.id.calcCos);
        myCalcTan = findViewById(R.id.calcTan);
        myCalcInv = findViewById(R.id.calcInv);
        myCalcLn = findViewById(R.id.calcLn);
        myCalcSqrt = findViewById(R.id.calcSqrt);
        myCalcPi = findViewById(R.id.calcPi);
        myCalcLP = findViewById(R.id.calcLeftParentheses);
        myCalcRP = findViewById(R.id.calcRightParentheses);
        myCalcE = findViewById(R.id.calcE);
        myCalcPow = findViewById(R.id.calcPower);
        myCalcPeriod = findViewById(R.id.calcPeriod);
        myCalcPlus = findViewById(R.id.calcPlus);
        myCalcMinus = findViewById(R.id.calcMinus);
        myCalcTimes = findViewById(R.id.calcTimes);
        myCalcDivide = findViewById(R.id.calcDivide);
        myCalcCE = findViewById(R.id.calcCE);
        myCalcAC = findViewById(R.id.calcAC);
        myCalcEquals = findViewById(R.id.calcEquals);

        LinkedList<Button> buttons = new LinkedList<>();
        buttons.add(myCalc0);
        buttons.add(myCalc1);
        buttons.add(myCalc2);
        buttons.add(myCalc3);
        buttons.add(myCalc4);
        buttons.add(myCalc5);
        buttons.add(myCalc6);
        buttons.add(myCalc7);
        buttons.add(myCalc8);
        buttons.add(myCalc9);
        buttons.add(myCalcSin);
        buttons.add(myCalcCos);
        buttons.add(myCalcTan);
        buttons.add(myCalcInv);
        buttons.add(myCalcLn);
        buttons.add(myCalcSqrt);
        buttons.add(myCalcPi);
        buttons.add(myCalcLP);
        buttons.add(myCalcRP);
        buttons.add(myCalcE);
        buttons.add(myCalcPow);
        buttons.add(myCalcPeriod);
        buttons.add(myCalcPlus);
        buttons.add(myCalcMinus);
        buttons.add(myCalcTimes);
        buttons.add(myCalcDivide);
        buttons.add(myCalcCE);
        buttons.add(myCalcAC);
        buttons.add(myCalcEquals);


        for(Button b : buttons)
        {
            b.setOnClickListener(this);
        }
        ExpressionBuilder e = new ExpressionBuilder(this);

        Calculator c = new Calculator();

    }

    @Override
    public void onClick(View view) {
        ExpressionBuilder.onClick(view);
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
