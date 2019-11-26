package com.example.strainforpain.Network;

import com.example.strainforpain.Adapters.Model.diseaseModels.DiseaseResponse;
import com.example.strainforpain.models.signupModels.ResponseDataStep2;
import com.example.strainforpain.models.signupModels.ResponseDataStep3;
import com.example.strainforpain.models.signupModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("signUpfirst")
    Call<SignUpResponse> registration(@Field("fullname") String fullName,
                                      @Field("email") String name,
                                      @Field("password") String email,
                                      @Field("retype_password") String password);

    @FormUrlEncoded
    @POST("signUpsecond")
    Call<ResponseDataStep2> registrationStep2(@Field("id") String id,
                                         @Field("year_born") String yearBorn,
                                         @Field("weight") String weight,
                                         @Field("height") String height,
                                         @Field("gender") String gender);

    @FormUrlEncoded
    @POST("signUpthird")
    Call<ResponseDataStep3> registrationStep3(@Field("id") String id,
                                              @Field("description") String description,
                                              @Field("location") String location);

    @GET("diseases")
    Call<DiseaseResponse> getDiseases();

//    @GET("diseases?")
//    Call<DiseaseHomeResponse> getHomeDiseases(@Query("id") int id);



}
