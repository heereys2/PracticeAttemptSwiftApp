package ie.swiftapp.practiceattemptswiftapp;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Toast;


public class Register extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.et_email);
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        radioGroup = findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
    }

    public void OnReg(View view) {
        String str_email = email.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String str_userType = radioButton.getText().toString();
        String type = "register";

        if ((str_email == null || str_username == null || str_password == null || str_userType == null)){
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
        }
        else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_email, str_username, str_password, str_userType);
        }
    }
}
