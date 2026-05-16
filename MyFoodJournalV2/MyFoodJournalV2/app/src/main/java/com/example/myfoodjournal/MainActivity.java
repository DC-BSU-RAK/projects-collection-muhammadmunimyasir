package com.example.myfoodjournal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Static list to store all journal entries during the session
    public static ArrayList<String[]> entries = new ArrayList<>();

    private LinearLayout llEntries;
    private TextView tvEmpty;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llEntries = findViewById(R.id.ll_entries);
        tvEmpty   = findViewById(R.id.tv_empty);
        tvWelcome = findViewById(R.id.tv_welcome);

        // Button to add a new meal entry
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v ->
            startActivity(new Intent(this, AddEntryActivity.class))
        );

        // Button to open settings
        Button btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(v ->
            startActivity(new Intent(this, SettingsActivity.class))
        );

        // Button to show info modal
        Button btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(v -> showInfoDialog());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWelcomeName();
        refreshEntries();
    }

    // Load the saved name from SharedPreferences and show it
    private void loadWelcomeName() {
        SharedPreferences prefs = getSharedPreferences("FoodJournalPrefs", MODE_PRIVATE);
        String name = prefs.getString("user_name", "Foodie");
        tvWelcome.setText("Welcome, " + name + "! 👋");
    }

    // Rebuild the list of entries on screen
    private void refreshEntries() {
        llEntries.removeAllViews();

        if (entries.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            for (String[] entry : entries) {
                // entry[0] = meal name, entry[1] = meal type, entry[2] = rating, entry[3] = notes, entry[4] = date
                View card = getLayoutInflater().inflate(R.layout.item_entry, llEntries, false);
                ((TextView) card.findViewById(R.id.tv_meal)).setText(entry[0]);
                ((TextView) card.findViewById(R.id.tv_type)).setText(entry[1] + "  •  " + entry[4]);
                ((TextView) card.findViewById(R.id.tv_rating)).setText(entry[2]);
                ((TextView) card.findViewById(R.id.tv_notes)).setText(entry[3]);
                llEntries.addView(card);
            }
        }
    }

    // Show the info modal dialog
    private void showInfoDialog() {
        new AlertDialog.Builder(this)
            .setTitle("📔 About My Food Journal")
            .setMessage(
                "My Food Journal helps you track every meal you eat!\n\n" +
                "➕ Tap 'Add Meal' to log what you ate\n\n" +
                "⚙️ Tap 'Settings' to save your name and food preferences\n\n" +
                "Your preferences are saved on your device even after closing the app.\n\n" +
                "Happy eating! 🍽️"
            )
            .setPositiveButton("Got it!", null)
            .show();
    }
}
