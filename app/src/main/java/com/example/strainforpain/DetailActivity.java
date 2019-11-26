package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView back_arrow ;
    private TextView titleName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        back_arrow = findViewById(R.id.back_arrow);
        titleName = findViewById(R.id.name);




        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("titile");

        titleName.setText(id);


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, HomeActivity.class));
            }
        });
    }

}
