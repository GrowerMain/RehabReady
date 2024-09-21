package com.example.rehabready;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rehabready.R;



public class ProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextEmail;
    private Spinner spinnerGender;
    private Button btnSave, btnEdit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        sharedPreferences = getSharedPreferences("ProfilePrefs", MODE_PRIVATE);

        initView();
        restoreProfile();
    }


    private void initView() {
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnSave = findViewById(R.id.btnSave);
        btnEdit = findViewById(R.id.btnEdit);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);


        btnSave.setOnClickListener(v -> saveProfile());

        btnEdit.setOnClickListener(v -> setFieldsEditable(true));


        View backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    private void restoreProfile() {
        boolean isSaved = sharedPreferences.getBoolean("isSaved", false);
        if (isSaved) {
            String name = sharedPreferences.getString("name", "");
            int age = sharedPreferences.getInt("age", 0);
            String email = sharedPreferences.getString("email", "");
            int genderPosition = sharedPreferences.getInt("genderPosition", 0);

            editTextName.setText(name);
            editTextAge.setText(String.valueOf(age));
            editTextEmail.setText(email);
            spinnerGender.setSelection(genderPosition);
        }
        setFieldsEditable(!isSaved);
    }


    private void saveProfile() {
        String name = editTextName.getText().toString();
        String ageText = editTextAge.getText().toString();
        String email = editTextEmail.getText().toString();
        int genderPosition = spinnerGender.getSelectedItemPosition();

        int age = 0;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid age input", Toast.LENGTH_SHORT).show();
            return;
        }


        sharedPreferences.edit()
                .putBoolean("isSaved", true)
                .putString("name", name)
                .putInt("age", age)
                .putString("email", email)
                .putInt("genderPosition", genderPosition)
                .apply();

        Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();
        setFieldsEditable(false);
        hideKeyboard();
    }


    private void setFieldsEditable(boolean isEditable) {
        editTextName.setEnabled(isEditable);
        editTextAge.setEnabled(isEditable);
        editTextEmail.setEnabled(isEditable);
        spinnerGender.setEnabled(isEditable);
        btnSave.setEnabled(isEditable);
        btnEdit.setVisibility(isEditable ? View.GONE : View.VISIBLE);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
