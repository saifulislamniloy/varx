package com.niloy.varx;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TalkActivity extends AppCompatActivity {
    private ImageButton mic;
    private boolean isMicOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        hideActionBar();

        mic = findViewById(R.id.mic);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isMicOn){
                    mic.setImageResource(R.drawable.mic_on);
                    mic.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    isMicOn = true;
                }else {
                    mic.setImageResource(R.drawable.mic_off);
                    mic.setBackgroundColor(getResources().getColor(R.color.lightGray));
                    isMicOn = false;
                }
            }
        });
    }


    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}