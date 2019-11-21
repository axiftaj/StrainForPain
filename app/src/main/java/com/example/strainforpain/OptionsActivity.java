package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {


    private Button login_optopn , start_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        login_optopn = findViewById(R.id.login_option);
        start_btn = findViewById(R.id.start_id);


        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String uName = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

//        if (!uName.isEmpty() && !password.isEmpty()){
//            startActivity(new Intent(OptionsActivity.this , HomeActivity.class));
//        }


        login_optopn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this , LoginActivity.class));
            }
        });


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this , RegisterationActivity.class));
            }
        });
    }
}
