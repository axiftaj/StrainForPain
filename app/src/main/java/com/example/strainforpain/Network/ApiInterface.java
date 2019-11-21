package com.example.strainforpain.Network;

import com.example.strainforpain.Adapters.Model.RegisterationResponseModelStepOne;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("post")
    Call<RegisterationResponseModelStepOne> registration(@Part("email") RequestBody name,
                                                         @Part("password") RequestBody email,
                                                         @Part("retype_password") RequestBody password);

}
