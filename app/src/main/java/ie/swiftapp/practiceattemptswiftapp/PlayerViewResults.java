package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayerViewResults extends AppCompatActivity {

    public String playerResults[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view_results);

        playerResults = getIntent().getStringArrayExtra("resultData");
        String event1 = playerResults[0];
        String result1 = playerResults[1];
        String date1 = playerResults[2];

        String event2 = playerResults[3];
        String result2 = playerResults[4];
        String date2 = playerResults[5];

        String event3 = playerResults[6];
        String result3 = playerResults[7];
        String date3 = playerResults[8];


        TextView event1t = (TextView) findViewById(R.id.tv_event1);
        event1t.setText(event1);
        TextView result1t = (TextView) findViewById(R.id.tv_result1);
        result1t.setText(result1);
        TextView date1t = (TextView) findViewById(R.id.tv_date1);
        date1t.setText(date1);

        TextView event2t = (TextView) findViewById(R.id.tv_event2);
        event2t.setText(event2);
        TextView result2t = (TextView) findViewById(R.id.tv_result2);
        result2t.setText(result2);
        TextView date2t = (TextView) findViewById(R.id.tv_date2);
        date2t.setText(date2);

        TextView event3t = (TextView) findViewById(R.id.tv_event3);
        event3t.setText(event3);
        TextView result3t = (TextView) findViewById(R.id.tv_result3);
        result3t.setText(result3);
        TextView date3t = (TextView) findViewById(R.id.tv_date3);
        date3t.setText(date3);

    }
}
