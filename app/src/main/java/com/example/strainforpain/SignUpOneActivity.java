package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class SignUpOneActivity extends AppCompatActivity {


    private ImageView back_arrow ;
    private Button next_btn;
    private boolean check = false;
    private ImageView boy , woman ;



    private IndicatorSeekBar yearSeekBar , weightSeekbar , heightSeekbar ;

    private String id , year_born , weight , height , gender ;
    private TextView et_year_born , et_weight , et_height , et_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signoone);


        next_btn = findViewById(R.id.next_to_home);
        back_arrow = findViewById(R.id.back_arrow);


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpOneActivity.this , RegisterationActivity.class));
            }
        });

        yearSeekBar = findViewById(R.id.yearBorn_seekBar);
        weightSeekbar = findViewById(R.id.weight_seekBar);
        heightSeekbar = findViewById(R.id.height_seekBar);



        et_year_born = findViewById(R.id.yearBorn);
        et_weight = findViewById(R.id.weight);
        et_height = findViewById(R.id.height);
        boy = findViewById(R.id.boy);
        woman = findViewById(R.id.women);



        yearSeekBar.setMax(Calendar.getInstance().get(Calendar.YEAR));
        yearSeekBar.setMin(1950);



        boy.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                boy.setBackgroundColor(R.color.colorPrimary);
                String et_boy = "Boy";
                Toast.makeText(SignUpOneActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

        woman.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                woman.setBackgroundColor(R.color.colorPrimaryDark);
                String et_girl = "Women";
                Toast.makeText(SignUpOneActivity.this, "Click", Toast.LENGTH_SHORT).show();

            }
        });

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

        year_born = et_year_born.getText().toString();
        weight = et_weight.getText().toString().trim();
        height = et_height.getText().toString().trim();


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(SignUpOneActivity.this , SignUpTwoActivity.class));

                  //  registerUser(id , year_born , weight , height , "boy");

            }
        });

    }


    private void registerUser(String id, String year_born, String weight, String height, String gender){
        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<SignUpResponse> call = apiInterface.registration(this.id, this.year_born, this.weight, this.height, this.gender);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()){

                    Toast.makeText(SignUpOneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpOneActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpOneActivity.this , SignUpOneActivity.class));

                }else{

                    //  Toast.makeText(RegisterationActivity.this, String.valueOf(jsonArray.get(0)), Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpOneActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpOneActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



    private boolean validate() {
        check = true;


        boy.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                boy.setBackgroundColor(R.color.colorPrimary);
                String et_boy = "Boy";
            }
        });

        woman.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                woman.setBackgroundColor(R.color.colorPrimaryDark);
                String et_girl = "Women";

            }
        });
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
