package com.example.flavormood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 * MainActivity - The single-view "Flavor Mood Calculator"
 *
 * The user picks:
 *   1. Their current MOOD (Happy / Tired / Stressed / Adventurous)
 *   2. Time of DAY  (Morning / Afternoon / Evening / Late Night)
 *
 * The app then "calculates" a creative food recommendation based on
 * the combination — no numbers involved, just food + vibes!
 *
 * There is also an INFO button that opens a modal (InfoDialogFragment)
 * explaining how the app works.
 */
public class MainActivity extends AppCompatActivity {

    // ---- UI references ----
    private TextView tvResult;          // Shows the food recommendation
    private TextView tvResultEmoji;     // Shows an emoji for the result
    private TextView tvResultDesc;      // Shows a fun description
    private CardView cardResult;        // The result card (hidden until calculated)

    // Track which mood and time the user has selected
    private String selectedMood = null;
    private String selectedTime = null;

    // Mood buttons
    private Button btnHappy, btnTired, btnStressed, btnAdventurous;

    // Time buttons
    private Button btnMorning, btnAfternoon, btnEvening, btnLateNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Link all views ---
        tvResult       = findViewById(R.id.tv_result);
        tvResultEmoji  = findViewById(R.id.tv_result_emoji);
        tvResultDesc   = findViewById(R.id.tv_result_desc);
        cardResult     = findViewById(R.id.card_result);

        btnHappy       = findViewById(R.id.btn_happy);
        btnTired       = findViewById(R.id.btn_tired);
        btnStressed    = findViewById(R.id.btn_stressed);
        btnAdventurous = findViewById(R.id.btn_adventurous);

        btnMorning     = findViewById(R.id.btn_morning);
        btnAfternoon   = findViewById(R.id.btn_afternoon);
        btnEvening     = findViewById(R.id.btn_evening);
        btnLateNight   = findViewById(R.id.btn_late_night);

        Button btnCalculate = findViewById(R.id.btn_calculate);
        Button btnInfo      = findViewById(R.id.btn_info);

        // --- Hide result card until user calculates ---
        cardResult.setVisibility(View.GONE);

        // --- Mood button click listeners ---
        btnHappy.setOnClickListener(v -> selectMood("Happy", btnHappy));
        btnTired.setOnClickListener(v -> selectMood("Tired", btnTired));
        btnStressed.setOnClickListener(v -> selectMood("Stressed", btnStressed));
        btnAdventurous.setOnClickListener(v -> selectMood("Adventurous", btnAdventurous));

        // --- Time button click listeners ---
        btnMorning.setOnClickListener(v -> selectTime("Morning", btnMorning));
        btnAfternoon.setOnClickListener(v -> selectTime("Afternoon", btnAfternoon));
        btnEvening.setOnClickListener(v -> selectTime("Evening", btnEvening));
        btnLateNight.setOnClickListener(v -> selectTime("LateNight", btnLateNight));

        // --- Calculate button: show the result ---
        btnCalculate.setOnClickListener(v -> calculateFlavor());

