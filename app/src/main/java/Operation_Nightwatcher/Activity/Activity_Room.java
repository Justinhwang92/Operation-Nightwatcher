package Operation_Nightwatcher.Activity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import com.td.OperationNightwatcher.R;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Random;

import Operation_Nightwatcher.Activity.ProblemClasses.AbstractQuestions;
import Operation_Nightwatcher.Game.Calculator;
import Operation_Nightwatcher.Game.ExpressionBuilder;
import Operation_Nightwatcher.Game.TimerClass;

//activity for a room in the game
public class Activity_Room  extends AppCompatActivity implements View.OnClickListener {

    static AbstractQuestions myQuesObject;
    static String currentQuestion;
    static String currentAnswer;
    static int currentScore;
    boolean isCalcOn = false;
    boolean isCheatSheetOn;
    boolean isQuesOn = false;

    Activity_Game activity_game;

    Group groupofquestionthing;

    private MediaPlayer myBGM;

//    // FIELDS REGARDING MATH PROBLEMS IN THE GAME
//    public ViewGroup.LayoutParams lp;
//    public View secondLayerView;

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
        setContentView(R.layout.activity_room);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myBGM = MediaPlayer.create(this, R.raw.bgm_room);
        myBGM.setLooping(true);
        myBGM.start();

        //Initialize objects and call method to initialize the questions
        currentScore = 0;
        myQuesObject = new AbstractQuestions();
        activity_game = new Activity_Game();

        Random rnd = new Random();
        int index = rnd.nextInt(5);

        changebackground(activity_game.allImagesnumber.get(index));
//        activity_game.allImagesnumber.add(activity_game.allImagesnumber.get(0));
//        activity_game.allImagesnumber.remove(0);

        findViewById(R.id.xmlCalculator).setVisibility(View.GONE);
        EditText ed = (EditText) findViewById(R.id.answerText);
        ed.setImeOptions(EditorInfo.IME_ACTION_DONE);
        groupofquestionthing = findViewById(R.id.group);
        groupofquestionthing.setVisibility(View.GONE);
        findViewById(R.id.formulaScroll).setVisibility(View.GONE);

        initializeQuestions();
        currentScore = activity_game.score;
        ((TextView)findViewById(R.id.Currentscore)).setText(currentScore+"");
        myQuesObject = new AbstractQuestions();

        //initialize the calculator buttons below, for now manually
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

        //Bulb icon blinking animation
        Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
        animation.setDuration(1000); //1 second duration for each animation cycle
        //animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.

        (findViewById(R.id.bulbicon)).startAnimation(animation); //to start animation

        for(Button b : buttons)
        {
            b.setOnClickListener(this);
        }
        //for dealing with the calculator screen
        ExpressionBuilder e = new ExpressionBuilder(this);

        Calculator c = new Calculator();

        //for 15 minutes timer game clock
        //timer class object to receive exact time
//        TimerClass counter;
        try {
//            counter = TimerClass.getInstance();
//            System.out.println("Timer: " + counter.getFormatedTime());
//            ((TextView) findViewById(R.id.time)).setText(counter.getFormatedTime());

            new CountDownTimer(Activity_Game.time, 1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    Activity_Game.time = (int) millisUntilFinished;
                    millisUntilFinished = millisUntilFinished/1000;
                    ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
                            (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");

                    if(Activity_Game.time < (180000)){
                        //stage 4 on
//                        System.out.println("\n******\n 4");
                        findViewById(R.id.bulbicon).setVisibility(View.GONE);
                        findViewById(R.id.bulbicon).setBackgroundResource(R.drawable.light_stage4_removebg);
                    }
                    else if(Activity_Game.time < (360000)){ //22500*2
//                        System.out.println("\n******\n 3");
                        findViewById(R.id.bulbicon).setVisibility(View.GONE);
                        findViewById(R.id.bulbicon).setBackgroundResource(R.drawable.light_stage3_removebg);
                    }
                    else if(Activity_Game.time < (540000)){
//                        System.out.println("\n******\n 2");
                        findViewById(R.id.bulbicon).setVisibility(View.GONE);
                        findViewById(R.id.bulbicon).setBackgroundResource(R.drawable.light_stage2_removebg);
                    }
                    else{
                        //nothing basically as it is already on stage 1 for the light
//                        System.out.println("\n *** \n 1");
                    }
                }

                @Override
                public void onFinish() {
//                    System.out.println("Activity done room class");
                    Intent finish = new Intent(Activity_Room.this, Activity_Menu.class);
                    startActivity(finish);
                }
            }.start();

        } catch (Exception exception) {
            exception.printStackTrace();
        }


        //end calculator initialization
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

        System.out.println("Current Answer is: " + currentAnswer);

        quesPrompt = findViewById(R.id.quePrompt);
        quesPrompt.setText(currentQuestion);
        Spannable spanna = new SpannableString(currentQuestion);
        spanna.setSpan(new BackgroundColorSpan(0xFF000000),0, currentQuestion.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        quesPrompt.setText(spanna);
//        quesPrompt.setBackgroundResource(R.color.black);
    }

