package com.app.android_retrofit_tutorials.app_network_call;

import com.app.android_retrofit_tutorials.app_model.Response_getUsers;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Manish Ahire on 22,January,2021,
 * manishahire.ahire221@gmail.com.
 */

public interface NetworkServiceApi {

    @GET("users")
    Call<Response_getUsers> getUsers();

}
