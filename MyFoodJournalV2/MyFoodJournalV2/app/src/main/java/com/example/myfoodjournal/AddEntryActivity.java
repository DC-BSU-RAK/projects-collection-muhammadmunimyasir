package com.example.myfoodjournal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        EditText etMeal   = findViewById(R.id.et_meal);
        RadioGroup rgType = findViewById(R.id.rg_type);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        EditText etNotes  = findViewById(R.id.et_notes);
        Button btnSave    = findViewById(R.id.btn_save);
        Button btnBack    = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String meal = etMeal.getText().toString().trim();
            if (meal.isEmpty()) {
                Toast.makeText(this, "Please enter a meal name!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get meal type
            String type;
            int checked = rgType.getCheckedRadioButtonId();
            if      (checked == R.id.rb_breakfast) type = "🌅 Breakfast";
            else if (checked == R.id.rb_lunch)     type = "☀️ Lunch";
            else if (checked == R.id.rb_dinner)    type = "🌙 Dinner";
            else if (checked == R.id.rb_snack)     type = "🍿 Snack";
            else {
                Toast.makeText(this, "Please select a meal type!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Build star string from rating
            int rating = (int) ratingBar.getRating();
            StringBuilder stars = new StringBuilder();
            for (int i = 1; i <= 5; i++) stars.append(i <= rating ? "★" : "☆");

            String notes = etNotes.getText().toString().trim();
            String date  = new SimpleDateFormat("MMM d, HH:mm", Locale.getDefault()).format(new Date());

            // Add to the shared list (newest first)
            MainActivity.entries.add(0, new String[]{meal, type, stars.toString(), notes, date});

            Toast.makeText(this, "Meal logged! 🎉", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
