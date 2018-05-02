package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CoachClubSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_club_selection);
        BackgroundWorker back = new BackgroundWorker(this);
        back.execute("Clubs");
        String [] clubsArray = getIntent().getStringArrayExtra("clubsArray");
        Spinner clubNames = findViewById(R.id.chooseClubSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clubsArray);
        clubNames.setAdapter(adapter);
        TextView chooseClub = findViewById(R.id.chooseClubText);
        TextView chooseTeam = findViewById(R.id.chooseTeamText);
        Spinner textNames = findViewById(R.id.chooseTeamSpinner);
        chooseTeam.setVisibility(View.INVISIBLE);
        textNames.setVisibility(View.INVISIBLE);
    }

}
