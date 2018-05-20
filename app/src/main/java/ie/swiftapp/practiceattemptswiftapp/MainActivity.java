package ie.swiftapp.practiceattemptswiftapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Read the EditText fields containing the username and paasword.
        UsernameEt = findViewById(R.id.etUsername);
        PasswordEt = findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        //When login clicked, get the value of the 2 fields and store in String
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";

        //Exectute the backgroundoworker to connect to database, sending username, password and execution type as parameters
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }
    //When register is clicked, open register activity
    public void OpenReg(View view) {
        startActivity(new Intent(this,Register.class));
    }
}
