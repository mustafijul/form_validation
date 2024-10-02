package com.example.form_validation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Regex patterns
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,2}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    private EditText etName, etEmail, etPassword, etPhone;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Set button click listener for form validation
        btnSubmit.setOnClickListener(view -> {
            if (validateForm()) {
                Toast.makeText(MainActivity.this, "Form Submitted Successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Form validation method
    private boolean validateForm() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (!validateName(name)) {
            etName.setError("Invalid name");
            return false;
        }

        if (!validateEmail(email)) {
            etEmail.setError("Invalid email");
            return false;
        }

        if (!validatePassword(password)) {
            etPassword.setError("Password must be at least 8 characters with uppercase, lowercase, digit, and special character");
            return false;
        }

        if (!validatePhone(phone)) {
            etPhone.setError("Invalid phone number");
            return false;
        }

        return true;
    }

    // Validation methods
    private boolean validateName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    private boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean validatePhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
