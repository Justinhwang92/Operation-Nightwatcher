package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;
//activity to display instructions for the game
public class Activity_Instruction extends AppCompatActivity {

    private SoundPool mySoundPool;
    private MediaPlayer myBGM;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);


        findViewById(R.id.backToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                startActivity(new Intent(Activity_Instruction.this, Activity_Menu.class));

            }
        });

        myBGM = MediaPlayer.create(this, R.raw.bgm_instructions);
        myBGM.setLooping(true);
        myBGM.start();

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
