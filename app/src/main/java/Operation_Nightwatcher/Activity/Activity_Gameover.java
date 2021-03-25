package Operation_Nightwatcher.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.td.OperationNightwatcher.R;


public class Activity_Gameover extends Activity implements View.OnClickListener  {
    TextView gameVictoryTitle;
    TextView gameVictoryText;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize text based on xml
        gameVictoryTitle = findViewById(R.id.gameVictoryTitile);
        gameVictoryText = findViewById(R.id.gameVictoryMessage);
        score = findViewById(R.id.scoreLabel);

        //Extract the data…
        Bundle bundle = getIntent().getExtras();
        System.out.println("bundle" + bundle);
        String text = bundle.getString("Score");
        score.setText(text);

        //play again button
        final Button tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Activity_Menu.class);
        finish();
        startActivity(i);
    }

}
