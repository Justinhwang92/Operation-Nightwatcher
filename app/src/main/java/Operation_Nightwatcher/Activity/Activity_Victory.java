package Operation_Nightwatcher.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.td.OperationNightwatcher.R;

import java.io.IOException;

public class Activity_Victory extends Activity implements View.OnClickListener  {
    String userName;
    TextView gameVictoryTitle;
    TextView gameVictoryText;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

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

        //ok button
        final Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, Activity_High_Scores.class);
        try {
            boolean flag = saveScore();
            if(!flag){
                Toast.makeText(getApplicationContext(),"Unexpected Error!", Toast.LENGTH_LONG).show();
            }else {
                finish();
                startActivity(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean saveScore() throws IOException {
        userName = Activity_SignIn.user.getUsername();

        // Coneection with firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        //Generating id for every record
        String id = myRef.push().getKey();
        Activity_SignIn.user.setId(id);

        //Adding user in the database
        myRef.child(id).setValue(Activity_SignIn.user);

        Toast.makeText(getApplicationContext(), "Thanks, " + userName + "!", Toast.LENGTH_SHORT).show();

        return true;
    }



}
