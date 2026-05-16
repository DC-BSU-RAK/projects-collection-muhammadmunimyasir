package com.example.flavormood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashActivity - This is the launch screen shown when the app first opens.
 * It displays for 2 seconds and then moves to MainActivity.
 */
public class SplashActivity extends AppCompatActivity {

    // How long to show the splash screen (in milliseconds)
    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Wait for SPLASH_DURATION milliseconds, then open MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the main screen
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the splash so user can't go back to it
            }
        }, SPLASH_DURATION);
    }
}
