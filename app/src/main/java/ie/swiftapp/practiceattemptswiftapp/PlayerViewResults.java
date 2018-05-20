package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayerViewResults extends AppCompatActivity {

    public String playerResults[];
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view_results);

        playerResults = getIntent().getStringArrayExtra("resultData");
        username = getIntent().getStringExtra("username");

        TextView playername = (TextView) findViewById(R.id.tv_playername);
        playername.setText(username);

        String event1 = playerResults[playerResults.length-9];
        String result1 = playerResults[playerResults.length-8];
        String date1 = playerResults[playerResults.length-7];

        String event2 = playerResults[playerResults.length-6];
        String result2 = playerResults[playerResults.length-5];
        String date2 = playerResults[playerResults.length-4];

        String event3 = playerResults[playerResults.length-3];
        String result3 = playerResults[playerResults.length-2];
        String date3 = playerResults[playerResults.length-1];


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
