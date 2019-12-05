package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerficationActivity extends AppCompatActivity {

    Button verify_next ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);

        verify_next = findViewById(R.id.verify_next);

        verify_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerficationActivity.this , PasswordResetActivity.class));
            }
        });
    }
}
