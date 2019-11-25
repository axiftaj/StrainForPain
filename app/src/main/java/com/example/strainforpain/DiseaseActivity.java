package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.strainforpain.Adapters.DiseaseModelAdapter;
import com.example.strainforpain.Adapters.HomeAdapter;
import com.example.strainforpain.Adapters.Model.HomeModel;
import com.example.strainforpain.models.DiseaseModel.DiseaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private DiseaseModelAdapter diseaseModelAdapter ;
    private List<DiseaseModel> diseaseModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);


        recyclerView = findViewById(R.id.recyclerView);


        diseaseModelAdapter = new DiseaseModelAdapter(this, diseaseModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(diseaseModelAdapter);

        ApiCall();

    }


    private void ApiCall(){

        final String url = "http://dahawwalur.org/staging/StrainsForPains/public/api/diseases";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                , new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Toast.makeText(DiseaseActivity.this, "response"+response, Toast.LENGTH_SHORT).show();
                try {

                    JSONObject jsonObject = new JSONObject(response);

                        JSONArray jsonArray=jsonObject.getJSONArray("data");

                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject json_data = jsonArray.getJSONObject(i);
                            String name = json_data.getString("disease_name");

                            DiseaseModel model = new DiseaseModel();
                            model.setName(name);
                            diseaseModels.add(model);
                            diseaseModelAdapter.notifyDataSetChanged();


                        }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DiseaseActivity.this,"error"+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );


        RequestQueue mRequestQueue = Volley.newRequestQueue(DiseaseActivity.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);

    }
}
