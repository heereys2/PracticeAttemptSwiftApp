package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoachHome extends AppCompatActivity {

    public String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_home);
        user_name = getIntent().getStringExtra("username");
    }

    public void onCreateTeam(View view) {
        Intent p = new Intent(CoachHome.this, CreateTeam.class);
        p.putExtra("username",user_name);
        CoachHome.this.startActivity(p);
    }
}
