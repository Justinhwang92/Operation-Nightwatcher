package Operation_Nightwatcher.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;
//activity to display instructions for the game
public class Activity_Instruction extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);
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
