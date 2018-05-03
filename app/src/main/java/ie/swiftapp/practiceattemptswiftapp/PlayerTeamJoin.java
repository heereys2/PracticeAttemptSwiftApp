package ie.swiftapp.practiceattemptswiftapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerTeamJoin extends AppCompatActivity {

    public String username;
    public Spinner clubNames;
    public Spinner teamNames;
    public boolean onClickSpinner = false;
    Context context;
    public String[] clubsArray;
    public String[] teamsArray;
    public String chosenClub;
    public String pickedClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_player_team_join);
        username = getIntent().getStringExtra("username");
        teamNames = findViewById(R.id.chooseTeamSpinner);
        clubNames = findViewById(R.id.chooseClubSpinner);
        TextView chooseTeam = findViewById(R.id.chooseTeamText);
        TextView chooseClub = findViewById(R.id.chooseClubText);
        clubsArray = getIntent().getStringArrayExtra("clubsArray");
        teamsArray = getIntent().getStringArrayExtra("teamsArray");
        chooseTeam.setVisibility(View.INVISIBLE);
        teamNames.setVisibility(View.INVISIBLE);
        if (clubsArray == null) {

        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clubsArray);
            clubNames.setAdapter(adapter);
        }
        if (teamsArray == null) {

        } else {
            ArrayAdapter<String> adapterTeams = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsArray);
            teamNames.setAdapter(adapterTeams);
            chooseTeam.setVisibility(View.VISIBLE);
            teamNames.setVisibility(View.VISIBLE);
            clubNames.setVisibility(View.INVISIBLE);
            chooseClub.setVisibility(View.INVISIBLE);
        }
        clubNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (onClickSpinner) {
                    OnClickShowTeams();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        onClickSpinner = true;
    }


    public void OnClickShowTeams(){
           chosenClub = clubNames.getSelectedItem().toString();
        if (!chosenClub.equals("Choose your club")) {
            BackgroundWorker findTeams = new BackgroundWorker(context);
            String type = "playerteams";
            findTeams.execute(type, chosenClub, username);
        }
    }

    public void onPlayerRegTeam(View view) {
        pickedClub = getIntent().getStringExtra("clubName");
        String chosenTeam = teamNames.getSelectedItem().toString();;
        BackgroundWorker addPlayerToTeam = new BackgroundWorker(context);
        String type = "addplayertoteam";
        addPlayerToTeam.execute(type, username, pickedClub, chosenTeam);

    }
}

