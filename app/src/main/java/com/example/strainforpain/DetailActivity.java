package com.example.strainforpain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.strainforpain.Network.ApiClientPrivate;
import com.example.strainforpain.Network.ApiInterface;
import com.example.strainforpain.models.DetailsModel.Datum2;
import com.example.strainforpain.models.DetailsModel.DetailResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {

    private ImageView back_arrow ;
    private TextView titleName ;
    private List<Datum2> datum2Lis = new ArrayList<>();
    private ImageView imageView ;

    public TextView title , gradient1 , gradient2 , gradient3 , gradient4 , gradient5 , prtency , description , origin , type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        back_arrow = findViewById(R.id.back_arrow);
        titleName = findViewById(R.id.name);

        title = findViewById(R.id.title);
        gradient1 = findViewById(R.id.CDB);
        gradient2 = findViewById(R.id.THC);
        gradient3 = findViewById(R.id.CBN);
        gradient4 = findViewById(R.id.THCV);
        gradient5 = findViewById(R.id.CBC);
        imageView = findViewById(R.id.image);

        prtency = findViewById(R.id.et_prtency);
        origin = findViewById(R.id.et_origin);
        type = findViewById(R.id.et_type);

        description = findViewById(R.id.description);


        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString("titile");
        String id = bundle.getString("id");

     //   Toast.makeText(this, "zama id id : " + id, Toast.LENGTH_SHORT).show();

        titleName.setText(title);


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, HomeActivity.class));
            }
        });


        ApiCal(id);


    }

    private void ApiCal(String id) {
        ApiInterface apiInterface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);
        }
        Call<DetailResponseModel> call = apiInterface.getHomeDiseasesDetails(id);
        call.enqueue(new Callback<DetailResponseModel>() {
            @Override
            public void onResponse(Call<DetailResponseModel> call, retrofit2.Response<DetailResponseModel> response) {

                if (response.isSuccessful()) {


                    String str_title = String.valueOf(response.body().getData().get(0).getTitle());
                    String str_image = String.valueOf(response.body().getData().get(0).getImage());
                    String str_gradient1 = String.valueOf(response.body().getData().get(0).getTHC());
                    String str_gradient2 = String.valueOf(response.body().getData().get(0).getCBD());
                    String str_gradient3 = String.valueOf(response.body().getData().get(0).getCBN());
                    String str_gradient4 = String.valueOf(response.body().getData().get(0).getTHCV());
                    String str_gradient5 = String.valueOf(response.body().getData().get(0).getCBG());
                    String str_Type = String.valueOf(response.body().getData().get(0).getType());
                    String str_origin = String.valueOf(response.body().getData().get(0).getOrigins());
                    String str_Potency = String.valueOf(response.body().getData().get(0).getPotency());
                    String str_description = String.valueOf(response.body().getData().get(0).getDescription());



                    title.setText(str_title);
                    Glide.with(DetailActivity.this).load(str_image).into(imageView);
                    gradient1.setText(str_gradient1);
                    gradient2.setText(str_gradient2);
                    gradient3.setText(str_gradient3);
                    gradient4.setText(str_gradient4);
                    gradient5.setText(str_gradient5);
                    type.setText(str_Type);
                    origin.setText(str_origin);
                    prtency.setText(str_Potency);
                      description.setText(str_description);



                } else {
                Toast.makeText(DetailActivity.this, "false" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DetailResponseModel> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