    public void nextQuestion(){

//        System.out.println("Yeah! That's Current Answer: ");

        Toast t = Toast.makeText( Activity_Room.this, "+2", Toast.LENGTH_SHORT+100);
        t.show();
        //finish current room activity and go back to previous one
        finish();
//        myQuesObject.generateQuestionChoice();
//        currentQuestion = myQuesObject.getQuestion();
//        currentAnswer = myQuesObject.getAnswer();

//        quesPrompt = findViewById(R.id.quePrompt);
//        quesPrompt.setText(currentQuestion);

//       .setBackground(R.drawable.room2_fog)
    }

    public void onClickFormulaSheet(View view){
        if(isCheatSheetOn){
            findViewById(R.id.formulaScroll).setVisibility(View.GONE);
            isCheatSheetOn = false;
        }
        else{
            findViewById(R.id.formulaScroll).setVisibility(View.VISIBLE);
            isCheatSheetOn = true;
        }
    }


    public void onClickCalculator(View view){

        if(isCalcOn){
            findViewById(R.id.xmlCalculator).setVisibility(View.GONE);
            isCalcOn = false;
        }
        else{
            findViewById(R.id.xmlCalculator).setVisibility(View.VISIBLE);
            isCalcOn = true;
        }
    }

    public void onClickQuestion(View view){
        if(isQuesOn){
            groupofquestionthing.setVisibility(View.GONE);
            isQuesOn = false;
        }
        else{
            groupofquestionthing.setVisibility(View.VISIBLE);
            isQuesOn = true;
        }
    }

    public void changebackground(int id){

        ConstraintLayout v = (ConstraintLayout) findViewById(R.id.activityroom);

        switch (id){

            case 0:
                v.setBackgroundResource(R.drawable.room1_clear);
                break;
            case 1:
                v.setBackgroundResource(R.drawable.room2clear);
                break;
            case 2:
                v.setBackgroundResource(R.drawable.room3_clear);
                break;
            case 3:
                v.setBackgroundResource(R.drawable.room4_clear);
                break;
            case 4:
                v.setBackgroundResource(R.drawable.room5_clear);
                break;
            default:
                break;

        }
    }

    /**
     * when user submits their answer
     * @param view View as object
     */
    public void onClickSubmit(View view){

        //Get user entered text
        EditText ansText = findViewById(R.id.answerText);
        String userInput = ansText.getText().toString();

        //choice 4 ans contains comma
        //
        double RightansInDouble;
        double UseansInDouble;

        try {
            if (currentAnswer.contains(",")) {
//            RightansInDouble = Double.parseDouble(currentAnswer);
                UseansInDouble = Double.parseDouble(userInput);

                String[] ans = currentAnswer.split(",");
                double ans1 = Double.parseDouble(ans[0]);
                double ans2 = Double.parseDouble(ans[1]);

                //If the answer is right
                if (Math.abs(ans1 - UseansInDouble) <= 0.2 || Math.abs(ans2 - UseansInDouble) <= 0.2) {
                    //Do something
                    //Increment in points
                    TextView score = findViewById(R.id.Currentscore);
                    currentScore = Integer.parseInt(score.getText().toString());
                    currentScore+= 2;

                    activity_game.score+= 2;
                    score.setText(activity_game.score + "");

                    //new question to be displayed
                    nextQuestion();
                } else {
                    //if the use entered answer is incorrect
                    // go down a stage
                    System.out.println("Wrong answer");
                    Toast t = Toast.makeText(Activity_Room.this, "Wrong Answer! Try again!", Toast.LENGTH_SHORT);
                    t.show();
                }
            } else {
                RightansInDouble = Double.parseDouble(currentAnswer);
                UseansInDouble = Double.parseDouble(userInput);

                //If the answer is right
                if (Math.abs(RightansInDouble - UseansInDouble) <= 0.2) {
                    //Do something
                    //Increment in points
                    TextView score = findViewById(R.id.Currentscore);
                    currentScore+= 2;
                    activity_game.score+= 2;
                    score.setText(activity_game.score + "");

                    //new question to be displayed
                    nextQuestion();
                } else {
                    //if the use entered answer is incorrect
                    System.out.println("Wrong answer");
                    Toast t = Toast.makeText(Activity_Room.this, "Wrong Answer! Try again!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }
        catch (Exception e){
            Toast t = Toast.makeText( Activity_Room.this, "Invalid Input! Use only digits and points if necessary!", Toast.LENGTH_SHORT);
            t.show();
            System.out.println("Invalid Input");
        }

        //for testing purpose
//        System.out.println("Clicked submit button");
//        nextQuestion();
    }


    @Override
    protected void onStop(){
        super.onStop();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onRestart(){ super.onRestart(); }

    @Override
    protected void onPause() {
        super.onPause();
        if (myBGM != null)
            myBGM.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myBGM != null)
            myBGM.start();
    }
}
