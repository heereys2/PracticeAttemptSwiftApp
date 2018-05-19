package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by steph on 25/04/2018.
 */

public class multiPlayerSW extends AppCompatActivity {

    public Spinner distanceDropdown;
    public Spinner playerDropdown;
    public String [] playerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayersw);
        playerList = getIntent().getStringArrayExtra("playerNames");
        distanceDropdown = findViewById(R.id.multiDistanceDropDown);
        String[] distanceTypes = new String[]{"20 meters", "50 Meters", "100 meters", "200 meters", "400 meters", "1 km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, distanceTypes);
        distanceDropdown.setAdapter(adapter);

        playerDropdown = findViewById(R.id.playerDropdown);
        String[] playerAmount = new String[]{"2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapterPlayer = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerAmount);
        playerDropdown.setAdapter(adapterPlayer);

    }

    public void onClickGoToMultiStopwatch(View v){
        Intent goToStoreTime = new Intent(this, storeTimeActivity.class);
        goToStoreTime.putExtra("playerList",playerList);
        String storingDistance = distanceDropdown.getSelectedItem().toString();
        String storingNoPlayers = playerDropdown.getSelectedItem().toString();
        goToStoreTime.putExtra("playerChoice", storingNoPlayers);
        goToStoreTime.putExtra("distanceChoice", storingDistance);

        startActivity(goToStoreTime);
    }
}
