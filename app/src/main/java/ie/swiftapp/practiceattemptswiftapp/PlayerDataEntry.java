package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerDataEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String dataChoice, dataValue, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data_entry);

        Spinner spinner = findViewById(R.id.spinner_dataEntry);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dataentry, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        dataValue = String.valueOf(findViewById(R.id.etdataValue));
        date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        dataChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onDataEntered(View view) {
        String str_username = "John";
        String str_datachoice = dataChoice.toString();
        String str_datavalue = dataValue.toString();
        String str_date = date.toString();
        String type = "playerentereddata";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_username, str_datachoice, str_datavalue, str_date);

    }
}
