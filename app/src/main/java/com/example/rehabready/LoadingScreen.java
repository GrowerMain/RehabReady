package com.example.rehabready;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LoadingScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        // Delay moving to the activity_intro page
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Move to the activity_intro page
                Intent intent = new Intent(LoadingScreen.this, IntroActivity.class);
                startActivity(intent);
                finish(); // Finish this activity to prevent going back to the sprite animation
            }
        }, SPLASH_DELAY);
    }
}
