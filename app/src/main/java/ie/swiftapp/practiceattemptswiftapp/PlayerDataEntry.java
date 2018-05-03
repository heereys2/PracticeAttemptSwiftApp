package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerDataEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String dataChoice, dataValue, date, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data_entry);

        username = getIntent().getStringExtra("username");
        Spinner spinner = findViewById(R.id.spinner_dataEntry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dataentry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        date = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        dataChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onDataEntered(View view) {
        dataValue = ((EditText) findViewById(R.id.etdataValue)).getText().toString();
        String str_username = username;
        String str_datachoice = dataChoice.toString();
        String str_datavalue = dataValue.toString();
        String str_date = date.toString();
        String type = "playerentereddata";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_username, str_datachoice, str_datavalue, str_date);

    }
}
