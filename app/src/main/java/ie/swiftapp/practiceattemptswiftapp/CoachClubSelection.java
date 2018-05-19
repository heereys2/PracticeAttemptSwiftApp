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

//this class give a coach the option to either make a new club, new team in a club, or join an existing team/club
public class CoachClubSelection extends AppCompatActivity {

    //declaring some of the variables that we will have to read in from other pages or send to other pages.
    public Spinner clubNames;
    public Spinner teamNames;
    public boolean onClickSpinner = false;
    public String user_name;
    public String clubChosen;
    Context context;
    public String userType = "Coach";
    public Button submitChoice;
    @Override
    //creates the UI and gives values to some of the variables.
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

        //if else statement where if clubsArray isn't null then show the dropdown menu.
        if(clubsArray == null){

        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clubsArray);
            clubNames.setAdapter(adapter);
        }
        //if else statement where if teamsArray isn't null then show the dropdown menu.
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
        //this waits for someone to select a value from the clubsArray dropdown then performs an action.
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

    //This function is to take the club that was selected, then run it through a query and then show the teams available
    public void OnClickShowTeams(){
        String chosenClub = clubNames.getSelectedItem().toString();
        //makes sure that it cant be "Choose your Club" that is selected.
        if (!chosenClub.equals("Choose your club")) {
            BackgroundWorker findTeams = new BackgroundWorker(context);
            String type = "coachTeam";
            findTeams.execute(type, chosenClub, user_name);
        }
    }

    //when a user clicks this it brings them to create a new club
    public void OnClickRegisterClub(View view){
        Intent goToRegisterClub = new Intent(this, CreateClub.class);
        goToRegisterClub.putExtra("username",user_name);
        goToRegisterClub.putExtra("userType", userType);
        startActivity(goToRegisterClub);
    }
    //when a user clicks this it takes the club that was selected an then allows them to create a team inside this club
    public void OnClickRegisterTeam(View view){
        Intent goToRegisterTeam = new Intent(this, CreateTeam.class);
        goToRegisterTeam.putExtra("username",user_name);
        goToRegisterTeam.putExtra("userType", userType);
        goToRegisterTeam.putExtra("clubchosen", clubChosen);
        startActivity(goToRegisterTeam);
    }

    //this can only be clicked once a club and team has been selected, it brings the coach to the team homepage.
    public void onClickGoToTeamHome(View view){
        String type = "joinTeam";
        String teamChoice = teamNames.getSelectedItem().toString();
        BackgroundWorker back = new BackgroundWorker(this);
        back.execute(type,user_name,teamChoice);
    }
}
