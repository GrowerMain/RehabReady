package com.example.rehabready;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CalendarView;
import androidx.cardview.widget.CardView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTrendList;
    private RecyclerView recyclerViewTrends;
    private CalendarView calendarView;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            // Find the NavController and NavHostActivity

            BottomNavigationView bottomNav = findViewById(R.id.nav_view);
            bottomNav.setOnNavigationItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    finish(); // Close this activity
                    return true;
                } else if (itemId == R.id.navigation_journal) {
                    startActivity(new Intent(MainActivity.this, calendar.class));
                    finish(); // Close this activity
                    return true;
                } else if (itemId == R.id.navigation_med) {
                    startActivity(new Intent(MainActivity.this, activity_medication.class));
                    finish(); // Close this activity
                    return true;
                }

                return false;
            });

        // Assuming your CalendarView has the id 'calendarView'
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Set the OnDateChangeListener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                // Create an Intent to navigate to the activity_calendar page
                Intent intent = new Intent(MainActivity.this, calendar.class);

                // Pass any necessary data to the activity_calendar if needed
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("dayOfMonth", dayOfMonth);

                // Start the activity_calendar activity
                startActivity(intent);
            }
        });


        // Set click listeners for CardViews
            CardView profileCard = findViewById(R.id.profileCard);
            CardView timerCard = findViewById(R.id.timerCard);
            CardView journalCard = findViewById(R.id.journalCard);

            profileCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));

            timerCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SummaryActivity.class)));

            journalCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, JournalEntryActivity.class)));

    }

    // ProfileActivity


    @Override
    protected void onResume() {
        super.onResume();
        updateGreeting(); // Ensure greeting is updated if the user's name was changed
    }



    private void initCalendarView() {
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Handle the date change
            }
        });
    }

    private void updateGreeting() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("name", "User"); // Default to "User" if name not found
        TextView greetingTextView = findViewById(R.id.textView3);
        String greetingText = "Hi, " + userName;
        greetingTextView.setText(greetingText);
    }




}
