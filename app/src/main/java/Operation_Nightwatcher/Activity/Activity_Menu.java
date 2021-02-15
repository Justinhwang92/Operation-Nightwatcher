package Operation_Nightwatcher.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.td.OperationNightwatcher.R;

public class Activity_Menu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize textViews and buttons
        TextView tittle = findViewById(R.id.title);
        Button playButton = findViewById(R.id.playButton);
        Button instructionButton = findViewById(R.id.instructionButton);
        Button tdgamesButton = findViewById(R.id.tdgamesButton);

        // Attach the click listener to the buttons
        playButton.setOnClickListener(this);
        instructionButton.setOnClickListener(this);
        tdgamesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Activity_Game.class);
        Intent j = new Intent(this, Activity_Instruction.class);

        switch (view.getId()) {
            case R.id.playButton:
                startActivity(i);
                finish();
                break;
            case R.id.instructionButton:
                startActivity(j);
                finish();
                break;
            case R.id.tdgamesButton:
                finish();
                break;
        }
    }

}