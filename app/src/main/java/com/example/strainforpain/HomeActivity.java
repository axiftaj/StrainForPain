package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strainforpain.Adapters.HomeAdapter;
import com.example.strainforpain.Adapters.Model.DiseaseHomeResponse.Datum1;
import com.example.strainforpain.Adapters.Model.DiseaseHomeResponse.DiseaseHomeResponse;
import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.Utills.GeneralUtills;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private HomeAdapter homeAdapter ;
    private List<Datum1> homeModelsList = new ArrayList<>();

    private TextView diseaseName;
    private Button drop_down_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        diseaseName = findViewById(R.id.diseaseName);
        recyclerView = findViewById(R.id.recyclerView);
        drop_down_btn = findViewById(R.id.drop_down_btn);



        drop_down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , DiseaseActivity.class));
            }
        });



        Bundle bundle = getIntent().getExtras();

        if (bundle!=null) {
            String name = bundle.getString("name");
            diseaseName.setText(name);

        }
       // Log.d("diseaseName" , name);
        //Toast.makeText(this, "id : "+ id, Toast.LENGTH_SHORT).show();


        homeAdapter = new HomeAdapter(homeModelsList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(homeAdapter);

                SharedPreferences sp=getSharedPreferences("Login", Context.MODE_PRIVATE);

                ApicCall(GeneralUtills.getSharedPreferences(HomeActivity.this).getString("id" , ""));

    }


    public void ApicCall(String i){

        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<DiseaseHomeResponse> call = apiInterface.getHomeDiseases(i);
        call.enqueue(new Callback<DiseaseHomeResponse>() {
            @Override
            public void onResponse(Call<DiseaseHomeResponse> call, retrofit2.Response<DiseaseHomeResponse> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()) {

                    homeModelsList.addAll(response.body().getData());
                    homeAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(HomeActivity.this, "false" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DiseaseHomeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity();
    }
}

