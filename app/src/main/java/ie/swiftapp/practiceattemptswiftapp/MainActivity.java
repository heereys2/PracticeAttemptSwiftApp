package ie.swiftapp.practiceattemptswiftapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by steph on 15/03/2018.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();

    }
    long MillisecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;
    private boolean running, reset;
    protected void onClick(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickStart(View view) {
        running = true;
        reset = false;
        StartTime = SystemClock.uptimeMillis();
//        Button button = (Button) view;

    }
    public void onClickStop(View view){
        running = false;
        TimeBuff += MillisecondTime;
    }

    public void onClickReset(View view){
        running = false;
        reset = true;
        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0;
    }

    public void runTimer(){
        final TextView timeView = findViewById(R.id.stopwatch_Timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(running){
                MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                UpdateTime = TimeBuff + MillisecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = Seconds / 60;

                Seconds = Seconds % 60;

                MilliSeconds = (int) (UpdateTime % 1000);

                String time = String.format("%02d:%02d:%03d",Minutes,Seconds,MilliSeconds);
                timeView.setText(time);

                }
                if(reset){
                    String timeReset = String.format("%02d:%02d:%03d",Minutes,Seconds,MilliSeconds);
                    timeView.setText(timeReset);
                }
                handler.postDelayed(this, 0);
            }
        });

    }
}
