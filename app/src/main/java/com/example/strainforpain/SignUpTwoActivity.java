package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.signupModels.ResponseDataStep2;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpTwoActivity extends AppCompatActivity {


    private ImageView back_arrow;
    private Button next_btn;
    private boolean check = false;
    private ImageView boy, woman;
    LinearLayout male,female;
    String id;


    private IndicatorSeekBar yearSeekBar, weightSeekbar, heightSeekbar;

    private String ids, year_born, weight, height, gender;
    private TextView et_year_born, et_weight, et_height, et_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signoone);


        next_btn = findViewById(R.id.next_to_home);
        back_arrow = findViewById(R.id.back_arrow);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);


        Bundle intent = getIntent().getExtras();

        id = String.valueOf(intent.getInt("ids"));


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpTwoActivity.this, RegisterationActivity.class));
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


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";

                female.setBackgroundColor(getResources().getColor(R.color.gray));
                male.setBackground(getResources().getDrawable(R.drawable.round));
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
                male.setBackgroundColor(getResources().getColor(R.color.gray));
                female.setBackground(getResources().getDrawable(R.drawable.round));
            }
        });


        yearSeekBar.setMax(Calendar.getInstance().get(Calendar.YEAR));
        yearSeekBar.setMin(1950);


        yearSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                year_born = String.valueOf(seekParams.progress);
                et_year_born.setText(year_born);
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

                weight = String.valueOf(seekParams.progress);
                et_weight.setText(weight);

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
                height = String.valueOf(seekParams.progress);
                et_height.setText(height);

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

                registerUserStepTwo(id, year_born, weight, height, gender);

            }
        });

    }

    private void registerUserStepTwo(String id, String year_born, String weight, String height, String gender) {

        final ProgressDialog progressDialog  = new ProgressDialog(SignUpTwoActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();


        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<ResponseDataStep2> call = apiInterface.registrationStep2(id, year_born, weight, height, gender);
        call.enqueue(new Callback<ResponseDataStep2>() {
            @Override
            public void onResponse(Call<ResponseDataStep2> call, retrofit2.Response<ResponseDataStep2> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()) {

                    Intent intent = new Intent(SignUpTwoActivity.this, SignUpThreeActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("ids",response.body().getData().getId());
                    startActivity(new Intent(SignUpTwoActivity.this, SignUpThreeActivity.class).putExtras(bundle));

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();

                }
            }
            @Override
            public void onFailure(Call<ResponseDataStep2> call, Throwable t) {
                Toast.makeText(SignUpTwoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        });
    }
}



