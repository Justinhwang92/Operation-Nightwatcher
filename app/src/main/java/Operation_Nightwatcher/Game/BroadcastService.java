package Operation_Nightwatcher.Game;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BroadcastService extends Service {

    private final static String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR1 = "Operation_Nightwatcher.Activity.Activity_Game";
    Intent mainGame = new Intent(COUNTDOWN_BR1);

    public static final String COUNTDOWN_BR2 = "Operation_Nightwatcher.Activity.Activity_Room";
    Intent gameRoom = new Intent(COUNTDOWN_BR2);

    CountDownTimer cdt = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");

        cdt = new CountDownTimer((900000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished );
                mainGame.putExtra("countdown", millisUntilFinished);
                mainGame.putExtra("done", 0);
                sendBroadcast(mainGame);
                gameRoom.putExtra("countdown", millisUntilFinished);
                gameRoom.putExtra("done", 0);
                sendBroadcast(gameRoom);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                mainGame.putExtra("done", 999);
                sendBroadcast(mainGame);
                gameRoom.putExtra("done", 999);
                sendBroadcast(gameRoom);
            }
        };

        cdt.start();
    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}