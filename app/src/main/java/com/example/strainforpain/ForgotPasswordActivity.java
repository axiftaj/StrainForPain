package com.example.strainforpain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.Utills.GeneralUtills;
import com.example.strainforpain.models.ForgotPassword.ForgotResponse;
import com.example.strainforpain.models.signupModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button forgot_next ;
    EditText et_email ;
    ImageView back_btn ;
    String email ;

    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot_next = findViewById(R.id.forgot_next);
        back_btn = findViewById(R.id.back_btn) ;
        et_email = findViewById(R.id.email_id_option);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            }
        });

        forgot_next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (validate()){
                    ForgotApiCall();
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ForgotApiCall(){

        final ProgressDialog progressDialog  = new ProgressDialog(ForgotPasswordActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        Call<ForgotResponse> call = apiInterface.forgotPassword(email);
        call.enqueue(new Callback<ForgotResponse>() {
            @Override
            public void onResponse(Call<ForgotResponse> call, retrofit2.Response<ForgotResponse> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(ForgotPasswordActivity.this, SignUpTwoActivity.class);

                    Bundle bundle = new Bundle();
                    GeneralUtills.putStringValueInEditor(ForgotPasswordActivity.this , "veremail" ,email);

                    startActivity(new Intent(ForgotPasswordActivity.this, VerficationActivity.class).putExtras(bundle));
                    progressDialog.dismiss();

                }else{
                    Toast.makeText(ForgotPasswordActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ForgotResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

    private boolean validate() {
        check = true;

        email = et_email.getText().toString();


        if (email.isEmpty()) {
            check = false;
            et_email.setError("some parameter is missing");
        }

        return check;
    }
}
