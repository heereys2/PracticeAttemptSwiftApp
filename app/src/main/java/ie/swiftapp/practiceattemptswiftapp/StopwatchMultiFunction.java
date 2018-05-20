package ie.swiftapp.practiceattemptswiftapp;

import android.content.Context;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class StopwatchMultiFunction extends AppCompatActivity  {

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
    private String[] playerNamesChanged;
    private String distance;
    private int playerAmount;
    private TextView playerString;
    Context context;
    private int playerAmt;
    private String chosenPlayer;
    private String [] playerResultList;
    private String [] timeResultList;
    private int resultCounter = 0;
    private boolean onClickSpinner = false;
    private boolean resetPlayerButtons = false;
    long MillisecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds ;
    private boolean running, reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch_multi_function);
        runTimer();
        setUpPlayers();
        distance = getIntent().getStringExtra("distanceChoice");
        String playerqty = getIntent().getStringExtra("playerChoice");
        playerNames = getIntent().getStringArrayExtra("playerList");
        playerAmount = Integer.parseInt(playerqty);
        playerAmt = 1;
        playerResultList = new String[playerAmount];
        timeResultList = new String[playerAmount];
        playerNamesChanged = playerNames;

        choosingPlayers(1,"none");

        playerSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(onClickSpinner) {
                    OnClickSelectPlayerName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        onClickSpinner = true;
    }

    public void OnClickSelectPlayerName(){
        String chosenPlayer = playerSpinner.getSelectedItem().toString();
        //makes sure that it cant be "Choose your Club" that is selected.
        if (!chosenPlayer.equals("Select Player")) {
            String savedName = chosenPlayer;
            setUpPlayerString(playerAmt, savedName);
            playerSpinner.setVisibility(View.INVISIBLE);
            if (playerAmount > playerAmt){
                choosingPlayers(playerAmt + 1, chosenPlayer);
            } else {
                setupButtons();
            }
            playerAmt++;
        }

    }

    public void setupButtons(){
        startButton.setVisibility(View.VISIBLE);
    }

    public void onClickStartMulti(View view){
        running =true;
        reset = false;
        resetButton.setVisibility(View.VISIBLE);
        StartTime = SystemClock.uptimeMillis();

    }

    public void onClickResetMulti(View view){
        running = false;
        reset = true;
        resultCounter = 0;
        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0;
        resetButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        for (int i = 1; i <= playerAmount; i++){
            setUpPlayerArray(i,playerNames);
        }
    }

    public void onClickStopMulti1(View view){
        TextView text = findViewById(R.id.player1NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton1.setVisibility(View.INVISIBLE);
        timerChecker();

    }
    public void onClickStopMulti2(View view){
        TextView text = findViewById(R.id.player2NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton2.setVisibility(View.INVISIBLE);
        timerChecker();
    }
    public void onClickStopMulti3(View view){
        TextView text = findViewById(R.id.player3NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton3.setVisibility(View.INVISIBLE);
        timerChecker();
    }
    public void onClickStopMulti4(View view){
        TextView text = findViewById(R.id.player4NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton4.setVisibility(View.INVISIBLE);
        timerChecker();
    }
    public void onClickStopMulti5(View view){
        TextView text = findViewById(R.id.player5NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton5.setVisibility(View.INVISIBLE);
        timerChecker();
    }
    public void onClickStopMulti6(View view){
        TextView text = findViewById(R.id.player6NameText);
        playerResultList[resultCounter] = text.getText().toString();
        timeResultList[resultCounter] = numberString(Seconds,Minutes,MilliSeconds);
        playerButton6.setVisibility(View.INVISIBLE);
        timerChecker();
    }

    public void timerChecker(){
        resultCounter++;
        if (resultCounter == playerAmount){
            resetButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            resetPlayerButtons = true;
            running = false;
        }
    }

    public void onClickSaveMulti(View view){
        Intent goToStoreTime = new Intent(this, storeTimeActivityMulti.class);
        goToStoreTime.putExtra("arrayPlayer", playerResultList);
        goToStoreTime.putExtra( "arrayTime", timeResultList);
        goToStoreTime.putExtra("savedDistance", distance);
        String type = "playerSaveTime";
        String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

        for (int i = 0;i < playerAmount;i++) {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, playerResultList[i], distance, timeResultList[i], date);
        }
        startActivity(goToStoreTime);
    }

    public String numberString(int seconds, int minutes, int milli){
        String time;
        if (seconds ==0 && minutes == 0){
            time = milli + " Milliseconds";
        } else if (minutes == 0){
            time = seconds + "." + milli + " Seconds";
        } else {
            time = minutes + " minute " + seconds + "." + milli + " Seconds";
        }
        return time;
    }
    public void choosingPlayers(int playerAmt,String playerChosen){

        if (playerAmt == 1) {
            setUpPlayerArray(playerAmt, playerNames);
        } else {
            String [] newPlayerList;
            List<String> list = new ArrayList<String>(Arrays.asList(playerNamesChanged));
            list.remove(playerChosen);
            playerNamesChanged = list.toArray(new String[0]);
            setUpPlayerArray(playerAmt, playerNamesChanged);

        }

    }



    public void setUpPlayers() {
        playerButton1 = findViewById(R.id.player1MultiSW);
        playerButton2 = findViewById(R.id.player2MultiSW);
        playerButton3 = findViewById(R.id.player3MultiSW);
        playerButton4 = findViewById(R.id.player4MultiSW);
        playerButton5 = findViewById(R.id.player5MultiSW);
        playerButton6 = findViewById(R.id.player6MultiSW);
        restartButton = findViewById(R.id.restartAll);
        saveButton = findViewById(R.id.save_Button_Multi);
        resetButton = findViewById(R.id.resetTimerMulti);
        startButton = findViewById(R.id.timerStart);
        playerButton2.setVisibility(View.INVISIBLE);
        playerButton3.setVisibility(View.INVISIBLE);
        playerButton4.setVisibility(View.INVISIBLE);
        playerButton5.setVisibility(View.INVISIBLE);
        playerButton6.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
    }

    public void setUpPlayerArray(int playerAmt, String [] playerAvail){
        if (playerAmt == 1){
            if (resetPlayerButtons == false) {
                playerSpinner = findViewById(R.id.player1MultiSWChoice);
            }
            playerButton1.setVisibility(View.VISIBLE);
        } else if (playerAmt == 2){
            playerSpinner = findViewById(R.id.player2MultiSWChoice);
            if (resetPlayerButtons == false) {
                playerSpinner.setVisibility(View.VISIBLE);
            }
            playerButton2.setVisibility(View.VISIBLE);
        } else if (playerAmt == 3){
            playerSpinner = findViewById(R.id.player3MultiSWChoice);
            if (resetPlayerButtons == false) {
                playerSpinner.setVisibility(View.VISIBLE);
            }
            playerButton3.setVisibility(View.VISIBLE);
        } else if (playerAmt == 4){
            playerSpinner = findViewById(R.id.player4MultiSWChoice);
            if (resetPlayerButtons == false) {
                playerSpinner.setVisibility(View.VISIBLE);
            }
            playerButton4.setVisibility(View.VISIBLE);
        } else if (playerAmt == 5){
            playerSpinner = findViewById(R.id.player5MultiSWChoice);
            if (resetPlayerButtons == false) {
                playerSpinner.setVisibility(View.VISIBLE);
            }
            playerButton5.setVisibility(View.VISIBLE);
        } else if (playerAmt == 6){
            playerSpinner = findViewById(R.id.player6MultiSWChoice);
            if (resetPlayerButtons == false) {
                playerSpinner.setVisibility(View.VISIBLE);
            }
            playerButton6.setVisibility(View.VISIBLE);
        }
        if (resetPlayerButtons == false) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerAvail);
            playerSpinner.setAdapter(adapter);
            playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (onClickSpinner) {
                        OnClickSelectPlayerName();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void setUpPlayerString(int playerAmt, String playerName){
        if (playerAmt == 1){
            playerString = findViewById(R.id.player1NameText);
        } else if (playerAmt == 2){
            playerString = findViewById(R.id.player2NameText);
        } else if (playerAmt == 3){
            playerString = findViewById(R.id.player3NameText);
        } else if (playerAmt == 4){
            playerString = findViewById(R.id.player4NameText);
        } else if (playerAmt == 5){
            playerString = findViewById(R.id.player5NameText);
        } else if (playerAmt == 6){
            playerString = findViewById(R.id.player6NameText);
        }
        playerString.setText(playerName);
    }



    //this is the stopwatch function, where the time is processed and then displayed back to the user.
    public void runTimer() {
        final TextView timeView = findViewById(R.id.stopwatch_Timer2);
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
