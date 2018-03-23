package ie.swiftapp.practiceattemptswiftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button btn = (Button) findViewById(R.id.loginButton);

    @Override
    public void onClick(View view) {
        
    }
}
//    btn.setOnClickListener(new View.OnClickListener() {
//        public void onClick(View v) {
//            loginButtonOnClick(v);
//        }
//    });
//
//    public void loginButtonOnClick(View v){
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(invent);
//    }
//}
