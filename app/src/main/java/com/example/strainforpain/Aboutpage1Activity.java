package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.signupModels.SignUpResponse;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;

public class Aboutpage1Activity extends AppCompatActivity {


    private Button next_btn;
    private boolean check = false;
    private Switch switch_btn ;

    private IndicatorSeekBar yearSeekBar , weightSeekbar , heightSeekbar ;

    private String id , year_born , weight , height , gender ;
    private TextView et_year_born , et_weight , et_height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutpage2);


        next_btn = findViewById(R.id.next_to_home);

        yearSeekBar = findViewById(R.id.yearBorn_seekBar);
        weightSeekbar = findViewById(R.id.weight_seekBar);
        heightSeekbar = findViewById(R.id.height_seekBar);

        et_year_born = findViewById(R.id.yearBorn);
        et_weight = findViewById(R.id.weight);
        et_height = findViewById(R.id.height);


        yearSeekBar.setMax(Calendar.getInstance().get(Calendar.YEAR));
        yearSeekBar.setMin(1950);
        yearSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                Log.i("seekbar", String.valueOf(seekParams.seekBar));
                Log.i("prgress", String.valueOf(seekParams.progress));

                et_year_born.setText(String.valueOf(seekParams.progress));
                Log.i("frogresFloat", String.valueOf(seekParams.progressFloat));
                Log.i("fromuser", String.valueOf(seekParams.fromUser));
                //when tick count > 0
                Log.i("thumpostion", String.valueOf(seekParams.thumbPosition));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });


        weightSeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                Log.i("seekbar", String.valueOf(seekParams.seekBar));
                Log.i("prgress", String.valueOf(seekParams.progress));

                et_weight.setText(String.valueOf(seekParams.progress));
                Log.i("frogresFloat", String.valueOf(seekParams.progressFloat));
                Log.i("fromuser", String.valueOf(seekParams.fromUser));
                //when tick count > 0
                Log.i("thumpostion", String.valueOf(seekParams.thumbPosition));

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });


        heightSeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                Log.i("seekbar", String.valueOf(seekParams.seekBar));
                Log.i("prgress", String.valueOf(seekParams.progress));

                et_height.setText(String.valueOf(seekParams.progress));
                Log.i("frogresFloat", String.valueOf(seekParams.progressFloat));
                Log.i("fromuser", String.valueOf(seekParams.fromUser));
                //when tick count > 0
                Log.i("thumpostion", String.valueOf(seekParams.thumbPosition));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){
                    registerUser();

                }
            }
        });

    }


    private void registerUser(){
        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<SignUpResponse> call = apiInterface.registration(id, year_born, weight, height , gender);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()){

                    Toast.makeText(Aboutpage1Activity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Aboutpage1Activity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Aboutpage1Activity.this , Aboutpage1Activity.class));

                }else{

                    //  Toast.makeText(RegisterationActivity.this, String.valueOf(jsonArray.get(0)), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Aboutpage1Activity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(Aboutpage1Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private boolean validate() {
        check = true;

        year_born = et_year_born.getText().toString();
        weight = et_weight.getText().toString().trim();
        height = et_height.getText().toString().trim();


        if (year_born.isEmpty()) {
            check = false;
            et_year_born.setError("some parameter is missing");
        } else {
            check = true;
        }

        if (weight.isEmpty()) {
            check = false;
            et_weight.setError("some parameter is missing");
        } else {
            check = true;
        }

        if (height.isEmpty()) {
            check = false;
            et_height.setError("some parameter is missing");
        } else {
            check = true;
        }

        return check;

    }
}
