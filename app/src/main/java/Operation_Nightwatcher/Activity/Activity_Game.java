package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

import java.util.Timer;

import Operation_Nightwatcher.Game.TimerClass;

//this class represents the main gameplay loop
public class Activity_Game extends AppCompatActivity {
    //the room you go into when you click a door
    Activity_Room activityRoom;
    static int time;
    static int score;
    private ImageView img_profile;

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
        TimerClass counterClass = TimerClass.initInstance(900000, 1000);

//        counterClass.start();
//        TimerClass.initInstance((15*60*1000), 1000);
        try {
            counterClass = TimerClass.getInstance();
            counterClass.start();
            System.out.println("Timer: " + counterClass.getFormatedTime());
//            ((TextView) findViewById(R.id.time)).setText(counterClass.getFormatedTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Bulb icon blinking animation
        Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
        animation.setDuration(1000); //1 second duration for each animation cycle
        //animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.


        //to start the time of 15 minutes
        time = counterClass.time;

        new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished = millisUntilFinished/1000;
                ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
                        (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");
                (findViewById(R.id.bulbicon)).startAnimation(animation); //to start animation
            }

            @Override
            public void onFinish() {
                System.out.println("Done timer");
//                Intent finish = new Intent(Activity_Game.this, Activity_Menu.class);
//                startActivity(finish);
            }
        }.start();

        //door blinking animation
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
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

        if(getIntent().getByteArrayExtra("profileImage") != null && getIntent().getByteArrayExtra("profileImage").length > 0){
            // set the profile image
            byte[] bytes = getIntent().getByteArrayExtra("profileImage");
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img_profile.setImageBitmap(bitmap);
        }
    }



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
