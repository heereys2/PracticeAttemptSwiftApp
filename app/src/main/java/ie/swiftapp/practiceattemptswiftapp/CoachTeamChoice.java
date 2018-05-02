package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CoachTeamChoice extends AppCompatActivity {

    public String user_name;
    public String userTeamArray[];
    public Spinner userTeamSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_team_choice);
        user_name = getIntent().getStringExtra("username");
        userTeamArray = getIntent().getStringArrayExtra("clubsArray");
        userTeamSpinner = findViewById(R.id.userTeamsSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userTeamArray);
        userTeamSpinner.setAdapter(adapter);

    };

    public void onCreateTeam(View view) {
        Intent p = new Intent(CoachTeamChoice.this, CoachClubSelection.class);
        p.putExtra("username",user_name);
        CoachTeamChoice.this.startActivity(p);
    }

    public void onClickGoToTeamHomeDirect(View view){
        Intent goToRegisterTeam = new Intent(this, CoachHome.class);
        goToRegisterTeam.putExtra("username",user_name);
        String teamChoice = userTeamSpinner.getSelectedItem().toString();
        goToRegisterTeam.putExtra("teamchoice", teamChoice);
        startActivity(goToRegisterTeam);
    }
}
