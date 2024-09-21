package com.example.rehabready;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Switch to the activity_intro layout after the splash delay
                setContentView(R.layout.activity_intro);
                setupStartButton();
            }
        }, SPLASH_DELAY);
    }

    private void setupStartButton() {
        Button startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity when the button is clicked
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish(); // Close this activity
            }
        });
    }
}
