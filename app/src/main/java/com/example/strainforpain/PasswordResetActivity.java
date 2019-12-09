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
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.ForgotPassword.ForgotResponse;
import com.example.strainforpain.models.ForgotPassword.PasswordReset;
import com.example.strainforpain.models.ForgotPassword.VerificationCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class PasswordResetActivity extends AppCompatActivity {

    private Button next ;
    private EditText newPassword , confirmPassword ;
    String password , confPassword ;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        newPassword = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confrim_password);

        next = findViewById(R.id.logoid_btn);


        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (validate()){
                    ForgetPassword();
                }
            }
        });



    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ForgetPassword(){

        final ProgressDialog progressDialog  = new ProgressDialog(PasswordResetActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        Call<PasswordReset> call = apiInterface.passwordReset(password);
        call.enqueue(new Callback<PasswordReset>() {
            @Override
            public void onResponse(Call<PasswordReset> call, retrofit2.Response<PasswordReset> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(PasswordResetActivity.this, LoginActivity.class);
//ok
                    Bundle bundle = new Bundle();
                    startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class).putExtras(bundle));
                    progressDialog.dismiss();

                }else{
                    Toast.makeText(PasswordResetActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PasswordReset> call, Throwable t) {
                Toast.makeText(PasswordResetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

    private boolean validate() {
        check = true;

        password = newPassword.getText().toString();
        confPassword = confirmPassword.getText().toString();

        if (password.isEmpty()) {
            check = false;
            newPassword.setError("some parameter is missing");
        }else if(confPassword.isEmpty()){
            check = false;
            confirmPassword.setError("some parameter is missing");

        }

        return check;
    }
}
