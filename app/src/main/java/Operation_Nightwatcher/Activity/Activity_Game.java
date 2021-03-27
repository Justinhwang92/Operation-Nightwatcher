package Operation_Nightwatcher.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import Operation_Nightwatcher.Game.BroadcastService;
import Operation_Nightwatcher.Game.TimerClass;

//this class represents the main gameplay loop
public class Activity_Game extends AppCompatActivity {
    //the room you go into when you click a door
    Activity_Room activityRoom;
    static int time = 900000;
    static int score;
    private ImageView img_profile;
    boolean done = false;
    static List<Integer> allImagesnumber = Arrays.asList(2,3,1,0,4);
    CountDownTimer tt;

    private MediaPlayer myBGM;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        myBGM = MediaPlayer.create(this, R.raw.bgm_doors);
        myBGM.setLooping(true);
        myBGM.start();

        activityRoom = new Activity_Room();
        score = 0;

//        allImagesnumber = new ArrayList<>();

//        startService(new Intent(this, BroadcastService.class));
//        Log.i(TAG, "Started service");

        if(!done) {
        tt  =   new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time = (int) millisUntilFinished;
                    millisUntilFinished = millisUntilFinished / 1000;
                    ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
                            (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");
                    ((TextView) findViewById(R.id.scoreID)).setText("Score : " + score);
                    doneGame();
//                    System.out.println("Started : **********************************************");
                }

                @Override
                public void onFinish() {
//                System.out.println("Done timer");
//                Intent finish = new Intent(Activity_Game.this, Activity_Menu.class);
//                startActivity(finish);
                    Toast t = Toast.makeText(Activity_Game.this, "Time Out! ", Toast.LENGTH_SHORT + 100);
                    t.show();
                    doneGame();
                    Intent finish = new Intent(Activity_Game.this, Activity_Gameover.class);
                    Bundle bundle = new Bundle();
                    String points;
                    points = Integer.toString(score);
                    bundle.putString("Score",points);
                    finish.putExtras(bundle);
                    startActivity(finish);
                    finish();
//                finish();
                }
            }.start();
        }

        //door blinking animation
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.door1).startAnimation(anim);
        findViewById(R.id.door2).startAnimation(anim);
        findViewById(R.id.door3).startAnimation(anim);
        findViewById(R.id.door4).startAnimation(anim);
        findViewById(R.id.door5).startAnimation(anim);


        // Initialize the image view
        img_profile = findViewById(R.id.img_profile);

        if(getIntent().getParcelableExtra("imageUri") != null){
            Uri imageUri = getIntent().getParcelableExtra("imageUri");
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            img_profile.setImageBitmap(selectedImage);
        }
    }

//    private BroadcastReceiver br = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            updateGUI(intent); // or whatever method used to update your GUI fields
//        }
//    };

//    private void updateGUI(Intent intent) {
//        if (intent.getExtras() != null) {
//            long millisUntilFinished = intent.getLongExtra("countdown", 0);
//            int flag = intent.getIntExtra("done", 0);
//
//            if(flag == 999){
//                Toast t = Toast.makeText(Activity_Game.this, "Time Out! ", Toast.LENGTH_SHORT + 100);
//                t.show();
//                doneGame();
//                finish();
//            }
//            else {
////            Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
//                System.out.println("Recieved : time = " + millisUntilFinished);
////                ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
////                        (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");
//                String tim = "Time: " + ((millisUntilFinished % 3600)/60) + ":" + (millisUntilFinished%60);
//                ((TextView) findViewById(R.id.time)).setText(millisUntilFinished+"");
//                ((TextView) findViewById(R.id.scoreID)).setText("Score : " + score);
//            }
//        }
//    }
//    }

    //below are onclick methods for the doors
    public void door1Click(View view){
        findViewById(R.id.door1).setBackgroundColor(00000000);
        findViewById(R.id.door1).clearAnimation();
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
//        activityRoom.changebackground(1);
    }

    public void door2Click(View view){
        findViewById(R.id.door2).setBackgroundColor(00000000);
        findViewById(R.id.door2).clearAnimation();
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
//        activityRoom.changebackground(2);
    }

    public void door3Click(View view){
        findViewById(R.id.door3).setBackgroundColor(00000000);
        findViewById(R.id.door3).clearAnimation();
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
//        activityRoom.changebackground(3);

    }

    public void door4Click(View view){
        findViewById(R.id.door4).setBackgroundColor(00000000);
        findViewById(R.id.door4).clearAnimation();
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
//        activityRoom.changebackground(4);

    }

    public void door5Click(View view){
        findViewById(R.id.door5).setBackgroundColor(00000000);
        findViewById(R.id.door5).clearAnimation();
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
//        activityRoom.changebackground(5);

    }

    public void doneGame(){
        Intent intent;
        if(score == 10){
            //Victory
            //Credit screen -> Game victory (just like asteroid fighter)
            done = true;
            tt.cancel();
            intent = new Intent(this, Activity_Credit.class);
            Bundle bundle = new Bundle();
            String points;
            points = Integer.toString(score);
            bundle.putString("Score",points);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
//        else{
//            Game over
//            intent = new Intent(this, Activity_Gameover.class);
//        }


    }

    @Override
    protected void onStop(){
        super.onStop();
        stopService(new Intent(this, BroadcastService.class));
    }
    @Override
    protected void onStart(){

        super.onStart();
//        try {
//            TimerClass.getInstance().start();
//            System.out.println("\n *************** \n time "+TimerClass.time+" \n ************");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
//        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR1));
//        Log.i(TAG, "Registered broacast receiver");

//        TimerClass counterClass;

////        counterClass.start();
////        TimerClass.initInstance((15*60*1000), 1000);
//        try {
//            counterClass = TimerClass.getInstance();
////            counterClass.start();
//            time = counterClass.time;
////            System.out.println("Timer: " + counterClass.getFormatedTime());
////            ((TextView) findViewById(R.id.time)).setText(counterClass.getFormatedTime());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
////        TimerClass counter;
//        try {
////            counter = TimerClass.getInstance();
//            //to start the time of 15 minutes
//            System.out.println("\n****\ntime : "+time);
//        } catch(Exception e){
//            System.out.println("Found error.");
//        }
//
//        doneGame();
//
//        if(time != 0) {
//            new CountDownTimer(time, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    millisUntilFinished = millisUntilFinished / 1000;
//                    ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
//                            (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");
//                    ((TextView) findViewById(R.id.scoreID)).setText("Score : " + score);
//                }
//
//                @Override
//                public void onFinish() {
////                System.out.println("Done timer");
////                Intent finish = new Intent(Activity_Game.this, Activity_Menu.class);
////                startActivity(finish);
//                    Toast t = Toast.makeText(Activity_Game.this, "Time Out! ", Toast.LENGTH_SHORT + 100);
//                    t.show();
//                    doneGame();
////                finish();
//                }
//            }.start();
//        }

        if (myBGM != null)
            myBGM.start();
    }
}
