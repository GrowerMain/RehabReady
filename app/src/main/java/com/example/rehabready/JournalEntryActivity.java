package com.example.rehabready;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class JournalEntryActivity extends AppCompatActivity {

    private EditText headingEditText;
    private EditText contentEditText;
    private Button addButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> entriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        // Find the NavController and NavHostActivity

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(JournalEntryActivity.this, MainActivity.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_journal) {
                startActivity(new Intent(JournalEntryActivity.this, calendar.class));
                finish(); // Close this activity
                return true;
            } else if (itemId == R.id.navigation_med) {
                startActivity(new Intent(JournalEntryActivity.this, activity_medication.class));
                finish(); // Close this activity
                return true;
            }

            return false;
        });

        // Initialize views
        headingEditText = findViewById(R.id.editTextTextPersonName);
        contentEditText = findViewById(R.id.contentfield);
        addButton = findViewById(R.id.savebutton);
        listView = findViewById(R.id.listView);

        // Initialize list to hold entries
        entriesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entriesList);
        listView.setAdapter(adapter);

        // Set OnClickListener for the addButton
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditText fields
                String heading = headingEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();

                // Check if both fields are not empty
                if (!heading.isEmpty() && !content.isEmpty()) {
                    // Concatenate heading and content and add to list
                    String entry = heading + ": " + content;
                    entriesList.add(entry);

                    // Notify adapter that data set has changed
                    adapter.notifyDataSetChanged();

                    // Clear EditText fields
                    headingEditText.getText().clear();
                    contentEditText.getText().clear();
                }
            }
        });
    }
}
