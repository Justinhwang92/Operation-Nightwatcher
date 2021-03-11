package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.td.OperationNightwatcher.R;

public class Activity_Game extends AppCompatActivity {

    Activity_Room activityRoom;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        activityRoom = new Activity_Room();
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
