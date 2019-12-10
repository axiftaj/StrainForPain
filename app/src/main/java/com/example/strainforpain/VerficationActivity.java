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
import android.widget.Toast;

import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.Utills.GeneralUtills;
import com.example.strainforpain.models.ForgotPassword.ForgotResponse;
import com.example.strainforpain.models.ForgotPassword.VerificationCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class VerficationActivity extends AppCompatActivity {

    Button verify_next ;
    EditText nub1 , nub2 , nub3 , nub4 ;
    private ImageView back_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);

        nub1 = findViewById(R.id.num1);
        nub2 = findViewById(R.id.num2);
        nub3 = findViewById(R.id.num3);
        nub4 = findViewById(R.id.num4);



        final String email = GeneralUtills.getSharedPreferences(VerficationActivity.this ).getString("veremail" , "");

        verify_next = findViewById(R.id.verify_next);


        verify_next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final String code = nub1.getText().toString()+nub2.getText().toString()+nub3.getText().toString()+nub4.getText().toString();
                Verification(code ,email );
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Verification(String code, String email){

        final ProgressDialog progressDialog  = new ProgressDialog(VerficationActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        Call<VerificationCodeResponse> call = apiInterface.verficationCode(code , email);
        call.enqueue(new Callback<VerificationCodeResponse>() {
            @Override
            public void onResponse(Call<VerificationCodeResponse> call, retrofit2.Response<VerificationCodeResponse> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(VerficationActivity.this, SignUpTwoActivity.class);

                    Bundle bundle = new Bundle();
                    startActivity(new Intent(VerficationActivity.this, PasswordResetActivity.class).putExtras(bundle));
                    progressDialog.dismiss();

                }else{
                    Toast.makeText(VerficationActivity.this, "error:"+response.message(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VerificationCodeResponse> call, Throwable t) {
                Toast.makeText(VerficationActivity.this,"error :"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }
}
