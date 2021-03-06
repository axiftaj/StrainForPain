package com.example.strainforpain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.signupModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterationActivity extends AppCompatActivity {

    private ImageView reg_back_btn;
    private Button next_btn;
    private boolean check = false;
    private TextView login ;

    private String email, password, confirmPassword, fullname;

    private EditText et_fullName, et_email, et_password, et_retypePassord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);


        reg_back_btn = findViewById(R.id.reg_back_btn);
        next_btn = findViewById(R.id.next_btn);

        et_fullName = findViewById(R.id.fillName);
        et_email = findViewById(R.id.email_reg_btn);
        et_password = findViewById(R.id.password_reg_btn);
        et_retypePassord = findViewById(R.id.confrimr_password_reg_btn);
        login = findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterationActivity.this , LoginActivity.class));
            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (validate()) {
                    registerUser();
                }

            }
        });

        reg_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterationActivity.this, OptionsActivity.class));
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void registerUser(){

        final ProgressDialog progressDialog  = new ProgressDialog(RegisterationActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();



        ApiInterface apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        Call<SignUpResponse> call = apiInterface.registration(fullname, email, password, confirmPassword);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response) {
                if (response.isSuccessful()){

                  Intent intent = new Intent(RegisterationActivity.this, SignUpTwoActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("ids", Math.toIntExact(response.body().getData().getId()));
                    startActivity(new Intent(RegisterationActivity.this, SignUpTwoActivity.class).putExtras(bundle));

                    progressDialog.dismiss();

                }else{
                    Toast.makeText(RegisterationActivity.this, "Email All ready Taken", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }


    private boolean validate() {
        check = true;

        email = et_email.getText().toString();
        fullname = et_fullName.getText().toString();
        password = et_password.getText().toString().trim();
        confirmPassword = et_password.getText().toString().trim();


        if (email.isEmpty()) {
            check = false;
            et_email.setError("some parameter is missing");
        } else {
            check = true;
        }

        if (password.isEmpty()) {
            check = false;
            et_password.setError("some parameter is missing");
        } else {
            check = true;
        }

        if (confirmPassword.isEmpty()) {
            check = false;
            et_retypePassord.setError("some parameter is missing");
        } else {
            check = true;
        }

        if (fullname.isEmpty()) {
            check = false;
            et_fullName.setError("some parameter is missing");
        } else {
            check = true;
        }


        return check;
    }
}

