package vistula.ar.vocationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            //this code will pause the app for 1.5 secs and then any thing in run method will run.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);

                    if (isLoggedIn){
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    } else {
                        isFirstTime();
                    }
                }
            }, 1500);

        }

        private void isFirstTime() {
            //for checking if the app is running for the very first time
            //we need to save a value to shared preferences
            SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
            boolean isFirstTIme = preferences.getBoolean("isFirstTime", true);
            //default value true
            if (isFirstTIme){
                //if its true then its first time and we will change it false
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirstTime", false);
                editor.apply();

                //start onboard activity
                startActivity(new Intent(SplashScreen.this, OnBoardActivity.class));
                finish();
            } else {
                //start auth activity
                startActivity(new Intent(SplashScreen.this, AuthActivity.class));
                finish();
            }

        }

    }