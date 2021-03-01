package Operation_Nightwatcher.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.td.OperationNightwatcher.R;

public class Activity_Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.activity__splash);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);

//        final RelativeLayout animationlogo = findViewById(R.id.imageViewLogo);
        final ImageView animationlogo = findViewById(R.id.imageViewLogo);
        animationlogo.setSelected(true);

        //moves only once
//        ObjectAnimator animator = Objectnimator.ofFloat(animationlogo, "translationX",420f);
//        animator.setDuration(4000);
//        animator.start();

        animationlogo.startAnimation(animation);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Activity_Splash.this, Activity_Menu.class);
                Activity_Splash.this.startActivity(mainIntent);
                Activity_Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}