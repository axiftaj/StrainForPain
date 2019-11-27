package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.strainforpain.Adapters.DiseaseModelAdapter;
import com.example.strainforpain.Adapters.Model.diseaseModels.Datum;
import com.example.strainforpain.Adapters.Model.diseaseModels.DiseaseResponse;
import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DiseaseActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private DiseaseModelAdapter diseaseModelAdapter ;
    private List<Datum> diseaseModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);


        recyclerView = findViewById(R.id.recyclerView);


        diseaseModelAdapter = new DiseaseModelAdapter(diseaseModels,this );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(diseaseModelAdapter);

        ApiCall();

    }


    private void ApiCall(){

        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<DiseaseResponse> call = apiInterface.getDiseases();
        call.enqueue(new Callback<DiseaseResponse>() {
            @Override
            public void onResponse(Call<DiseaseResponse> call, retrofit2.Response<DiseaseResponse> response) {
                Log.d("zma response", response.message());
                if (response.isSuccessful()){
                    diseaseModels.addAll(response.body().getData());
                    diseaseModelAdapter.notifyDataSetChanged();

                }else{

                    Toast.makeText(DiseaseActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DiseaseResponse> call, Throwable t) {
                Toast.makeText(DiseaseActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
