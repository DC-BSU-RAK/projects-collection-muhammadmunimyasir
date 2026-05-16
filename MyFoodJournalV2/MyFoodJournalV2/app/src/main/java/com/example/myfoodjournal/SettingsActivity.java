package com.example.myfoodjournal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("FoodJournalPrefs", MODE_PRIVATE);

        EditText etName    = findViewById(R.id.et_name);
        RadioGroup rgDiet  = findViewById(R.id.rg_diet);
        Switch swNotif     = findViewById(R.id.sw_notif);
        Button btnSave     = findViewById(R.id.btn_save);
        Button btnBack     = findViewById(R.id.btn_back);

        // Load saved values into the form
        etName.setText(prefs.getString("user_name", ""));
        swNotif.setChecked(prefs.getBoolean("notifications", true));

        String diet = prefs.getString("diet", "none");
        if      (diet.equals("halal"))       rgDiet.check(R.id.rb_halal);
        else if (diet.equals("vegetarian"))  rgDiet.check(R.id.rb_vegetarian);
        else if (diet.equals("vegan"))       rgDiet.check(R.id.rb_vegan);
        else                                 rgDiet.check(R.id.rb_none);

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            // Save name
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) name = "Foodie";

            // Save diet
            String dietChoice;
            int checked = rgDiet.getCheckedRadioButtonId();
            if      (checked == R.id.rb_halal)       dietChoice = "halal";
            else if (checked == R.id.rb_vegetarian)  dietChoice = "vegetarian";
            else if (checked == R.id.rb_vegan)       dietChoice = "vegan";
            else                                     dietChoice = "none";

            // Write to SharedPreferences
            prefs.edit()
                .putString("user_name", name)
                .putString("diet", dietChoice)
                .putBoolean("notifications", swNotif.isChecked())
                .apply();

            Toast.makeText(this, "✅ Settings saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
