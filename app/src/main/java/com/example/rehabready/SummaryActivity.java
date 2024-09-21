package com.example.rehabready;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class SummaryActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_TIMER = 1001; // Request code for starting AddTimerActivity

    private LinearLayout timersContainer;
    private Button addTimerButton;
    private TimerDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_timer);

        // Find the NavController and NavHostActivity

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(SummaryActivity.this, MainActivity.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_journal) {
                startActivity(new Intent(SummaryActivity.this, calendar.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_med) {
                startActivity(new Intent(SummaryActivity.this, activity_medication.class));
                finish(); // Close this activity
                return true;
            }

            return false;
        });


        timersContainer = findViewById(R.id.timersContainer);
        addTimerButton = findViewById(R.id.addTimerButton);
        dbHelper = new TimerDatabaseHelper(this);

        addTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddTimerActivity to add a new timer
                Intent intent = new Intent(SummaryActivity.this, AddTimerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TIMER);
            }
        });

        // Load saved timer data
        loadTimerData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TIMER && resultCode == RESULT_OK && data != null) {
            // Get start time and user input from the result intent
            long startTime = data.getLongExtra("startTime", 0);
            String userInput = data.getStringExtra("userInput");

            // Add new timer entry to the UI
            addNewTimerToUI(startTime, userInput);
        }
    }

    private void addNewTimerToUI(long startTime, String userInput) {



        // Display all timers from the database
        displayAllTimers();
    }

    private void displayAllTimers() {
        // Clear the timers container before adding new timers
        timersContainer.removeAllViews();

        // Get all timers from the database
        List<Timer> timers = dbHelper.getAllTimers();


        // Add each timer to the UI
        for (Timer timer : timers) {
            View timerEntryView = getLayoutInflater().inflate(R.layout.timer_entry_layout, null);
            // Find views in the timer entry layout
            TextView timerTextView = timerEntryView.findViewById(R.id.timerTextView);
            TextView userInputTextView = timerEntryView.findViewById(R.id.userInputTextView);
            Button resetTimerButton = timerEntryView.findViewById(R.id.resetTimerButton);
            Button deleteTimerButton = timerEntryView.findViewById(R.id.deleteTimerButton);

            // Set user input
            userInputTextView.setText(timer.getUserInput());

            // Start timer counter
            Handler handler = new Handler();
            Runnable timerRunnable = new Runnable() {
                long initialTime = timer.getStartTime();

                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - initialTime;

                    // Convert elapsed time to format HH:MM:SS
                    int seconds = (int) (elapsedTime / 1000) % 60;
                    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                    int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);

                    // Update timer display
                    timerTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                    // Schedule the next update in 1 second
                    handler.postDelayed(this, 1000);
                }
            };


    // Start the timer counter
            handler.post(timerRunnable);

            resetTimerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset the timer display
                    timerTextView.setText("00:00:00");

                    // Reset the start time to current time
                    timer.setStartTime(System.currentTimeMillis());

                    // Update the timer data in the database
                    dbHelper.updateTimer(timer);

                    // Reload timer data to update the affected timer view
                    loadTimerData();
                }
            });

            deleteTimerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Delete the timer from the database
                    dbHelper.deleteTimer(timer.getId());
                    // Remove the timer entry from the UI
                    timersContainer.removeView(timerEntryView);
                }
            });

            // Add the timer entry to the timers container
            timersContainer.addView(timerEntryView);
        }
    }

    private void loadTimerData() {
        // Display all timers from the database
        displayAllTimers();
    }




}
