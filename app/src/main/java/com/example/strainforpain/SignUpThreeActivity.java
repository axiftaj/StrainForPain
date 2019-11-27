package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.signupModels.ResponseDataStep2;
import com.example.strainforpain.models.signupModels.ResponseDataStep3;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpThreeActivity extends AppCompatActivity {

    private ImageView back_arrow;

    private Button next_to_home;
    private IndicatorSeekBar seekBar;
    String id ;
    String  option1 , option2 , option3 , option4 , choose_location;

    private TextView options;
    private Spinner spinner;
    private static final String[] paths = {"Choose Your Location", "Toronto", "Ottawa" , "Calgary" , "Victoria" , "Quebec City"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuptwo);

        back_arrow = findViewById(R.id.back_arrow);
        next_to_home = findViewById(R.id.next_to_home);
        seekBar = findViewById(R.id.seekBar);

        options = findViewById(R.id.options);




        Bundle intent = getIntent().getExtras();

        id = String.valueOf(intent.getInt("ids"));


        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                if (seekParams.progress <= 25){

                    option1 = "Rarely";
                    options.setText(option1);
                }
                else if(seekParams.progress >25 && seekParams.progress <=50){

                    option2 = "Offenly";
                    options.setText(option2);

                }
                else if(seekParams.progress >50 && seekParams.progress <=70){

                    option3 = "Mostly";
                    options.setText(option3);
                }
                else if(seekParams.progress >75 && seekParams.progress <=100){

                    option4 = "Mostly offenly";
                    options.setText(option4);

                }

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpThreeActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, final long id) {

                 choose_location = spinner.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        next_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCall(id , String.valueOf(options), choose_location );

            }
        });
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpThreeActivity.this , SignUpTwoActivity.class));
            }
        });



    }



    public void ApiCall(String id, String options, String countruy){

        final ProgressDialog progressDialog  = new ProgressDialog(SignUpThreeActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();

        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<ResponseDataStep3> call = apiInterface.registrationStep3(id, options , countruy);
        call.enqueue(new Callback<ResponseDataStep3>() {
            @Override
            public void onResponse(Call<ResponseDataStep3> call, retrofit2.Response<ResponseDataStep3> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()) {


                    startActivity(new Intent(SignUpThreeActivity.this , DiseaseActivity.class));
                    Toast.makeText(SignUpThreeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.show();


                } else {
                    Toast.makeText(SignUpThreeActivity.this, "false" + response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<ResponseDataStep3> call, Throwable t) {
                Toast.makeText(SignUpThreeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();                    progressDialog.dismiss();
                progressDialog.dismiss();


            }
        });
    }


}

