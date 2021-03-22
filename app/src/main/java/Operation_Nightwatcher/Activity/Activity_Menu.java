package Operation_Nightwatcher.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.td.OperationNightwatcher.R;

import Operation_Nightwatcher.Game.Calculator;

//activity for the main menu of the game
public class Activity_Menu extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer myBGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        myBGM = MediaPlayer.create(this, R.raw.bgm_main_menu);
        myBGM.setLooping(true);
        myBGM.start();

        // Initialize textViews and buttons
        TextView title = findViewById(R.id.title);
        Button playButton = findViewById(R.id.playButton);
        Button instructionButton = findViewById(R.id.instructionButton);
        Button tdGamesButton = findViewById(R.id.tdgamesButton);
        Button highScoreButton = findViewById(R.id.highScoreButton);
        // Attach the click listener to the buttons
        playButton.setOnClickListener(this);
        instructionButton.setOnClickListener(this);
        tdGamesButton.setOnClickListener(this);
        highScoreButton.setOnClickListener(this);
    }

    public void onClickBackstory(View view){
        Intent l = new Intent(this, Activity_BackStory.class);
        System.out.println("\n *\nback Story clicked");
        startActivity(l);
        if (myBGM != null) {
            if (myBGM.isPlaying())
                myBGM.stop();
            myBGM.reset();
            myBGM.release();
            myBGM = null;
        }
        finish();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Activity_SignIn.class);
        Intent j = new Intent(this, Activity_Instruction.class);
        Intent k = new Intent(this, Activity_High_Scores.class);

        switch (view.getId()) {
            case R.id.playButton:
                startActivity(i);
                if (myBGM != null) {
                    if (myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM = null;
                }
                finish();
                break;
            case R.id.instructionButton:
                startActivity(j);
                if (myBGM != null) {
                    if (myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM = null;
                }
                finish();
                break;
            case R.id.highScoreButton:
                startActivity(k);
                if (myBGM != null) {
                    if (myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM = null;
                }
                finish();
                break;
            case R.id.tdgamesButton:
                Uri uri = Uri.parse("https://www.tutordudes.com/tdgames"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                if (myBGM != null) {
                    if (myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM = null;
                }
                finish();
                break;
        }
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