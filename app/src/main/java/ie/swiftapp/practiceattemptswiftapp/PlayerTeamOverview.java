package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PlayerTeamOverview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String dataChoiceResult, teamChoice, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_team_overview);
        username = getIntent().getStringExtra("username");
        teamChoice = getIntent().getStringExtra("teamChoice");
        TextView teamName = (TextView) findViewById(R.id.txtTeamName);
        teamName.setText(teamChoice);

        Spinner spinner = findViewById(R.id.spinner_dataEntryResults);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dataentry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onRecordTime(View view) {
        Intent p = new Intent(PlayerTeamOverview.this, Stopwatch.class);
        p.putExtra("username", username);
        PlayerTeamOverview.this.startActivity(p);
    }

    public void onEnterData(View view) {
        Intent q = new Intent(PlayerTeamOverview.this, PlayerDataEntry.class);
        q.putExtra("username", username);
        PlayerTeamOverview.this.startActivity(q);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        dataChoiceResult = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
