package com.app.android_retrofit_tutorials.app_network_call;

import com.app.android_retrofit_tutorials.app_model.Resp_get_All_Notification_for_EveryOne;
import com.app.android_retrofit_tutorials.app_model.Response_getUsers;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Manish Ahire on 22,January,2021,
 * manishahire.ahire221@gmail.com.
 */

public interface NetworkServiceApi {

    @GET("users")
    Call<Response_getUsers> getUsers();

}
