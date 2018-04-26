package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class storeTimeActivity extends AppCompatActivity {
    public TextView distanceTextView;
    public TextView timeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String time;
        setContentView(R.layout.activity_store_time);
        String distance = getIntent().getStringExtra("savedDistance");
        String milli = getIntent().getStringExtra("savedMilli");
        String seconds = getIntent().getStringExtra("savedSeconds");
        String minutes = getIntent().getStringExtra("savedMinutes");
        distanceTextView = findViewById(R.id.distanceText);
        timeTextView = findViewById(R.id.timeText);
        if (seconds.equals("0") && minutes.equals("0")){
            time = milli + " Milliseconds";
        } else if (minutes.equals("0")){
            time = seconds + "." + milli + " Seconds";
        } else {
           time = minutes + " minute " + seconds + "." + milli + " Seconds";
        }
        timeTextView.setText(time);
        distanceTextView.setText(distance);

    }

    public void returnToStopwatch(View view){
        Intent goToStopwatch = new Intent(this, CoachStopwatch.class);
        startActivity(goToStopwatch);
    }


}
