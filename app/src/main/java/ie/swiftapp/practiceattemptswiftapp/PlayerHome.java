package ie.swiftapp.practiceattemptswiftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayerHome extends AppCompatActivity {

    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);
        user_name = getIntent().getStringExtra("username");
    }

    public void onJoinTeam(View view) {
        Intent r = new Intent(PlayerHome.this, PlayerClubJoin.class);
        r.putExtra("username", user_name);
        PlayerHome.this.startActivity(r);
    }
}