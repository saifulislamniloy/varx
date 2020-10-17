package com.niloy.varx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private CardView botController;
    private CardView talk;
    private CardView liveView;
    private CardView others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        botController = findViewById(R.id.control_robot);
        botController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BotControllerActivity.class);
                startActivity(intent);
            }
        });

        talk = findViewById(R.id.talk_bot);
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TalkActivity.class);
                startActivity(intent);
            }
        });

        liveView = findViewById(R.id.live_view);
        liveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LiveViewActivity.class);
                startActivity(intent);
            }
        });

        others = findViewById(R.id.others);
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming soon :)", Toast.LENGTH_LONG).show();
            }
        });
    }
}