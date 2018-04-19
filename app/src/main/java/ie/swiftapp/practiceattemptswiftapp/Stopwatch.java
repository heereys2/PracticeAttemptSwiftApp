package ie.swiftapp.practiceattemptswiftapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by steph on 15/03/2018.
 */

public class Stopwatch extends AppCompatActivity{

    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button saveButton;
    private Spinner distanceDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
        startButton = findViewById(R.id.start_Button);
        stopButton = findViewById(R.id.stop_Button);
        resetButton = findViewById(R.id.reset_Button);
        saveButton = findViewById(R.id.save_Button);
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
    }
    long MillisecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;
    private boolean running, reset;
    protected void onClick(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
    }

//    startButton.OnClickListener(View view);


    public void onClickStart(View view) {
        running = true;
        reset = false;
        StartTime = SystemClock.uptimeMillis();
        stopButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        startButton.setText("Resume");
        saveButton.setVisibility(View.INVISIBLE);

    }
    public void onClickStop(View view){
        running = false;
        TimeBuff += MillisecondTime;
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.VISIBLE);
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
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        startButton.setText("Start");
        saveButton.setVisibility(View.INVISIBLE);
    }

    public void runTimer() {
        final TextView timeView = findViewById(R.id.stopwatch_Timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    MillisecondTime = SystemClock.uptimeMillis() - StartTime;

                    UpdateTime = TimeBuff + MillisecondTime;

                    Seconds = (int) (UpdateTime / 1000);

                    Minutes = Seconds / 60;

                    Seconds = Seconds % 60;

                    MilliSeconds = (int) (UpdateTime % 1000);

                    String time = String.format("%02d:%02d:%03d", Minutes, Seconds, MilliSeconds);
                    timeView.setText(time);

                }
                if (reset) {
                    String timeReset = String.format("%02d:%02d:%03d", Minutes, Seconds, MilliSeconds);
                    timeView.setText(timeReset);
                }
                handler.postDelayed(this, 0);
            }
        });
        distanceDropdown = findViewById(R.id.distanceDropDown);
        String[] distanceTypes = new String[]{"20 meters", "50 Meters", "100 meters", "200 meters", "400 meters", "1 km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, distanceTypes);
        distanceDropdown.setAdapter(adapter);


    }
    public void onClickSave(View view){
        Intent goToStoreTime = new Intent(this, storeTimeActivity.class);
        String storeMilli = Integer.toString(MilliSeconds);
        String storeSeconds = Integer.toString(Seconds);
        String storeMinutes = Integer.toString(Minutes);
        goToStoreTime.putExtra("savedMilli", storeMilli);
        goToStoreTime.putExtra( "savedSeconds", storeSeconds);
        goToStoreTime.putExtra("savedMinutes", storeMinutes);
        String storingDistance = distanceDropdown.getSelectedItem().toString();
        goToStoreTime.putExtra("savedDistance", storingDistance);
        startActivity(goToStoreTime);
    }



}
