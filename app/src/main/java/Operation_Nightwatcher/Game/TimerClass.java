package Operation_Nightwatcher.Game;

import android.os.CountDownTimer;

public class TimerClass extends CountDownTimer {

    private static String hms;
    public static int time;
    private static TimerClass instance;

    private TimerClass(long millisInFuture, long countDownInterval){
        super(millisInFuture,countDownInterval);
    }

    public static TimerClass  initInstance(long millisInFuture, long countDownInterval){
        if(instance==null){
            instance =  new TimerClass(millisInFuture,countDownInterval);
        }
        return instance;
    }

    public static TimerClass  getInstance() throws Exception{
        if(instance==null){
            throw new Exception("Parameters not initialized. Initiate with initInstance");
        }else{
            return instance;
        }
    }

    public static String getFormatedTime(){
        return hms;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        time = (int) millisUntilFinished;
        millisUntilFinished = millisUntilFinished/1000;
        hms = (String.format("Time: %02d:%02d", (millisUntilFinished % 3600) / 60, (millisUntilFinished % 60)) );
    }
    @Override
    public void onFinish() {

    }
}
