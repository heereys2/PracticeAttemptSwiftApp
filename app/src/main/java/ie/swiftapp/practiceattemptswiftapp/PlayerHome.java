package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PlayerHome extends AppCompatActivity {

    public String user_name, teamChoice;
    public String userTeamArray[];
    public Spinner userTeamSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);

        user_name = getIntent().getStringExtra("username");
        userTeamArray = getIntent().getStringArrayExtra("clubsArray");
        userTeamSpinner = findViewById(R.id.spinPlayerTeamList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, userTeamArray);
        userTeamSpinner.setAdapter(adapter);
    }

    public void onConfirmTeam(View view){
        teamChoice = userTeamSpinner.getSelectedItem().toString();
        Intent s = new Intent(PlayerHome.this, PlayerTeamOverview.class);
        s.putExtra("username", user_name);
        s.putExtra("teamChoice", teamChoice);
        PlayerHome.this.startActivity(s);
    }

    public void onJoinTeam(View view) {
            String type = "allclubsforplayers";
            BackgroundWorker back = new BackgroundWorker(this);
            back.execute(type,user_name);
    }
}