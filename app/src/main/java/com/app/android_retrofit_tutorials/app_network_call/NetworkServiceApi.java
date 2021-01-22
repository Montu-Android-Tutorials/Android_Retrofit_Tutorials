package com.app.android_retrofit_tutorials.app_network_call;

import com.app.smsipltest.model.Resp_testAPI;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Manish Ahire
 */

public interface NetworkServiceApi {

    @POST("Gasco/seat-map.php")
    @FormUrlEncoded
//    Call<Resp_testAPI> testAPI(@Query("key") String key, @Field("auth_key") String auth_key, @Field("password") String password);

    Call<JsonObject> testAPI(@Query("key") String key, @Field("auth_key") String auth_key, @Field("password") String password);


}
