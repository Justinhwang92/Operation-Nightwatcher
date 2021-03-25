package Operation_Nightwatcher.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;


public class Activity_Credit extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ok button
        final Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Activity_Victory.class);
        Bundle bundle = new Bundle();
        bundle.putString("Score",Integer.toString(Activity_Game.score));
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }
}
