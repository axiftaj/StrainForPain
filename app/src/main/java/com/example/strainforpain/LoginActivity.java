package com.example.strainforpain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.strainforpain.Utills.GeneralUtills;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ImageView back ;
    private Button login ;
    private EditText email_id_option , password ;
    private TextView LetsStart , forgotPassword ;
    String id ;

    private static final String PREFER_NAME = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back = findViewById(R.id.back_btn);
        login = findViewById(R.id.logoid_btn);
        email_id_option = findViewById(R.id.email_id_option);
        password = findViewById(R.id.password);
        LetsStart = findViewById(R.id.LetsStart);
        forgotPassword = findViewById(R.id.forgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , ForgotPasswordActivity.class));
            }
        });

        LetsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterationActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , OptionsActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiCall();

            }
        });

    }

    private void ApiCall() {

                final ProgressDialog progressDialog  = new ProgressDialog(LoginActivity.this);

                progressDialog.setTitle("Loading...");
                progressDialog.setMessage("Wait");
                progressDialog.show();

                final String url = "http://www.dahawwalur.org/staging/StrainsForPains/public/api/login";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url
                        , new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message = jsonObject.getString("msg");
                            if(message.contains("Successfull")) {


                                Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
//                                Bundle bundle = new Bundle();
//                                bundle.putString("id" ,  jsonObject.getString("disease_id"));
//                                intent.putExtras(bundle);

                                GeneralUtills.putStringValueInEditor(LoginActivity.this , "id" , jsonObject.getString("disease_id"));
                                GeneralUtills.putStringValueInEditor(LoginActivity.this , "name" , jsonObject.getString("disease_name"));


                                progressDialog.dismiss();

                                GeneralUtills.putBooleanValueInEditor(LoginActivity.this , "isLogin" , true);

                                startActivity(intent);

                                finishAffinity();



                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Your Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"error"+ error.toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params = new HashMap<>();

                        params.put("email",email_id_option.getText().toString().trim());
                        params.put("password",password.getText().toString().trim());



                        return params;
                    }

                };

                RequestQueue mRequestQueue = Volley.newRequestQueue(LoginActivity.this);
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                mRequestQueue.add(stringRequest);

    }


}
