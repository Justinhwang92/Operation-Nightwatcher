package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.td.OperationNightwatcher.R;

import java.util.Timer;

import Operation_Nightwatcher.Game.TimerClass;

public class Activity_Game extends AppCompatActivity {

    Activity_Room activityRoom;
    static int time;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        activityRoom = new Activity_Room();

        TimerClass counterClass = TimerClass.initInstance(900000, 1000);
        counterClass.start();
//        TimerClass.initInstance((15*60*1000), 1000);
        try {
            System.out.println("Timer: " + counterClass.getFormatedTime());
//            ((TextView) findViewById(R.id.time)).setText(counterClass.getFormatedTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = counterClass.time;

        new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished = millisUntilFinished/1000;
//                ((TextView) findViewById(R.id.time)).setText(String.format("Time: %02d:%02d",
//                        (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) + "");
            }

            @Override
            public void onFinish() {
                System.out.println("Done timer");
//                Intent finish = new Intent(Activity_Game.this, Activity_Menu.class);
//                startActivity(finish);
            }
        }.start();
    }

    public void door1Click(View view){
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
//        activityRoom.changebackground(1);
    }

    public void door2Click(View view){
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
//        activityRoom.changebackground(2);
    }

    public void door3Click(View view){
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
//        activityRoom.changebackground(3);

    }

    public void door4Click(View view){
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
//        activityRoom.changebackground(4);

    }

    public void door5Click(View view){
        Intent room = new Intent(Activity_Game.this, Activity_Room.class);
        startActivity(room);
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
}
