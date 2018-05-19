package ie.swiftapp.practiceattemptswiftapp;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by steph on 15/03/2018.
 */

public class Stopwatch extends AppCompatActivity{

    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button saveButton;
    private Spinner distanceDropdown;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start up function that opens the app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        username = getIntent().getStringExtra("username");
        runTimer();
        startButton = findViewById(R.id.start_Button);
        stopButton = findViewById(R.id.stop_Button);
        resetButton = findViewById(R.id.reset_Button);
        saveButton = findViewById(R.id.save_Button);
        //setting buttons for the stopwatch visible and invisible
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
    }
    //declaring variables to make the stopwatch work
    long MillisecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;
    private boolean running, reset;
    protected void onClick(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
    }

    //this function starts the time on the stopwatch when the button is pressed
    public void onClickStart(View view) {
        running = true;
        reset = false;
        StartTime = SystemClock.uptimeMillis();
        stopButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        //changing the button to resume while the timer is not 0
        startButton.setText("Resume");
        saveButton.setVisibility(View.INVISIBLE);

    }

    //this function stops the time on the stopwatch when the button is pressed
    public void onClickStop(View view){
        running = false;
        TimeBuff += MillisecondTime;
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.VISIBLE);
    }

    //this function both stops the stopwatch but also resets the timer.
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

    //this is the stopwatch function, where the time is processed and then displayed back to the user.
    public void runTimer() {
        final TextView timeView = findViewById(R.id.stopwatch_Timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //when running is true it continues to run the stopwatch
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
                //resetting the time on the stopwatch
                if (reset) {
                    String timeReset = String.format("%02d:%02d:%03d", Minutes, Seconds, MilliSeconds);
                    timeView.setText(timeReset);
                }
                handler.postDelayed(this, 0);
            }
        });
        //array that takes in the various values for distance and displays them to the user in a dropdown menu
        distanceDropdown = findViewById(R.id.distanceDropDown);
        String[] distanceTypes = new String[]{"20 meters", "50 Meters", "100 meters", "200 meters", "400 meters", "1 km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, distanceTypes);
        distanceDropdown.setAdapter(adapter);
    }
    //when this button is pressed, it takes the time, the distance and the name of the user, so that it can be stored on the database,it then brings the user to another page to show them what time and distance has been saved.
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
        String completeTime = storeMinutes +":" + storeSeconds + ":" + storeMilli;
        String type = "playerSaveTime";
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, storingDistance, completeTime, date);

        startActivity(goToStoreTime);
    }
}
