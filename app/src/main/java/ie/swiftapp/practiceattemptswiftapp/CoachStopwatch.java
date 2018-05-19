package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class CoachStopwatch extends AppCompatActivity {

    public String [] playerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_stopwatch);
        playerList = getIntent().getStringArrayExtra("playerNames");
    }

    public void onClickOne(View view){
        Intent goToClickOne = new Intent(this, onePlayerSW.class);
        startActivity(goToClickOne);
//        onePlayerSW onePlayerSW = new onePlayerSW();
//        onePlayerSW.startUp();
    }

    public void onClickMulti(View view){
        Intent goToClickMulti = new Intent(this, multiPlayerSW.class);
        goToClickMulti.putExtra("playerList", playerList);
        startActivity(goToClickMulti);
    }

}
