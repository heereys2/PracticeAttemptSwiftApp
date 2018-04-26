package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoachStopwatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_stopwatch);
    }

    public void onClickOne(View view){
        Intent goToClickOne = new Intent(this, onePlayerSW.class);
        startActivity(goToClickOne);
    }

    public void onClickMulti(View view){
        Intent goToClickMulti = new Intent(this, multiPlayerSW.class);
        startActivity(goToClickMulti);
    }

}