        // --- Info button: open the modal info dialog ---
        btnInfo.setOnClickListener(v -> {
            InfoDialogFragment dialog = new InfoDialogFragment();
            dialog.show(getSupportFragmentManager(), "InfoDialog");
        });
    }

    /**
     * Handles selecting a mood.
     * Resets all mood buttons to unselected style, then highlights the chosen one.
     */
    private void selectMood(String mood, Button clickedButton) {
        selectedMood = mood;
        // Reset all mood buttons
        resetButtonStyle(btnHappy);
        resetButtonStyle(btnTired);
        resetButtonStyle(btnStressed);
        resetButtonStyle(btnAdventurous);
        // Highlight the selected one
        clickedButton.setBackgroundResource(R.drawable.btn_selected);
        clickedButton.setTextColor(getColor(R.color.white));
    }

    /**
     * Handles selecting a time of day.
     * Resets all time buttons to unselected style, then highlights the chosen one.
     */
    private void selectTime(String time, Button clickedButton) {
        selectedTime = time;
        // Reset all time buttons
        resetButtonStyle(btnMorning);
        resetButtonStyle(btnAfternoon);
        resetButtonStyle(btnEvening);
        resetButtonStyle(btnLateNight);
        // Highlight the selected one
        clickedButton.setBackgroundResource(R.drawable.btn_selected);
        clickedButton.setTextColor(getColor(R.color.white));
    }

    /** Resets a button back to the default unselected style */
    private void resetButtonStyle(Button button) {
        button.setBackgroundResource(R.drawable.btn_unselected);
        button.setTextColor(getColor(R.color.primaryDark));
    }

    /**
     * The core "calculation" logic.
     * Maps every combination of Mood + Time → a creative food recommendation.
     * This is the heart of the app — it's a non-numerical calculator.
     */
    private void calculateFlavor() {
        // Make sure user picked both a mood and a time
        if (selectedMood == null || selectedTime == null) {
            tvResult.setText("Please select both a mood and a time of day!");
            tvResultEmoji.setText("🤔");
            tvResultDesc.setText("");
            cardResult.setVisibility(View.VISIBLE);
            return;
        }

        String food, emoji, desc;

        // Build a combined key like "Happy_Morning"
        String key = selectedMood + "_" + selectedTime;

        // Switch over all 16 combinations (4 moods × 4 times)
        switch (key) {

            // ---- HAPPY ----
            case "Happy_Morning":
                food  = "Açaí Bowl";
                emoji = "🫐";
                desc  = "You're glowing! Start the day with purple superfoods and fresh fruit that match your sunshine energy.";
                break;
            case "Happy_Afternoon":
                food  = "Street Tacos";
                emoji = "🌮";
                desc  = "Your happy afternoon deserves bold flavours. Grab some tacos and celebrate doing absolutely nothing wrong.";
                break;
            case "Happy_Evening":
                food  = "Sushi Platter";
                emoji = "🍱";
                desc  = "A joyful evening calls for an elegant spread. Share a sushi platter and soak in the good vibes.";
                break;
            case "Happy_LateNight":
                food  = "Loaded Fries";
                emoji = "🍟";
                desc  = "Happy + Late Night = ultimate comfort reward. Cheese, sauce, the works — you deserve it all.";
                break;

            // ---- TIRED ----
            case "Tired_Morning":
                food  = "Avocado Toast & Coffee";
                emoji = "☕";
                desc  = "Tired but trying. This combo gives you slow-release energy and the caffeine kick to face the world.";
                break;
            case "Tired_Afternoon":
                food  = "Warm Lentil Soup";
                emoji = "🍲";
                desc  = "The afternoon slump hits hard. A warm bowl of lentil soup is like a hug for your tired soul.";
                break;
            case "Tired_Evening":
                food  = "Butter Chicken & Rice";
                emoji = "🍛";
                desc  = "A tired evening calls for soft, cosy comfort. Creamy butter chicken will wrap you like a blanket.";
                break;
            case "Tired_LateNight":
                food  = "Instant Ramen";
                emoji = "🍜";
                desc  = "Classic tired late-night energy. A steaming bowl of ramen is low effort, maximum comfort. Sleep soon after.";
                break;

            // ---- STRESSED ----
            case "Stressed_Morning":
                food  = "Overnight Oats";
                emoji = "🌾";
                desc  = "You need calm, not chaos. Overnight oats are already made — one less decision for your overwhelmed brain.";
                break;
            case "Stressed_Afternoon":
                food  = "Dark Chocolate & Almonds";
                emoji = "🍫";
                desc  = "Science says chocolate reduces stress. So does crunch. This combo is basically therapy in snack form.";
                break;
            case "Stressed_Evening":
                food  = "Homemade Pasta";
                emoji = "🍝";
                desc  = "Make the pasta yourself — the rhythm of stirring and boiling is meditative. Stress goes in, carbs come out.";
                break;
            case "Stressed_LateNight":
                food  = "Chamomile Tea & Toast";
                emoji = "🍵";
                desc  = "Put the phone down. Breathe. Chamomile tea and plain toast is your body telling your brain to chill.";
                break;

            // ---- ADVENTUROUS ----
            case "Adventurous_Morning":
                food  = "Shakshuka";
                emoji = "🍳";
                desc  = "An adventurous morning needs a bold breakfast. Spiced tomato eggs from North Africa — unexpected and glorious.";
                break;
            case "Adventurous_Afternoon":
                food  = "Korean Bibimbap";
                emoji = "🥗";
                desc  = "Mix it all together and try something new. Bibimbap rewards the brave — every bite is different.";
                break;
            case "Adventurous_Evening":
                food  = "Ethiopian Injera Platter";
                emoji = "🫓";
                desc  = "Dinner as an experience. Eat with your hands, share the platter, and let the spices take you somewhere new.";
                break;
            case "Adventurous_LateNight":
                food  = "Spicy Mango Chaat";
                emoji = "🥭";
                desc  = "Chaos at midnight. Sweet, sour, spicy — a late-night chaat is peak adventurous energy. Live a little!";
                break;

            // Fallback (shouldn't reach here)
            default:
                food  = "Mystery Dish";
                emoji = "❓";
                desc  = "We couldn't figure out your flavour. Try again!";
                break;
        }

        // Show the result card
        tvResult.setText(food);
        tvResultEmoji.setText(emoji);
        tvResultDesc.setText(desc);
        cardResult.setVisibility(View.VISIBLE);
    }
}
