package com.app.android_retrofit_tutorials.app_network_call;

import com.app.android_retrofit_tutorials.app_model.Resp_getUsersWithID;
import com.app.android_retrofit_tutorials.app_model.Resp_postUserJob;
import com.app.android_retrofit_tutorials.app_model.Response_getUsers;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Manish Ahire on 22,January,2021,
 * manishahire.ahire221@gmail.com.
 */

public interface NetworkServiceApi {

    @GET("users")
    Call<Response_getUsers> getUsers();


    @GET("users/{id}")
    Call<Resp_getUsersWithID> getUsersWithID(@Path("id") int id);

    @POST("users")
    Call<Resp_postUserJob> postUserJob(@Body JsonObject jsonBody);


}
