package com.example.strainforpain.Network;

import com.example.strainforpain.Adapters.Model.RegisterationResponseModelStepOne;
import com.example.strainforpain.models.signupModels.SignUpResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("signUp_StepOne")
    Call<SignUpResponse> registration(@Field("fullname") String fullName,
                                      @Field("email") String name,
                                      @Field("password") String email,
                                      @Field("retype_password") String password);

}
