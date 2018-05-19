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

        //This passes the username of the person logged in to the class. This is needed to link the data entered with the right user in the database
        username = getIntent().getStringExtra("username");
        //This is the creation of the dropdown menue. The values are taken from an array in the strings.xml file.
        Spinner spinner = findViewById(R.id.spinner_dataEntry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dataentry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //This stores the current data in the form DD/MM/YYYY in a string. This is entered to the database to show when an activity was done, for progression purposes
        date = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
    }

    //This updates the dropdown menu value when it is changed
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        dataChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //This is executed when the Submit button is pressed
    public void onDataEntered(View view) {
        //The username, data type, data value and date are stored in strings. The background worker execution type is also stored
        dataValue = ((EditText) findViewById(R.id.etdataValue)).getText().toString();
        String str_username = username;
        String str_datachoice = dataChoice.toString();
        String str_datavalue = dataValue.toString();
        String str_date = date.toString();
        String type = "playerentereddata";

        //The aboove variables are passed to the background worker which communicates with the database
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_username, str_datachoice, str_datavalue, str_date);

    }
}
