package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoachHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_home);
    }

    public void onCreateTeam(View view) {
        Intent p = new Intent(CoachHome.this, CreateTeam.class);
        CoachHome.this.startActivity(p);
    }
}
