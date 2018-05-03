package ie.swiftapp.practiceattemptswiftapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CoachClubSelection extends AppCompatActivity {

    public Spinner clubNames;
    public Spinner teamNames;
    public boolean onClickSpinner = false;
    public String user_name;
    public String clubChosen;
    Context context;
    public String userType = "Coach";
    public Button submitChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        user_name = getIntent().getStringExtra("username");
        clubChosen = getIntent().getStringExtra("clubname");
        setContentView(R.layout.activity_coach_club_selection);
        teamNames = findViewById(R.id.chooseTeamSpinner);
        clubNames = findViewById(R.id.chooseClubSpinner);
        submitChoice = findViewById(R.id.submitTeamChoice);
        TextView chooseTeam = findViewById(R.id.chooseTeamText);
        TextView chooseClub = findViewById(R.id.chooseClubText);
        TextView registerClub = findViewById(R.id.registerClub);
        TextView registerTeam = findViewById(R.id.registerTeam);
        Button goToClubRegisterButton = findViewById(R.id.goToRegisterClub);
        Button goToTeamRegisterButton = findViewById(R.id.goToRegisterTeam);
        String [] clubsArray = getIntent().getStringArrayExtra("clubsArray");
        String [] teamsArray = getIntent().getStringArrayExtra("teamsArray");
        chooseTeam.setVisibility(View.INVISIBLE);
        teamNames.setVisibility(View.INVISIBLE);
        registerTeam.setVisibility(View.INVISIBLE);
        goToTeamRegisterButton.setVisibility(View.INVISIBLE);
        submitChoice.setVisibility(View.INVISIBLE);

        if(clubsArray == null){

        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clubsArray);
            clubNames.setAdapter(adapter);
        }
        if (teamsArray == null){

        } else {
            ArrayAdapter<String> adapterTeams = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamsArray);
            teamNames.setAdapter(adapterTeams);
            chooseTeam.setVisibility(View.VISIBLE);
            teamNames.setVisibility(View.VISIBLE);
            clubNames.setVisibility(View.INVISIBLE);
            chooseClub.setVisibility(View.INVISIBLE);
            registerTeam.setVisibility(View.VISIBLE);
            goToTeamRegisterButton.setVisibility(View.VISIBLE);
            registerClub.setVisibility(View.INVISIBLE);
            goToClubRegisterButton.setVisibility(View.INVISIBLE);
            submitChoice.setVisibility(View.VISIBLE);
        }
        clubNames.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(onClickSpinner) {
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
        String chosenClub = clubNames.getSelectedItem().toString();
        if (!chosenClub.equals("Choose your club")) {
            BackgroundWorker findTeams = new BackgroundWorker(context);
            String type = "coachTeam";
            findTeams.execute(type, chosenClub, user_name);
        }
    }

    public void OnClickRegisterClub(View view){
        Intent goToRegisterClub = new Intent(this, CreateClub.class);
        goToRegisterClub.putExtra("username",user_name);
        goToRegisterClub.putExtra("userType", userType);
        startActivity(goToRegisterClub);
    }

    public void OnClickRegisterTeam(View view){
        Intent goToRegisterTeam = new Intent(this, CreateTeam.class);
        goToRegisterTeam.putExtra("username",user_name);
        goToRegisterTeam.putExtra("userType", userType);
        goToRegisterTeam.putExtra("clubchosen", clubChosen);
        startActivity(goToRegisterTeam);
    }

    public void onClickGoToTeamHome(View view){
        Intent goToRegisterTeam = new Intent(this, CoachHome.class);
        goToRegisterTeam.putExtra("username",user_name);
        String teamChoice = teamNames.getSelectedItem().toString();
        goToRegisterTeam.putExtra("teamchoice", teamChoice);
        startActivity(goToRegisterTeam);
    }
}
