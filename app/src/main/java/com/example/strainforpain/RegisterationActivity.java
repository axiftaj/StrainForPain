package com.example.strainforpain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.signupModels.SignUpResponse;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterationActivity extends AppCompatActivity {

    private ImageView reg_back_btn;
    private Button next_btn;
    private boolean check = false;

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


        next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (validate()) {
//                    RegApiCall();
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

    private void registerUser(){
        ApiInterface apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        Call<SignUpResponse> call = apiInterface.registration(fullname, email, password, confirmPassword);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()){

                    Toast.makeText(RegisterationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterationActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterationActivity.this , Aboutpage2Activity.class));

                }else{

                    try {
                        JSONArray jsonArray = new JSONArray("errors");
                        Toast.makeText(RegisterationActivity.this, String.valueOf(jsonArray.get(0)), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(RegisterationActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void RegApiCall() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.signup
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterationActivity.this, response, Toast.LENGTH_SHORT).show();
                if (response.contains("success")) {
                    try {

                        JSONArray jsonArray = new JSONArray(response);

                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        JSONObject object = jsonObject.getJSONObject("data");

                        String message = jsonObject.getString("msg");

                        Toast.makeText(RegisterationActivity.this, message, Toast.LENGTH_SHORT).show();

                        String id = object.getString("id");

                        Log.d("zamaid", id);
                        if (message.contains("SignUp Successfully")) {

                            startActivity(new Intent(RegisterationActivity.this, Aboutpage2Activity.class));
                            Toast.makeText(RegisterationActivity.this, "succes" + response, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterationActivity.this, "Incorrect" + response, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(RegisterationActivity.this, "you have got some error"+response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("fullname", fullname);
                params.put("email", email);
                params.put("password", password);
                params.put("retype_password", confirmPassword);

                return params;
            }

        };

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);

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

