package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.strainforpain.Utills.GeneralUtills;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                boolean isLogin = GeneralUtills.getSharedPreferences(MainActivity.this).getBoolean("isLogin" , false);

                if (isLogin){
                    startActivity(new Intent(MainActivity.this , HomeActivity.class));

                }else {
                    startActivity(new Intent(MainActivity.this , OptionsActivity.class));

                }

                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
