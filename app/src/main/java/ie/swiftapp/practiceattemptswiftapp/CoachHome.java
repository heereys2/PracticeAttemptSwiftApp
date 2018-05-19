package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CoachHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String dataChoiceResult, teamChoice, username;
    String playerList[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_home2);

        username = getIntent().getStringExtra("username");
        playerList = getIntent().getStringArrayExtra("playerList");
        teamChoice = getIntent().getStringExtra("teamChoice");
        TextView teamName = findViewById(R.id.txtTeamName);
        teamName.setText(teamChoice);
        Spinner spinner = findViewById(R.id.spinner_dataEntryResults);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dataentry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.spinner_selectPlayer);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerList);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    public void onRecordTime(View view) {
        Intent p = new Intent(CoachHome.this, CoachStopwatch.class);
        p.putExtra("playerNames", playerList);
        CoachHome.this.startActivity(p);
    }

    public void onEnterData(View view) {
        Intent q = new Intent(CoachHome.this, PlayerDataEntry.class);
        CoachHome.this.startActivity(q);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        dataChoiceResult = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

