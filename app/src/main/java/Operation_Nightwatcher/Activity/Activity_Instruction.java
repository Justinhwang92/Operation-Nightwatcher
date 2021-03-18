package Operation_Nightwatcher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.td.OperationNightwatcher.R;

public class Activity_Instruction extends AppCompatActivity {
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
