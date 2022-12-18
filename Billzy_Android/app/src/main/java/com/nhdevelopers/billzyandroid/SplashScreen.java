package com.nhdevelopers.billzyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nhdevelopers.billzyandroid.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Runnable r = new Runnable() {

            @Override
            public void run() {
                // if you are redirecting from a fragment
                // then use getActivity() as the context.
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        };

        Handler h = new Handler();
// The Runnable will be executed after the given delay time
        h.postDelayed(r, 1500); // will be delayed for 1.5 seconds

    }
}