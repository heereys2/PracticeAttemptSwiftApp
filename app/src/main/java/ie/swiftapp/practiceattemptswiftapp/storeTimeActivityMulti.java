package ie.swiftapp.practiceattemptswiftapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class storeTimeActivityMulti extends AppCompatActivity {
    public TextView distanceTable;
    public TextView timeTable;
    public TextView playerTable;
    public String [] timeArray;
    public String [] playerArray;
    public String distance;
    public TableRow table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String time;
        setContentView(R.layout.activity_store_time_multi);
        distance = getIntent().getStringExtra("savedDistance");
        playerArray = getIntent().getStringArrayExtra("arrayPlayer");
        timeArray = getIntent().getStringArrayExtra("arrayTime");

        int counter = playerArray.length-1;
        for (int i = 0; i <= counter; i++){
            fillDetails(i);
        }
    }

    public void fillDetails(int count){
        if (count == 0){
            timeTable = findViewById(R.id.showingResultTime1);
            playerTable = findViewById(R.id.showingResultPlayerName1);
            distanceTable = findViewById(R.id.showingResultDistance1);
            table = findViewById(R.id.table1);
        } else if (count == 1){
            timeTable = findViewById(R.id.showingResultTime2);
            playerTable = findViewById(R.id.showingResultPlayerName2);
            distanceTable = findViewById(R.id.showingResultDistance2);
            table = findViewById(R.id.table2);
        } else if (count == 2){
            timeTable = findViewById(R.id.showingResultTime3);
            playerTable = findViewById(R.id.showingResultPlayerName3);
            distanceTable = findViewById(R.id.showingResultDistance3);
            table = findViewById(R.id.table3);
        } else if (count == 3){
            timeTable = findViewById(R.id.showingResultTime4);
            playerTable = findViewById(R.id.showingResultPlayerName4);
            distanceTable = findViewById(R.id.showingResultDistance4);
            table = findViewById(R.id.table4);
        } else if (count == 4){
            timeTable = findViewById(R.id.showingResultTime5);
            playerTable = findViewById(R.id.showingResultPlayerName5);
            distanceTable = findViewById(R.id.showingResultDistance5);
            table = findViewById(R.id.table5);
        } else if (count == 5){
            timeTable = findViewById(R.id.showingResultTime6);
            playerTable = findViewById(R.id.showingResultPlayerName6);
            distanceTable = findViewById(R.id.showingResultDistance6);
            table = findViewById(R.id.table6);
        }
        timeTable.setText(timeArray[count]);
        playerTable.setText(playerArray[count]);
        distanceTable.setText(distance);
        timeTable.setVisibility(View.VISIBLE);
        playerTable.setVisibility(View.VISIBLE);
        distanceTable.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);
    }
}
