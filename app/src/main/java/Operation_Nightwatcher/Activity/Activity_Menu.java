package Operation_Nightwatcher.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.td.OperationNightwatcher.R;

import Operation_Nightwatcher.Game.Calculator;

//activity for the main menu of the game
public class Activity_Menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

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

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Activity_SignIn.class);
        Intent j = new Intent(this, Activity_Instruction.class);
        Intent k = new Intent(this, Activity_High_Scores.class);
        switch (view.getId()) {
            case R.id.playButton:
                startActivity(i);
                finish();
                break;
            case R.id.instructionButton:
                startActivity(j);
                finish();
                break;
            case R.id.highScoreButton:
                startActivity(k);
                finish();
                break;
            case R.id.tdgamesButton:
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

}