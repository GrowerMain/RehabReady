package com.example.rehabready;

import android.content.Intent;
import android.os.Bundle;

import com.example.rehabready.databinding.ActivityCalendarBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;

public class calendar extends AppCompatActivity {

    private ActivityCalendarBinding binding;

    private CalendarView calendarView;
    private Button btnAddAchievement;
    private TextView tvAchievement;
    private EditText etAchievement;

    private int currentYear = 0, currentMonth = 0, currentDate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(calendar.this, MainActivity.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_journal) {
                startActivity(new Intent(calendar.this, calendar.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_med) {
                startActivity(new Intent(calendar.this, activity_medication.class));
                finish(); // Close this activity
                return true;
            }

            return false;
        });

        calendarView = findViewById(R.id.calendarView);
        btnAddAchievement = findViewById(R.id.btnAddAchievement);
        tvAchievement = findViewById(R.id.tvAchievement);

        etAchievement = findViewById(R.id.etAchievement);

        // Set a date change listener for the CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                currentDate = dayOfMonth;
                currentYear = year;
                currentMonth = month;
                // Retrieve achievements for the selected date and update TextView
                updateAchievementTextView(year, month, dayOfMonth);
            }
        });

        // Set a click listener for the "Add Achievement" button
        btnAddAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the logic to add achievements
                if (!etAchievement.getText().toString().isEmpty()) {
                    addAchievement();
                    etAchievement.setText("");
                }
            }
        });
    }

    private void updateAchievementTextView(int year, int month, int dayOfMonth) {
        // Use SharedPreferences to retrieve achievements for the selected date
        // The key can be constructed based on the date (e.g., "achievement_2022_12_1")
        String key = "achievement_" + year + "_" + (month + 1) + "_" + dayOfMonth;
        String achievement = getSharedPreferences("achievements", MODE_PRIVATE)
                .getString(key, "No achievement for this date");

        tvAchievement.setText(achievement);
    }

    private void addAchievement() {
        // Handle the logic to add achievements
        // This can involve showing a dialog, taking input, and storing in SharedPreferences

        // For example, storing a sample achievement for the selected date
        long selectedDateInMillis = calendarView.getDate();

        // Convert the selected date in milliseconds to a Calendar instance
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(selectedDateInMillis);


        String key = "achievement_" + currentYear + "_" + (currentMonth + 1) + "_" + currentDate;
        String newAchievement = etAchievement.getText().toString();

        // Store the achievement in SharedPreferences
        getSharedPreferences("achievements", MODE_PRIVATE)
                .edit()
                .putString(key, newAchievement)
                .apply();

        // Update TextView
        updateAchievementTextView(currentYear, currentMonth, currentDate);
    }

}