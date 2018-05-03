package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateTeam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText teamName;
    String sportChoice;
    String user_name, clubName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team2);
        user_name = getIntent().getStringExtra("username");
        clubName = getIntent().getStringExtra("clubchosen");
        Spinner spinner = findViewById(R.id.spinner_Sport_Team);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        teamName = findViewById(R.id.newTeamName);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        sportChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onMakeTeam(View view) {
        String str_teamName = teamName.getText().toString();
        String str_clubName = clubName;
        String str_sport = sportChoice.toString();
        String str_username = user_name.toString();
        String type = "createteam";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_teamName, str_clubName, str_sport, str_username);


    }
}
