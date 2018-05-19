package ie.swiftapp.practiceattemptswiftapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopwatchMultiFunction extends AppCompatActivity {

    private Button playerButton1;
    private Button playerButton2;
    private Button playerButton3;
    private Button playerButton4;
    private Button playerButton5;
    private Button playerButton6;
    private Button restartButton;
    private Spinner playerSpinner;
    private Button resetButton;
    private Button saveButton;
    private Button startButton;
    private String[] playerNames;
    private String distance;
    private int playerAmount;
    private String chosenPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch_multi_function);
        runTimer();
        setUpPlayers();
        distance = getIntent().getStringExtra("distanceChoice");
        String playerqty = getIntent().getStringExtra("playerChoice");
        playerNames = getIntent().getStringArrayExtra("playerList");
        playerAmount = Integer.parseInt(playerqty);
        choosingPlayers(1,"none");

        playerSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(onClickSpinner) {
                    OnClickShowTeams();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void choosingPlayers(int playerAmt,String playerChosen){
        if(playerAmt == playerAmount){

        } else if (playerAmt == 1) {
            setUpPlayerArray(playerAmt, playerNames);
        } else {
            String [] newPlayerList;
            List<String> list = new ArrayList<String>(Arrays.asList(playerNames));
            list.remove(playerChosen);
            newPlayerList = list.toArray(new String[0]);
            setUpPlayerArray(playerAmt, newPlayerList);
        }

    }

    long MillisecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;
    private boolean running, reset;


    public void setUpPlayers() {
        playerButton1 = findViewById(R.id.player1MultiSW);
        playerButton2 = findViewById(R.id.player2MultiSW);
        playerButton3 = findViewById(R.id.player3MultiSW);
        playerButton4 = findViewById(R.id.player4MultiSW);
        playerButton5 = findViewById(R.id.player5MultiSW);
        playerButton6 = findViewById(R.id.player6MultiSW);
        restartButton = findViewById(R.id.restartAll);
        saveButton = findViewById(R.id.save_Button);
        resetButton = findViewById(R.id.resetTimer);
        startButton = findViewById(R.id.start_Button);
        playerButton2.setVisibility(View.INVISIBLE);
        playerButton3.setVisibility(View.INVISIBLE);
        playerButton4.setVisibility(View.INVISIBLE);
        playerButton5.setVisibility(View.INVISIBLE);
        playerButton6.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
    }

    public void setUpPlayerArray(int playerAmt, String [] playerAvail){
        if (playerAmt == 1){
            playerSpinner = findViewById(R.id.player1MultiSWChoice);
        } else if (playerAmt == 2){
            playerSpinner = findViewById(R.id.player2MultiSWChoice);
        } else if (playerAmt == 3){
            playerSpinner = findViewById(R.id.player3MultiSWChoice);
        } else if (playerAmt == 4){
            playerSpinner = findViewById(R.id.player4MultiSWChoice);
        } else if (playerAmt == 5){
            playerSpinner = findViewById(R.id.player5MultiSWChoice);
        } else if (playerAmt == 6){
            playerSpinner = findViewById(R.id.player6MultiSWChoice);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerAvail);
        playerSpinner.setAdapter(adapter);
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
    }


}
