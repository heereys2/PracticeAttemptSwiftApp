package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CoachClubSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_club_selection);
        BackgroundWorker back = new BackgroundWorker(this);
        back.execute("Clubs");
        Spinner clubNames = findViewById(R.id.chooseClubSpinner);
        String[] distanceTypes = new String[]{"20 meters", "50 Meters", "100 meters", "200 meters", "400 meters", "1 km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, distanceTypes);
        clubNames.setAdapter(adapter);
    }
}
