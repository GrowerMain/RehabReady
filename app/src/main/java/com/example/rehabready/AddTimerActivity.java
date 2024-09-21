package com.example.rehabready;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AddTimerActivity extends AppCompatActivity {

    private TimerDatabaseHelper dbHelper;
    private TextView timerTextView;
    private Button startTimerButton;
    private Button resetTimerButton;
    private EditText textInputEditText;
    private Button duplicateButton;
    private RelativeLayout timerLayout;

    private boolean isTimerRunning = false;
    private long startTime = 0;

    private Handler timerHandler = new Handler();

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            int days = hours / 24;
            seconds = seconds % 60;
            minutes = minutes % 60;
            hours = hours % 24;

            String time = String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
            timerTextView.setText(time);

            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timer);

        dbHelper = new TimerDatabaseHelper(this);

        timerTextView = findViewById(R.id.timerTextView);
        startTimerButton = findViewById(R.id.startTimerButton);
        resetTimerButton = findViewById(R.id.resetTimerButton);
        textInputEditText = findViewById(R.id.textInputEditText);
        duplicateButton = findViewById(R.id.TimerBtnAdd);
        timerLayout = findViewById(R.id.timerLayout);

        // Find the timerBackBtn ImageView
        ImageView timerBackBtn = findViewById(R.id.timerBackBtn);

        // Set OnClickListener for timerBackBtn
        timerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event to navigate back to SummaryActivity
                Intent intent = new Intent(AddTimerActivity.this, SummaryActivity.class);
                startActivity(intent);
                finish(); // Optional: Finish the current activity to prevent going back to it when pressing back
            }
        });

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    timerHandler.removeCallbacks(timerRunnable);
                    isTimerRunning = false;
                    startTimerButton.setText("Start Timer");
                    startTime = System.currentTimeMillis();
                    insertTimerData(startTime, textInputEditText.getText().toString()); // Change this line
                }else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    isTimerRunning = true;
                    startTimerButton.setText("Pause Timer");
                }
            }
        });

        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerHandler.removeCallbacks(timerRunnable);
                isTimerRunning = false;
                startTimerButton.setText("Start Timer");
                timerTextView.setText("00:00:00:00");
            }
        });



        findViewById(R.id.TimerBtnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                String userInput = textInputEditText.getText().toString();

                // Insert timer data into the database
                insertTimerData(startTime, userInput);

                // Pass data back to SummaryActivity
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void insertTimerData(long startTime, String userInput) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TimerDatabaseHelper.COLUMN_START_TIME, startTime);
        values.put(TimerDatabaseHelper.COLUMN_USER_INPUT, userInput);
        db.insert(TimerDatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }
}
