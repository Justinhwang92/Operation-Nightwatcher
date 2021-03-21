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
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

//this class represents the main gameplay loop
public class Activity_Game extends AppCompatActivity {
    //the room you go into when you click a door
    Activity_Room activityRoom;
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

        // Initialize the image view
        img_profile = findViewById(R.id.img_profile);

        if(getIntent().getByteArrayExtra("profileImage") != null && getIntent().getByteArrayExtra("profileImage").length > 0){
            // set the profile image
            byte[] bytes = getIntent().getByteArrayExtra("profileImage");
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img_profile.setImageBitmap(bitmap);
        }

    }
    //below are onclick methods for the doors
    public void door1Click(View view){
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
