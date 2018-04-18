package ie.swiftapp.practiceattemptswiftapp;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;


public class Register extends AppCompatActivity {
    EditText firstname, surname, email, password, username, gender, country, age, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = (EditText)findViewById(R.id.et_fname);
        surname= (EditText)findViewById(R.id.et_sname);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        username = (EditText)findViewById(R.id.et_username);
        gender = (EditText)findViewById(R.id.et_gender);
        country = (EditText)findViewById(R.id.et_country);
        age = (EditText)findViewById(R.id.et_age);
        type = (EditText)findViewById(R.id.et_type);
    }

    public void OnReg(View view) {
        String str_fname = firstname.getText().toString();
        String str_sname = surname.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_username = username.getText().toString();
        String str_gender = gender.getText().toString();
        String str_country = country.getText().toString();
        String str_age = age.getText().toString();
        String str_type = type.getText().toString();
        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_fname, str_sname, str_email, str_password, str_username, str_gender, str_country, str_age, str_type);
    }
}
