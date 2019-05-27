package com.nikvay.patientapplication.apicallcommon;


import com.nikvay.patientapplication.model.SuccessModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(EndApi.LOGIN)
    @FormUrlEncoded
    Call<SuccessModel> loginCall(@Field("email") String email,
                                 @Field("password") String password,
                                 @Field("device_token") String device_token);


    @POST(EndApi.SERVICE_LIST)
    @FormUrlEncoded
    Call<SuccessModel> serviceList(@Field("doctor_id") String doctor_id);
}
